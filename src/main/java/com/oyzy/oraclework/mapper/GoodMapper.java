package com.oyzy.oraclework.mapper;

import com.oyzy.oraclework.bean.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodMapper {

    @Select("select * from goods")
    List<Goods> findAll();

    @Select("select * from goods where gid=#{gid}")
    public Goods getGoodsById(Integer gid);

    @Delete("delete from goods where gid=#{gid}")
    public int deleteGoodsById(Integer gid);

    //    @Options(useGeneratedKeys = true,keyProperty = "gid")
    @Insert("insert into goods(gname) values(#{gname})")
    public int insertGoods(Goods goods);

    @Update("update goods set gname=#{gname} where gid=#{gid}")
    public int updateGoods(Goods goods);
}
