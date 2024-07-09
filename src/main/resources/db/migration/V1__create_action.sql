CREATE SEQUENCE actions_sequence START WITH 1 INCREMENT 1;

CREATE TABLE actions (
    id             INTEGER                  DEFAULT NEXTVAL('actions_sequence') NOT NULL PRIMARY KEY,
    title          varchar(50)              NOT NULL,
    description    varchar(255)             NOT NULL
);

