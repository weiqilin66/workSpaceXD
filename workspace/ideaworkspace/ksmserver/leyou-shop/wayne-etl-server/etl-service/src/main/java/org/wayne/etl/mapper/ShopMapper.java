package org.wayne.etl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wayne.etl.entity.Shop;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-05-12
 */
public interface ShopMapper extends BaseMapper<Shop> {
    List<String> selShopName();
}
