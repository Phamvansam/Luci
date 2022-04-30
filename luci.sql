-- Drop the database if it already exists
DROP DATABASE IF EXISTS TestingSystem;
-- Create database
CREATE DATABASE IF NOT EXISTS TestingSystem;
USE TestingSystem;

-- Create table Management
DROP TABLE IF EXISTS 	`Management`;
CREATE TABLE IF NOT EXISTS `Management` (
	id 						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`name` 					NVARCHAR(50) NOT NULL UNIQUE KEY,
    total_member			INT	UNSIGNED,
    `type`					ENUM('Technology','Administrative','Accounting') NOT NULL,
    created_date			DATETIME DEFAULT NOW()
);

-- create table: Resident
DROP TABLE IF EXISTS `Resident`;
CREATE TABLE `Resident`(
	id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username				VARCHAR(50) NOT NULL UNIQUE KEY,
    management_id 			INT UNSIGNED NOT NULL,
    FOREIGN KEY(management_id) REFERENCES Management(id)
);

-- =============================================
-- INSERT DATA 
-- =============================================
-- Add data Management
INSERT INTO Management	(	`name`		, 	total_member, 	`type`		, 		created_date) 
VALUES
						(N'Marketing'	, 		1,		'Technology'	, 		'2022-03-05'),
						(N'Sale'		, 		2,		'Administrative', 		'2022-03-05'),
						(N'Bảo vệ'		, 		3,		'Accounting'	, 		'2022-03-07'),
						(N'Nhân sự'		, 		4,		'Administrative', 		'2020-03-08'),
						(N'Kỹ thuật'	, 		5,		'Accounting'	, 		'2022-03-10'),
						(N'Tài chính'	, 		6,		'Technology'	, 		NOW()		),
						(N'Phó giám đốc', 		7,		'Accounting'	, 		NOW()		),
						(N'Giám đốc'	, 		8,		'Administrative', 		'2022-04-07'),
						(N'Thư kí'		, 		9,		'Technology'	, 		'2022-04-07'),
						(N'Bán hàng'	, 		1,		'Administrative', 		'2022-04-09');
                    
-- Add data Resident
INSERT INTO `Resident`	(	username		,	management_id	)
VALUES 					(	'dangblack'		,   	'5'			),
						(	'quanganh'		,		'1'			),
						(	'vanchien'		,		'1'			),
						(	'cocoduongqua'	,		'1'			),
						(	'doccocaubai'	,   	'2'			),
						(	'khabanh'		,   	'2'			),
						(	'huanhoahong'	,   	'2'			),
						(	'tungnui'		,   	'8'			),
						(	'duongghuu'		,   	'9'			),
						(	'vtiaccademy'	,   	'10'		);