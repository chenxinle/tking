package com.me.tking.dao;

import com.me.tking.entity.ActivityInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface ActivityInfoDao {

    List<ActivityInfo> getAll(RowBounds rowBounds, @Param("keyword") String keyword, @Param("orderByType") int orderByType);

    @Delete("DELETE FROM ActivityInfo")
    int deleteAll();

    int batchInsertActivityInfo(@Param("activityInfos") List<ActivityInfo> activityInfos);

    int countAll(@Param("keyword") String keyword,  @Param("orderByType") int orderByType);
}
