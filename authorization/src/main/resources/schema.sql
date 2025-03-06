DROP TABLE IF EXISTS secure_resource;

CREATE TABLE secure_resource
(
    resource_id   VARCHAR(255) PRIMARY KEY,
    resource_name VARCHAR(255) NOT NULL,
    owner         VARCHAR(255) NOT NULL
);