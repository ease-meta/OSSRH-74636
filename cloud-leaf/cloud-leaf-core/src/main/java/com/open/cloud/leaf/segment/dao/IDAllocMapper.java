package com.open.cloud.leaf.segment.dao;

import com.open.cloud.leaf.segment.model.LeafAlloc;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IDAllocMapper {

    @Select("SELECT biz_tag, max_id, step, update_time FROM leaf_alloc")
    @Results(value = { @Result(column = "biz_tag", property = "bizTag"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step"),
            @Result(column = "update_time", property = "updateTime") })
    List<LeafAlloc> getAllLeafAllocs();

    @Select("SELECT biz_tag, max_id, step FROM leaf_alloc WHERE biz_tag = #{bizTag}")
    @Results(value = { @Result(column = "biz_tag", property = "bizTag"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step") })
    LeafAlloc getLeafAlloc(@Param("bizTag") String bizTag);

    @Update("UPDATE leaf_alloc SET max_id = max_id + step WHERE biz_tag = #{bizTag}")
    void updateMaxId(@Param("bizTag") String bizTag);

    @Update("UPDATE leaf_alloc SET max_id = max_id + #{step} WHERE biz_tag = #{bizTag}")
    void updateMaxIdByCustomStep(@Param("leafAlloc") LeafAlloc leafAlloc);

    @Select("SELECT biz_tag FROM leaf_alloc")
    List<String> getAllTags();
}
