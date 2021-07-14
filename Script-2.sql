DROP TABLE IF EXISTS bank_account;
DROP TABLE IF EXISTS user_info;

CREATE TABLE USER_INFO(
user_name varchar(50) UNIQUE NOT NULL,
pass_word varchar(50) NOT NULL,
access_type varchar(10) NOT null,
first_name varchar(50),
last_name varchar(50),
address varchar(100),
phone_number varchar(15),
email varchar(75),
approval_status integer DEFAULT 0
);

INSERT INTO USER_INFO (user_name, pass_word, access_type, first_name, last_name, address, phone_number, email, approval_status)
	VALUES ('customer', 'customer', 'customer', 'customer', 'remotsuc', '123 street', '1234567890', 'a@a.com', 2),
	('employee', 'employee', 'employee', 'employee', 'eeyolpme', '234 street', '1234567891', 'b@b.com', 2),
	('admin', 'admin', 'admin', 'admin', 'nimda', '345 street', '1234567892', 'c@c.com', 2);

CREATE TABLE bank_account(
	account_id SERIAL PRIMARY KEY,
	u_name varchar(50) REFERENCES USER_INFO(user_name), 
	p_word varchar(50) NOT null, 
	has_checkings boolean DEFAULT FALSE,
	checkings_balance NUMERIC(12,2) DEFAULT 0,
	has_savings boolean DEFAULT FALSE,
	savings_balance NUMERIC(12,2) DEFAULT 0
	);

INSERT INTO BANK_ACCOUNT (u_name, p_word, has_checkings, checkings_balance)
	VALUES ('customer', 'customer', TRUE , 300);

INSERT INTO BANK_ACCOUNT (u_name, p_word, has_checkings)
	VALUES ('employee', 'employee', TRUE);


SELECT access_type, approval_status FROM user_info WHERE user_name = 'employee' AND pass_word = 'employee';

/*

CREATE OR REPLACE FUNCTION adjust_deposit_amount_insert() RETURNS TRIGGER AS 
$BODY$
BEGIN 
	UPDATE BANK_ACCOUNT SET checkings_balance =
		0 WHERE NEW.has_checkings = TRUE AND NEW.checkings_balance < 0;
	RETURN NEW;
END
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER adjust_deposit_amount_insert AFTER INSERT ON BANK_ACCOUNT
	FOR EACH ROW EXECUTE PROCEDURE adjust_deposit_amount_insert();

DELETE FROM BANK_ACCOUNT WHERE u_name = 'admin'; 

INSERT INTO BANK_ACCOUNT (u_name, p_word, has_checkings)
	VALUES ('admin', 'admin', TRUE);
	
*/
