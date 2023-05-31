package ua.nure.liapota.models.file;

import com.fasterxml.jackson.annotation.JsonRawValue;

import javax.persistence.*;

@Entity
@Table(name = "file_mappings")
public class FileMapping {
    @Id
    @Column(name = "file_mapping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @JsonRawValue
    @Column(name = "mapping_json")
    private String mappingJson;
    @Column(name = "created_under")
    private int createdUnder;
    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "file_type_id")
    private FileType fileType;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public int getCreatedUnder() {
        return createdUnder;
    }

    public void setCreatedUnder(int createdUnder) {
        this.createdUnder = createdUnder;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getMappingJson() {
        return mappingJson;
    }

    public void setMappingJson(String mappingJson) {
        this.mappingJson = mappingJson;
    }
}
