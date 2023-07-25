create table PropertyDataTax
(
    ListingId            int unsigned not null
        primary key,
    ParcelNumber         varchar(50) null,
    TaxAnnualAmount      int null,
    TaxSpecialAssessment int null
);

