package com.maids.cc.librarymanager.service;

import com.maids.cc.librarymanager.controller.dto.BookDTO;
import com.maids.cc.librarymanager.mappers.BookMapper;
import com.maids.cc.librarymanager.repository.BookRepository;
import com.maids.cc.librarymanager.repository.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Use initMocks for JUnit 4
    }

    @Test
    public void testSetup() {
        assertNotNull(bookRepository);
        assertNotNull(bookService);
    }

    @Test
    public void testGetAllBooksReturnsEmptyListWhenNoBooks() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<Book>());
        List<BookDTO> result = bookService.getAllBooks();
        assertEquals(0, result.size());
    }

    @Test
    public void testGetAllBooksReturnsListWhenBooksExist() {
        Book book1 = new Book(1L, "Book1", "Author1", 1234567890L, 10, null);
        Book book2 = new Book(2L, "Book2", "Author2", 1234567891L, 15, null);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<BookDTO> result = bookService.getAllBooks();

        assertEquals(2, result.size()); // Ensure the result size is correct
//        assertEquals(book1.getId(), result.get(0).getId());
//        assertEquals(book1.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testCreateBookSavesAndReturnsBook() {
        BookDTO bookDTO = new BookDTO(null, "Book1", "Author1", 1234567890L, 10);
        Book createdBook = new Book(1L, "Book1", "Author1", 1234567890L, 10, new ArrayList<>());

        when(bookRepository.save(any(Book.class))).thenReturn(createdBook);

        BookDTO result = bookService.createBook(bookDTO);

        assertNotNull(result);
        assertEquals(createdBook.getId(), result.getId());
        assertEquals(createdBook.getTitle(), result.getTitle());

        verify(bookRepository).save(any(Book.class));

    }

    @Test
    public void testUpdateBookUpdatesAndReturnsBook() {
        BookDTO bookDTO = new BookDTO(1L, "Updated Book", "Updated Author", 1234567890L, 10);
        Book existingBook = new Book(1L, "Book1", "Author1", 1234567890L, 10, new ArrayList<>());

        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(BookMapper.INSTANCE.bookDTOToBook(bookDTO));

        BookDTO result = bookService.updateBook(1L, bookDTO);

        assertEquals(bookDTO.getId(), result.getId());
        assertEquals(bookDTO.getTitle(), result.getTitle());
    }

    @Test
    public void testDeleteBookDeletesBook() {
        Book existingBook = new Book(1L, "Book1", "Author1", 1234567890L, 10, new ArrayList<>());
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(existingBook));

        bookService.deleteBook(1L);

        verify(bookRepository).deleteById(1L);
    }
}
