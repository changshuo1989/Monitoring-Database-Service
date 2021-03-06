CREATE TABLE IF NOT EXISTS user_types(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS connection_types(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS schedule_types(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS recipient_types(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS rule_types(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS rule_status(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS check_benchmark_types(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS check_logic_types(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS check_conjunction_types(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS check_operator_types(
	id INTEGER PRIMARY KEY,
	value VARCHAR(64) NOT NULL
);

