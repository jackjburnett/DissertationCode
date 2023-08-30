-- AppUsage
CREATE TABLE AppUsage
(
 UsageID     int NOT NULL,
 UserID      int NOT NULL,
 PackageName varchar(100) NOT NULL,
 AppName     varchar(50) NOT NULL,
 TimeOpened  timestamp with time zone NOT NULL,
 TimeClosed  timestamp with time zone NOT NULL,
 CONSTRAINT USAGE_PK PRIMARY KEY ( UsageID ),
 CONSTRAINT USAGE_FK FOREIGN KEY ( UserID ) 
    REFERENCES UserDetails ( UserID )
);

-- AppUsage Indexes
CREATE INDEX USAGE_FK ON AppUsage
(
 UserID
);

-- UserDetails
CREATE TABLE UserDetails
(
 UserID int NOT NULL,
 AppKey varchar(256) NOT NULL,
 CONSTRAINT USER_PK PRIMARY KEY ( UserID )
);