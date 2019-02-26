create database softwareSecurity;

use softwareSecurity;

CREATE TABLE `tbl_user` 
(
	`user_id` int NOT NULL AUTO_INCREMENT,
	`username` varchar(255),
	`password` varchar(255),
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
	
	PRIMARY KEY (`request_id`)
);

CREATE TABLE `tbl_account` 
(
	`account_id` int NOT NULL AUTO_INCREMENT,
	`user_id` int,
	`account_type` int,
	`current_amount` bigint,
	`created_date` datetime,
	
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



