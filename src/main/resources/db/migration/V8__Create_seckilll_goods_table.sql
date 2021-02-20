CREATE TABLE `seckill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `stock_count` int(11) NOT NULL COMMENT '库存数量',
  `status` tinyint(4) NOT NULL COMMENT '状态标示：-1指审核未通过，0指未审核，1指审核通过',
  `start_time` datetime NOT NULL  COMMENT '秒杀开启时间',
  `end_time` datetime NOT NULL  COMMENT '秒杀结束时间',
  `create_time` datetime default null comment '创建时间',
  `update_time` datetime default null comment '修改时间',
  `version` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';