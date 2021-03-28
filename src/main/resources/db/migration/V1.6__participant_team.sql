CREATE TABLE respa.participant_team
(
    participant_team_id bigint,
    participant_id  bigint,
    team_id bigint,
    constraint participant_team_pk primary key (participant_team_id),
    constraint participant_team_pair_keys_constraint unique (participant_id, team_id)
);

COMMENT ON TABLE respa.participant_team IS 'Таблица, связывающая участников и команды';
COMMENT ON COLUMN respa.participant_team.participant_team_id IS 'Идентификатор записи';
COMMENT ON COLUMN respa.participant_team.participant_id IS 'Идентификатор участника';
COMMENT ON COLUMN respa.participant_team.team_id IS 'Идентификатор команды';

CREATE SEQUENCE respa.participant_team_seq START WITH 1 INCREMENT BY 1;