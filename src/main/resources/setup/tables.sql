create table public."user"
(
    "id"         UUID    not null default gen_random_uuid(),
    "first_name" varchar not null,
    "last_name"  varchar not null,
    "email"      varchar not null,
    "password"   varchar not null,
    "created_at" date    not null default now(),
    "updated_at" date    not null default now(),
    primary key ("id")
);

create table public."companyId"
(
    "id"         uuid    not null default gen_random_uuid(),
    "name"       varchar not null,
    "siren"      varchar not null,
    "created_at" date    not null default now(),
    "updated_at" date    not null default now(),
    primary key ("id")
);

create table public."card"
(
    "id"         uuid    not null default gen_random_uuid(),
    "user_id"    uuid    not null references "user" (id),
    "company_id" uuid    not null references "companyId" (id),
    "amount"     integer not null,
    "expire_at"  date    not null,
    "created_at" date    not null default now(),
    "updated_at" date    not null default now(),
    primary key ("id")
);