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
create table USER
(
	ID INT auto_increment,
	ACCOUNTID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMTCREATE BIGINT,
	GMTMODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);
```