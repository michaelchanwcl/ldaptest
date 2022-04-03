package com.example.ldaptest;

import lombok.Data;

@Data
public class PersonRequestDTO {
    private String distinguishedName;
    private String loginId;
}
