package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.GlRpMapping;
import ua.nure.liapota.repositories.data.GlRpMappingRepository;

import java.util.List;

@Service
public class GlRpMappingService extends EntityService<GlRpMapping, Integer, GlRpMappingRepository>{
    @Autowired
    public GlRpMappingService(GlRpMappingRepository repository) {
        this.repository = repository;
    }

    public List<GlRpMapping> getByValueType(Integer valueTypeId, boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingByValueTypeMapped(valueTypeId);
        } else {
            result = repository.getGlRpMappingByValueTypeUnmapped(valueTypeId);
        }

        return result;
    }

    public List<GlRpMapping> getByAccountType(Integer valueTypeId, String accountCode, boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingByValueTypeAndAccountMapped(valueTypeId, accountCode);
        } else {
            result = repository.getGlRpMappingByValueTypeAndAccountUnmapped(valueTypeId, accountCode);
        }

        return result;
    }
}
