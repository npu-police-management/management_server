INSERT INTO `account`(`id`, `account_number`, `password`, `role`)
VALUES (1, 'root', '$2a$10$ke1f7hBqyTkYzPpaXY0ACeZ.CaxNs675edbx5Q.l7VB18xykS4uEG', 2);
# 密码为: root;

INSERT INTO `admin`(`id`, `nickname`, `account_id`)
VALUES (1, '平台运维', 1);
