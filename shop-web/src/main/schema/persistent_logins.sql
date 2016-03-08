-- -------------
-- 这是spring cookie持久化策略的一个方式，cookie时间以服务器为准
-- 防止人为篡改
-- -------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
`username` varchar(64) not null,
`series` varchar(64) not null,
`token` varchar(64) not null,
`last_used` timestamp not null,
PRIMARY KEY (series)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;