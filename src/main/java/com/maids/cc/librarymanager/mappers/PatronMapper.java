package com.maids.cc.librarymanager.mappers;

import com.maids.cc.librarymanager.controller.dto.PatronDTO;
import com.maids.cc.librarymanager.repository.entity.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatronMapper {
    PatronMapper INSTANCE = Mappers.getMapper(PatronMapper.class);

    PatronDTO patronToPatronDTO(Patron patron);
    Patron patronDTOToPatron(PatronDTO patronDTO);
}
