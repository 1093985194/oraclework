package com.oyzy.oraclework.mapper;

import com.oyzy.oraclework.bean.Custom;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustMapper {
    @Select("select * from custom")
    List<Custom> findAll();

    @Select("select * from custom where cid=#{cid}")
    public Custom getCustomById(Integer cid);

    @Delete("delete from custom where cid=#{cid}")
    public int deleteCustomById(Integer cid);

    //    @Options(useGeneratedKeys = true,keyProperty = "cid")
    @Insert("insert into custom(cname,sex) values(#{cname},#{sex})")
    public int insertCustom(Custom custom);

    @Update("update custom set cname=#{cname},sex=#{sex} where cid=#{cid}")
    public int updateCustom(Custom custom);
}
