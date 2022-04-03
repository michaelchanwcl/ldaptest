package com.example.ldaptest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.List;

@Data
@Entry(objectClasses = {"group"})
public final class Group {
    @Id
    @JsonIgnore
    private Name id;

    @Attribute(name = "description")
    private String description;

    @Attribute(name = "cn")
    private String groupName;

    @Attribute(name = "member")
    private List<String> member;
}
