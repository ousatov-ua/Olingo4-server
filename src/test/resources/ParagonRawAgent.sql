create table ParagonRawAgent
(
    User_Code                 varchar(20)  not null
        primary key,
    Active                    varchar(1)   null,
    Goomzee                   varchar(1)   null,
    NRDS_ID                   varchar(9)   null,
    Agent_Url                 varchar(80)  null,
    Agent_Zip                 varchar(20)  null,
    Team_Name                 varchar(50)  null,
    Agent_City                varchar(50)  null,
    Agent_Type                varchar(3)   null,
    Date_Added                datetime     null,
    Agent_Email               varchar(50)  null,
    Agent_State               varchar(2)   null,
    Status_Date               date         null,
    Team_E_mail               varchar(50)  null,
    Update_Date               datetime     null,
    Agent_Status              varchar(1)   null,
    Photographer              varchar(1)   null,
    Board_User_ID             varchar(10)  null,
    Agent_Address2            varchar(50)  null,
    Agent_Mail_Zip            varchar(20)  null,
    Multiple_Logon            varchar(1)   null,
    Security_Level            int          null,
    Agent_Last_Name           varchar(50)  null,
    Agent_Mail_City           varchar(50)  null,
    Agent_Office_ID           int          null,
    Update_By_Agent           int          null,
    Agent_Birth_Date          date         null,
    Agent_First_Name          varchar(50)  null,
    Agent_Identifier          int          null,
    Agent_License_ID          varchar(25)  null,
    Agent_Logon_Name          varchar(20)  null,
    Agent_Mail_State          varchar(2)   null,
    Agent_Salutation          varchar(6)   null,
    MLStimate_Active          varchar(1)   null,
    User_Photos_Code          varchar(1)   null,
    Agent_Designation         varchar(15)  null,
    Agent_License_Date        date         null,
    Agent_Mail_Address2       varchar(50)  null,
    Agent_Phone1_Number       varchar(30)  null,
    Agent_Phone2_Number       varchar(30)  null,
    Agent_Phone3_Number       varchar(30)  null,
    Agent_Phone4_Number       varchar(30)  null,
    Agent_Phone5_Number       varchar(30)  null,
    Primary_Association       varchar(100) null,
    Agent_Middle_Initial      varchar(1)   null,
    Agent_Mail_Preference     varchar(1)   null,
    Agent_Mail_Street_Name    varchar(50)  null,
    Agent_Phone1_CountryId    varchar(10)  null,
    Agent_Phone1_Extension    varchar(5)   null,
    Agent_Phone2_CountryId    varchar(10)  null,
    Agent_Phone2_Extension    varchar(5)   null,
    Agent_Phone3_CountryId    varchar(10)  null,
    Agent_Phone3_Extension    varchar(5)   null,
    Agent_Phone4_CountryId    varchar(10)  null,
    Agent_Phone4_Extension    varchar(5)   null,
    Agent_Phone5_CountryId    blob         null,
    Agent_Phone5_Extension    varchar(5)   null,
    UPLogo_image_Timestamp    datetime     null,
    UPAgent_image_Timestamp   datetime     null,
    Agent_Phone1_Description  varchar(10)  null,
    Agent_Phone2_Description  varchar(10)  null,
    Agent_Phone3_Description  varchar(10)  null,
    Agent_Phone4_Description  varchar(10)  null,
    Agent_Phone5_Description  varchar(10)  null,
    State_Agent_Is_Member_Of  varchar(2)   null,
    Agent_Address_Street_Name varchar(50)  null
);
