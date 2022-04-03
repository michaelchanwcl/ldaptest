package com.example.ldaptest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LdapController {
    @Autowired(required = false)
    DirectoryListServiceInterface ldapService;

//    @Value("${docpal.spring.ldap.enabled}")
//    boolean isLdapEnabled;

//    @GetMapping("/")
//    public List<Person> get() {
//        List<Person> personList = ldapService.searchLdapUser("michael_chan");
//        return personList;
//    }
//
    @GetMapping("/allLdapUser")
    public List<UserDTO> getAllUser() {
        List<UserDTO> userDTOList = ldapService.searchAllLdapUserId();
        return userDTOList;
    }

    @GetMapping("/allLdapGroup")
    public List<GroupDTO> getAllGroup() {
        List<GroupDTO> groupDTOLIst = ldapService.searchAllLdapGroup();
        return groupDTOLIst;
    }
//
//    @GetMapping("/allLdapGroup")
//    public List<Group> getAllGroup() {
//        List<Group> groupList = ldapService.searchAllLdapGroup();
//        return groupList;
//    }
//
//    @PostMapping("/ldapGroup")
//    public List<Group> getGroupForDistinguishedName(@RequestBody PersonRequestDTO personRequestDTO) {
//        List<Group> groupList = ldapService.searchAllLdapGroupByDistinguishName(personRequestDTO.getDistinguishedName());
//        return groupList;
//    }

//    @PostMapping("/getUserById")
//    public List<String> getUserById(@RequestBody PersonRequestDTO personRequestDTO) {
//        List<String> groupList = ldapService.searchAllLdapGroupByLoginId(personRequestDTO.getLoginId());
//        return groupList;
//    }
}
