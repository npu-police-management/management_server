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
    role 取值：警员(0)，监所管理员(1)，超级管理员(2)
*/
CREATE TABLE `account` (
    `id`                BIGINT          NOT NULL,   -- 账号id
    `account_number`    VARCHAR(50)     NOT NULL,   -- 账号
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

    PRIMARY KEY(`id`)
);

/*
    警员
*/
CREATE TABLE `police` (
    `id`                BIGINT          NOT NULL,   -- 警员id
    `account_id`        BIGINT          NOT NULL,   -- 账号id
    `name`              VARCHAR(20)     NOT NULL,   -- 警员姓名
    `prison_id`         BIGINT          NOT NULL,   -- 所属监所id

    PRIMARY KEY(`id`),
    foreign key (account_id) references account(id) on delete cascade, -- 联级删除，以删除账号的方式进行管理员的删除删除账号的话，直接将详细警员信息删除，也就是以删除账号的方式进行警员删除；同时也是使用删除账号的方式进行管理员的删除
    foreign key (prison_id) references prison(id) on delete cascade    -- 联级删除，以删除账号的方式进行管理员的删除删除监狱的话，也就是监狱放弃使用该训练系统，此时将该监狱的警擦都移出本系统，正在登录态的警员请求信息时应该给予用户不存在的提示
);


/*
    监所管理员
*/
CREATE TABLE `prison_admin` (
    `id`                BIGINT          NOT NULL,   -- 监所管理员id
    `account_id`        BIGINT          NOT NULL,   -- 账号id
    `nickname`          VARCHAR(20)     NOT NULL,   -- 昵称
    `prison_id`         BIGINT          NOT NULL,   -- 所属监所id

    PRIMARY KEY(`id`),
    foreign key (account_id) references account(id) on delete cascade, -- 联级删除，同警员，以删除账号的方式进行管理员的删除
    foreign key (prison_id) references prison(id) on delete cascade   -- 联机删除，删除监狱的话，也就是监狱放弃使用该训练系统，管理员自然应该被移除
);
/*
    平台管理员，超级管理员
*/
CREATE TABLE `admin` (
    `id`                BIGINT          NOT NULL,   -- 超级管理员id
    `account_id`        BIGINT          NOT NULL,   -- 账号id
    `nickname`          VARCHAR(20)     NOT NULL,   -- 昵称

    PRIMARY KEY(`id`),
    foreign key (account_id) references account(id) on delete cascade -- 联级删除，同警员，以删除账号的方式进行管理员的删除
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
    `result`            VARCHAR(200)    NOT NULL,   -- 评估结果

    PRIMARY KEY(`id`),
    foreign key (police_id) references police(id) on delete cascade, -- 联级删除，当删除警员时，该警员的训练记录自然没了价值
    foreign key (model_id) references training_model(id) -- 不联带删除，删除model之前，将所有训练记录表中的外键model_id设置为-1，查询拼接model名字时对查不出来的设为“已删除模型”

);

/*
    访问记录
*/
CREATE TABLE `access_record` (
    `id`                BIGINT          NOT NULL,   -- id
    `access_time`       DATETIME        NOT NULL,   -- 访问时间
    `account_id`        BIGINT          NOT NULL,   -- 访问账号id
    `content`           VARCHAR(200)    NOT NULL,   -- 内容

    PRIMARY KEY(`id`),
    foreign key (account_id) references training_model(id) on delete cascade -- 级联删除，如果一个警员已经移除该系统，那也没必要保留他的访问记录了
);