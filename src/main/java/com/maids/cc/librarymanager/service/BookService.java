package com.maids.cc.librarymanager.service;

import com.maids.cc.librarymanager.controller.dto.BookDTO;
import com.maids.cc.librarymanager.mappers.BookMapper;
import com.maids.cc.librarymanager.repository.BookRepository;
import com.maids.cc.librarymanager.repository.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books.stream()
                .map(BookMapper.INSTANCE::bookToBookDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return BookMapper.INSTANCE.bookToBookDTO(book);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
        Book createdBook = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDTO(createdBook);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            return null;
        }
        if(bookDTO.getTitle() != null)
            existingBook.setTitle(bookDTO.getTitle());
        if(bookDTO.getAuthor() != null)
            existingBook.setAuthor(bookDTO.getAuthor());
        if(bookDTO.getQuantity() != null)
            existingBook.setQuantity(bookDTO.getQuantity());
        if(bookDTO.getIsbn() != null)
            existingBook.setIsbn(bookDTO.getIsbn());

        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.INSTANCE.bookToBookDTO(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
