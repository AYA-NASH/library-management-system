package com.maids.cc.librarymanager.service;

import com.maids.cc.librarymanager.repository.BookRepository;
import com.maids.cc.librarymanager.repository.BorrowingRecordRepository;
import com.maids.cc.librarymanager.repository.PatronRepository;
import com.maids.cc.librarymanager.repository.entity.Book;
import com.maids.cc.librarymanager.repository.entity.BorrowingRecord;
import com.maids.cc.librarymanager.repository.entity.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingRecordService {
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    @Autowired
    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if (book.getQuantity() <= 0){
            throw new ResourceNotFoundException("Book is not available");
//            throw new IllegalStateException("Book not available");
        }

        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new ResourceNotFoundException("Patron not found"));

        BorrowingRecord newRecord = new BorrowingRecord();
        newRecord.setBook(book);
        newRecord.setPatron(patron);
        newRecord.setBorrowDate(LocalDate.now());
        newRecord.setStatus("BORROWED");

        borrowingRecordRepository.save(newRecord);

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
    }

    public void returnBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new ResourceNotFoundException("Patron not found"));

        BorrowingRecord record = borrowingRecordRepository.findByBookIdAndPatronIdAndStatus(bookId, patronId, "BORROWED")
                .orElseThrow(() -> new RuntimeException("Borrowing record not found or already returned"));

        record.setReturnDate(LocalDate.now());
        record.setStatus("RETURNED");

        borrowingRecordRepository.save(record);

        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }
}
