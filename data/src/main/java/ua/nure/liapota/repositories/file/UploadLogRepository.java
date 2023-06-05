package ua.nure.liapota.repositories.file;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.file.UploadLog;

public interface UploadLogRepository extends CrudRepository<UploadLog, Integer> {
}
