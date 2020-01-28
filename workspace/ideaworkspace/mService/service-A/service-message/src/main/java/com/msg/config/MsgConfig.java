package com.msg.config;

/**
 * @Description: 秒滴云短信登陆配置
 * @author: LinWeiQi
 */
public class MsgConfig {
    /**
     * url前半部分
     */
    public static final String BASE_URL = "https:https://openapi.miaodiyun.com/distributor/sendSMS";

    /**
     * 开发者注册后系统自动生成的账号，可在官网登录后查看
     */
    public static final String ACCOUNT_SID = "161c7b713951cc0ec2b25b1e33c740db";

    /**
     * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
     */
    public static final String AUTH_TOKEN = "d1cebba68ba0a24eb6d08399d0247fe3";

    /**
     * 响应数据类型, JSON或XML
     */
    public static final String RESP_DATA_TYPE = "json";
}
