drop table if exists comments;
drop table if exists news;

create sequence seq_news increment by 1 start with 1;
create table news
(
    news_id    bigint not null default nextval('seq_news')
        primary key,
    news_created_at  timestamp,
    news_text  text not null,
    news_title varchar(255) not null

);
ALTER SEQUENCE seq_news OWNED BY news_test_db.public.news.news_id;

create index concurrently "full_text_search_index"
    on news using btree (news_title, news_text);

create sequence seq_comment increment by 1 start with 1;

create table comments
(
    comment_id       bigint not null default nextval('seq_comment')
        primary key,
    comment_created_at     timestamp,
    comment_text     varchar(255) not null,
    comment_author varchar(255) not null,
    news_id  bigint,
    foreign key (news_id) references news(news_id)
);

ALTER SEQUENCE seq_comment OWNED BY news_test_db.public.comments.comment_id;

insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-30 13:34:32.000000', 'Test title #1', 'Test text #1');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-31 11:34:52.403000', 'Test title #2', 'Test text #2');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-31 19:38:32.000000', 'Test title #3', 'Test text #3');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-31 20:14:52.403000', 'Test title #4', 'Test text #4');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-31 21:04:55.423000', 'Test title #5', 'Test text #5');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-31 22:11:33.000000', 'Test title #6', 'Test text #6');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-31 22:25:52.343000', 'Test title #7', 'Test text #7');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-31 22:43:39.000000', 'Test title #8', 'Test text #8');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-31 23:01:42.403000', 'Test title #9', 'Test text #9');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-07-31 23:29:15.423000', 'Test title #10', 'Test text #10');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 07:54:32.000000', 'Test title #11', 'Test text #11');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 08:04:55.403000', 'Test title #12', 'Test text #12');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 08:18:33.000000', 'Test title #13', 'Test text #13');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 08:54:12.403000', 'Test title #14', 'Test text #14');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 09:23:45.423000', 'Test title #15', 'Test text #15');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 09:44:53.000000', 'Test title #16', 'Test text #16');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 10:15:38.343000', 'Test title #17', 'Test text #17');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 11:23:39.000000', 'Test title #18', 'Test text #18');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 11:36:49.403000', 'Test title #19', 'Test text #19');
insert into news(news_created_at, news_title, news_text)
VALUES ('2022-08-01 12:09:35.423000', 'Test title #20', 'Test text #20');


insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:34:52.403000', 'Test comment #1', 'Test author #1', 1);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:35:10.403000', 'Test comment #2', 'Test author #2', 1);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:36:11.403000', 'Test comment #3', 'Test author #3', 1);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:36:52.403000', 'Test comment #4', 'Test author #4', 1);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:37:10.403000', 'Test comment #5', 'Test author #5', 1);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:38:11.403000', 'Test comment #6', 'Test author #6', 1);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:38:52.403000', 'Test comment #7', 'Test author #7', 1);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:39:10.403000', 'Test comment #8', 'Test author #8', 1);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:39:11.403000', 'Test comment #9', 'Test author #9', 1);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-30 14:39:52.403000', 'Test comment #10', 'Test author #10', 1);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:34:52.403000', 'Test comment #11', 'Test author #11', 2);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:35:10.403000', 'Test comment #12', 'Test author #12', 2);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:36:11.403000', 'Test comment #13', 'Test author #13', 2);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:36:52.403000', 'Test comment #14', 'Test author #14', 2);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:37:10.403000', 'Test comment #15', 'Test author #15', 2);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:38:11.403000', 'Test comment #16', 'Test author #16', 2);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:38:52.403000', 'Test comment #17', 'Test author #17', 2);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:39:10.403000', 'Test comment #18', 'Test author #18', 2);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:39:11.403000', 'Test comment #19', 'Test author #19', 2);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 14:39:52.403000', 'Test comment #20', 'Test author #20', 2);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:44:52.403000', 'Test comment #21', 'Test author #21', 3);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:45:10.403000', 'Test comment #22', 'Test author #22', 3);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:46:11.403000', 'Test comment #23', 'Test author #23', 3);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:46:52.403000', 'Test comment #24', 'Test author #14', 3);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:47:10.403000', 'Test comment #25', 'Test author #15', 3);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:48:11.403000', 'Test comment #26', 'Test author #26', 3);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:48:52.403000', 'Test comment #27', 'Test author #27', 3);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:49:10.403000', 'Test comment #28', 'Test author #18', 3);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:49:11.403000', 'Test comment #29', 'Test author #29', 3);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 19:49:52.403000', 'Test comment #30', 'Test author #20', 3);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:14:52.403000', 'Test comment #31', 'Test author #21', 4);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:15:10.403000', 'Test comment #32', 'Test author #22', 4);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:16:11.403000', 'Test comment #33', 'Test author #23', 4);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:16:52.403000', 'Test comment #34', 'Test author #24', 4);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:17:10.403000', 'Test comment #35', 'Test author #25', 4);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:18:11.403000', 'Test comment #36', 'Test author #26', 4);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:18:52.403000', 'Test comment #37', 'Test author #27', 4);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:19:10.403000', 'Test comment #38', 'Test author #28', 4);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:19:11.403000', 'Test comment #39', 'Test author #29', 4);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 20:19:52.403000', 'Test comment #40', 'Test author #30', 4);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:04:55.403000', 'Test comment #41', 'Test author #31', 5);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:04:58.403000', 'Test comment #42', 'Test author #32', 5);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:06:11.403000', 'Test comment #43', 'Test author #33', 5);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:06:52.403000', 'Test comment #44', 'Test author #34', 5);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:07:10.403000', 'Test comment #45', 'Test author #35', 5);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:08:11.403000', 'Test comment #46', 'Test author #36', 5);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:08:52.403000', 'Test comment #47', 'Test author #37', 5);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:09:10.403000', 'Test comment #48', 'Test author #38', 5);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:09:11.403000', 'Test comment #49', 'Test author #39', 5);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 21:09:52.403000', 'Test comment #50', 'Test author #40', 5);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:14:55.403000', 'Test comment #51', 'Test author #41', 6);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:14:58.403000', 'Test comment #52', 'Test author #42', 6);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:16:11.403000', 'Test comment #53', 'Test author #43', 6);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:16:52.403000', 'Test comment #54', 'Test author #44', 6);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:17:10.403000', 'Test comment #55', 'Test author #45', 6);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:18:11.403000', 'Test comment #56', 'Test author #46', 6);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:18:52.403000', 'Test comment #57', 'Test author #47', 6);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:19:10.403000', 'Test comment #58', 'Test author #48', 6);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:19:11.403000', 'Test comment #59', 'Test author #49', 6);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:19:52.403000', 'Test comment #60', 'Test author #50', 6);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:26:55.403000', 'Test comment #61', 'Test author #41', 7);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:26:58.403000', 'Test comment #62', 'Test author #42', 7);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:27:11.403000', 'Test comment #63', 'Test author #43', 7);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:27:52.403000', 'Test comment #64', 'Test author #44', 7);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:28:10.403000', 'Test comment #65', 'Test author #45', 7);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:28:11.403000', 'Test comment #66', 'Test author #46', 7);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:28:52.403000', 'Test comment #67', 'Test author #47', 7);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:29:10.403000', 'Test comment #68', 'Test author #48', 7);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:29:11.403000', 'Test comment #69', 'Test author #49', 7);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:19:52.403000', 'Test comment #70', 'Test author #50', 7);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:46:55.403000', 'Test comment #71', 'Test author #51', 8);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:46:58.403000', 'Test comment #72', 'Test author #52', 8);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:47:11.403000', 'Test comment #73', 'Test author #53', 8);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:47:52.403000', 'Test comment #74', 'Test author #54', 8);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:48:10.403000', 'Test comment #75', 'Test author #55', 8);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:48:11.403000', 'Test comment #76', 'Test author #56', 8);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:48:52.403000', 'Test comment #77', 'Test author #57', 8);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:49:10.403000', 'Test comment #78', 'Test author #58', 8);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:49:11.403000', 'Test comment #79', 'Test author #59', 8);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 22:49:52.403000', 'Test comment #80', 'Test author #60', 8);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:06:55.403000', 'Test comment #81', 'Test author #51', 9);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:06:58.403000', 'Test comment #82', 'Test author #52', 9);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:07:11.403000', 'Test comment #83', 'Test author #53', 9);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:07:52.403000', 'Test comment #84', 'Test author #54', 9);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:08:10.403000', 'Test comment #85', 'Test author #55', 9);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:08:11.403000', 'Test comment #86', 'Test author #56', 9);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:08:52.403000', 'Test comment #87', 'Test author #57', 9);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:09:10.403000', 'Test comment #88', 'Test author #58', 9);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:09:11.403000', 'Test comment #89', 'Test author #59', 9);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:09:52.403000', 'Test comment #90', 'Test author #60', 9);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:36:55.403000', 'Test comment #91', 'Test author #61', 10);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:36:58.403000', 'Test comment #92', 'Test author #62', 10);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:37:11.403000', 'Test comment #93', 'Test author #63', 10);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:37:52.403000', 'Test comment #94', 'Test author #64', 10);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:38:10.403000', 'Test comment #95', 'Test author #65', 10);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:38:11.403000', 'Test comment #96', 'Test author #66', 10);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:38:52.403000', 'Test comment #97', 'Test author #67', 10);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:39:10.403000', 'Test comment #98', 'Test author #68', 10);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:39:11.403000', 'Test comment #99', 'Test author #69', 10);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-07-31 23:39:52.403000', 'Test comment #100', 'Test author #60', 10);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:56:55.403000', 'Test comment #101', 'Test author #61', 11);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:56:58.403000', 'Test comment #102', 'Test author #62', 11);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:57:11.403000', 'Test comment #103', 'Test author #63', 11);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:57:52.403000', 'Test comment #104', 'Test author #64', 11);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:58:10.403000', 'Test comment #105', 'Test author #65', 11);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:58:11.403000', 'Test comment #106', 'Test author #66', 11);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:58:52.403000', 'Test comment #107', 'Test author #67', 11);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:59:10.403000', 'Test comment #108', 'Test author #68', 11);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:59:11.403000', 'Test comment #109', 'Test author #69', 11);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 07:59:52.403000', 'Test comment #110', 'Test author #70', 11);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:06:55.403000', 'Test comment #111', 'Test author #71', 12);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:06:58.403000', 'Test comment #112', 'Test author #72', 12);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:07:11.403000', 'Test comment #113', 'Test author #73', 12);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:07:52.403000', 'Test comment #114', 'Test author #74', 12);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:08:10.403000', 'Test comment #115', 'Test author #75', 12);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:08:11.403000', 'Test comment #116', 'Test author #76', 12);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:08:52.403000', 'Test comment #117', 'Test author #77', 12);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:09:10.403000', 'Test comment #118', 'Test author #78', 12);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:09:11.403000', 'Test comment #119', 'Test author #79', 12);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:09:52.403000', 'Test comment #120', 'Test author #80', 12);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:26:55.403000', 'Test comment #121', 'Test author #71', 13);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:26:58.403000', 'Test comment #122', 'Test author #72', 13);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:27:11.403000', 'Test comment #123', 'Test author #73', 13);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:27:52.403000', 'Test comment #124', 'Test author #74', 13);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:28:10.403000', 'Test comment #125', 'Test author #75', 13);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:28:11.403000', 'Test comment #126', 'Test author #76', 13);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:28:52.403000', 'Test comment #127', 'Test author #77', 13);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:29:10.403000', 'Test comment #128', 'Test author #78', 13);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:29:11.403000', 'Test comment #129', 'Test author #79', 13);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:29:52.403000', 'Test comment #130', 'Test author #80', 13);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:56:55.403000', 'Test comment #131', 'Test author #81', 14);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:56:58.403000', 'Test comment #132', 'Test author #82', 14);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:57:11.403000', 'Test comment #133', 'Test author #83', 14);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:57:52.403000', 'Test comment #134', 'Test author #84', 14);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:58:10.403000', 'Test comment #135', 'Test author #85', 14);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:58:11.403000', 'Test comment #136', 'Test author #86', 14);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:58:52.403000', 'Test comment #137', 'Test author #87', 14);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:59:10.403000', 'Test comment #138', 'Test author #88', 14);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:59:11.403000', 'Test comment #139', 'Test author #89', 14);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 08:59:52.403000', 'Test comment #140', 'Test author #90', 14);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:26:55.403000', 'Test comment #141', 'Test author #91', 15);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:26:58.403000', 'Test comment #142', 'Test author #92', 15);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:27:11.403000', 'Test comment #143', 'Test author #93', 15);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:27:52.403000', 'Test comment #144', 'Test author #94', 15);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:28:10.403000', 'Test comment #145', 'Test author #95', 15);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:28:11.403000', 'Test comment #146', 'Test author #96', 15);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:28:52.403000', 'Test comment #147', 'Test author #97', 15);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:29:10.403000', 'Test comment #148', 'Test author #98', 15);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:29:11.403000', 'Test comment #149', 'Test author #99', 15);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:29:52.403000', 'Test comment #150', 'Test author #100', 15);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:46:55.403000', 'Test comment #151', 'Test author #91', 16);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:46:58.403000', 'Test comment #152', 'Test author #92', 16);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:47:11.403000', 'Test comment #153', 'Test author #93', 16);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:47:52.403000', 'Test comment #154', 'Test author #94', 16);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:48:10.403000', 'Test comment #155', 'Test author #95', 16);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:48:11.403000', 'Test comment #156', 'Test author #96', 16);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:48:52.403000', 'Test comment #157', 'Test author #97', 16);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:49:10.403000', 'Test comment #158', 'Test author #98', 16);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:49:11.403000', 'Test comment #159', 'Test author #99', 16);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 09:49:52.403000', 'Test comment #160', 'Test author #100', 16);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:16:55.403000', 'Test comment #161', 'Test author #101', 17);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:16:58.403000', 'Test comment #162', 'Test author #102', 17);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:17:11.403000', 'Test comment #163', 'Test author #103', 17);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:17:52.403000', 'Test comment #164', 'Test author #104', 17);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:18:10.403000', 'Test comment #165', 'Test author #105', 17);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:18:11.403000', 'Test comment #166', 'Test author #106', 17);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:18:52.403000', 'Test comment #167', 'Test author #107', 17);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:19:10.403000', 'Test comment #168', 'Test author #108', 17);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:19:11.403000', 'Test comment #169', 'Test author #109', 17);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 10:19:52.403000', 'Test comment #170', 'Test author #110', 17);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:26:55.403000', 'Test comment #171', 'Test author #111', 18);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:26:58.403000', 'Test comment #172', 'Test author #112', 18);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:27:11.403000', 'Test comment #173', 'Test author #113', 18);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:27:52.403000', 'Test comment #174', 'Test author #114', 18);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:28:10.403000', 'Test comment #175', 'Test author #115', 18);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:28:11.403000', 'Test comment #176', 'Test author #116', 18);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:28:52.403000', 'Test comment #177', 'Test author #117', 18);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:29:10.403000', 'Test comment #178', 'Test author #118', 18);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:29:11.403000', 'Test comment #179', 'Test author #119', 18);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:29:52.403000', 'Test comment #180', 'Test author #120', 18);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:46:55.403000', 'Test comment #181', 'Test author #111', 19);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:46:58.403000', 'Test comment #182', 'Test author #112', 19);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:47:11.403000', 'Test comment #183', 'Test author #113', 19);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:47:52.403000', 'Test comment #184', 'Test author #114', 19);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:48:10.403000', 'Test comment #185', 'Test author #115', 19);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:48:11.403000', 'Test comment #186', 'Test author #116', 19);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:48:52.403000', 'Test comment #187', 'Test author #117', 19);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:49:10.403000', 'Test comment #188', 'Test author #118', 19);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:49:11.403000', 'Test comment #189', 'Test author #119', 19);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 11:49:52.403000', 'Test comment #190', 'Test author #120', 19);

insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:16:55.403000', 'Test comment #191', 'Test author #121', 20);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:16:58.403000', 'Test comment #192', 'Test author #122', 20);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:17:11.403000', 'Test comment #193', 'Test author #123', 20);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:27:52.403000', 'Test comment #194', 'Test author #124', 20);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:28:10.403000', 'Test comment #195', 'Test author #125', 20);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:28:11.403000', 'Test comment #196', 'Test author #126', 20);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:38:52.403000', 'Test comment #197', 'Test author #127', 20);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:49:10.403000', 'Test comment #198', 'Test author #128', 20);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:49:11.403000', 'Test comment #199', 'Test author #129', 20);
insert into comments(comment_created_at, comment_text, comment_author, news_id)
VALUES ('2022-08-01 12:59:52.403000', 'Test comment #200', 'Test author #130', 20);