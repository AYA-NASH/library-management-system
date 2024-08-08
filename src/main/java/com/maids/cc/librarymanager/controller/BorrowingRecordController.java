package com.maids.cc.librarymanager.controller;

import com.maids.cc.librarymanager.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowingRecordController implements BorrowingRecordAPI {
    private final BorrowingRecordService borrowingRecordService;

    @Autowired
    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @Override
    public ResponseEntity<Void> borrowBook(Long bookId, Long patronId) {
        borrowingRecordService.borrowBook(bookId, patronId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> returnBook(Long bookId, Long patronId) {
        borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok().build();
    }
}
