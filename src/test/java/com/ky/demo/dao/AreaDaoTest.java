package com.ky.demo.dao;

import com.ky.demo.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
//添加标签成为UT类
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;
//    无法明确是哪个DAO，无错误
    @Test
    public void queryArea() {
        List<Area> areaList=areaDao.queryArea();
        assertEquals(3,areaList.size());
    }

    @Test
    public void queryAreaById() {
        Area area=areaDao.queryAreaById(1);
        assertEquals("qw",area.getAreaName());
//        System.out.println(area.getAreaName());
    }

    @Test
    public void insertArea() {
        Area area=new Area();
        area.setAreaName("南苑");
        area.setPriority(2);
        int effictedNum=areaDao.insertArea(area);
//        判断两边是否相同，如果不同则测试失败
        assertEquals(1,effictedNum);
    }

    @Test
    public void updataArea() {
        Area area=new Area();
        area.setAreaName("西苑");
        area.setAreaId(10);
        area.setLastEditTime(new Date());
        int eff=areaDao.updataArea(area);
        assertEquals(1,eff);
    }

    @Test
    public void deleteArea() {
        int eff=areaDao.deleteArea(10);
        assertEquals(1,eff);
    }
}