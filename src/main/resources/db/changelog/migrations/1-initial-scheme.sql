--liquibase formatted sql

CREATE SCHEMA IF NOT EXISTS "notification";
SET search_path TO "notification";

--changeset Andrey Antonov:1
--comment create table system_event
CREATE TABLE system_event
(
    id         BIGSERIAL PRIMARY KEY,
    entity_id  VARCHAR(256)   NOT NULL,
    type       SMALLINT       NOT NULL,
    content    VARCHAR(16384) NOT NULL,
    created_at TIMESTAMP      NOT NULL
);

CREATE INDEX ON system_event (created_at DESC);

--changeset Andrey Antonov:1
--comment create table system_event_participant
CREATE TABLE system_event_participant
(
    id              BIGSERIAL PRIMARY KEY,
    system_event_id BIGINT NOT NULL REFERENCES system_event (id) ON DELETE CASCADE,
    user_id         BIGINT NOT NULL
);

CREATE INDEX ON system_event_participant (system_event_id, user_id);

--changeset Andrey Antonov:1
--comment create table system_event_participant
CREATE TABLE sent_system_event_participant
(
    id                          BIGSERIAL PRIMARY KEY,
    system_event_participant_id BIGINT NOT NULL REFERENCES system_event_participant (id) ON DELETE CASCADE,
    sent                        BOOL   NOT NULL DEFAULT FALSE
);

