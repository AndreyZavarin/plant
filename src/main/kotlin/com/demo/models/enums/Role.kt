package com.demo.models.enums

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

enum class Role {
    NONE, ADMIN, USER, GUEST;

    fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return AuthorityUtils.createAuthorityList(name);
    }
}