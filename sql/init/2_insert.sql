INSERT INTO accounts 
    (email, name, password)
VALUES
    ('general@example.com', 'general', '$2y$10$h2UnFVTbRJhE6jiuq4vVO.CJat4w8cQtrn5SxctpuaNebr3dtEwcm'),
    ('admin@example.com', 'admin', '$2y$10$Y2LsuiW5Ws.Uy5umA3ya8OMdz974vhvUJDCNS/yH5RY/Ga844Olnm')
;

INSERT INTO roles
    (name)
VALUES
    ('SYS_ADMIN'),
    ('USER_ADMIN'),
    ('GENERAL')
;

INSERT INTO account_role
    (account_id, role_id)
VALUES
    (
        (SELECT id FROM accounts WHERE name = 'admin'),
        (SELECT id FROM roles WHERE name = 'SYS_ADMIN')
    ),
    (
        (SELECT id FROM accounts WHERE name = 'admin'),
        (SELECT id FROM roles WHERE name = 'USER_ADMIN')
    ),
    (
        (SELECT id FROM accounts WHERE name = 'general'),
        (SELECT id FROM roles WHERE name = 'GENERAL')
    )
;
