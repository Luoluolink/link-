package com.Mapper;

import com.people.Fans;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface FansMapper {
    @Select("SELECT * FROM fan_members WHERE groupName = #{groupName}")
    List<Fans> getFansByGroupName(@Param("groupName") String groupName);
}
