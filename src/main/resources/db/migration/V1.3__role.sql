CREATE TABLE respa.role
(
    role_id bigint,
    role_name   varchar(50),
    description varchar(50),
    create_date timestamp,
    constraint role_pk primary key (role_id)
);

COMMENT ON TABLE respa.role IS 'Таблица, хранящая роли пользователей';

COMMENT ON COLUMN respa.role.role_id IS 'Уникальный идентификатор';
COMMENT ON COLUMN respa.role.role_name IS 'Название роли (модификатор доступа)';
COMMENT ON COLUMN respa.role.description IS 'Описание роли';
COMMENT ON COLUMN respa.role.create_date IS 'Дата и время создания объекта';

CREATE SEQUENCE respa.role_seq START WITH 1 INCREMENT BY 1;