package com.lwq.hr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Empsalary implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer eid;

    private Integer sid;


}
