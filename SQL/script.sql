create table bankaccounts
(
    id        bigint auto_increment
        primary key,
    number    char(9) charset utf8mb3 not null,
    client_id bigint                  not null
);

create table clients
(
    id            int auto_increment
        primary key,
    first_name    varchar(32)                 not null,
    last_name     varchar(32) charset utf8mb3 not null,
    birthdate     date                        not null,
    email_address varchar(64)                 not null,
    password      varchar(32) charset utf8mb3 not null,
    constraint email_address
        unique (email_address)
);

create table creditcards
(
    id                int auto_increment
        primary key,
    number            char(9)    not null,
    pin               char(4)    not null,
    bankaccount_id    mediumtext null,
    hasPinBeenChanged tinyint(1) not null
);

create table debitcards
(
    id                int auto_increment
        primary key,
    number            char(9)    not null,
    pin               char(4)    not null,
    bankaccount_id    mediumtext null,
    hasPinBeenChanged tinyint(1) not null
);

create table deposits
(
    id         int auto_increment
        primary key,
    amount     decimal(15, 2) not null,
    timestamp  datetime       not null,
    account_id bigint         not null
);

create table transfers
(
    id          int auto_increment
        primary key,
    amount      decimal(15, 2) not null,
    timestamp   datetime       not null,
    receiver_id bigint         not null,
    sender_id   bigint         not null
);

create table withdraws
(
    id         int auto_increment
        primary key,
    amount     decimal(15, 2) not null,
    timestamp  datetime       not null,
    account_id bigint         not null
);


