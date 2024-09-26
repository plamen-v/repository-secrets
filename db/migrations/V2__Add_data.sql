
INSERT INTO records(url)
VALUES('RECORD_1');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_1'),'KEY_SECRET_1_1', '11');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_1'),'KEY_SECRET_1_2', '12');

INSERT INTO records(url)
VALUES('RECORD_2');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_2'),'KEY_SECRET_2_1', '21');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_2'),'KEY_SECRET_2_2', '22');

INSERT INTO records(url)
VALUES('RECORD_3');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_3'),'KEY_SECRET_3_1', '31');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_3'),'KEY_SECRET_3_2', '32');


INSERT INTO records(url)
VALUES('RECORD_4');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_4'),'KEY_SECRET_4_1', '41');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_4'),'KEY_SECRET_4_2', '42');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_4'),'KEY_SECRET_4_3', '43');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_4'),'KEY_SECRET_4_4', '44');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_4'),'KEY_SECRET_4_5', '45');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_4'),'KEY_SECRET_4_6', '46');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_4'),'KEY_SECRET_4_7', '47');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_4'),'KEY_SECRET_4_8', '48');

INSERT INTO records_secrets(record_id, secret_key, secret_value)
VALUES((SELECT id FROM records WHERE url = 'RECORD_4'),'KEY_SECRET_4_9', '49');
