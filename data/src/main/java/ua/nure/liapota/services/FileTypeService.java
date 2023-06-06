package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.file.FileType;
import ua.nure.liapota.repositories.file.FileTypeRepository;

@Service
public class FileTypeService extends EntityService<FileType, Integer, FileTypeRepository> {
    @Autowired
    public FileTypeService(FileTypeRepository repository) {
        this.repository = repository;
    }
}
