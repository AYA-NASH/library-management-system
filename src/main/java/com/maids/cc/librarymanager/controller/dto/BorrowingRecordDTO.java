package com.maids.cc.librarymanager.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecordDTO {
    public Long id;
    public Long bookId;
    public Long patronId;

    @JsonProperty("borrowing_date")
    public LocalDate borrowingDate;

    @JsonProperty("return_date")
    public LocalDate returnDate;

    @JsonProperty("status")
    public String status;
}
