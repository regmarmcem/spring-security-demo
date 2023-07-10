INSERT INTO accounts 
    (email, name, password)
VALUES
    ('general@example.com', 'general', 'general'),
    ('addmin@example.com', 'admin', 'admin')
;

INSERT INTO roles
    (name)
VALUES
    ('sys_admin'),
    ('user_admin'),
    ('general')
;

INSERT INTO account_role
    (account_id, role_id)
VALUES
    (
        (SELECT id FROM accounts WHERE name = 'admin'),
        (SELECT id FROM roles WHERE name = 'sys_admin')
    ),
    (
        (SELECT id FROM accounts WHERE name = 'admin'),
        (SELECT id FROM roles WHERE name = 'user_admin')
    ),
    (
        (SELECT id FROM accounts WHERE name = 'general'),
        (SELECT id FROM roles WHERE name = 'general')
    )
;
