CREATE DATABASE IF NOT EXISTS guitarshop1;
USE guitarshop1;

CREATE TABLE IF NOT EXISTS Member
(
    MemberID  INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50)  NOT NULL,
    LastName  VARCHAR(50)  NOT NULL,
    Email     VARCHAR(100) NOT NULL,
    Phone     VARCHAR(20),
    Address   TEXT
);

CREATE TABLE IF NOT EXISTS Guitar
(
    GuitarID INT PRIMARY KEY AUTO_INCREMENT,
    Brand    VARCHAR(50)    NOT NULL,
    Model    VARCHAR(50)    NOT NULL,
    Type     VARCHAR(50)    NOT NULL,
    Price    DECIMAL(10, 2) NOT NULL,
    Quantity INT            NOT NULL
);

CREATE TABLE IF NOT EXISTS ReservationAppointment
(
    AppointmentID   INT PRIMARY KEY AUTO_INCREMENT,
    GuitarID        INT,
    MemberID        INT,
    AppointmentDate DATE NOT NULL,
    AppointmentTime TIME NOT NULL,
    Description     TEXT,
    CalendarEventID VARCHAR(255),
    FOREIGN KEY (GuitarID) REFERENCES Guitar (GuitarID) ON DELETE CASCADE,
    FOREIGN KEY (MemberID) REFERENCES Member (MemberID) ON DELETE SET NULL
);


CREATE TABLE IF NOT EXISTS ReparationAppointment
(
    AppointmentID   INT PRIMARY KEY AUTO_INCREMENT,
    GuitarID        INT,
    MemberID        INT,
    AppointmentDate DATE NOT NULL,
    AppointmentTime TIME NOT NULL,
    Description     TEXT,
    CalendarEventID VARCHAR(255),
    FOREIGN KEY (GuitarID) REFERENCES Guitar (GuitarID) ON DELETE CASCADE,
    FOREIGN KEY (MemberID) REFERENCES Member (MemberID) ON DELETE SET NULL
);



CREATE TABLE IF NOT EXISTS Cases
(
    CaseID      INT PRIMARY KEY AUTO_INCREMENT,
    MemberID    INT,
    GuitarID    INT,
    CaseType    VARCHAR(50) NOT NULL,
    Description TEXT,
    FOREIGN KEY (MemberID) REFERENCES Member (MemberID) ON DELETE SET NULL,
    FOREIGN KEY (GuitarID) REFERENCES Guitar (GuitarID) ON DELETE CASCADE
);

With Java facelets xhtml injection, and the following mysql table, display the ShopOwnerAvailability in calendar in a bootstrap page, with google calendar api. Implement all the code for that with the CalendarBean class, Calendar class , calendar.xhtml and anything else needed.

    CREATE TABLE IF NOT EXISTS ShopOwnerAvailability
    (
    AvailabilityID INT PRIMARY KEY AUTO_INCREMENT,
    OwnerID        INT,
    Date           DATE    NOT NULL,
    IsAvailable    BOOLEAN NOT NULL,
    CalendarEventID VARCHAR(255)
    );


