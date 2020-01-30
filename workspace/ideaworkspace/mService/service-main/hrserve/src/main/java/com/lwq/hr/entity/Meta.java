package com.lwq.hr.entity;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @Description: menu表的额外属性
 * @author: LinWeiQi
 */
public class Meta {
    @TableField("keepAlive")
    private Boolean keepAlive;

    @TableField("requireAuth")
    private Boolean requireAuth;

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
