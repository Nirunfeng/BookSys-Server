package com.booksys.mapper;

import com.booksys.pojo.Reader;
import com.booksys.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * 用户Mapper
 */
@Mapper
public interface ReaderMapper extends MyMapper<Reader> {
    /**
     * 根据账户查找用户
     * @param account
     * @return
     */
    @Select("select rid,account,password,name,sex,time,condi from t_reader where account=#{account}")
    Reader selectWholeByAccount(@Param("account") String account);

    /**
     * 根据主键,修改用户权限
     * @param rid
     */
    @Update("update t_reader set condi=#{condi} where rid=#{rid}")
    void update(@Param("rid") int rid,@Param("condi") int condi);

    /**
     * 查找用户集数据
     * @param account
     * @return
     */
    @Select("select * from t_reader where account like concat('%',concat(#{account},'%'))")
    List<Reader> selectByAccount(@Param("account") String account);

    @Select("select * from t_reader where rid=#{rid}")
    Reader selectById(@Param("rid") int rid);
}
