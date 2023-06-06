package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.file.FileMapping;
import ua.nure.liapota.models.file.FileType;
import ua.nure.liapota.services.FileMappingService;
import ua.nure.liapota.services.FileTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Authorize("data,data-configuration")
@RestController
@RequestMapping("/fileMappings")
@CrossOrigin(origins = "http://localhost:4200")
public class FileMappingController {
    private final FileMappingService service;
    private final FileTypeService fileTypeService;

    @Autowired
    public FileMappingController(FileMappingService service, FileTypeService fileTypeService) {
        this.service = service;
        this.fileTypeService = fileTypeService;
    }

    @GetMapping("/fileTypes")
    public ResponseEntity<List<FileType>> getFileTypes() {
        return new ResponseEntity<>(fileTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FileMapping>> getAll(HttpServletRequest request) {
        return new ResponseEntity<>(service.getByCreatedUnder((Integer) request.getAttribute("customerId")),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FileMapping> create(@RequestBody FileMapping fileMapping,
                                              HttpServletRequest request) {
        fileMapping.setCreatedBy((String) request.getAttribute("userId"));
        fileMapping.setCreatedUnder((Integer) request.getAttribute("customerId"));
        return new ResponseEntity<>(service.create(fileMapping), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody FileMapping fileMapping,
                                       HttpServletRequest request) {
        fileMapping.setCreatedBy((String) request.getAttribute("userId"));
        fileMapping.setCreatedUnder((Integer) request.getAttribute("customerId"));
        service.update(fileMapping);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
