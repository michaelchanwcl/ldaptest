package com.example.ldaptest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;

import java.util.ArrayList;
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

//    public List<Person> searchLdapUser(String keyword) {
//        keyword = "*" + keyword + "*";
//        LdapQuery query = query().where("sAMAccountName").like(keyword);
//        return ldapTemplate.find(query, Person.class);
//    }

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

//    public List<Group> searchAllLdapGroup() {
//        LdapQuery query = query().where("objectClass").is("group");
//        return ldapTemplate.find(query, Group.class);
//    }
//
//    public List<Group> searchAllLdapGroupByDistinguishName(String name) {
//        LdapQuery query = query().where("objectClass").is("group").and("member").is(name);
//        return ldapTemplate.find(query, Group.class);
//    }

//    @Override
//    public List<String> searchAllLdapGroupByLoginId(String name) {
//        List<Person> personList = getPeople(name, ldapTemplate);
//        if (personList.isEmpty()) {
//            // search for PRC user
//            personList = getPeople(name, ldapTemplatePRC);
//            if (personList.isEmpty()) {
//                return new ArrayList<>();   // Cannot find any person, return error
//            } else {
//                return getGroup(personList.get(0).getDistinguishedName(), ldapTemplatePRC);
//            }
//        } else {
//            return getGroup(personList.get(0).getDistinguishedName(), ldapTemplate);
//        }
//    }
//
//    private List<String> getGroup(String distinguishedName, LdapTemplate ldapTemplate) {
//        LdapQuery query = query().where("objectClass").is("group").and("member").is(distinguishedName);
//        List<Group> groupList = ldapTemplate.find(query, Group.class);
//        List<String> groupStringList = groupList.stream().map(p -> {
//            return p.getGroupName();
//        }).collect(Collectors.toList());
//        return groupStringList;
//    }
//
//    private List<Person> getPeople(String name, LdapTemplate ldapTemplatePRC) {
//        LdapQuery query = query().where("objectClass").is("user").and("sAMAccountName").is(name);
//        List<Person> personList = ldapTemplatePRC.find(query, Person.class);
//        if (personList.isEmpty()) {
//            return new ArrayList<>();
//        }
//        return personList;
//    }
}
