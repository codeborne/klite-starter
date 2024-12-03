--changeset users
create table users(
  ${id},
  firstName text not null,
  lastName text not null,
  role text not null
);
