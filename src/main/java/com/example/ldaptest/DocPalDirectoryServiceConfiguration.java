package com.example.ldaptest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocPalDirectoryServiceConfiguration {

    @Bean
    @ConditionalOnExpression("${docpal.spring.ldap.enabled:false} and ${docpal.spring.ldap2.enabled:false}")
    public DocPalDirectoryDualLdapServiceImpl ldapServiceBean() {
        return new DocPalDirectoryDualLdapServiceImpl();
    }

    @Bean
    @ConditionalOnExpression("${docpal.spring.ldap.enabled:false} and !${docpal.spring.ldap2.enabled:false}")
    public DocPalDirectorySingularLdapServiceImpl ldapServiceSingular() {
        return new DocPalDirectorySingularLdapServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(value = {DocPalDirectoryDualLdapServiceImpl.class, DocPalDirectorySingularLdapServiceImpl.class})
    public DocPalDirectoryServiceRepositoryImpl userServiceBean() {
        return new DocPalDirectoryServiceRepositoryImpl();
    }
}
