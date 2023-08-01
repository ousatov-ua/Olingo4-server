create table Lookups
(
    LookupIds   int auto_increment
        primary key,
    Lookups     varchar(255) null,
    LookupField varchar(255) null,
    Value       bigint
);

