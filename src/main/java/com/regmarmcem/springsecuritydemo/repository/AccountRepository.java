package com.regmarmcem.springsecuritydemo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.regmarmcem.springsecuritydemo.record.Account;

@Repository
public class AccountRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final String SQL_FIND_BY_EMAIL = """
            SELECT
                a.email,
                a.name AS user_name,
                a.password,
                r.name AS role_name
            FROM
                account a
            JOIN
                account_role ar
            ON
                a.id = ar.account_id
            JOIN
                roles r
            ON
                ar.role_id = r.id
            WHERE a.email = :email                
            """;
    
    private static final ResultSetExtractor<Account> ACCOUNT_EXTRACTOR = (rs) -> {
        String email = null;
        String userName = null;
        String password = null;    
        List<String> roleList = new ArrayList<>();
        while(rs.next()) {
            if (email == null) {
                email = rs.getString("email");
                userName = rs.getString("user_name");
                password = rs.getString("password");
            }
            roleList.add(rs.getString("role_name"));
        }
        if (email == null) {
            return null;
        }
        return new Account(email, userName, password, roleList);
    };

    public Optional<Account> findByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource("email", email);
        Account account = namedParameterJdbcTemplate.query(SQL_FIND_BY_EMAIL, params, ACCOUNT_EXTRACTOR);
        return Optional.ofNullable(account);
    }
}
