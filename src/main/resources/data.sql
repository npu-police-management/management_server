INSERT INTO `account`(`id`, `account_number`, `password`, `role`)
VALUES (1, 'root', '$2a$10$ke1f7hBqyTkYzPpaXY0ACeZ.CaxNs675edbx5Q.l7VB18xykS4uEG', 2);
# 密码为: root;

INSERT INTO `admin`(`id`, `nickname`, `account_id`)
VALUES (1, 'RooT', 1);


# 插入警员数据, 测试用;
INSERT INTO `account`(`id`, `account_number`, `password`, `role`)
VALUES (2, 'police', '$2a$10$ke1f7hBqyTkYzPpaXY0ACeZ.CaxNs675edbx5Q.l7VB18xykS4uEG', 0);
# 监所;
INSERT INTO  `prison` (id, name) VALUES (2, 'P');
# 警员信息;
INSERT INTO `police` (id, name, image_url, prison_id, account_id)
VALUES (2, '警员', '', 2, 2);
# 模型;
INSERT INTO `training_model`(id, name, description, enable, priority)
VALUES (2, 'Model', 'Description: 123456', 1, 1);

# 监所开启的模型
INSERT INTO `prison_model`(id, prison_id, model_id)
VALUES (2, 2, 2);

INSERT INTO `police_training`(id, police_id, model_id, start_time, end_time, status, result)
VALUES (2, 2, 2, '2023-2-4 12:00:00', '2023-2-4 23:00:00', 1, '好');
INSERT INTO `police_training`(id, police_id, model_id, start_time, end_time, status, result)
VALUES (3, 2, 2, '2023-2-5 12:00:00', '2023-2-5 23:00:00', 1, '好');
INSERT INTO `police_training`(id, police_id, model_id, start_time, end_time, status, result)
VALUES (4, 2, 2, '2023-2-6 12:00:00', '2023-2-6 23:00:00', 1, '好');
INSERT INTO `police_training`(id, police_id, model_id, start_time, end_time, status, result)
VALUES (5, 2, 2, '2023-2-6 9:00:00', '2023-2-6 11:00:00', 1, '好');
INSERT INTO `police_training`(id, police_id, model_id, start_time, end_time, status, result)
VALUES (6, 2, 2, '2023-2-7 9:00:00', '2023-2-7 11:00:00', 1, '好');
INSERT INTO `police_training`(id, police_id, model_id, start_time, end_time, status, result)
VALUES (7, 2, 2, '2023-2-8 9:00:00', '2023-2-8 11:00:00', 1, '好');
