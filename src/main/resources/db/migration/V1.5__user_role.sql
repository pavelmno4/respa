CREATE TABLE respa.user_role
(
    user_role_id  bigint,
    user_id bigint,
    role_id bigint,
    constraint user_role_pk primary key (user_role_id),
    constraint user_role_pair_keys_constraint unique (user_id, role_id)
);

COMMENT ON TABLE respa.user_role IS 'Таблица, связывающая пользователей и роли';
COMMENT ON COLUMN respa.user_role.user_role_id IS 'Идентификатор записи';
COMMENT ON COLUMN respa.user_role.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN respa.user_role.role_id IS 'Идентификатор роли';

CREATE SEQUENCE respa.user_role_seq START WITH 1 INCREMENT BY 1;