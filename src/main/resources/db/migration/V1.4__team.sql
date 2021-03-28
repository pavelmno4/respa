CREATE TABLE respa.team
(
    team_id  bigint,
    team_name    varchar(50),
    city    varchar(100),
    constraint team_pk primary key (team_id)
);

COMMENT ON TABLE respa.team IS 'Таблица с командами';
COMMENT ON COLUMN respa.team.team_id IS 'Идентификатор команды';
COMMENT ON COLUMN respa.team.team_name IS 'Название команды';
COMMENT ON COLUMN respa.team.city IS 'Город/регион команды';

CREATE SEQUENCE respa.team_seq START WITH 1 INCREMENT BY 1;