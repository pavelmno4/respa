CREATE TABLE respa.usr
(
    user_id     bigint,
    login   varchar(50),
    password varchar(100),
    status boolean,
    last_update_date timestamp,
    constraint usr_pk primary key (user_id),
    constraint usr_login_unique unique (login)
);

COMMENT ON TABLE respa.usr IS 'Таблица, хранящая аутентификационные данные пользователей';

COMMENT ON COLUMN respa.usr.user_id IS 'Уникальный идентификатор пользователя';
COMMENT ON COLUMN respa.usr.login IS 'Логин пользователя';
COMMENT ON COLUMN respa.usr.password IS 'Пароль пользователя';
COMMENT ON COLUMN respa.usr.status IS 'Статус пользователя: активен или неактивен';
COMMENT ON COLUMN respa.usr.last_update_date IS 'Дата и время последнего изменения объекта';

CREATE SEQUENCE respa.usr_seq START WITH 1 INCREMENT BY 1;