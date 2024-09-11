CREATE SEQUENCE IF NOT EXISTS records_id_seq;
CREATE TABLE IF NOT EXISTS records (
    id integer NOT NULL DEFAULT nextval('records_id_seq'),
    url varchar(1024) NOT NULL,
    CONSTRAINT records_pk PRIMARY KEY(id),
    UNIQUE(url)
);
ALTER SEQUENCE records_id_seq
OWNED BY records.id;

CREATE TABLE IF NOT EXISTS records_secrets (
    record_id integer NOT NULL,
    secret_key varchar(1024) NOT NULL,
    secret_value varchar(1024),
    CONSTRAINT records_secrets_pk PRIMARY KEY(record_id, secret_key),
    CONSTRAINT records_secrets_fk_records FOREIGN KEY (record_id) REFERENCES records(id)
);

