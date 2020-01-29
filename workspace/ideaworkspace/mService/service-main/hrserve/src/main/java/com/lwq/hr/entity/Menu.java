package com.lwq.hr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * @TableId：表主键标识
 * @TableField：表字段标识 (当表字段没使用_时且包含2个以上单词,sql生成时不注解会解析成带_字段 所以要标识来不反转驼峰)
 * @TableField(exist = false)：表示该属性不为数据库表字段，但又是必须使用的。
 * @Accessors(chain = true): setter生成方法的返回值是对象
 * // 生成的setter方法如下，方法体略
 *     public User setId(Long id) {}
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Menu implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;
    // 非rotes数组内容
    private Meta meta;
    // 子组件
    private List<Menu> children;

    @TableField("iconCls")
    private String iconCls;

    @TableField("parentId")
    private Integer parentId;

    private Boolean enabled;


}
