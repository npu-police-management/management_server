DROP TABLE IF EXISTS `police_training`;
DROP TABLE IF EXISTS `access_record`;
DROP TABLE IF EXISTS `police`;
DROP TABLE IF EXISTS `prison_admin`;
DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `training_model`;
DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `prison`;

/*
    账号，供警员，监所管理员，超级管理员登录的账号表
*/
CREATE TABLE `account` (
    `id`                BIGINT          NOT NULL,   -- 账号id
    `account_number`    VARCHAR(20)     NOT NULL,   -- 账号
    `password`          CHAR(60)        NOT NULL,   -- 密码
    `role`              TINYINT         NOT NULL,   -- 角色

    PRIMARY KEY(`id`),
    UNIQUE(`account_number`)
);


/*
    监所
*/
CREATE TABLE `prison` (
    `id`                BIGINT          NOT NULL,   -- 监所id
    `name`              VARCHAR(20)     NOT NULL,   -- 监所名称

    PRIMARY KEY(`id`),
    UNIQUE(`name`)  -- 监所不能重名
);

/*
    警员
*/
CREATE TABLE `police` (
    `id`                BIGINT          NOT NULL,   -- 警员id
    `name`              VARCHAR(20)     NOT NULL,   -- 警员姓名
    `image_url`         VARCHAR(200)    NOT NULL,   -- 头像url(没有url时值为空字符串)
    `prison_id`         BIGINT          NOT NULL,   -- 所属监所id
    `account_id`        BIGINT          NOT NULL,   -- 账号id

    PRIMARY KEY(`id`)
);


/*
    监所管理员
*/
CREATE TABLE `prison_admin` (
    `id`                BIGINT          NOT NULL,   -- 监所管理员id
    `nickname`          VARCHAR(20)     NOT NULL,   -- 昵称
    `account_id`        BIGINT          NOT NULL,   -- 账号id
    `prison_id`         BIGINT          NOT NULL,   -- 所属监所id

    PRIMARY KEY(`id`)
);
/*
    平台管理员，超级管理员
*/
CREATE TABLE `admin` (
    `id`                BIGINT          NOT NULL,   -- 超级管理员id
    `nickname`          VARCHAR(20)     NOT NULL,   -- 昵称
    `account_id`        BIGINT          NOT NULL,   -- 账号id

    PRIMARY KEY(`id`)
);

/*
    心理训练模型
*/
CREATE TABLE `training_model` (
    `id`                BIGINT          NOT NULL,   -- 模型id
    `name`              VARCHAR(20)     NOT NULL,   -- 模型名称
    `description`       VARCHAR(255)    NOT NULL,   -- 模型描述
    `enable`            BOOLEAN         NOT NULL,   -- 是否启用模型
    `priority`          INT             NOT NULL,   -- 优先级

    PRIMARY KEY(`id`)
);

/*
    警员-训练表
*/
CREATE TABLE `police_training` (
    `id`                BIGINT          NOT NULL,   -- id
    `police_id`         BIGINT          NOT NULL,   -- 警员id
    `model_id`          BIGINT          NOT NULL,   -- 心理模型id
    `start_time`        DATETIME        NOT NULL,   -- 训练开始时间
    `end_time`          DATETIME        NOT NULL,   -- 训练结束时间
    `status`            TINYINT         NOT NULL,   -- 状态
    `result`            VARCHAR(200),               -- 评估结果

    PRIMARY KEY(`id`)
);

/*
    访问记录
*/
CREATE TABLE `access_record` (
    `id`                BIGINT          NOT NULL,   -- id
    `access_time`       DATETIME        NOT NULL,   -- 访问时间
    `account_id`        BIGINT          NOT NULL,   -- 访问账号id
    `content`           VARCHAR(200)    NOT NULL,   -- 内容

    PRIMARY KEY(`id`)
);

/*
   监所管理员开启训练模型的记录
 */
CREATE TABLE `prison_model`(
    `id`                BIGINT          PRIMARY KEY,
    `prison_id`         BIGINT          NOT NULL,
    `model_id`          BIGINT          NOT NULL
);

/*
 综合评估
 */
CREATE TABLE `total_assess` (
    `id`                BIGINT          PRIMARY KEY,
    `police_id`         BIGINT          NOT NULL,   -- 综合评估的对象
    `mental_state`      VARCHAR(100)    NOT NULL,   -- 心理状态
    `press_type`        VARCHAR(100)    NOT NULL,   -- 压力类型
    `create_time`       DATETIME        NOT NULL,   -- 创建时间
    `update_time`       DATETIME                    -- 更新时间
);