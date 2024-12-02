package com.javarush.jira.bugtracking.jwt;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
@RequiredArgsConstructor
public class UsernameNotFoundException extends IOException {
    public UsernameNotFoundException(String string){
        super(string);
    }
}
