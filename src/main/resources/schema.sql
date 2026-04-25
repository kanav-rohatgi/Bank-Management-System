CREATE DATABASE IF NOT EXISTS bankdb;
USE bankdb;

CREATE TABLE IF NOT EXISTS Customer (
    Customer_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name        VARCHAR(100),
    Address     VARCHAR(255),
    Phone       VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS Branch (
    Branch_ID   INT AUTO_INCREMENT PRIMARY KEY,
    Branch_Name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Account (
    Account_No   INT AUTO_INCREMENT PRIMARY KEY,
    Account_Type VARCHAR(50),
    Balance      DECIMAL(10,2),
    Customer_ID  INT,
    Branch_ID    INT,
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID) ON DELETE CASCADE,
    FOREIGN KEY (Branch_ID)   REFERENCES Branch(Branch_ID)     ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Transaction_Table (
    Transaction_ID INT AUTO_INCREMENT PRIMARY KEY,
    Date           DATE,
    Amount         DECIMAL(10,2),
    Type           VARCHAR(50),
    Account_No     INT,
    FOREIGN KEY (Account_No) REFERENCES Account(Account_No) ON DELETE CASCADE
);
