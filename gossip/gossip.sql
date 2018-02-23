CREATE TABLE t_account (
  name VARCHAR(15) NOT NULL,
  email VARCHAR(128) NOT NULL,
  password VARCHAR(32) NOT NULL,
  salt VARCHAR(256) NOT NULL,
  enabled TINYINT NOT NULL,
  PRIMARY KEY (name)
);
CREATE TABLE t_message (
    name VARCHAR(15) NOT NULL,
    time BIGINT NOT NULL,
    blabla VARCHAR(512) NOT NULL,
    FOREIGN KEY (name) REFERENCES t_account(name) 
);
CREATE TABLE t_account_role (
    name VARCHAR(15) NOT NULL,
    role VARCHAR(15) NOT NULL,
    PRIMARY KEY (name, role)
);
