package ua.nure.liapota.models.file;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "file_types")
public class FileType {
    @Id
    @Column(name = "file_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "fileType")
    private Set<FileMapping> fileMappings;

    @JsonIgnore
    @OneToMany(mappedBy = "fileType")
    private Set<UploadLog> uploadLogs;

    public Set<FileMapping> getFileMappings() {
        return fileMappings;
    }

    public void setFileMappings(Set<FileMapping> fileMappings) {
        this.fileMappings = fileMappings;
    }

    public Set<UploadLog> getUploadLogs() {
        return uploadLogs;
    }

    public void setUploadLogs(Set<UploadLog> uploadLogs) {
        this.uploadLogs = uploadLogs;
    }
}
