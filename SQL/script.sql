create table accounts
(
    id        bigint auto_increment
        primary key,
    number    char(9) charset utf8mb3 not null,
    name      char(16)                not null,
    client_id bigint                  not null
);

create table clients
(
    id            int auto_increment
        primary key,
    first_name    varchar(32)                 not null,
    last_name     varchar(32) charset utf8mb3 not null,
    birthdate     date                        not null,
    nif           char(9)                     not null,
    email_address varchar(64)                 not null,
    password      varchar(32) charset utf8mb3 not null,
    mobile_phone  char(9)                     not null,
    phone         char(9)                     null,
    profession    varchar(32) charset utf8mb3 null,
    constraint email_address
        unique (email_address)
);

create table creditcards
(
    id                  bigint auto_increment
        primary key,
    number              char(9)    not null,
    pin                 char(4)    not null,
    expiration_date     date       null,
    default_pin_changed tinyint(1) not null,
    account_id          mediumtext not null
);

create table debitcards
(
    id                  bigint auto_increment
        primary key,
    number              char(9)    not null,
    pin                 char(4)    not null,
    expiration_date     date       null,
    default_pin_changed tinyint(1) not null,
    account_id          mediumtext not null
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