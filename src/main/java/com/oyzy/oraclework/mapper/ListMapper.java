package com.oyzy.oraclework.mapper;

import com.oyzy.oraclework.bean.Goods;
import com.oyzy.oraclework.bean.Lorder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ListMapper {
    @Select("select gid from lorder where cid=#{cid}")
     List<Goods> getGidByCId(Integer cid);

    @Select("select * from goods where gid=#{gid}")
//    List<Goods> getGoodByGId(Integer cid);
    public Goods getGoodByGId(Integer cid);

    @Delete("delete from lorder where gid=#{gid}")
    public int deletelorderByGId(Integer gid);

    @Insert("insert into Lorder(cid,gid) values(#{cid},#{gid})")
    public int insertCustom(Integer cid,Integer gid);

}
