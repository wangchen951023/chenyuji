CREATE TABLE `chenyuji`.`bill`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` int(11) NOT NULL DEFAULT 0 COMMENT '账单类型1，购物。。。',
  `title` varchar(255) NULL COMMENT '账单描述',
  `url` varchar(255) NULL COMMENT '账单附件地址',
  `create_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  PRIMARY KEY (`id`)
);

ALTER TABLE `chenyuji`.`bill`
ADD COLUMN `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '数据修改时间' AFTER `create_date`;

ALTER TABLE `chenyuji`.`bill`
ADD COLUMN `amount` varchar(10) NULL DEFAULT 0 COMMENT '消费金额' AFTER `title`,
ADD COLUMN `content` varchar(255) NULL COMMENT '内容' AFTER `amount`;

ALTER TABLE `chenyuji`.`bill`
ADD COLUMN `admin_login_id` varchar(255) NOT NULL COMMENT '用户id' AFTER `content`;