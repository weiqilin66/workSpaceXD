package com.sys.entity;

import lwq.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String pid;

    private String title;

    private String brand;

    private String pname;

    private String price;


}
