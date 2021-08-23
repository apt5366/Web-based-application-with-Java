--
--  Create a table to hold customers!
create table public.album (
    album_id INTEGER PRIMARY KEY,
    title character varying,
--     artist character varying,
--     artist character varying  FOREIGN KEY REFERENCES artist,
artist_id INTEGER,

    date_released TIMESTAMP WITH TIME ZONE ,
    genre character varying,
    no_of_tracks INTEGER,
    price numeric
);

drop sequence public.album_id_seq;

create sequence public.album_id_seq
    increment by 100
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 100;


--
--  Create a table to hold artists!
create table public.artist (
    artist_id INTEGER PRIMARY KEY,
    artist_name character varying
);

drop sequence public.artist_id_seq;

create sequence public.artist_id_seq
    increment by 100
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 100;



--
--  Create a table to hold users!
create table public.application_user (
    user_id INTEGER PRIMARY KEY,
    username character  varying ,
    password character varying,
    is_admin boolean
);

drop sequence public.application_user_id_seq;

create sequence public.application_user_id_seq
    increment by 100
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 100;