package edu.nwpu.managementserver.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TrainingStatusEnum {

    NotStart(0, "未开始"),

    Training(1, "训练中"),

    Paused(2, "已暂停"),

    Finished(3, "已结束");

    private final int value;

    private final String name;

    public static String nameOf(Integer value) {

        return Arrays.stream(values()).filter(e -> e.getValue() == value).findAny().get().getName();
    }
}
