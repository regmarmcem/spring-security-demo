package com.regmarmcem.springsecuritydemo.record;

import java.util.List;

public record Account(String email, String name, String password, List<String> roleList) {
}
