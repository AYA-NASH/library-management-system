package com.maids.cc.librarymanager.service;

import com.maids.cc.librarymanager.repository.BookRepository;
import com.maids.cc.librarymanager.repository.BorrowingRecordRepository;
import com.maids.cc.librarymanager.repository.PatronRepository;
import com.maids.cc.librarymanager.repository.entity.Book;
import com.maids.cc.librarymanager.repository.entity.BorrowingRecord;
import com.maids.cc.librarymanager.repository.entity.Patron;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class BorrowingRecordServiceTest {

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private BorrowingRecordService borrowingRecordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBorrowBook() {
        Book book = new Book(1L, "Book1", "Author1", 1234567890L, 10, new ArrayList<>());
        Patron patron = new Patron(1L, "Patron1", "patron1@mail.com", "1234567890", "Address1", new ArrayList<>());

        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));
        when(patronRepository.findById(any(Long.class))).thenReturn(Optional.of(patron));
        when(borrowingRecordRepository.save(any(BorrowingRecord.class))).thenReturn(null);

        borrowingRecordService.borrowBook(1L, 1L);

        verify(borrowingRecordRepository, times(1)).save(any(BorrowingRecord.class));
    }

    @Test
    public void testReturnBook() {
        Book book = new Book(1L, "Book1", "Author1", 1234567890L, 10, new ArrayList<>());
        Patron patron = new Patron(1L, "Patron1", "patron1@mail.com", "1234567890", "Address1", new ArrayList<>());
        BorrowingRecord record = new BorrowingRecord(1L, book, patron, LocalDate.now(), null, "BORROWED");

        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));
        when(patronRepository.findById(any(Long.class))).thenReturn(Optional.of(patron));
        when(borrowingRecordRepository.findByBookIdAndPatronIdAndStatus(any(Long.class), any(Long.class), anyString())).thenReturn(Optional.of(record));

        borrowingRecordService.returnBook(1L, 1L);

        verify(borrowingRecordRepository, times(1)).save(any(BorrowingRecord.class));
    }
}