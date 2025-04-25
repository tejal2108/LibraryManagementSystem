-- show databases;
-- use newLibrary;

-- create table StudentLogin(
-- StudentUserId varchar(45),
-- StudentUserFName varchar(45),
-- StudentUserLName varchar(45),
-- password varchar(45),
-- primary key(StudentUserId));

create table login(
userid varchar(45),
userRole varchar(45),
password varchar(45),
primary key(userid));


-- create table student(
-- StudentId int,
-- SFName varchar(35),
-- SLName varchar(35),
-- SCourse varchar(30),
-- SBranch varchar(15),
-- Ssemester varchar(10),
-- primary key(StudentId));


-- create table admin(
-- AdminId int,
-- AFName varchar(35),
-- ALName varchar(35),
-- SBranch varchar(15),
-- primary key(AdminId));


-- create table book(
-- bookId varchar(15) not null,
-- bookName varchar(30),
-- bookPublisher varchar(45),
-- bookPrices varchar(30),
-- bookYear varchar(30),
-- bookStatus varchar(35),
-- bookIssueDate varchar(60),
-- bookDueDate varchar(60),
-- StudentId int,
-- AdminId int,
-- primary key(bookId));


show tables;

desc adminLogin;


