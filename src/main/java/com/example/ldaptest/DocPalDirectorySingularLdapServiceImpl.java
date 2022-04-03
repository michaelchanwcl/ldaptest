package com.example.ldaptest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

public class DocPalDirectorySingularLdapServiceImpl implements DirectoryListServiceInterface {
    @Autowired
    private LdapTemplate ldapTemplate;
    
    public List<GroupDTO> searchAllLdapGroup() {
        LdapQuery query = query()
                .filter("(&(objectClass=person)(!(|(userAccountControl=514)(userAccountControl=66050)))(mail=*@*))");
        List<Person> personList = ldapTemplate.find(query, Person.class);
        HashMap<String, String> groupHashMap = new HashMap<>();
        for (Person person : personList) {
            for (String group : person.getGroupList()) {
                groupHashMap.put(group, group);
            }
        }

        List<GroupDTO> groupDTOList = groupHashMap.keySet().stream().map(p -> {
            String groupName = p.substring(p.indexOf("CN=")+3, p.indexOf(","));
            if (groupName.equals("")) return null;
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setId(groupName);
            groupDTO.setName(groupName);
            return groupDTO;
        }).filter(p -> p!=null).collect(Collectors.toList());
        return groupDTOList;
    }

    @Override
    public List<UserDTO> searchAllLdapUserId() {
        LdapQuery query = query()
                .filter("(&(objectClass=person)(!(|(userAccountControl=514)(userAccountControl=66050)))(mail=*@*))");
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
        return personDTOList;
    }

//    public List<Group> searchAllLdapGroupByDistinguishName(String name) {
//        LdapQuery query = query().where("objectClass").is("group").and("member").is(name);
//        return ldapTemplate.find(query, Group.class);
//    }

//    @Override
//    public List<String> searchAllLdapGroupByLoginId(String name) {
//        List<Person> personList = getPeople(name, ldapTemplate);
//        if (personList.isEmpty()) {
//            return new ArrayList<>();   // Cannot find any person, return error
//        }
//        return getGroup(personList.get(0).getDistinguishedName(), ldapTemplate);
//    }

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

    //    public List<Person> searchLdapUser(String keyword) {
//        keyword = "*" + keyword + "*";
//        LdapQuery query = query().where("sAMAccountName").like(keyword);
//        return ldapTemplate.find(query, Person.class);
//    }
//
//    public List<Person> searchAllLdapUser() {
//        LdapQuery query = query().where("objectClass").is("user").and("userAccountControl").not().is("66050");
//        return ldapTemplate.find(query, Person.class);
//    }
}
