DROP DATABASE IF EXISTS `q_blog_db`;

CREATE DATABASE q_blog_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;



DROP TABLE IF EXISTS `user_admin`;

# 用户表
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


# 博客表
CREATE TABLE `blog_post`(
 blog_id INT PRIMARY KEY AUTO_INCREMENT COMMENT "博文id",
 release_time DATE NOT NULL COMMENT "发布日期",
 publishing_users INT  COMMENT "发表用户 & 用户id",
 blog_title VARCHAR(20) NOT NULL UNIQUE COMMENT "博客标题",
 blog_content_html MEDIUMTEXT NOT NULL COMMENT "博客内容",
 blog_content_md MEDIUMTEXT NOT NULL COMMENT "博客内容",
 blog_sort_name VARCHAR(10) COMMENT "分类名称 可选项",
 blog_tags_name VARCHAR(10) COMMENT "标签名称 可选项",
 replies INT DEFAULT 0 COMMENT "回复数量"
)ENGINE=INNODB DEFAULT CHARSET=utf8;
ALTER TABLE blog_post ADD CONSTRAINT FK_UID FOREIGN KEY(publishing_users) REFERENCES user_admin(user_id);

# 分类标签表
CREATE TABLE `blog_sort`(
	sort_id INT PRIMARY KEY AUTO_INCREMENT COMMENT "分类id",
	sort_name VARCHAR(10) NOT NULL COMMENT "分类名称"
)


# 标签表
CREATE TABLE `blog_tags`(
	tags_id INT PRIMARY KEY AUTO_INCREMENT COMMENT "标签id",
	tags_name VARCHAR(10) NOT NULL COMMENT "标签名称"
)

CREATE TABLE `blog_tags_relation`(
	relation_id INT PRIMARY KEY AUTO_INCREMENT COMMENT "标签关系id",
	blog_id INT NOT NULL COMMENT "博文id",
	tags_id INT NOT NULL COMMENT "标签id"
)

INSERT INTO blog_sort(sort_name) VALUES ("青花瓷")
DROP TABLE blog_post
更新数据库 ER 修改博客回显 发布博客提示分类
TRUNCATE TABLE blog_post
SELECT * FROM blog_post