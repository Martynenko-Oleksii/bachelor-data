package ua.nure.liapota.models.file;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "upload_logs")
public class UploadLog {
    @Id
    @Column(name = "upload_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "mapping_name")
    private String mappingName;
    @Column(name = "lines_count")
    private int linesCount;
    @Column(name = "error_lines_count")
    private int errorLinesCount;
    private String status;
    @Column(name = "upload_by")
    private String uploadBy;
    @Column(name = "upload_date")
    private Date uploadDate;
    @Column(name = "facility_id")
    private int facilityId;
    @Column(name = "time_period")
    private String timePeriod;

    @ManyToOne
    @JoinColumn(name = "fyle_type_id")
    private FileType fileType;

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getErrorLinesCount() {
        return errorLinesCount;
    }

    public void setErrorLinesCount(int errorLinesCount) {
        this.errorLinesCount = errorLinesCount;
    }

    public int getLinesCount() {
        return linesCount;
    }

    public void setLinesCount(int linesCount) {
        this.linesCount = linesCount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
