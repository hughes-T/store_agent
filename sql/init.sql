
CREATE TABLE bas_product
(
    id           BIGINT NOT NULL PRIMARY KEY,
    status       CHAR(1) DEFAULT '1',
    creator_id   BIGINT,
    create_time  TIMESTAMP,
    modifyier_id BIGINT,
    modify_time  TIMESTAMP,
    name VARCHAR(100) COMMENT '商品名称',
    type_id BIGINT COMMENT '商品分类',
    brand VARCHAR(100) COMMENT '商品品牌',
    spec VARCHAR(100) COMMENT '商品规格',
    spec_unit VARCHAR(32) COMMENT '规格单位',
    bar_code VARCHAR(100) COMMENT '零售规格条形码',
    prop_type VARCHAR(32) COMMENT '商品属性 1-普通商品',
    pic_path VARCHAR(1000) COMMENT '商品图片',
    flow_status VARCHAR(32) COMMENT '审批状态 1-通过 2-未通过 3-审核中 9-草稿',
    flow_remark VARCHAR(32) COMMENT '审批备注'
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
    name VARCHAR(100) COMMENT '分类名称',
    code VARCHAR(100) COMMENT '分类编码',
    sort NUMERIC COMMENT '序号'
)
    COMMENT '商品分类表';
