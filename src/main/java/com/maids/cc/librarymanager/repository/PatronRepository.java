package com.maids.cc.librarymanager.repository;

import com.maids.cc.librarymanager.repository.entity.Patron;
import org.springframework.data.repository.CrudRepository;

public interface PatronRepository extends CrudRepository<Patron, Long>{
}
