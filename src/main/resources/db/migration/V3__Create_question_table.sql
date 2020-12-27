create table question(
    id int AUTO_INCREMENT primary key not null comment '问题编号',
    title varchar(50) default null comment '标题',
    description text default null comment '问题描述',
    create_time datetime default null comment '创建时间',
    update_time datetime default null comment '修改时间',
    comment_count int default 0 comment '回复数',
    view_count int default 0 comment '浏览数',
    like_count int default 0 comment '喜欢数',
    tag varchar(256) default null comment '标签',
    user_id int not null comment '用户id',
    CONSTRAINT `question_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;