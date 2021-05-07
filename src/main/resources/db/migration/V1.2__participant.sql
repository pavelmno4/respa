CREATE TABLE respa.participant
(
    participant_id  bigint,
    first_name  varchar(100),
    last_name   varchar(100),
    birth_year  int,
    gender varchar(1),
    team_id    bigint,
    weight  real,
    weight_category int,
    constraint participant_pk primary key (participant_id)
);

COMMENT ON TABLE respa.participant IS 'Таблица, хранящая данные участников';

COMMENT ON COLUMN respa.participant.participant_id IS 'Уникальный идентификатор участника';
COMMENT ON COLUMN respa.participant.first_name IS 'Имя участника';
COMMENT ON COLUMN respa.participant.last_name IS 'Фамилия участника';
COMMENT ON COLUMN respa.participant.birth_year IS 'Год рождения';
COMMENT ON COLUMN respa.participant.gender IS 'Пол участника';
COMMENT ON COLUMN respa.participant.team_id IS 'Идентификатор команды';
COMMENT ON COLUMN respa.participant.weight IS 'Вес';
COMMENT ON COLUMN respa.participant.weight_category IS 'Весовая категория';

CREATE SEQUENCE respa.participant_seq START WITH 1 INCREMENT BY 1;