--changeset todos
create table todos(
  ${id},
  item text not null,
  completedAt timestamptz
);
