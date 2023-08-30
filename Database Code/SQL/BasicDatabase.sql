-- AppUsage
CREATE TABLE AppUsage
(
 UsageID     serial NOT NULL,
 PackageName varchar(100) NOT NULL,
 AppName     varchar(50) NOT NULL,
 TimeOpened  timestamp with time zone NOT NULL,
 TimeClosed  timestamp with time zone NOT NULL,
 UserID      serial NOT NULL,
 CONSTRAINT USAGE_PK PRIMARY KEY ( UsageID ),
 CONSTRAINT USAGE_FK FOREIGN KEY ( UserID ) 
    REFERENCES UserDetails ( UserID )
);

-- AppUsage Indexes
CREATE INDEX USAGE_FK ON AppUsage
(
 UserID
);

-- GPSLog
CREATE TABLE GPSLog
(
 GPSLogID  serial NOT NULL,
 "Time"      timestamp with time zone NOT NULL,
 Longitude decimal NOT NULL,
 Latitude  decimal NOT NULL,
 UserID    serial NOT NULL,
 CONSTRAINT GPS_PK PRIMARY KEY ( GPSLogID ),
 CONSTRAINT GPS_FK FOREIGN KEY ( UserID ) 
    REFERENCES UserDetails ( UserID )
);

--  GPS Indexes
CREATE INDEX GPS_FK ON GPSLog
(
 UserID
);

-- UserDetails
CREATE TABLE UserDetails
(
 UserID serial NOT NULL,
 AppKey varchar(256) NOT NULL,
 CONSTRAINT USER_PK PRIMARY KEY ( UserID )
);

-- MSReports
CREATE TABLE MSReports
(
 ReportID serial NOT NULL,
 UserID   int NOT NULL,
 "Time"     timestamp with time zone NOT NULL,
 Q1       smallint NULL,
 Q2       smallint NULL,
 Q3       smallint NULL,
 Q4       smallint NULL,
 Q5       smallint NULL,
 Q6       smallint NULL,
 Q7       smallint NULL,
 Q8       smallint NULL,
 Q9       smallint NULL,
 Q10      smallint NULL,
 Q11      smallint NULL,
 Q12      smallint NULL,
 Q13      smallint NULL,
 Q14      smallint NULL,
 Q15      smallint NULL,
 Q16      smallint NULL,
 Q17      smallint NULL,
 Q18      smallint NULL,
 CONSTRAINT REPORT_PK PRIMARY KEY ( ReportID ),
 CONSTRAINT REPORT_FK FOREIGN KEY ( UserID ) 
    REFERENCES UserDetails ( UserID )
);

-- MSReports Indexes
CREATE INDEX REPORT_FK ON MSReports
(
 UserID
);