create table notification(
    id int auto_increment primary key not null,
    notifier_id int not null comment '发出通知的人',
    status int default 0 not null comment '0未读1已读',
    receiver_id int not null comment '接收通知的人，即发出问题的人',
    question_id int not null comment '针对哪个问题的通知',
    question_title varchar(256) default null,
    notifier_name varchar(100) default null,
    create_time datetime default null,
    update_time datetime default null,
    CONSTRAINT `notification_fk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
    CONSTRAINT `notification_fk_2` FOREIGN KEY (`notifier_id`) REFERENCES `user` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;