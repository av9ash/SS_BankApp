create database softwareSecurity;

use softwareSecurity;

CREATE TABLE `tbl_user` 
(
	`user_id` int NOT NULL AUTO_INCREMENT,
	`username` varchar(255) UNIQUE,
	`password` varchar(60),
	`status` int,
	`incorrect_attempts` int,
	`created_date` datetime,
	`modified_date` datetime,
	`is_external_user` int,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `tbl_event_log` 
(
	`event_id` int NOT NULL AUTO_INCREMENT,
	`event_name` varchar(255),
	`created_date` datetime,
	`event_type` int,
	
	PRIMARY KEY (`event_id`)
);

CREATE TABLE `tbl_user_profile` 
(
	`user_id` int,
	`first_name` varchar(255),
	`middle_name` varchar(255),
	`last_name` varchar(255),
	`email` varchar(255),
	`phone` varchar(15),
	`tier` int(1),
	`address1` varchar(255),
	`address2` varchar(255),
	`city` varchar(255),
	`province` varchar(255),
	`zip` int(5),
	
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `tbl_transaction` 
(
	`transaction_id` int NOT NULL AUTO_INCREMENT,
	`transaction_type` int,
	`transaction_status` int,
	`transaction_amount` int,
	`is_critical_transaction` int,
	`transaction_created_date` datetime,
	`transaction_updated_date` datetime,
	`from_account` int,
	`to_account` int,
	`transaction_approved_by` int,
	
	PRIMARY KEY (`transaction_id`)
);

CREATE TABLE `tbl_request` 
(
	`request_id` int NOT NULL AUTO_INCREMENT,
	`requested_by` int,
	`type_of_request` int,
	`request_assigned_to` int,
	`type_of_account` int,
	PRIMARY KEY (`request_id`)
);

CREATE TABLE `tbl_account` 
(
	`account_id` int NOT NULL AUTO_INCREMENT,
	`user_id` int,
	`account_type` int,
	`current_amount` bigint,
	`created_date` datetime,
	`status` int,
	
	PRIMARY KEY (`account_id`)
);

CREATE TABLE `tbl_appointment` 
(
	`appointment_id` int NOT NULL AUTO_INCREMENT,
	`appointment_user_id` int,
	`assigned_to_user_id` int,
	`created_date` datetime,
	`appointment_status` int,
	
	PRIMARY KEY (`appointment_id`)
);

CREATE TABLE `tbl_catalog` 
(
	`catalog_category_id` int,
	`catalog_category_type` int,
	`catalog_category_description` varchar(255),
	
	PRIMARY KEY (`catalog_category_id`,`catalog_category_type`)
);

ALTER TABLE `tbl_user_profile` ADD FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`);

ALTER TABLE `tbl_account` ADD FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`);

ALTER TABLE `tbl_transaction` ADD FOREIGN KEY (`from_account`) REFERENCES `tbl_account` (`account_id`);

ALTER TABLE `tbl_transaction` ADD FOREIGN KEY (`to_account`) REFERENCES `tbl_account` (`account_id`);

ALTER TABLE `tbl_transaction` ADD FOREIGN KEY (`transaction_approved_by`) REFERENCES `tbl_user` (`user_id`);

ALTER TABLE `tbl_request` ADD FOREIGN KEY (`requested_by`) REFERENCES `tbl_user` (`user_id`);

ALTER TABLE `tbl_request` ADD FOREIGN KEY (`request_assigned_to`) REFERENCES `tbl_user` (`user_id`);

ALTER TABLE `tbl_appointment` ADD FOREIGN KEY (`appointment_user_id`) REFERENCES `tbl_user` (`user_id`);

ALTER TABLE `tbl_appointment` ADD FOREIGN KEY (`assigned_to_user_id`) REFERENCES `tbl_user` (`user_id`);

ALTER TABLE tbl_request ADD transaction_id int;

ALTER TABLE `tbl_request` ADD FOREIGN KEY (`transaction_id`) REFERENCES `tbl_transaction` (`transaction_id`);

ALTER TABLE tbl_appointment ADD descript varchar(100);

ALTER TABLE tbl_account add status int;

insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(100,1,"Tier 1 Employee");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(100,2,"Tier 2 Employee");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(100,3,"Tier 3 Employee");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(100,4,"External Customer");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(100,5,"External Merchant");

insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(101,1,"Checking Account");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(101,2,"Savings Account");

insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(102,1,"Transaction In Progress");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(102,2,"Transaction Completed");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(102,3,"Transaction Declined");

insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(103,1,"Appointment Created");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(103,2,"Appointment Serviced");

insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(104,1,"Request Created");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(104,2,"Request Approved");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(104,1,"Request Declined");

insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(105,1,"RequestModule,ViewAccountModule");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(105,2,"RequestModule,ViewAccountModule");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(105,3,"RequestModule,ViewAccountModule,ViewAndModifyModule,ViewLogFile");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(105,4,"AccountTransactionModule,EmailPhoneTransactionModule,AppointmentModule,OpenAccount,BankingStatementModule");
insert into tbl_catalog(catalog_category_id,catalog_category_type,catalog_category_description) values(105,5,"AccountTransactionModule,EmailPhoneTransactionModule,AppointmentModule,OpenAccount,BankingStatementModule");
