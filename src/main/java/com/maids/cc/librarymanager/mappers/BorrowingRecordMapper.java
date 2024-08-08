package com.maids.cc.librarymanager.mappers;

import com.maids.cc.librarymanager.repository.entity.BorrowingRecord;
import org.mapstruct.factory.Mappers;

public interface BorrowingRecordMapper {
    BorrowingRecordMapper INSTANCE = Mappers.getMapper(BorrowingRecordMapper.class);
    BorrowingRecord borrowingRecordDTOToBorrowingRecord(BorrowingRecord borrowingRecord);
    BorrowingRecord borrowingRecordToBorrowingRecordDTO(BorrowingRecord borrowingRecord);
}
