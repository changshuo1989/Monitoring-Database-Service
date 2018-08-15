-- user_types --
INSERT INTO user_types (id, value) VALUES(1, "Administrator") ON DUPLICATE KEY UPDATE    
value = "Administrator";

INSERT INTO user_types (id, value) VALUES(2, "Monitor") ON DUPLICATE KEY UPDATE    
value = "Monitor";


-- connection_types --
INSERT INTO connection_types (id, value) VALUES(1, "MySQL") ON DUPLICATE KEY UPDATE    
value = "MySQL";

INSERT INTO connection_types (id, value) VALUES(2, "PostgreSQL") ON DUPLICATE KEY UPDATE    
value = "PostgreSQL";

INSERT INTO connection_types (id, value) VALUES(3, "Redshift") ON DUPLICATE KEY UPDATE    
value = "Redshift";


-- schedule_types --
INSERT INTO schedule_types (id, value) VALUES(1, "Everyx") ON DUPLICATE KEY UPDATE    
value = "Everyx";

INSERT INTO schedule_types (id, value) VALUES(2, "Daily") ON DUPLICATE KEY UPDATE    
value = "Daily";

INSERT INTO schedule_types (id, value) VALUES(3, "Weekly") ON DUPLICATE KEY UPDATE    
value = "Weekly";

INSERT INTO schedule_types (id, value) VALUES(4, "Monthly") ON DUPLICATE KEY UPDATE    
value = "Monthly";


-- recipient_types --
INSERT INTO recipient_types (id, value) VALUES(1, "Email") ON DUPLICATE KEY UPDATE    
value = "Email";

INSERT INTO recipient_types (id, value) VALUES(2, "Datadog") ON DUPLICATE KEY UPDATE    
value = "Datadog";

INSERT INTO recipient_types (id, value) VALUES(3, "Slack") ON DUPLICATE KEY UPDATE    
value = "Slack";



-- rule_types --
INSERT INTO rule_types (id, value) VALUES(1, "Report") ON DUPLICATE KEY UPDATE    
value = "Report";

INSERT INTO rule_types (id, value) VALUES(2, "Alert") ON DUPLICATE KEY UPDATE    
value = "Alert";


--check_benchmark_types--
INSERT INTO check_benchmark_types (id, value) VALUES(1, "Numeric") ON DUPLICATE KEY UPDATE    
value = "Numeric";

INSERT INTO check_benchmark_types (id, value) VALUES(2, "String") ON DUPLICATE KEY UPDATE    
value = "String";



--check_logic_types--
INSERT INTO check_logic_types (id, value) VALUES(1, "Is all of") ON DUPLICATE KEY UPDATE    
value = "Is all of";

INSERT INTO check_logic_types (id, value) VALUES(2, "Is any of") ON DUPLICATE KEY UPDATE    
value = "Is any of";

--INSERT INTO check_logic_types (id, value) VALUES(3, "Is not any of") ON DUPLICATE KEY UPDATE    
--value = "Is not any of";



--check_conjunction_types--
INSERT INTO check_conjunction_types (id, value) VALUES(1, "AND") ON DUPLICATE KEY UPDATE    
value = "AND";

INSERT INTO check_conjunction_types (id, value) VALUES(2, "OR") ON DUPLICATE KEY UPDATE    
value = "OR";


--check_operator_types--
INSERT INTO check_operator_types (id, value) VALUES(1, "=") ON DUPLICATE KEY UPDATE    
value = "=";

INSERT INTO check_operator_types (id, value) VALUES(2, "!=") ON DUPLICATE KEY UPDATE    
value = "!=";

INSERT INTO check_operator_types (id, value) VALUES(3, ">") ON DUPLICATE KEY UPDATE    
value = ">";

INSERT INTO check_operator_types (id, value) VALUES(4, ">=") ON DUPLICATE KEY UPDATE    
value = ">=";

INSERT INTO check_operator_types (id, value) VALUES(5, "<") ON DUPLICATE KEY UPDATE    
value = "<";

INSERT INTO check_operator_types (id, value) VALUES(6, "<=") ON DUPLICATE KEY UPDATE    
value = "<=";

INSERT INTO check_operator_types (id, value) VALUES(7, "CONTAINS") ON DUPLICATE KEY UPDATE    
value = "CONTAINS";


-- rule_status --
INSERT INTO rule_status (id, value) VALUES(1, "Active") ON DUPLICATE KEY UPDATE    
value = "Active";

INSERT INTO rule_status (id, value) VALUES(2, "Inactive") ON DUPLICATE KEY UPDATE    
value = "Inactive";

INSERT INTO rule_status (id, value) VALUES(3, "Invalid") ON DUPLICATE KEY UPDATE    
value = "Invalid";





















