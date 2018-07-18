--
-- Create Tables
--
CREATE TABLE tblConfig (
	config_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	config_key TEXT NOT NULL,
	config_value TEXT NOT NULL
	);

CREATE TABLE tblPolicy (
	policy_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	policy_insured TEXT NOT NULL,
	policy_insurance_number TEXT NOT NULL,
	policy_validity TEXT NOT NULL,
	policy_expires TEXT NOT NULL 
	);
