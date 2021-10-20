create table socks
(
    id         bigserial not null,
    color      varchar(255),
    cottonpart int4,
    quantity   int4,
    primary key (id)
)