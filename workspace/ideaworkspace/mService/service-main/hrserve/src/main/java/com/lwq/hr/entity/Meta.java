package com.lwq.hr.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Description: menu表的额外属性
 * @author: LinWeiQi
 */
@Data
public class Meta {
    @TableField("keepAlive")
    private Boolean keepAlive;

    @TableField("requireAuth")
    private Boolean requireAuth;
}
