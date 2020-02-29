package com.crud.mapper;

import com.crud.entity.joblevel;
import com.crud.entity.joblevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface joblevelMapper {
    long countByExample(joblevelExample example);

    int deleteByExample(joblevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(joblevel record);

    int insertSelective(joblevel record);

    List<joblevel> selectByExample(joblevelExample example);

    joblevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") joblevel record, @Param("example") joblevelExample example);

    int updateByExample(@Param("record") joblevel record, @Param("example") joblevelExample example);

    int updateByPrimaryKeySelective(joblevel record);

    int updateByPrimaryKey(joblevel record);
}