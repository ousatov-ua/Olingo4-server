create table MemberData
(
    MemberKey                   varchar(255) not null
        primary key,
    JobTitle                    varchar(50)  null,
    LastLoginTimestamp          datetime     null,
    MemberAOR                   varchar(50)  null,
    MemberAORMlsId              varchar(25)  null,
    MemberAORkey                varchar(255) null,
    MemberAddress1              varchar(50)  null,
    MemberAddress2              varchar(50)  null,
    MemberAssociationComments   varchar(500) null,
    MemberCarrierRoute          varchar(9)   null,
    MemberCity                  varchar(50)  null,
    MemberCountry               varchar(2)   null,
    MemberCountyOrParish        varchar(50)  null,
    MemberDesignation           json         null,
    MemberDirectPhone           varchar(16)  null,
    MemberEmail                 varchar(80)  null,
    MemberFax                   varchar(16)  null,
    MemberFirstName             varchar(50)  null,
    MemberFullName              varchar(150) null,
    MemberHomePhone             varchar(16)  null,
    MemberIsAssistantTo         varchar(50)  null,
    MemberLanguages             json         null,
    MemberLastName              varchar(50)  null,
    MemberLoginId               varchar(25)  null,
    MemberMiddleName            varchar(50)  null,
    MemberMlsAccessYN           tinyint(1)   null,
    MemberMlsId                 varchar(25)  null,
    MemberMlsSecurityClass      varchar(25)  null,
    MemberMobilePhone           varchar(16)  null,
    MemberNamePrefix            varchar(10)  null,
    MemberNameSuffix            varchar(10)  null,
    MemberNationalAssociationId varchar(25)  null,
    MemberNickname              varchar(50)  null,
    MemberOfficePhone           varchar(16)  null,
    MemberOfficePhoneExt        varchar(10)  null,
    MemberOtherPhoneType        varchar(25)  null,
    MemberPager                 varchar(16)  null,
    MemberPassword              varchar(25)  null,
    MemberPhoneTTYTDD           varchar(16)  null,
    MemberPostalCode            varchar(10)  null,
    MemberPostalCodePlus4       varchar(4)   null,
    MemberPreferredPhone        varchar(16)  null,
    MemberPreferredPhoneExt     varchar(10)  null,
    MemberStateLicense          varchar(50)  null,
    MemberStateLicenseState     varchar(2)   null,
    MemberStateOrProvince       varchar(2)   null,
    MemberStatus                varchar(25)  null,
    MemberTollFreePhone         varchar(16)  null,
    MemberType                  varchar(50)  null,
    MemberVoiceMail             varchar(16)  null,
    MemberVoiceMailExt          varchar(10)  null,
    ModificationTimestamp       datetime     null,
    OfficeKey                   varchar(255) null,
    OfficeMlsId                 varchar(25)  null,
    OfficeName                  varchar(255) null,
    OriginalEntryTimestamp      datetime     null,
    OriginatingSystemID         varchar(25)  null,
    OriginatingSystemMemberKey  varchar(255) null,
    OriginatingSystemName       varchar(255) null,
    SocialMediaType             varchar(25)  null,
    SourceSystemID              varchar(25)  null,
    SourceSystemMemberKey       varchar(255) null,
    SourceSystemName            varchar(255) null,
    SyndicateTo                 json         null,
    Office                      blob         null,
    OriginatingSystem           blob         null,
    SourceSystem                blob         null,
    MemberOtherPhone            blob         null,
    MemberSocialMedia           blob         null,
    HistoryTransactional        blob         null,
    Media                       blob         null
);

