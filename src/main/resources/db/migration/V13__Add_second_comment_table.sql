create table secondcomment (
    id int auto_increment primary key not null,
    parent_id int not null,
    type int not null comment '1or2级评论',
    commentator int not null,
    content varchar(1024) default null,
    create_time datetime default null,
    update_time datetime default null,
    like_count int default 0,
    comment_count int default 0,
    CONSTRAINT `secondcomment_fk_1` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`),
    CONSTRAINT `secondcomment_fk_2` FOREIGN KEY (`commentator`) REFERENCES `user` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;