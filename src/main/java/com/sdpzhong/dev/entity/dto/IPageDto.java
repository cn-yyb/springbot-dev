package com.sdpzhong.dev.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class IPageDto implements Serializable {

    /* 页码 */
    @Schema(description = "页码")
    public Integer current = 1;

    /* 页容量 */
    @Schema(description = "页容量")
    public Integer size = 20;

    /* 排序 */
    @Schema(description = "排序")
    public String sorts;
}
