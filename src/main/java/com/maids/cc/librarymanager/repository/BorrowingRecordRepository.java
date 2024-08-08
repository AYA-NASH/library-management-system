package com.maids.cc.librarymanager.repository;

import com.maids.cc.librarymanager.repository.entity.BorrowingRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends CrudRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookIdAndPatronIdAndStatus(Long bookId, Long patronId, String status);

}
