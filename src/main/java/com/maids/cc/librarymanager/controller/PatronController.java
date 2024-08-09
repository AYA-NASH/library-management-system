package com.maids.cc.librarymanager.controller;

import com.maids.cc.librarymanager.controller.dto.PatronDTO;
import com.maids.cc.librarymanager.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatronController implements PatronAPI{
    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @Override
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        List<PatronDTO> patrons = patronService.getAllPatrons();
        return ResponseEntity.ok(patrons);
    }

    @Override
    public ResponseEntity<PatronDTO> getPatronById(Long id) {
        PatronDTO patron = patronService.getPatronById(id);
        return ResponseEntity.ok(patron);
    }

    @Override
    public ResponseEntity<PatronDTO> createPatron(PatronDTO patronDTO) {
        PatronDTO createdPatron = patronService.createPatron(patronDTO);
        return new ResponseEntity<>(createdPatron, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PatronDTO> updatePatron(Long id, PatronDTO patronDTO) {
        PatronDTO updatedPatron = patronService.updatePatron(id, patronDTO);
        return ResponseEntity.ok(updatedPatron);
    }

    @Override
    public ResponseEntity<Void> deletePatron(Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.ok().build();
    }
}
