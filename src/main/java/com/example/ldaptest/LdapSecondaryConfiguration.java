package com.example.ldaptest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.ldap.LdapProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import java.util.Collections;

@Configuration
@ConditionalOnProperty(prefix = "docpal.spring.ldap2",
        name = "enabled",
        havingValue="true")
@ConditionalOnBean(LdapPrimaryConfiguration.class)
public class LdapSecondaryConfiguration {
    @Bean(name="ldapProperties2")
    @ConfigurationProperties("docpal.spring.ldap2")
    public LdapProperties ldapProperties2() {
        return new LdapProperties();
    }

    @Bean(name="contextSource2")
    public LdapContextSource ldapContextSource2(@Qualifier("ldapProperties2") LdapProperties properties) {
        return getContextSource(properties);
    }

    private LdapContextSource getContextSource(@Qualifier("ldapProperties2") LdapProperties properties) {
        LdapContextSource source = new LdapContextSource();
        source.setUrls(properties.getUrls());
        source.setUserDn(properties.getUsername());
        source.setPassword(properties.getPassword());
        source.setBase(properties.getBase());
        source.setBaseEnvironmentProperties(Collections.unmodifiableMap(properties.getBaseEnvironment()));
        return source;
    }

    @Bean(name="ldapTemplate2")
    public LdapTemplate ldapTemplate2(@Qualifier("contextSource2") LdapContextSource source) {
        return new LdapTemplate(source);
    }
}