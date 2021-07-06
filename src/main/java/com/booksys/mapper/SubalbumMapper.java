package com.booksys.mapper;

import com.booksys.pojo.Subalbum;
import com.booksys.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubalbumMapper extends MyMapper<Subalbum> {
    @Select("select * from t_subalbum where aid=#{aid}")
    List<Subalbum> selectByAid(@Param("aid") int aid);

    @Select("select * from t_subalbum where sid=#{sid}")
    Subalbum selectById(@Param("sid") int sid);

    @Select("select * from t_subalbum where number=#{number}")
    Subalbum selectByNumber(@Param("number") String number);
}
