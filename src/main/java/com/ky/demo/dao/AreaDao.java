package com.ky.demo.dao;

import com.ky.demo.entity.Area;

import java.util.List;

public interface AreaDao {
    List<Area> queryArea();
    Area queryAreaById(int areaId);
    int insertArea(Area area);
    int updataArea(Area area);
    int deleteArea(int areaId);
}
