package com.example.ldaptest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.ldap.LdapProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import java.util.Collections;

@Configuration
@ConditionalOnProperty(prefix = "docpal.spring.ldap",
        name = "enabled",
        havingValue="true")
public class LdapPrimaryConfiguration {
    @Bean(name="contextSource")
    public LdapContextSource ldapContextSource(@Qualifier("spring.ldap-org.springframework.boot.autoconfigure.ldap.LdapProperties") LdapProperties properties) {
        return getContextSource(properties);
    }

    @Bean(name="ldapTemplate")
    @Primary
    public LdapTemplate ldapTemplate(@Qualifier("contextSource") LdapContextSource source) {
        source.setReferral("follow");
        source.afterPropertiesSet();
        LdapTemplate ldapTemplate =  new LdapTemplate(source);
        ldapTemplate.setIgnorePartialResultException(true);
        ldapTemplate.setDefaultSearchScope(2);
        ldapTemplate.setDefaultTimeLimit(0);
        ldapTemplate.setDefaultCountLimit(0);
        ldapTemplate.setIgnoreNameNotFoundException(false);
        ldapTemplate.setIgnoreSizeLimitExceededException(true);
        return ldapTemplate;
    }

    private LdapContextSource getContextSource(LdapProperties properties) {
        LdapContextSource source = new LdapContextSource();
        source.setUrls(properties.getUrls());
        source.setUserDn(properties.getUsername());
        source.setPassword(properties.getPassword());
        source.setBase(properties.getBase());
        source.setBaseEnvironmentProperties(Collections.unmodifiableMap(properties.getBaseEnvironment()));
        return source;
    }
}
