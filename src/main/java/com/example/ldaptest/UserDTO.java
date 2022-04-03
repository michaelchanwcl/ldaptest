package com.example.ldaptest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/**
 * The type User dto.
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Comparable<UserDTO> {
    @EqualsAndHashCode.Exclude
    private Boolean isConnected;

    @EqualsAndHashCode.Exclude
    private Integer timeout;

    @EqualsAndHashCode.Exclude
    private String sessionId;

    @EqualsAndHashCode.Exclude
    private String username;

    @EqualsAndHashCode.Exclude
    private String userId;

    private String id;

    @EqualsAndHashCode.Exclude
    private String firstName;

    @EqualsAndHashCode.Exclude
    private String lastName;

    @EqualsAndHashCode.Exclude
    private String email;

    @EqualsAndHashCode.Exclude
    private String password;

    @EqualsAndHashCode.Exclude
    private String jwtExpiredAt;

    @Override
    public int compareTo(@NonNull UserDTO userDTO) {
        return StringUtils.compare(username, userDTO.getUsername());
    }
}
