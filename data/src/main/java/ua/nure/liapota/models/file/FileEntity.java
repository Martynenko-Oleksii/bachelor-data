package ua.nure.liapota.models.file;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "upload_by")
    private String uploadBy;
    @Column(name = "column_delimeter")
    private char columnDelimiter;

    @ManyToOne
    @JoinColumn(name = "file_mapping_id")
    private FileMapping fileMapping;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getColumnDelimiter() {
        return columnDelimiter;
    }

    public void setColumnDelimiter(char columnDelimiter) {
        this.columnDelimiter = columnDelimiter;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public FileMapping getFileMapping() {
        return fileMapping;
    }

    public void setFileMapping(FileMapping fileMapping) {
        this.fileMapping = fileMapping;
    }
}
