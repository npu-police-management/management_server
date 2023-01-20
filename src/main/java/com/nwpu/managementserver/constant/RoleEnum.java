package com.nwpu.managementserver.constant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Getter
public enum RoleEnum {

    /**
     * 警员
     */
    @JsonProperty("POLICE")
    POLICE(0),

    /**
     * 监所管理员
     */
    @JsonProperty("PRISON")
    PRISON(1),

    /**
     * 平台运维
     */
    @JsonProperty("ADMIN")
    ADMIN(2);

    RoleEnum(int value) {

        this.value = value;
    }

    public static RoleEnum fromValue(int value) {

        for (RoleEnum e: RoleEnum.values()) {
            if (value == e.getValue()) {
                return e;
            }
        }
        return null;
    }

    private final int value;

}
