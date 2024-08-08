package com.maids.cc.librarymanager.mappers;

import com.maids.cc.librarymanager.controller.dto.BookDTO;
import com.maids.cc.librarymanager.repository.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);
}

