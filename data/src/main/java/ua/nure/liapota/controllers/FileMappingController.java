package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.models.file.FileMapping;
import ua.nure.liapota.services.FileMappingService;

import java.util.List;

@RestController
@RequestMapping("/fileMappings")
@CrossOrigin(origins = "http://localhost:4200")
public class FileMappingController {
    private final FileMappingService service;

    @Autowired
    public FileMappingController(FileMappingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FileMapping>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FileMapping> create(@RequestBody FileMapping fileMapping) {
        return new ResponseEntity<>(service.create(fileMapping), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody FileMapping fileMapping) {
        service.update(fileMapping);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
