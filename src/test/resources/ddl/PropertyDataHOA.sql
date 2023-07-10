create table PropertyDataHOA
(
    ListingId                int unsigned not null
        primary key,
    AssociationYN            tinyint      not null,
    AssociationName          varchar(50)  null,
    AssocationFee            float        null,
    AssociationFeeFrequency  varchar(25)  null,
    AssociationPhone         varchar(16)  null,
    AssociationOneTimeFees   float        null,
    Association2YN           tinyint      not null,
    Association2Name         varchar(50)  null,
    Assocation2Fee           float        null,
    Association2FeeFrequency varchar(25)  null,
    Association2Phone        varchar(16)  null,
    Association2OneTimeFees  float        null,
    Association3YN           tinyint      not null,
    Association3Name         varchar(50)  null,
    Assocation3Fee           float        null,
    Association3FeeFrequency varchar(25)  null,
    Association3Phone        varchar(16)  null,
    Association3OneTimeFees  float        null
);

