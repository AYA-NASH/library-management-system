package com.maids.cc.librarymanager.service;

import com.maids.cc.librarymanager.controller.dto.PatronDTO;
import com.maids.cc.librarymanager.mappers.PatronMapper;
import com.maids.cc.librarymanager.repository.PatronRepository;
import com.maids.cc.librarymanager.repository.entity.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatronService {
    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<PatronDTO> getAllPatrons() {
        List<Patron> patrons = (List<Patron>) patronRepository.findAll();
        return patrons.stream().
                map(PatronMapper.INSTANCE::patronToPatronDTO).
                collect(Collectors.toList());
    }

    public PatronDTO getPatronById(Long id) {
        Patron patron = patronRepository.findById(id).orElse(null);
        return PatronMapper.INSTANCE.patronToPatronDTO(patron);
    }

    public PatronDTO createPatron(PatronDTO patronDTO) {
        Patron patron = PatronMapper.INSTANCE.patronDTOToPatron(patronDTO);
        patron = patronRepository.save(patron);
        return PatronMapper.INSTANCE.patronToPatronDTO(patron);
    }

    public PatronDTO updatePatron(Long id, PatronDTO patronDTO) {
        Patron existingPatron = patronRepository.findById(id).orElse(null);
        if (existingPatron == null) {
            return null;
        }
        if(patronDTO.getName() != null)
            existingPatron.setName(patronDTO.getName());
        if(patronDTO.getAddress() != null)
            existingPatron.setAddress(patronDTO.getAddress());
        if(patronDTO.getPhone() != null)
            existingPatron.setPhone(patronDTO.getPhone());
        if (patronDTO.getEmail() != null)
            existingPatron.setEmail(patronDTO.getEmail());

        Patron updatedPatron = patronRepository.save(existingPatron);

        return PatronMapper.INSTANCE.patronToPatronDTO(updatedPatron);
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
