package ua.nure.liapota.repositories.file;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.file.FileMapping;

import java.util.List;

public interface FileMappingRepository extends CrudRepository<FileMapping, Integer> {
    @Query(value = "SELECT * FROM file_mappings WHERE created_under = ?1", nativeQuery = true)
    List<FileMapping> getFileMappingsByCreatedUnder(Integer id);
}
