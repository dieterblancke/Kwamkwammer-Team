CREATE TABLE movie
(
    id           bigint not null auto_increment,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp,
    name         varchar(255),
    primary key (id)
) ENGINE = InnoDB;