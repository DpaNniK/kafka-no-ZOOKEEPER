CREATE TABLE IF NOT EXISTS client
(
    id      UUID NOT NULL PRIMARY KEY,
    name    TEXT NOT NULL,
    surname TEXT NOT NULL,
    city    TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS account
(
    id        UUID         NOT NULL PRIMARY KEY,
    client_id UUID         NOT NULL,
    account_type   varchar(255) NOT NULL,
    balance   numeric(16, 2),
    FOREIGN KEY (client_id) REFERENCES "client" (id)
);

CREATE TABLE IF NOT EXISTS transaction
(
    id         UUID NOT NULL PRIMARY KEY,
    account_id UUID NOT NULL,
    FOREIGN KEY (account_id) REFERENCES "account" (id)
);