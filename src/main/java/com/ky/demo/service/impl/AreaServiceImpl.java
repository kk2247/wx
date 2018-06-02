package com.ky.demo.service.impl;

import com.ky.demo.dao.AreaDao;
import com.ky.demo.entity.Area;
import com.ky.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

    @Override
    public Area getAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }

//    须加入事务的控制
    @Transactional
    @Override
    public boolean addArea(Area area) {
        if(area.getAreaName()!=null&& !"".equals(area.getAreaName())){
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try{
                int efficeedNum=areaDao.insertArea(area);
                if(efficeedNum>0){
                    return true;
                }
                else {
                    throw new RuntimeException("插入区域信息失败");
                }
            }catch (Exception e){
                throw new RuntimeException("插入区域信息失败:"+e.getMessage());
            }
        }else{
            throw new RuntimeException("区域信息不能为空");
        }
    }

    @Override
    public boolean modifyArea(Area area) {
        if(area.getAreaId()!=null&& area.getAreaId()>0){
            area.setLastEditTime(new Date());
            try{
                int efficeedNum=areaDao.updataArea(area);
                if(efficeedNum>0){
                    return true;
                }
                else {
                    throw new RuntimeException("更新区域信息失败");
                }
            }catch (Exception e){
                throw new RuntimeException("更新区域信息失败:"+e.toString());
            }
        }else{
            throw new RuntimeException("区域信息不能为空");
        }
    }

    @Override
    public boolean deleteArea(int areaId) {
        if(areaId>0){
            try{
                int efficeedNum=areaDao.deleteArea(areaId);
                if(efficeedNum>0){
                    return true;
                }
                else {
                    throw new RuntimeException("删除区域信息失败");
                }
            }catch (Exception e){
                throw new RuntimeException("删除区域信息失败:"+e.toString());
            }
        }else{
            throw new RuntimeException("区域信息不能为空");
        }
    }
}
