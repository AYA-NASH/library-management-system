package com.maids.cc.librarymanager.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatronDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}
