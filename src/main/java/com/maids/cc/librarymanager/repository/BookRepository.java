package com.maids.cc.librarymanager.repository;

import com.maids.cc.librarymanager.repository.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>{
}
