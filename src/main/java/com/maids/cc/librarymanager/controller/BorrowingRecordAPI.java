package com.maids.cc.librarymanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface BorrowingRecordAPI {
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    ResponseEntity<Void> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId);

    @PutMapping("/return/{bookId}/patron/{patronId}")
    ResponseEntity<Void> returnBook(@PathVariable Long bookId, @PathVariable Long patronId);
}
