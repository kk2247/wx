package com.ky.demo.web;


import com.ky.demo.entity.Area;
import com.ky.demo.service.AreaService;
import org.apache.commons.fileupload.*;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RestController
//=@Controller+@ResponseBody
@RequestMapping("/superadmin")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    private Map<String,Object> listArea(){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<Area> list=areaService.getAreaList();
        modelMap.put("areaList",list);
        return modelMap;
    }

    @RequestMapping(value = "/getareabyid", method = RequestMethod.GET)
    private Map<String,Object> getAreaById(Integer areaId){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        Area area=areaService.getAreaById(areaId);
        modelMap.put("area",area);
        return modelMap;

    }

//    需要前端传入一个Json对象,xml等
//    传入xml
    @RequestMapping(value = "/addarea", method = RequestMethod.POST)
    private Map<String,Object> addArea(@RequestBody Area area) {
        Map<String,Object> modelMap=new HashMap<String,Object>();
        modelMap.put("success",areaService.addArea(area));
        return modelMap;
    }

    @RequestMapping(value = "/modifyarea", method = RequestMethod.POST)
    private Map<String,Object> modifyArea(@RequestBody Area area) {
        Map<String,Object> modelMap=new HashMap<String,Object>();
        modelMap.put("success",areaService.modifyArea(area));
        return modelMap;
    }

    @RequestMapping(value = "/removearea", method = RequestMethod.GET)
    private Map<String,Object> removeArea(Integer areaId) {
        Map<String,Object> modelMap=new HashMap<String,Object>();
        modelMap.put("success",areaService.deleteArea(areaId));
        return modelMap;
    }

//    上传文件，在服务器中保存文件
    //todo
//    无法得到文件数据
    @RequestMapping(value = "/uploadfile",method = RequestMethod.POST)
    public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取文件需要上传到的路径
//        System.out.println(request.getParameter("type"));
//        System.out.println(request.getParameter("user"));
        ServletInputStream input=request.getInputStream();
//        System.out.println(strings.);
        OutputStream output = null;
        try {
            output = new FileOutputStream("1.jpg");
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }

}
