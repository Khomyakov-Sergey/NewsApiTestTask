drop table if exists comments;
drop table if exists news;

create table news
(
    news_id    bigint not null
        primary key,
    news_created_at  timestamp,
    news_text  text,
    news_title varchar(255)

);

create index concurrently "full_text_search_index"
    on news using btree (news_title, news_text);


create table comments
(
    comment_id       bigint not null
        primary key,
    comment_created_at     timestamp,
    comment_text     varchar(255) not null,
    comment_author varchar(255) not null,
    news_id  bigint,
    foreign key (news_id) references news(news_id)
);

insert into news(news_id, news_created_at, news_title, news_text)
VALUES (1, '2022-07-30 13:34:32.000000', 'Test title #1', 'Test title #1');
insert into news(news_id, news_created_at, news_title, news_text)
VALUES (2, '2022-07-31 11:34:52.403000', 'Test title #2', 'Test title #2');

insert into comments(comment_id, comment_created_at, comment_text, comment_author, news_id)
VALUES (1, '2022-07-30 14:34:52.403000', 'Test comment #1', 'Test author #1', 1);
insert into comments(comment_id, comment_created_at, comment_text, comment_author, news_id)
VALUES (2, '2022-07-30 14:35:10.403000', 'Test comment #2', 'Test author #2', 1);
insert into comments(comment_id, comment_created_at, comment_text, comment_author, news_id)
VALUES (3, '2022-07-31 14:25:11.403000', 'Test comment #3', 'Test author #3', 2);

