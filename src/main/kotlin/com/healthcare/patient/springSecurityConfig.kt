package com.healthcare.patient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class springSecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    override fun userDetailsServiceBean(): UserDetailsService? {
        val users: MutableList<UserDetails> = ArrayList()
        users.add(
            User.withDefaultPasswordEncoder()
                .username("Sakshi")
                .password("1234")
                .roles("USER")
                .build()
        )
        users.add(
            User.withDefaultPasswordEncoder()
                .username("SakshiMaan")
                .password("12345")
                .roles("ADMIN")
                .build()
        )
        return InMemoryUserDetailsManager(users)
    }

    override fun configure(http: HttpSecurity?) {
        super.configure(http)
        http?.csrf()?.disable()
    }
}
