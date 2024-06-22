package com.sdpzhong.dev.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class RedisQueryResponseDto implements Serializable {
    /* redis key */
    private String key;

    /* redis value */
    private String value;
}
