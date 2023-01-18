create database police_management;
use police_management;
/*
    账号，供警员，监所管理员，超级管理员登录的账号表
*/
CREATE TABLE `account` (
       `id`            BIGINT          NOT NULL,   -- 账号id
       `account_number`      VARCHAR(50)         NOT NULL,   --  账号
       `password`      CHAR(60)        NOT NULL,   -- 密码
       `role`          TINYINT         NOT NULL,   -- 角色

       PRIMARY KEY(`id`),
       UNIQUE(`account_number`)
);


/*
    监所
*/
CREATE TABLE `prison` (
      `id`            BIGINT          NOT NULL,   -- 监所id
      `name`          VARCHAR(20)     NOT NULL,   -- 监所名称
      PRIMARY KEY(`id`),
      unique (name)  -- 监所不能重名
);

/*
    警员
*/
CREATE TABLE `police` (
      `id`            BIGINT          NOT NULL,   -- 警员id
      `name`          VARCHAR(20)     NOT NULL,   -- 警员姓名
      `image_url`     varchar(200)    null  ,
      `prison_id`     BIGINT          NOT NULL,   -- 所属监所id
      `account_id`    BIGINT     NOT NULL,   -- 账号id

      PRIMARY KEY(`id`)
);


/*
    监所管理员
*/
CREATE TABLE `prison_admin` (
        `id`            BIGINT          NOT NULL,   -- 监所管理员id
        `nickname`      VARCHAR(20)     NOT NULL,   -- 昵称
        `account_id`    BIGINT     NOT NULL,   -- 账号id
        `prison_id`     BIGINT          NOT NULL,   -- 所属监所id

        PRIMARY KEY(`id`)
);
/*
    平台管理员，超级管理员
*/
CREATE TABLE `admin` (
     `id`            BIGINT          NOT NULL,   -- 超级管理员id
     `nickname`      VARCHAR(20)     NOT NULL,   -- 昵
     `account_id`    BIGINT     NOT NULL,   -- 账号id

     PRIMARY KEY(`id`)
);

/*
    心理训练模型
*/
CREATE TABLE `training_model` (
      `id`            BIGINT          NOT NULL,   -- 模型id
      `name`          VARCHAR(20)     NOT NULL,   -- 模型名称
      `description`   VARCHAR(255)    NOT NULL,   -- 模型描述
      `enable`     BOOLEAN   NOT NULL   default false ,   -- 是否启用模型
      `priority`      INT             NOT NULL,   -- 优先级

      PRIMARY KEY(`id`)
);

/*
    警员-训练表
*/
CREATE TABLE `police_training` (
       `id`            BIGINT          NOT NULL,   -- id
       `police_id`     BIGINT          NOT NULL,   -- 警员id
       `model_id`      BIGINT          NOT NULL,   -- 心理模型id
       `start_time`    DATETIME        NOT NULL,   -- 训练开始时间
       `end_time`      DATETIME        NOT NULL,   -- 训练结束时间
       `status`        TINYINT         NOT NULL,   -- 状态
       `result`        VARCHAR(200)    default null,   -- 评估结果

       PRIMARY KEY(`id`)
);

/*
    访问记录
*/
CREATE TABLE `access_record` (
     `id`            BIGINT          NOT NULL,   -- id
     `access_time`   DATETIME        NOT NULL,   -- 访问时间
     `account_id`    BIGINT          NOT NULL,   -- 访问账号id
     `content`       VARCHAR(200)    NOT NULL,   -- 内容

     PRIMARY KEY(`id`)
);

/*
   监所管理员开启训练模型的记录
 */
create table `prison_mode`(
      id bigint primary key ,
      prison_id bigint not null ,
      mode_id bigint not null
)

/*
 综合评估
 */
create table  `totalAssess`(
   id bigint primary key ,
   police_id bigint not null , -- 综合评估的对象
   mental_state varchar(100) not null , -- 心理状态
   press_type varchar(100) not null , -- 压力类型
   create_time datetime not null , -- 创建时间
   update_time datetime null  -- 更新时间
)