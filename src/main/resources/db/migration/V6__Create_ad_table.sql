create table ad(
    id int AUTO_INCREMENT primary key not null comment '编号',
    title varchar(256) default null comment '标题',
    url varchar(512) default null comment '地址',
    image varchar(256) default null comment '图片',
    pos varchar(10) not null comment '广告位置',
    start_time datetime default null comment '广告开始时间',
    end_time datetime default null comment '广告取消时间',
    create_time datetime default null comment '创建时间',
    update_time datetime default null comment '修改时间',
    status int default 0 comment '状态'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;