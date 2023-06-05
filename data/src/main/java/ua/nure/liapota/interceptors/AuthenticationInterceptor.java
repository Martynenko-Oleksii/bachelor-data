package ua.nure.liapota.interceptors;

import com.auth0.jwk.GuavaCachedJwkProvider;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.liapota.annotations.Authorize;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("${ids4.jwks.url}")
    private String jwksUrl;

    @Value("${ids4.issuer}")
    private String issuer;

    @Override
    public boolean preHandle(HttpServletRequest  request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        Class<?> controllerClass = method.getDeclaringClass();

        if (controllerClass.isAnnotationPresent(RestController.class)
            && controllerClass.isAnnotationPresent(Authorize.class)) {

            Authorize authorize = controllerClass.getAnnotation(Authorize.class);
            String[] roleParams = authorize.value().split(",");

            String originToken = request.getHeader("Authorization");
            if (originToken == null || originToken.isEmpty()){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            try {
                String token = getToken(originToken);
                DecodedJWT jwt = JWT.decode(token);
                JwkProvider http = new UrlJwkProvider(new URL(jwksUrl));
                JwkProvider provider = new GuavaCachedJwkProvider(http);
                Jwk jwk = provider.get(jwt.getKeyId());

                Claim roleClaim = jwt.getClaim("role");
                String[] roles = roleClaim.asArray(String.class);

                for (String roleParam : roleParams) {
                    if (Arrays.stream(roles).noneMatch(roleParam::equals)) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return false;
                    }
                }

                request.setAttribute("userId", jwt.getSubject());
                request.setAttribute("customerId", jwt.getClaim("customerId").asInt());

                Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(),null);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(issuer)
                        .withArrayClaim("scope","openid", "profile", "roles", "customer", "additional", "security", "offline_access")
                        .build();
                verifier.verify(token);
            } catch (JWTVerificationException exception){
                PrintWriter writer = response.getWriter();
                writer.write(exception.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private String getToken(String originToken) {
        String[] arr = originToken.split(" ");
        return arr[1];
    }
}
