package com.example.ldaptest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

public class DocPalDirectoryDualLdapServiceImpl implements DirectoryListServiceInterface {
    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    @Qualifier("ldapTemplate2")
    private LdapTemplate ldapTemplatePRC;

    public List<UserDTO> searchAllLdapUserId() {
        LdapQuery query = query().where("objectClass").is("user").and("userAccountControl").not().is("66050");
        List<Person> personList = ldapTemplate.find(query, Person.class);
        List<UserDTO> personDTOList = personList.stream().map(p -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(p.getLoginName());
            userDTO.setUsername(p.getUserName());
            userDTO.setEmail(p.getEmail());
            userDTO.setFirstName(p.getFirstName());
            userDTO.setLastName(p.getLastName());
            return userDTO;
        }).collect(Collectors.toList());

        LdapQuery queryPRC = query().where("objectClass").is("user").and("userAccountControl").not().is("514");
        List<Person> personListPRC = ldapTemplatePRC.find(queryPRC, Person.class);
        List<UserDTO> personDTOPRCList = personListPRC.stream().map(p -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(p.getLoginName());
            userDTO.setUsername(p.getUserName());
            userDTO.setEmail(p.getEmail());
            userDTO.setFirstName(p.getFirstName());
            userDTO.setLastName(p.getLastName());
            return userDTO;
        }).collect(Collectors.toList());
        return Stream.concat(personDTOList.stream(), personDTOPRCList.stream()).collect(Collectors.toList());
    }

    @Override
    public List<GroupDTO> searchAllLdapGroup() {
        return null;
    }
}
