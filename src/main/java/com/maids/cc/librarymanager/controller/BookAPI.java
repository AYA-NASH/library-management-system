package com.maids.cc.librarymanager.controller;

import com.maids.cc.librarymanager.controller.dto.BookDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/books")
public interface BookAPI {
    @GetMapping
    ResponseEntity<List<BookDTO>> getAllBooks();

    @GetMapping("/{id}")
    ResponseEntity<BookDTO> getBookById(@PathVariable Long id);

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO);

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable Long id);

}
