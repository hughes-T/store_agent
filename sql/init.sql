-- 用户表
CREATE TABLE bas_user
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    name         VARCHAR(100) COMMENT '名称',
    code         VARCHAR(100) COMMENT '登录账户',
    password         VARCHAR(100) COMMENT '登录密码',
    trade_type   VARCHAR(32) COMMENT '类型 1-管理员 2-经销商 3-店长 4-消费者 1001-其他',
    level        NUMERIC(15) COMMENT '用户级别'
)
    COMMENT '用户基础表';

-- 管理员暂不需独立表
CREATE TABLE bas_user_dealer
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    user_id BIGINT,
    name         VARCHAR(100) COMMENT '名称',
    linkman_name         VARCHAR(100) COMMENT '联系人姓名',
    linkman_mobile_number         VARCHAR(100) COMMENT '登录账户',
    address      VARCHAR(200) COMMENT '地址',
    min_delivery_price NUMERIC(10,4) COMMENT '起送价',
    logo_path VARCHAR(100) COMMENT 'LOGO',
    account_bank_name VARCHAR(100) COMMENT '开户银行',
    account_branches_name VARCHAR(100) COMMENT '开户支行',
    account_name VARCHAR(100) COMMENT '账户名称',
    bank_code VARCHAR(200) COMMENT '银行账号'
)
    COMMENT '经销商表';

CREATE TABLE bas_user_customer
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    user_id BIGINT,
    enable_status VARCHAR(32) COMMENT '用户状态 0-删除 1-启用 2-冻结',
    name         VARCHAR(100) COMMENT '昵称',
    real_name         VARCHAR(100) COMMENT '真实名称',
    wx_source_code         VARCHAR(100) COMMENT '微信唯一标识',
    gender         CHAR COMMENT '性别 （微信注册时就已获取）',
    avatar_pic VARCHAR(100) COMMENT '头像（图片文件）',
    mobile_number VARCHAR(32) COMMENT '手机号 （需使用验证码认证录入）',
    delivery_address BIGINT COMMENT '收货地址 （bas_location.id）'
)
    COMMENT '消费者表';

CREATE TABLE bas_user_store
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    user_id BIGINT,
    name         VARCHAR(100) COMMENT '昵称',
    real_name         VARCHAR(100) COMMENT '真实名称',
    store_name VARCHAR(100) COMMENT '店铺名称',
    open_time TIMESTAMP COMMENT '开店时间',
    store_notice VARCHAR(200) COMMENT '店铺公告',
    mobile_number VARCHAR(32) COMMENT '手机号 （需使用验证码认证录入）',
    store_logo VARCHAR(100) COMMENT '店铺LOGO',
    store_address BIGINT COMMENT '店铺地址 （bas_location.id）',
    min_delivery_price NUMERIC(10,4) COMMENT '起送价',
    audit_time  TIMESTAMP COMMENT '审批时间',
    flow_status  VARCHAR(32) COMMENT '审批状态 1-通过 2-未通过 3-审核中 9-草稿',
    flow_remark  VARCHAR(32) COMMENT '审批备注'
)
    COMMENT '店铺表';
-- todo 销售额、订单量、进货额、余额、提现 统计

-- 位置表
CREATE TABLE bas_location
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    name         VARCHAR(100) COMMENT '名称',
    code         VARCHAR(100) COMMENT '编码',
    name_full_path VARCHAR(100) COMMENT '全路径位置 如 南京/南京职业技术学院/1栋/339号'
)
    COMMENT '位置表';

CREATE TABLE bas_location_r
(
    id  BIGINT NOT NULL,
    pid BIGINT NOT NULL,
    n   BIGINT NOT NULL,
    PRIMARY KEY (id, pid)
)
    COMMENT '位置关系表';

