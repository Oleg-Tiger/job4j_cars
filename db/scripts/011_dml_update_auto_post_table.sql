ALTER TABLE auto_post ADD COLUMN photo bytea;

comment on column auto_post.photo is 'Фото авто';