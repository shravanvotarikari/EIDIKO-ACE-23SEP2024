-- Create the sequence for UMRN generation
CREATE SEQUENCE UMRN_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- Create the table MANDATE
CREATE TABLE mandate (
    UMRN VARCHAR2(20) PRIMARY KEY,
    Created_ON DATE NOT NULL,
    SponsorBankCode VARCHAR2(20) NOT NULL,
    UtilityCode VARCHAR2(20) NOT NULL,
    CorporateName VARCHAR2(100) NOT NULL,
    ToDebit VARCHAR2(20) NOT NULL,
    BankAccount VARCHAR2(20) NOT NULL,
    WithBank VARCHAR2(100) NOT NULL,
    IFSC_MICR VARCHAR2(20) NOT NULL,
    Amount NUMBER(15, 2) NOT NULL,
    Reference1 VARCHAR2(200),
    Reference2 VARCHAR2(200),
    Frequency VARCHAR2(20) NOT NULL,
    DebitType VARCHAR2(20) NOT NULL,
    Period VARCHAR2(10) NOT NULL,
    PhoneNumber VARCHAR2(15),
    EmailID VARCHAR2(100),
    Signature VARCHAR2(20)
);

-- Create the trigger to automatically populate UMRN
create or replace TRIGGER TRG_GENERATE_UMRN
BEFORE INSERT ON mandate
FOR EACH ROW
BEGIN
    :NEW.UMRN := LPAD(UMRN_SEQ.NEXTVAL, 20, '0');
end;
/

-----------------------------------------------------------------------------------------------------------------------------

-- Create the sequence for UIDAI id generation
CREATE SEQUENCE UIDAI_ID_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


-- Create the table UIDAI
CREATE TABLE UIDAI (
    UIDAI_ID VARCHAR2(50) PRIMARY KEY,
    Aadhaar VARCHAR2(12) NOT NULL,
    PhoneNumber VARCHAR2(10) NOT NULL
);

-- Create the trigger to automatically populate UIDAI_ID
create or replace TRIGGER TRG_GENERATE_UIDAI_ID
BEFORE INSERT ON UIDAI
FOR EACH ROW
BEGIN
    :NEW.UIDAI_ID := UIDAI_ID_SEQ.NEXTVAL;
end;
/

---------------------------------------------------------------------------------------------------------------------------------------



-- Create the sequence for CBS_ID generation
CREATE SEQUENCE CBS_ID_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


-- Create the table CBS_FINACLE
CREATE TABLE CBS_FINACLE (
    CBS_ID VARCHAR2(50) PRIMARY KEY,
    BankAccount VARCHAR2(20) NOT NULL,
    IFSC_MICR VARCHAR2(10) NOT NULL
);



-- Create the trigger to automatically populate CBS_ID
create or replace TRIGGER TRG_GENERATE_CBS_ID
BEFORE INSERT ON CBS_FINACLE
FOR EACH ROW
BEGIN
    :NEW.CBS_ID := CBS_ID_SEQ.NEXTVAL;
end;
/


-------------------------------------------------------------------------------------------------------------------------------------

-- Create the sequence for STATUS_ID generation
CREATE SEQUENCE STATUS_ID_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


-- Create the table Mandate_Status
CREATE TABLE MANDATE_STATUS (
    STATUS_ID VARCHAR2(50) PRIMARY KEY,
    EKYC VARCHAR2(15) NOT NULL,
    ACC_VALIDATION VARCHAR2(15) NOT NULL,
    MANDATE_GENERATION VARCHAR2(15) NOT NULL
);


-- Create the trigger to automatically populate CBS_ID
create or replace TRIGGER TRG_GENERATE_STATUS_ID
BEFORE INSERT ON MANDATE_STATUS
FOR EACH ROW
BEGIN
    :NEW.STATUS_ID := STATUS_ID_SEQ.NEXTVAL;
end;
/









