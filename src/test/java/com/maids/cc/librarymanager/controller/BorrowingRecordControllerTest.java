package com.maids.cc.librarymanager.controller;

import com.maids.cc.librarymanager.service.BorrowingRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BorrowingRecordController.class)
public class BorrowingRecordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingRecordService borrowingRecordService;

    @Test
    public void testBorrowBook() throws Exception {

        mockMvc.perform(post("/api/borrow/{bookId}/patron/{patronId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(borrowingRecordService, times(1)).borrowBook(1L, 1L);
    }

    @Test
    public void testReturnBook() throws Exception {

        mockMvc.perform(put("/api/return/{bookId}/patron/{patronId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(borrowingRecordService, times(1)).returnBook(1L, 1L);
    }

}
