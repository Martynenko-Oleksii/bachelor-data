package ua.nure.liapota.services;

import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.GlRpMapping;
import ua.nure.liapota.repositories.data.GlRpMappingRepository;

@Service
public class GlRpMappingService extends EntityService<GlRpMapping, Integer, GlRpMappingRepository>{
}
