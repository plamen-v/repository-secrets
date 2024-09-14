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
    secret_value varchar(1024) NOT NULL,
    CONSTRAINT records_secrets_pk PRIMARY KEY(record_id, secret_key),
    CONSTRAINT records_secrets_fk_records FOREIGN KEY (record_id) REFERENCES records(id)
);

INSERT INTO records(url)
VALUES('REPOSITORY_1');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_1'),'KEY_SECRET_1_1', '11');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_1'),'KEY_SECRET_1_2', '12');

INSERT INTO records(url)
VALUES('REPOSITORY_2');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_2'),'KEY_SECRET_2_1', '21');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_2'),'KEY_SECRET_2_2', '22');

INSERT INTO records(url)
VALUES('REPOSITORY_3');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_3'),'KEY_SECRET_3_1', '31');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_3'),'KEY_SECRET_3_2', '32');


INSERT INTO records(url)
VALUES('REPOSITORY_4');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_4'),'KEY_SECRET_4_1', '41');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_4'),'KEY_SECRET_4_2', '42');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_4'),'KEY_SECRET_4_3', '43');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_4'),'KEY_SECRET_4_4', '44');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_4'),'KEY_SECRET_4_5', '45');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_4'),'KEY_SECRET_4_6', '46');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_4'),'KEY_SECRET_4_7', '47');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_4'),'KEY_SECRET_4_8', '48');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'REPOSITORY_4'),'KEY_SECRET_4_9', '49');
