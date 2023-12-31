create table PropertyDataListing
(
    ListingId                           int unsigned  not null
        primary key,
    ListingAgreement                    varchar(50)   null,
    ListingService                      varchar(25)   null,
    MlsStatus                           varchar(50)   null,
    OriginatingSystemKey                varchar(255)  null,
    OriginatingSystemName               varchar(255)  null,
    SourceSystemKey                     varchar(255)  null,
    SourceSystemName                    varchar(255)  null,
    StandardStatus                      varchar(25)   null,
    BuyerAgencyCompensation             varchar(25)   null,
    BuyerAgencyCompensationType         varchar(25)   null,
    DualVariableCompensationYN          tinyint(1)    null,
    CancellationDate                    date          null,
    CloseDate                           date          null,
    ContingentDate                      date          null,
    ContractStatusChangeDate            date          null,
    ExpirationDate                      date          null,
    ListingContractDate                 date          null,
    ModificationTimestamp               datetime      null,
    OffMarketDate                       date          null,
    OnMarketDate                        date          null,
    OriginalEntryTimestamp              datetime      null,
    PurchaseContractDate                date          null,
    StatusChangeTimestamp               datetime      null,
    WithdrawnDate                       date          null,
    InternetAddressDisplayYN            tinyint(1)    null,
    InternetAutomatedValuationDisplayYN tinyint(1)    null,
    InternetConsumerCommentYN           tinyint(1)    null,
    InternetEntireListingDisplayYN      tinyint(1)    null,
    PhotosChangeTimestamp               datetime      null,
    PhotosCount                         int           null,
    ClosePrice                          float         null,
    ListPrice                           float         null,
    OriginalListPrice                   float         null,
    PrivateRemarks                      varchar(4000) null,
    PublicRemarks                       varchar(4000) null,
    SyndicationRemarks                  varchar(4000) null,
    ShowingContactPhone                 varchar(16)   null,
    ShowingContactPhoneExt              varchar(10)   null,
    ShowingInstructions                 varchar(20)   null,
    BuyerAgentKey                       varchar(255)  null,
    CoBuyerAgentKey                     varchar(255)  null,
    ListAgentKey                        varchar(255)  null,
    CoListAgentKey                      varchar(255)  null,
    BuyerOfficeKey                      varchar(255)  null,
    CoBuyerOfficeKey                    varchar(255)  null,
    ListOfficeKey                       varchar(255)  null,
    CoListOfficeKey                     varchar(255)  null,
    PropertyType                        varchar(4)    null,
    PropertySubType                     varchar(50)   null,
    DaysOnMarket                        int           null,
    CumulativeDaysOnMarket              int           null,
    DocumentsChangeTimestamp            timestamp     null,
    Possession                          varchar(255)  null,
    SpecialListingConditions            json          null
);

