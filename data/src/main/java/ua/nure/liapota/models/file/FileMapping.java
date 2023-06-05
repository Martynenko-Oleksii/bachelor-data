package ua.nure.liapota.models.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

    @JsonIgnore
    @OneToMany(mappedBy = "fileMapping")
    private Set<FileEntity> files;

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

    public Set<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(Set<FileEntity> files) {
        this.files = files;
    }

    public Map<Integer, String> getMap() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(mappingJson);
        Map<Integer, String> resultMap = new HashMap<>();

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();

        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            String key = field.getKey();
            String value = field.getValue().asText();
            int columnNumber = extractColumnNumber(value);

            resultMap.put(columnNumber, key);
        }

        return resultMap;
    }

    private static int extractColumnNumber(String key) {
        String columnNumberString = key.replaceAll("[^0-9]", "");
        return Integer.parseInt(columnNumberString);
    }
}
