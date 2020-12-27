## questions and answers system
这是问答社区项目的重构
## document
[spring 文档](https://spring.io/guides)|
[bootstrap 文档](https://www.bootcss.com/)|
[es 社区](https://elasticsearch.cn/)|
[Github Oath Doc](https://docs.github.com/en/free-pro-team@latest/developers/apps/building-oauth-apps)
## tools
[github](https://github.com/btoob/QAsys)
[Visual-paradigm](https://www.visual-paradigm.com)
## script
```sql
create table user(
    id int AUTO_INCREMENT primary key not null comment '用户编号',
    name varchar(50) default null comment '用户名',
    password varchar(50) default null comment '密码',
    email varchar(30) default null comment '邮箱',
    user_face varchar(255) default null comment '头像'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
## note
导入flyway依赖的同时必须导入mybatis的依赖，因为Flyway用到其中的数据库注解