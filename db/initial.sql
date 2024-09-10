CREATE SEQUENCE IF NOT EXISTS repos_id_seq;
CREATE TABLE IF NOT EXISTS repos (
    id integer NOT NULL DEFAULT nextval('repos_id_seq'),
    url varchar(1024) NOT NULL,
    CONSTRAINT repos_pk PRIMARY KEY(id),
    UNIQUE(url)
);
ALTER SEQUENCE repos_id_seq
OWNED BY repos.id;

CREATE TABLE IF NOT EXISTS repos_secrets (
    repo_id integer NOT NULL,
    secret_key varchar(1024) NOT NULL,
    secret_value varchar(1024),
    CONSTRAINT repos_secrets_pk PRIMARY KEY(repo_id, secret_key),
    CONSTRAINT repos_secrets_fk_repository FOREIGN KEY (repos_id) REFERENCES repos(id)
);

