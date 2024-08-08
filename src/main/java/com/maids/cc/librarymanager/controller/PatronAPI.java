package com.maids.cc.librarymanager.controller;

import com.maids.cc.librarymanager.controller.dto.PatronDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/patrons")
public interface PatronAPI {
    @GetMapping
    public ResponseEntity<List<PatronDTO>> getAllPatrons();

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(Long id);

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatronDTO> createPatron(@Valid @RequestBody PatronDTO patronDTO);

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Long id, @Valid @RequestBody PatronDTO patronDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id);
}
