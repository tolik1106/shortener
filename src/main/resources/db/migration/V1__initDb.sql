drop table if exists link_statistic;
drop table if exists links;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100;

create table links (
        link_id int8 default nextval('global_seq'),
        active boolean default true not null,
        created_date timestamp default now(),
        end_date timestamp not null,
        short_link varchar(255) not null,
        url varchar(2048) not null,
        primary key (link_id),
        unique (short_link, url)
);

create table link_statistic (
       statistic_id int8 default nextval('global_seq'),
       ipaddress varchar(255),
       browser varchar(255),
       follow_date_time timestamp,
       refferer varchar(255),
       link_id int8 not null,
       primary key (statistic_id),
       FOREIGN KEY (link_id) REFERENCES links
       ON DELETE CASCADE
);

