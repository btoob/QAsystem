CREATE TABLE `seckill_order` (
  `id` bigint(20) primary key NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `seckill_id` bigint(20) NOT NULL COMMENT '商品id',
  `user_id` int NOT NULL COMMENT '用户id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `stock_count` int(11) NOT NULL COMMENT '库存数量',
  `status` tinyint(4) NOT NULL COMMENT '状态标示：-1指无效，0指未支付，1指已支付',
  `pay_time` datetime default null comment '支付时间',
  `create_time` datetime default null comment '创建时间',
  `update_time` datetime default null comment '修改时间',
  `receiver_address` varchar(256) default null comment '买家地址',
  `receiver_mobile` varchar(15) default null comment '买家电话',
  `receiver_name` varchar(15) default null comment '买家名字',
  CONSTRAINT `order_fk_1` FOREIGN KEY (`seckill_id`) REFERENCES `seckill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';