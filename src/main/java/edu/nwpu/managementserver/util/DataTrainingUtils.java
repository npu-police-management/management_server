package edu.nwpu.managementserver.util;

import edu.nwpu.managementserver.constant.TrainingStatusEnum;

import java.util.Random;

/**
 * @author GengXuelong
 * @version 1.0
 * {@code Mail} 3349495429@qq.com
 * {@code time} 2023/3/4 1:47
 * {@code className} DataTrainingUitls
 * {@code description}:
 *      从机器学习系统得到警员的训练结果
 */
public class DataTrainingUtils {

    public static int getStatus(Long id, long modelId) {
        return TrainingStatusEnum.Training.getValue();
    }

    public static String getResult(Long id, long modelId) {

        Random random = new Random();
        if (random.nextBoolean())
            return "总体正常";
        else return "总体异常";
    }
}
