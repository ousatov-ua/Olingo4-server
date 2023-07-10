create table PropertyDataStructure
(
    ListingId             int unsigned not null
        primary key,
    BathroomsFull         int          null,
    BathroomsPartial      int          null,
    BathroomsTotalInteger int          null,
    BedroomsTotal         int          null,
    GarageSpaces          float        null,
    GarageYN              tinyint(1)   null,
    Levels                json         null,
    LivingArea            float        null,
    LivingAreaUnits       varchar(25)  null,
    Make                  varchar(50)  null,
    MobileLength          int          null,
    MobileWidth           int          null,
    Model                 varchar(50)  null,
    PropertyAttachedYN    tinyint(1)   null,
    Stories               int          null,
    StoriesTotal          int          null,
    YearBuilt             int          null
);