-- 商品表
CREATE TABLE bas_product
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    name         VARCHAR(100) COMMENT '商品名称',
    type_id      BIGINT COMMENT '商品分类',
    brand        VARCHAR(100) COMMENT '商品品牌',
    spec         VARCHAR(100) COMMENT '商品规格',
    bar_code     VARCHAR(100) COMMENT '规格条形码',
    prop_type    VARCHAR(32) COMMENT '商品属性 1-普通商品',
    pic_path     VARCHAR(1000) COMMENT '商品图片',
    flow_status  VARCHAR(32) COMMENT '审批状态 1-通过 2-未通过 3-审核中 9-草稿',
    flow_remark  VARCHAR(32) COMMENT '审批备注'
)
    COMMENT '商品表';

CREATE TABLE bas_product_type
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    name         VARCHAR(100) COMMENT '分类名称',
    code         VARCHAR(100) COMMENT '分类编码',
    sort         NUMERIC COMMENT '序号'
)
    COMMENT '商品分类表';

CREATE TABLE bas_pd_unit
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    bar_code     VARCHAR(100) COMMENT '规格条形码',
    pd_id        BIGINT,
    name         VARCHAR(100) COMMENT '单位名称',
    code         VARCHAR(100) COMMENT '单位编码',
    is_base      char(1) COMMENT '是否是基本单位（零售单位）',
    purchase_price NUMERIC(10, 4) COMMENT '进货价',
    sale_price NUMERIC(10, 4) COMMENT '零售价',
    ratio        numeric(28, 8) COMMENT '与基本单位换算系数'
)
    COMMENT '商品单位表';

-- 消费者订单表
CREATE TABLE order_customer
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    code         VARCHAR(100) COMMENT '订单号',
    order_status VARCHAR(32) COMMENT '订单状态 1-已完成 2-待付款 3-配送中 4-已取消',
    pay_type   VARCHAR(32) COMMENT '支付类型 1-微信支付',
    address_id BIGINT COMMENT '地址',
    store_id BIGINT COMMENT '店铺',
    order_amount   NUMERIC(10,4) COMMENT '订单金额'
)
    COMMENT '消费者订单表';

CREATE TABLE order_customer_detail
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    order_id BIGINT,
    pd_id BIGINT,
    pd_unit_id BIGINT,
    pd_num NUMERIC(10),
    pd_price NUMERIC(10,4) COMMENT '单价',
    pd_amount NUMERIC(10,4) COMMENT '金额 pd_num * pd_price'
)
    COMMENT '消费者订单详情表';

-- 店铺交易流水（正常订单、损耗报销等）
CREATE TABLE bills_flow_store
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    code         VARCHAR(100) COMMENT '流水号',
    order_id     BIGINT,
    amount   NUMERIC(10,4) COMMENT '金额（可为负）'
)
    COMMENT '店铺交易流水';

-- 店铺采购订单表
CREATE TABLE order_store
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    code         VARCHAR(100) COMMENT '订单号',
    order_status VARCHAR(32) COMMENT '订单状态 1-已完成 3-配送中 4-已取消',
    flow_status  VARCHAR(32) COMMENT '审批状态 1-通过 2-未通过 3-审核中 9-草稿',
    flow_remark  VARCHAR(32) COMMENT '审批备注',
    address_id BIGINT COMMENT '地址',
    store_id BIGINT COMMENT '店铺',
    dealer_id BIGINT COMMENT '经销商ID',
    order_amount   NUMERIC(10,4) COMMENT '订单总价值'
)
    COMMENT '店铺采购订单表';

-- 店铺采购订单详情表
CREATE TABLE order_store_detail
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    order_id BIGINT,
    pd_unit_id BIGINT,
    pd_id BIGINT,
    pd_num NUMERIC(10),
    pd_price NUMERIC(10,4) COMMENT '实际单价',
    pd_amount NUMERIC(10,4) COMMENT '金额 pd_num * pd_price'
)
    COMMENT '店铺采购订单详情表';

-- 店铺库存详情表
CREATE TABLE stock_store_detail
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    pd_unit BIGINT,
    pd_id BIGINT,
    pd_num NUMERIC(10)
)
    COMMENT '店铺库存详情表';



