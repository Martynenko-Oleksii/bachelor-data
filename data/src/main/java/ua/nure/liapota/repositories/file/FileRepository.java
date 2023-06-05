package ua.nure.liapota.repositories.file;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.file.FileEntity;

public interface FileRepository extends CrudRepository<FileEntity, Integer> {
}
