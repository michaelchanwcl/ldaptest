package com.example.ldaptest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;

import javax.naming.Name;
import java.util.Enumeration;
import java.util.List;

@Data
@Entry(objectClasses = {"person"})
public final class Person {
    @Id
    @JsonIgnore
    private Name id;

    @Attribute(name = "sAMAccountName")
    private String loginName;

    @Attribute(name = "displayName")
    private String userName;

    @Attribute(name = "mail")
    private String email;

    @Attribute(name = "givenName")
    private String lastName;

    @Attribute(name = "sn")
    private String firstName;

    @Attribute(name = "distinguishedName")
    private String distinguishedName;

    @Attribute(name = "memberOf")
    private List<String> groupList;
}
