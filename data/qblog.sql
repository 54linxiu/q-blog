DROP DATABASE IF EXISTS `q_blog_db`;

CREATE DATABASE q_blog_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;



DROP TABLE IF EXISTS `user_admin`;

CREATE TABLE `user_admin`  (
 user_id INT PRIMARY KEY AUTO_INCREMENT COMMENT "用户id",
 user_account	VARCHAR(10) NOT NULL UNIQUE COMMENT "用户账号",
 user_nickname VARCHAR ( 10 ) NOT NULL COMMENT "用户昵称",
 user_pwd CHAR(32) NOT NULL COMMENT "密码 md5",
 user_profile VARCHAR ( 250 ) NOT NULL COMMENT "头像",
 locked TINYINT(4) DEFAULT '0' COMMENT '是否锁定 0未锁定 1已锁定无法登陆'
) ENGINE=INNODB DEFAULT CHARSET=utf8;

SELECT * FROM user_admin
DESC tb_blog
DROP TABLE IF EXISTS `blog_post`;

/*CREATE TABLE `blog_post`(
 blog_id INT PRIMARY KEY AUTO_INCREMENT COMMENT "博文id",
 release_time DATE NOT NULL COMMENT "发布日期",
 publishing_users INT UNIQUE COMMENT "发表用户 & 用户id",
 blog_title VARCHAR(20) NOT NULL COMMENT "博客标题",
 blog_content MEDIUMTEXT NOT NULL COMMENT "博客内容",
 replies INT DEFAULT 0 COMMENT "回复数量"
)ENGINE=INNODB DEFAULT CHARSET=utf8;
ALTER TABLE blog_post ADD CONSTRAINT FK_UID FOREIGN KEY(publishing_users) REFERENCES user_admin(user_id);\
*/

CREATE TABLE `blog_post`(
 blog_id INT PRIMARY KEY AUTO_INCREMENT COMMENT "博文id",
 release_time DATE NOT NULL COMMENT "发布日期",
 publishing_users INT  COMMENT "发表用户 & 用户id",
 blog_title VARCHAR(20) NOT NULL COMMENT "博客标题",
 blog_content_html MEDIUMTEXT NOT NULL COMMENT "博客内容",
 blog_content_md MEDIUMTEXT NOT NULL COMMENT "博客内容",
 replies INT DEFAULT 0 COMMENT "回复数量"
)ENGINE=INNODB DEFAULT CHARSET=utf8;
ALTER TABLE blog_post ADD CONSTRAINT FK_UID FOREIGN KEY(publishing_users) REFERENCES user_admin(user_id);

DROP TABLE blog_post