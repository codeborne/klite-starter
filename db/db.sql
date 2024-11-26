--substitute id=id bigint primary key
--substitute createdAt=createdAt timestamptz not null default current_timestamp
--substitute createdBy=createdBy bigint default get_app_user()

--include app_user.sql
--include change_history.sql
