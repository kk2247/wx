package com.ky.demo.web;


import com.ky.demo.entity.Area;
import com.ky.demo.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//=@Controller+@ResponseBody
@RequestMapping("/superadmin")
public class AreaController {

    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

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
    public HashMap<String, String> uploadPicture(@RequestParam("file") MultipartFile file) throws Exception {

        HashMap<String, String> result = new HashMap<>();

        if(file.isEmpty()) {
            result.put("result", "false");
            result.put("message", "请选择一个文件");
            return result;
        }

        String UPLOAD_FOLDER = ResourceUtils.getURL("classpath:").getPath() + "static/uploadfiles/";

        File dir = new File(UPLOAD_FOLDER);
        if (!dir.isDirectory())
            dir.mkdirs();

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            //redirectAttributes.addFlashAttribute("message", "已经将 '" + file.getOriginalFilename() + "' 的文件上传成功") ;
            result.put("result", "true");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

}
