DROP TABLE IF EXISTS link_statistic;
DROP TABLE IF EXISTS links;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100;

CREATE TABLE links (
        link_id INT8 DEFAULT nextval('global_seq'),
        active BOOLEAN DEFAULT TRUE NOT NULL,
        created_date TIMESTAMP DEFAULT now(),
        end_date TIMESTAMP NOT NULL,
        short_link VARCHAR(255) NOT NULL,
        url VARCHAR(2048) NOT NULL,
        PRIMARY KEY (link_id),
        UNIQUE (short_link, url)
);

CREATE TABLE link_statistic (
       statistic_id INT8 DEFAULT nextval('global_seq'),
       ipaddress VARCHAR(255),
       browser VARCHAR(255),
       follow_date TIMESTAMP,
       refferer VARCHAR(255),
       link_id INT8 NOT NULL,
       PRIMARY KEY (statistic_id),
       FOREIGN KEY (link_id) REFERENCES links
       ON DELETE CASCADE
);

