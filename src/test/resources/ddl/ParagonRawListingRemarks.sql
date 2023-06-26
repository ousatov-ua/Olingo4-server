create table ParagonRawListingRemarks
(
    Mls_Number              varchar(15)   not null
        primary key,
    Extended_Public_Remarks varchar(4000) null,
    Private_Remarks         varchar(4000) null,
    Public_Remarks          varchar(512)  null,
    Syndication_Remarks     varchar(4000) null,
    SystemId                int unsigned  null
)
    engine = MyISAM
    KEY_BLOCK_SIZE = 8
    row_format = COMPRESSED;

