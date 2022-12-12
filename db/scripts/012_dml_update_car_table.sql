ALTER TABLE car ADD COLUMN model text NOT NULL;

comment on column car.model is 'Наименование модели авто';