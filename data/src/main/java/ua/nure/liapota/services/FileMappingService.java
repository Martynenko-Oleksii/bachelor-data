package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.file.FileMapping;
import ua.nure.liapota.repositories.file.FileMappingRepository;

import java.util.List;

@Service
public class FileMappingService extends EntityService<FileMapping, Integer, FileMappingRepository> {
    @Autowired
    public FileMappingService(FileMappingRepository repository) {
        this.repository = repository;
    }

    public List<FileMapping> getByCreatedUnder(Integer id) {
        return repository.getFileMappingsByCreatedUnder(id);
    }

    public void update(FileMapping updatedFileMapping) {
        FileMapping savedFileMapping = getById(updatedFileMapping.getId());
        savedFileMapping.setDescription(updatedFileMapping.getDescription());
        savedFileMapping.setFileType(updatedFileMapping.getFileType());
        savedFileMapping.setMappingJson(updatedFileMapping.getMappingJson());
        savedFileMapping.setName(updatedFileMapping.getName());
        savedFileMapping.setCreatedBy(updatedFileMapping.getCreatedBy());
        savedFileMapping.setCreatedUnder(updatedFileMapping.getCreatedUnder());
        repository.save(savedFileMapping);
    }
}
