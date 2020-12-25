create table user(
    id int AUTO_INCREMENT primary key not null comment '用户编号',
    name varchar(50) default null comment '用户名',
    password varchar(50) default null comment '密码',
    email varchar(30) default null comment '邮箱',
    user_face varchar(255) default null comment '头像'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;