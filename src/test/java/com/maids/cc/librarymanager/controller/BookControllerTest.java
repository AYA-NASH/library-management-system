package com.maids.cc.librarymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maids.cc.librarymanager.controller.dto.BookDTO;
import com.maids.cc.librarymanager.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;




@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        BookDTO book1 = new BookDTO(1L, "Book1", "Author1", 1234567890L, 10);
        BookDTO book2 = new BookDTO(2L, "Book2", "Author2", 1234567891L, 20);

        List<BookDTO> books = List.of(book1, book2);

        given(bookService.getAllBooks()).willReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(book1.getId()))
                .andExpect(jsonPath("$[0].title").value(book1.getTitle()))
                .andExpect(jsonPath("$[1].id").value(book2.getId()))
                .andExpect(jsonPath("$[1].title").value(book2.getTitle()));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testGetBookById() throws Exception {
        BookDTO book = new BookDTO(1L, "Book1", "Author1", 1234567890L, 10);

        given(bookService.getBookById(1L)).willReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()))
                .andExpect(jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(jsonPath("$.quantity").value(book.getQuantity()));

        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    public void testCreateBook() throws Exception {
        BookDTO book = new BookDTO(null, "New Book", "New Author", 1234567892L, 5);
        BookDTO createdBook = new BookDTO(1L, "New Book", "New Author", 1234567892L, 5);

        given(bookService.createBook(any(BookDTO.class))).willReturn(createdBook);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(createdBook.getId()))
                .andExpect(jsonPath("$.title").value(createdBook.getTitle()));

        verify(bookService, times(1)).createBook(any(BookDTO.class));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookDTO book = new BookDTO(1L, "Book1", "Author1", 1234567890L, 10);

        given(bookService.updateBook(eq(1L), any(BookDTO.class))).willReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.title").value(book.getTitle()));

        verify(bookService, times(1)).updateBook(eq(1L), any(BookDTO.class));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService, times(1)).deleteBook(1L);
    }

}
