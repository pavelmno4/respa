CREATE TABLE respa.category
(
    category_id bigint,
    category_name varchar(50),
    ages    integer[],
    weights integer[],
    constraint category_pk primary key (category_id),
    constraint category_name_unique unique (category_name)
);

COMMENT ON TABLE respa.category IS 'Таблица, хранящая списки весовых категорий';

COMMENT ON COLUMN respa.category.category_id IS 'Уникальный идентификатор категории';
COMMENT ON COLUMN respa.category.category_name IS 'Название категории';
COMMENT ON COLUMN respa.category.ages IS 'Список возрастов';
COMMENT ON COLUMN respa.category.weights IS 'Список весов';

CREATE SEQUENCE respa.category_seq START WITH 1 INCREMENT BY 1;