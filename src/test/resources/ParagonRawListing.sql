create table ParagonRawListing
(
    Mls_Number                         varchar(15)          not null
        primary key,
    _1_Bedroom___Avg_Rent_Or_Unit      int                  null,
    _1_Bedroom___Number__Baths         int                  null,
    _1_Bedroom___Number__Units         int                  null,
    _1_Bedroom___SqFt_Or_Unit          int                  null,
    _2_Bedroom___Avg_Rent_Or_Unit      int                  null,
    _2_Bedroom___Number__Baths         int                  null,
    _2_Bedroom___Number__Units         int                  null,
    _2_Bedroom___SqFt_Or_Unit          int                  null,
    _3_Bedroom___Avg_Rent_Or_Unit      int                  null,
    _3_Bedroom___Number__Baths         int                  null,
    _3_Bedroom___Number__Units         int                  null,
    _3_Bedroom___SqFt_Or_Unit          int                  null,
    _4_Plus__Bedroom___Number__Baths   int                  null,
    _4_Plus__Bedroom___Number__Units   int                  null,
    _4_Plus__Bedroom___SqFt_Or_Unit    int                  null,
    _4_Plus_Bedroom___Avg_Rent_Or_Unit int                  null,
    Acreage                            float                null,
    Acres_of_Water_Rights              float                null,
    ADA_Compliant                      varchar(10)          null,
    Address                            varchar(100)         null,
    Address_2                          varchar(50)          null,
    Address_Direction                  varchar(2)           null,
    Address_Number                     varchar(15)          null,
    Address_Search_Number              int                  null,
    Address_Street                     varchar(50)          null,
    Ag_Tax_Deferral                    varchar(10)          null,
    Agent_Hit_Count                    int                  null,
    Animal_Units_Monthly               int                  null,
    Annual_Debt_Service                float                null,
    Annual_Expenses                    float                null,
    Annual_Income_And__Expenses        varchar(10)          null,
    Annual_Vacancy                     float                null,
    APOD_Available                     varchar(10)          null,
    Area                               varchar(5)           null,
    Asking_Price                       int                  null,
    Assessment_Amt                     float                null,
    Assoc_____Trans_____Fee_Amt        float                null,
    Assoc____Trans_____Fee_Amt         float                null,
    Assoc_Fee___MoQtlyAnnu             varchar(10)          null,
    Assoc_Fee_Amt                      float                null,
    Associated_Document_Count          int                  null,
    Attached_Common_Wall               varchar(3)           null,
    Automated_Valuation                varchar(3)           null,
    Available_for_Showing              date                 null,
    Average_Monthly_CIC_Fee            float                null,
    BLM_Grazing_Rights                 varchar(10)          null,
    Branded_Virtual_Tour               varchar(1024)        null,
    Branded_Virtual_Tour_2             varchar(1024)        null,
    Branded_Virtual_Tour_3             varchar(1024)        null,
    Broker_Attrib_Contact              varchar(80)          null,
    Building_Name                      varchar(25)          null,
    CAM                                varchar(5)           null,
    CAM_Fee_Amt                        float                null,
    CAM_Or_Mgmt_Company_Name_and_Phone varchar(50)          null,
    CAM_Or_Mgt_Co                      varchar(50)          null,
    CAM_Setup_Fee                      float                null,
    CAP_RATEM                          int                  null,
    CAP_RateC                          float                null,
    CBB_Amt__or_Pct                    varchar(10)          null,
    CBB_Of                             varchar(25)          null,
    CC_Or_R_Restrictions               varchar(10)          null,
    CIC                                varchar(3)           null,
    CIC_2                              varchar(3)           null,
    CIC_2_Dues                         float                null,
    CIC_2_Other_Fee                    float                null,
    CIC_2_Setup_Fee                    float                null,
    CIC_2_Special_Assessments          float                null,
    CIC_2_Transfer_Fee                 float                null,
    CIC_3                              varchar(3)           null,
    CIC_3_Dues                         float                null,
    CIC_3_Other_Fee                    float                null,
    CIC_3_Setup_Fee                    float                null,
    CIC_3_Special_Assessments          float                null,
    CIC_3_Transfer_Fee                 float                null,
    CIC_Dues                           float                null,
    CIC_Mgmt_Company_Name_and_Phone    varchar(50)          null,
    CIC_Or_Mgt_Co                      varchar(50)          null,
    CIC2MgmtCompanyNameandPhone        varchar(50)          null,
    CIC3MgmtCompanyNameandPhone        varchar(50)          null,
    City                               varchar(50)          null,
    City_Limits                        varchar(10)          null,
    Class                              varchar(25)          null,
    Client_Hit_Count                   int                  null,
    Closing_Date                       date                 null,
    Comm_to_BB                         float                null,
    Commentary_Or_Reviews              varchar(3)           null,
    Common_Interest_Community_CIC      varchar(10)          null,
    Common_Interest_Ownership          varchar(10)          null,
    Complex_Name                       varchar(25)          null,
    Construction                       varchar(50)          null,
    Contact_Name                       varchar(25)          null,
    Contact_Phone                      varchar(25)          null,
    Contract_Date                      date                 null,
    County                             varchar(50)          null,
    Coverage                           varchar(10)          null,
    Cumulative_DOM                     int                  null,
    Cumulative_DOMLS                   int                  null,
    Current_Business_Name              varchar(25)          null,
    Current_Lease                      varchar(10)          null,
    Current_Use                        varchar(10)          null,
    Date_Available                     date                 null,
    Days_to_Contract                   int                  null,
    Deposit                            float                null,
    Doc_Timestamp                      datetime             null,
    DOM                                int                  null,
    DOMLS                              int                  null,
    Dwelling_Type                      varchar(10)          null,
    Elementary_School                  varchar(50)          null,
    ENERGY_EFFECIENCY_SCORE            varchar(5)           null,
    Energy_Efficiency_Date_Certified   varchar(5)           null,
    ENERGY_STAR_HOME_CERTIFIED         varchar(3)           null,
    ENERGY_STAR_YEAR_CERTIFIED         varchar(5)           null,
    Expiration_Date                    date                 null,
    First_Photo_Add_Timestamp          datetime             null,
    Franchise                          varchar(10)          null,
    Front_Footage                      int                  null,
    Geo_Address_Line                   varchar(255)         null,
    Geo_Latitude                       float                null,
    Geo_Longitude                      float                null,
    Geo_Match_Code                     int                  null,
    Geo_Matched_Method                 int                  null,
    Geo_Postal_Code                    varchar(10)          null,
    Geo_Primary_City                   varchar(100)         null,
    Geo_Quality                        float                null,
    Geo_Secondary_City                 varchar(100)         null,
    Geo_Subdivision                    varchar(2)           null,
    Geo_Update_Timestamp               datetime             null,
    Geo_Zoom_Level                     float                null,
    Gross_Scheduled_Income             float                null,
    Gross_Square_Footage               int                  null,
    HashTag__Buildings                 int                  null,
    HashTag__Employees                 int                  null,
    HashTag__Parking                   int                  null,
    HashTag__Parking_Spaces            int                  null,
    HashTag__Seating_Units             int                  null,
    HashTag__Units                     int                  null,
    HERS                               varchar(7)           null,
    HERS_INDEX_SCORE                   varchar(5)           null,
    HERS_RATING_YEAR                   varchar(5)           null,
    HES                                varchar(5)           null,
    HES_ASSESSMENT_YEAR                varchar(5)           null,
    HES_SCORE                          varchar(5)           null,
    High_School                        varchar(50)          null,
    Horizontal                         varchar(10)          null,
    Horses_Okay                        varchar(10)          null,
    HotSheet_Date                      date                 null,
    Housing_Cooperative                varchar(5)           null,
    How_Billed                         varchar(15)          null,
    How_CIC_2_Billed                   varchar(10)          null,
    How_CIC_3_Billed                   varchar(10)          null,
    How_Sold                           varchar(3)           null,
    HUD                                varchar(5)           null,
    HUD_Number                         varchar(25)          null,
    Income_Info_w_Or_h                 varchar(10)          null,
    Income_Producing                   varchar(10)          null,
    Individually_Metered               varchar(10)          null,
    Input_Date                         datetime             null,
    Internet                           varchar(15)          null,
    Internet_Plus                      varchar(10)          null,
    IPES                               varchar(10)          null,
    Irrigated_Acres                    float                null,
    Lease                              varchar(10)          null,
    Lease_Expires                      date                 null,
    Lease_Renewal_Option               varchar(10)          null,
    Lease_Terms                        varchar(10)          null,
    Leased                             varchar(10)          null,
    Leased_Acre                        float                null,
    LEED                               varchar(5)           null,
    LEED_RATING                        varchar(5)           null,
    LEED_SCORE                         varchar(5)           null,
    LEED_YEAR_CERTIFIED                varchar(5)           null,
    Limited_Service_Listing            varchar(7)           null,
    List_Team                          varchar(10)          null,
    Listing_Date                       date                 null,
    Listing_Type                       varchar(50)          null,
    Listing_Visibility_Type            varchar(10)          null,
    Lot_Number                         varchar(25)          null,
    LVT_Date                           date                 null,
    Main_Residence                     varchar(10)          null,
    Major_Tenants_Name                 varchar(25)          null,
    Management_Company_Name_Or_Phone   varchar(50)          null,
    Manager_Unit___Number__Baths       int                  null,
    Manager_Unit___Number__Bedrooms    int                  null,
    Manager_Unit___SqFt                int                  null,
    Map                                int                  null,
    MapCoordinate                      blob                 null,
    MH_License_Number                  varchar(25)          null,
    Middle_School                      varchar(20)          null,
    Mineral_Rights                     varchar(10)          null,
    Monthly_Asking_Price               int                  null,
    Monthly_Rent                       int                  null,
    Net_Operating_Income               float                null,
    NGBS                               varchar(5)           null,
    NGBS_RATING                        varchar(5)           null,
    NGBS_YEAR_CERTIFIED                varchar(5)           null,
    Occupied_By                        varchar(20)          null,
    Off_Market_Date                    date                 null,
    Off_Market_Status_Date             date                 null,
    Office_SQFT                        int                  null,
    Original_Price                     int                  null,
    Other_CAM_Fee                      float                null,
    Other_Fee                          float                null,
    Outbuildings                       varchar(10)          null,
    Parcel_Number                      varchar(30)          null,
    Parking_Capacity_per_Unit          int                  null,
    Permit                             varchar(10)          null,
    Personal_Property_Taxes            int                  null,
    Pet_Deposit                        float                null,
    Pet_Fee                            int                  null,
    Pets                               varchar(25)          null,
    Photo_Count                        int                  null,
    PhotoTimestamp                     datetime             null,
    Possession                         varchar(10)          null,
    Price_Date                         date                 null,
    Price_Or_Acre                      float                null,
    Price_Per_SQFT0                    float                null,
    Price_per_SQFT1                    float                null,
    Price_per_SQFT2                    float                null,
    Prop_Mgt_____Permit                varchar(5)           null,
    Property_Mgr_Permit_Number         varchar(25)          null,
    Publish_Until                      date                 null,
    Ranch_Or_Farm_Name                 varchar(25)          null,
    Real_Estate_Directory              varchar(10)          null,
    Rent_Increases                     varchar(10)          null,
    Rent_Price_per_SQFT                float                null,
    Sale_Or_Lease                      varchar(20)          null,
    Sale_Or_Rent                       varchar(1)           null,
    Search_Price                       int                  null,
    Serial_Number                      varchar(25)          null,
    Setup_Fee                          float                null,
    Showing_Instructions               varchar(50)          null,
    Sign_on_Property                   varchar(10)          null,
    Skirting                           varchar(10)          null,
    Sliding_Scale                      varchar(10)          null,
    Soil_Conservation_Plan             varchar(10)          null,
    Sold_Price                         int                  null,
    Sold_Price_Or_Acre                 float                null,
    Sold_Price_per_SqFt                float                null,
    Source_of_Acreage                  varchar(10)          null,
    Source_of_SqFt                     varchar(20)          null,
    Source_of_Zoning                   varchar(25)          null,
    Special_Assessments                float                null,
    Special_Condition_of_Sale          varchar(50)          null,
    Square_Footage                     int                  null,
    State                              varchar(2)           null,
    Status                             varchar(50)          null,
    Status_Date                        date                 null,
    Storage_SQFT                       int                  null,
    Stories                            varchar(15)          null,
    Studio___Average_Or_Unit           int                  null,
    Studio___Number__Baths             int                  null,
    Studio___Number__Units             int                  null,
    Studio___SqFt_Or_Unit              int                  null,
    Subdividable                       varchar(10)          null,
    SystemID                           int                  null,
    Tax_Database_ID                    int                  null,
    Tax_Property_ID                    varchar(50)          null,
    Taxes_Amt                          float                null,
    Tenant_Improv_____Allowance        varchar(10)          null,
    To_Show_Contact                    varchar(50)          null,
    Total_Deeded_Acres                 float                null,
    Total_Living_Space                 int                  null,
    Total_Other_Fees                   float                null,
    Total_Parking_Capacity             int                  null,
    Total_Setup_Fees                   float                null,
    Total_Special_Assessments          float                null,
    Total_Transfer_Fees                float                null,
    Trade_Name                         varchar(25)          null,
    Transfer_Fee                       float                null,
    Type                               varchar(30)          null,
    Type_of_Ownership                  varchar(10)          null,
    Type_Of_Property                   varchar(50)          null,
    UnBranded_Virtual_Tour             varchar(255)         null,
    UnBranded_Virtual_Tour_2           varchar(1024)        null,
    UnBranded_Virtual_Tour_3           varchar(1024)        null,
    Unit_Level                         varchar(10)          null,
    Unit_Number                        varchar(25)          null,
    Update_Date                        datetime             null,
    Variable_Rate                      varchar(10)          null,
    Vertical                           int                  null,
    Water_Rights                       varchar(10)          null,
    Water_Rights_Fees_Amt              float                null,
    Water_Use_Fees_Amt                 float                null,
    Width                              varchar(10)          null,
    Xstreet_Or_Directions              varchar(50)          null,
    Year_Built                         int                  null,
    Years_Owned                        int                  null,
    Zip                                varchar(20)          null,
    Zoning_Actual                      varchar(25)          null,
    Zoning_Category                    varchar(10)          null,
    User_Code                          varchar(20)          null,
    User_Code2                         varchar(20)          null,
    User_Code3                         varchar(20)          null,
    Office_Abbreviation                varchar(25)          null,
    Office_Abbreviation2               varchar(25)          null,
    Office_Abbreviation3               varchar(25)          null,
    Sell_Office_Abbreviation           varchar(25)          null,
    Sell_Office_Abbreviation2          varchar(25)          null,
    Sell_Office_Abbreviation3          varchar(25)          null,
    Sell_User_Code                     varchar(20)          null,
    Sell_User_Code2                    varchar(20)          null,
    Sell_User_Code3                    varchar(20)          null,
    NumBedrooms                        int                  null,
    BathFull                           int                  null,
    BathHalf                           int                  null,
    NumGarage                          int                  null,
    NumCarport                         int                  null,
    BedBathUpdateYN                    tinyint(1) default 0 null
)
    engine = MyISAM
    KEY_BLOCK_SIZE = 8
    row_format = COMPRESSED;

