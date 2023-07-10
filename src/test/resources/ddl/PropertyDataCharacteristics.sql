create table PropertyDataCharacteristics
(
    ListingId            int unsigned not null
        primary key,
    LandLeaseYN          tinyint(1)   null,
    LeaseTerm            varchar(25)  null,
    LotSizeArea          float        null,
    LotSizeUnits         varchar(25)  null,
    NumberOfUnitsTotal   int          null,
    UnitNumber           varchar(25)  null,
    ElementarySchool     varchar(50)  null,
    MiddleOrJuniorSchool varchar(50)  null,
    HighSchool           varchar(50)  null,
    Horses               tinyint      null,
    HUDOwned             tinyint      null,
    TaxAnnualAmount      float        null,
    Directions           varchar(255) null,
    Zoning               varchar(25)  null
);

