create table PropertyDataLocation
(
    ListingId            int unsigned not null
        primary key,
    City                 varchar(50)  null,
    Country              varchar(2)   null,
    CountyOrParish       varchar(50)  null,
    PostalCity           varchar(50)  null,
    PostalCode           varchar(10)  null,
    PostalCodePlus4      varchar(4)   null,
    StateOrProvince      varchar(2)   null,
    StreetAddtionalInfo  varchar(50)  null,
    StreetDirPrefix      varchar(15)  null,
    StreetDirSuffix      varchar(15)  null,
    StreetName           varchar(50)  null,
    StreetNumber         varchar(25)  null,
    StreetNumberNumeric  int          null,
    StreetSuffix         varchar(25)  null,
    StreetSuffixModifier varchar(25)  null,
    Township             varchar(50)  null,
    UnitNumber           varchar(25)  null,
    UnparsedAddress      varchar(25)  null,
    MLSAreaMajor         varchar(50)  null,
    Lattitude            float        null,
    Longitude            float        null
);

