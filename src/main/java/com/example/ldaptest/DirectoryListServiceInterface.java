package com.example.ldaptest;

import java.util.List;

public interface DirectoryListServiceInterface {
//    public List<String> searchAllLdapGroupByLoginId(String name);
    public List<UserDTO> searchAllLdapUserId();
    public List<GroupDTO> searchAllLdapGroup();
}
