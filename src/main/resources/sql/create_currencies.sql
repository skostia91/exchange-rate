CREATE TABLE currencies(
                           id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                           code varchar(4) NOT NULL UNIQUE,
                           name varchar(100) NOT NULL,
                           sign varchar(6) NOT NULL UNIQUE
);