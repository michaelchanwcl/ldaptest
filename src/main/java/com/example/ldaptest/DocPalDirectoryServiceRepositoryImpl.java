package com.example.ldaptest;

import java.util.ArrayList;
import java.util.List;

public class DocPalDirectoryServiceRepositoryImpl implements DirectoryListServiceInterface {
//    @Override
//    public List<String> searchAllLdapGroupByLoginId(String name) {
//        List<String> groupList = new ArrayList<>();
//        groupList.add("Group1");
//        groupList.add("Group2");
//        groupList.add("Group3");
//        return groupList;
//    }


    @Override
    public List<UserDTO> searchAllLdapUserId() {
        List<UserDTO> userDTOList = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setId("test_michael");
        userDTOList.add(userDTO);
        return userDTOList;
    }

    @Override
    public List<GroupDTO> searchAllLdapGroup() {
        return null;
    }
}
