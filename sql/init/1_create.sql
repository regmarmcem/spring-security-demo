CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    email VARCHAR(320),
    name VARCHAR(30),
    password VARCHAR(100)
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE account_role (
    account_id INT REFERENCES accounts(id),
    role_id INT REFERENCES roles(id),
    PRIMARY KEY (account_id, role_id)
);

