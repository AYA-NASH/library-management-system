package com.maids.cc.librarymanager.service;

import com.maids.cc.librarymanager.controller.dto.PatronDTO;
import com.maids.cc.librarymanager.mappers.PatronMapper;
import com.maids.cc.librarymanager.repository.PatronRepository;
import com.maids.cc.librarymanager.repository.entity.Patron;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PatronServiceTest {
    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    @Test
    public void testGetPatronById() {
        Patron patron = new Patron(1L, "Patron1", "patron1@mail.com", "1234567890", "Address1", new ArrayList<>());
        when(patronRepository.findById(any(Long.class))).thenReturn(Optional.of(patron));

        PatronDTO result = patronService.getPatronById(1L);

        assertEquals(patron.getId(), result.getId());
        assertEquals(patron.getName(), result.getName());
    }
    @Test
    public void testCreatePatron() {
        PatronDTO patronDTO = new PatronDTO(null, "Patron1", "patron1@mail.com", "1234567890", "Address1");
        Patron createdPatron = new Patron(1L, "Patron1", "patron1@mail.com", "1234567890", "Address1", new ArrayList<>());

        when(patronRepository.save(any(Patron.class))).thenReturn(createdPatron);

        PatronDTO result = patronService.createPatron(patronDTO);

        assertEquals(createdPatron.getId(), result.getId());
        assertEquals(createdPatron.getName(), result.getName());
    }

    @Test
    public void testUpdatePatron() {
        PatronDTO patronDTO = new PatronDTO(1L, "Updated Patron", "updatedpatron@mail.com", "1234567890", "Address1");
        Patron existingPatron = new Patron(1L, "Patron1", "patron1@mail.com", "1234567890", "Address1", new ArrayList<>());

        when(patronRepository.findById(any(Long.class))).thenReturn(Optional.of(existingPatron));
        when(patronRepository.save(any(Patron.class))).thenReturn(PatronMapper.INSTANCE.patronDTOToPatron(patronDTO));

        PatronDTO result = patronService.updatePatron(1L, patronDTO);

        assertEquals(patronDTO.getId(), result.getId());
        assertEquals(patronDTO.getName(), result.getName());
    }

    @Test
    public void testDeletePatron() {
        Patron existingPatron = new Patron(1L, "Patron1", "patron1@mail.com", "1234567890", "Address1", new ArrayList<>());
        when(patronRepository.findById(any(Long.class))).thenReturn(Optional.of(existingPatron));

        patronService.deletePatron(1L);

        verify(patronRepository, times(1)).deleteById(1L);
    }
}
