package com.maids.cc.librarymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maids.cc.librarymanager.controller.dto.BookDTO;
import com.maids.cc.librarymanager.controller.dto.PatronDTO;
import com.maids.cc.librarymanager.service.PatronService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PatronController.class)
public class PatronControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    @Test
    public void testGetAllPatrons() throws Exception {
        PatronDTO patron1 = new PatronDTO(1L, "Patron1", "address1", "email1", "phone1");
        PatronDTO patron2 = new PatronDTO(2L, "Patron2", "address2", "email2", "phone2");

        List<PatronDTO> patrons = List.of(patron1, patron2);
        given(patronService.getAllPatrons()).willReturn(patrons);

        mockMvc.perform(get("/api/patrons"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(patron1.getId()))
                .andExpect(jsonPath("$[0].name").value(patron1.getName()))
                .andExpect(jsonPath("$[1].id").value(patron2.getId()))
                .andExpect(jsonPath("$[1].name").value(patron2.getName()));

        verify(patronService, times(1)).getAllPatrons();
    }

    @Test
    public void testGetPatronById() throws Exception {
        PatronDTO patron = new PatronDTO(1L, "Patron1", "email1", "phone1","address1");

        given(patronService.getPatronById(1L)).willReturn(patron);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(patron.getId()))
                .andExpect(jsonPath("$.name").value(patron.getName()))
                .andExpect(jsonPath("$.email").value(patron.getEmail()))
                .andExpect(jsonPath("$.phone").value(patron.getPhone()))
                .andExpect(jsonPath("$.address").value(patron.getAddress()));

        verify(patronService, times(1)).getPatronById(1L);
    }

    @Test
    public void testCreatePatron() throws Exception {
        PatronDTO patron = new PatronDTO(null, "Patron1", "email1@example.com", "+1234567890", "address1");

        given(patronService.createPatron(any(PatronDTO.class))).willReturn(patron);

        mockMvc.perform(post("/api/patrons/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patron)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(patron.getId()))
                .andExpect(jsonPath("$.name").value(patron.getName()))
                .andExpect(jsonPath("$.email").value(patron.getEmail()))
                .andExpect(jsonPath("$.phone").value(patron.getPhone()))
                .andExpect(jsonPath("$.address").value(patron.getAddress()));

        verify(patronService, times(1)).createPatron(any(PatronDTO.class));
    }

    @Test
    public void testUpdatePatron() throws Exception {
        PatronDTO patron = new PatronDTO(1L, "Patron1", "email1@example.com", "+1234567890", "address1");

        given(patronService.updatePatron(eq(1L), any(PatronDTO.class))).willReturn(patron);
        mockMvc.perform(put("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patron)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(patron.getId()))
                .andExpect(jsonPath("$.name").value(patron.getName()))
                .andExpect(jsonPath("$.email").value(patron.getEmail()))
                .andExpect(jsonPath("$.phone").value(patron.getPhone()))
                .andExpect(jsonPath("$.address").value(patron.getAddress()));

        verify(patronService, times(1)).updatePatron(eq(1L),any(PatronDTO.class));
    }

    @Test
    public void testDeletePatron() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patrons/1"))
                .andExpect(status().isOk());

        verify(patronService, times(1)).deletePatron(1L);
    }

}
