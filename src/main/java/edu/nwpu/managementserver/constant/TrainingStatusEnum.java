package edu.nwpu.managementserver.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrainingStatusEnum {

    NotStart(0),

    Training(1),

    Paused(2),

    Finished(3);

    private final int value;
}
