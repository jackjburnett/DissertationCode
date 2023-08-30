-- ActivityDetails
CREATE TABLE ActivityDetails
(
 ActivityLogID     serial NOT NULL,
 UserID            int NOT NULL,
 Start             timestamp with time zone NOT NULL,
 "End"               timestamp with time zone NOT NULL,
 ActivityIntensity decimal NOT NULL,
 Heartrate         smallint NOT NULL,
 notes             multiline NULL,
 CONSTRAINT ACTIVITY_PK PRIMARY KEY ( ActivityLogID ),
 CONSTRAINT ACTIVITY_FK FOREIGN KEY ( UserID ) 
    REFERENCES UserDetails ( UserID )
);

-- ActivityDetails Indexes
CREATE INDEX ACTIVITY_FK ON ActivityDetails
(
 UserID
);

-- AppUsage
CREATE TABLE AppUsage
(
 UsageID     serial NOT NULL,
 UserID      int NOT NULL,
 PackageName varchar(100) NOT NULL,
 AppName     varchar(50) NOT NULL,
 TimeOpened  timestamp with time zone NOT NULL,
 TimeClosed  timestamp with time zone NOT NULL,
 CONSTRAINT APP_PK PRIMARY KEY ( UsageID ),
 CONSTRAINT APP_FK FOREIGN KEY ( UserID ) 
    REFERENCES UserDetails ( UserID )
);

-- AppUsage Indexes
CREATE INDEX APP_FK ON AppUsage
(
 UserID
);

-- CommunicationLog
CREATE TABLE CommunicationLog
(
 ComLogID serial NOT NULL,
 UserID   int NOT NULL,
 CallerID int NOT NULL,
 Context  varchar(4) NOT NULL,
 Start    timestamp with time zone NOT NULL,
 "End"      timestamp with time zone NULL,
 notes    multiline NOT NULL,
 CONSTRAINT COMM_PK PRIMARY KEY ( ComLogID ),
 CONSTRAINT COMM_FK FOREIGN KEY ( UserID )
    REFERENCES UserDetails ( UserID )
);

-- CommunicationLog Indexes
CREATE INDEX COMM_FK ON CommunicationLog
(
 UserID
);

-- GPSLog
CREATE TABLE GPSLog
(
 GPSLogID  serial NOT NULL,
 UserID    serial NOT NULL,
 "Time"      timestamp with time zone NOT NULL,
 Longitude decimal NOT NULL,
 Latitude  decimal NOT NULL,
 CONSTRAINT GPS_PK PRIMARY KEY ( GPSLogID ),
 CONSTRAINT GPS_FK FOREIGN KEY ( UserID ) 
    REFERENCES UserDetails ( UserID )
);

-- GPSLog Indexes
CREATE INDEX GPS_FK ON GPSLog
(
 UserID
);

-- MSReports
CREATE TABLE MSReports
(
 ReportID  serial NOT NULL,
 UserID    int NOT NULL,
 ReportURL varchar(50) NOT NULL,
 CONSTRAINT MSR_PK PRIMARY KEY ( ReportID ),
 CONSTRAINT MSR_FK FOREIGN KEY ( UserID ) 
    REFERENCES UserDetails ( UserID )
);

-- MSReports Indexes
CREATE INDEX MSR_FK ON MSReports
(
 UserID
);

-- PhoneDetails
CREATE TABLE PhoneDetails
(
 PhoneDetailsID      serial NOT NULL,
 UserID              int NOT NULL,
 BatteryLevel        smallint NOT NULL,
 "Wi-FiConnection"     varchar(50) NULL,
 BluetoothConnection varchar(50) NULL,
 CONSTRAINT PHONE_PK PRIMARY KEY ( PhoneDetailsID ),
 CONSTRAINT PHONE_FK FOREIGN KEY ( UserID ) 
    REFERENCES UserDetails ( UserID )
);

-- PhoneDetails Indexes
CREATE INDEX PHONE_FK ON PhoneDetails
(
 UserID
);

-- UserDetails
CREATE TABLE UserDetails
(
 UserID    serial NOT NULL,
 AppKey    varchar(256) NULL,
 Sex       varchar(1) NOT NULL,
 Gender    varchar(50) NOT NULL,
 Age       smallint NOT NULL,
 ethnicity varchar(50) NOT NULL,
 CONSTRAINT USER_PK PRIMARY KEY ( UserID )
);