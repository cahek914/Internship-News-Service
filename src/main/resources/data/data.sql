drop table if exists news;

create table if not exists news
(
    id    bigserial,
    date  timestamp,
    title varchar(255),
    text  varchar(2048),
    primary key (id)
);

drop table if exists comment;

create table if not exists comment
(
    id        bigserial,
    date      timestamp,
    text      varchar(2048),
    user_name varchar(255),
    news_id   bigint not null references news on delete cascade,
    primary key (id),
    constraint fk_comment_on_id foreign key (news_id) references news (id)
);