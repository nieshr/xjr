package com.ynyes.kjxjr.controller.management;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ynyes.kjxjr.entity.TdActivity;
import com.ynyes.kjxjr.entity.TdActivityEnterprise;
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.service.TdActivityEnterpriseService;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdArticleCategoryService;
import com.ynyes.kjxjr.service.TdArticleService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.util.SiteMagConstant;


import net.sf.json.JSONObject;

/**
 * 后台首页控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value = "/Verwalter")
public class TdUploadController {

    String ImageRoot = SiteMagConstant.imagePath;

    @Autowired
    TdArticleCategoryService tdArticleCategoryService;

    @Autowired
    TdArticleService tdArticleService;
    
    @Autowired
    TdEnterpriseService tdEnterpriseService;
    
    @Autowired
    TdActivityEnterpriseService tdActivityEnterpriseService;
    
    @Autowired
    TdActivityService tdActivityService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(String action,
            @RequestParam MultipartFile Filedata, HttpServletRequest req,HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("status", 0);
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username) {
            res.put("msg", "请重新登录！");
            return res;
        }

        if (null == Filedata || Filedata.isEmpty()) {
            res.put("msg", "图片不存在");
            return res;
        }

        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName;
            if (ext.equalsIgnoreCase(".mp4"))
            {
            	 fileName = "kjxjrVideo" + ext;
            }
            else
            {
            	 fileName = sdf.format(dt) + ext;
            }
            

            String uri = ImageRoot + "/" + fileName;


            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();

            res.put("status", 1);
            res.put("msg", "上传文件成功！");
            res.put("path", "/images/" + fileName);
            res.put("thumb", "/images/" + fileName);
            res.put("name", name);
            res.put("size", Filedata.getSize());
            res.put("ext", ext.substring(1));

        } catch (Exception e) {
            res.put("status", 0);
            res.put("msg", "上传文件失败！");
        }
        response.reset();
        response.setCharacterEncoding("UTF-8");
        if(ext.equalsIgnoreCase(".mp4"))
        {
        	response.setContentType("application/octet-stream");
        }
        else{
        	 response.setContentType("text/html");
        }

       
        
      
//        response.setContentType("text/html");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject jsonObject = JSONObject.fromObject(res);
            writer.println(jsonObject);  //想办法把map转成json
            writer.flush();
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
        


        return null;

    }
    
    @RequestMapping(value = "/dataUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> dataUpload(String action,
            @RequestParam MultipartFile Filedata, HttpServletRequest req,HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("status", 0);
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username) {
            res.put("msg", "请重新登录！");
            return res;
        }

        if (null == Filedata || Filedata.isEmpty()) {
            res.put("msg", "图片不存在");
            return res;
        }

        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName;
        	fileName = sdf.format(dt) + ext;

            

            String uri = ImageRoot + "/" + fileName;


            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();

            res.put("status", 1);
            res.put("msg", "上传文件成功！");
            res.put("path",  fileName);
            res.put("thumb", "/images/" + fileName);
            res.put("name", name);
            res.put("size", Filedata.getSize());
            res.put("ext", ext.substring(1));

        } catch (Exception e) {
            res.put("status", 0);
            res.put("msg", "上传文件失败！");
        }
        response.reset();
        response.setCharacterEncoding("UTF-8");
    	 response.setContentType("text/html");
      
//        response.setContentType("text/html");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject jsonObject = JSONObject.fromObject(res);
            writer.println(jsonObject);  //想办法把map转成json
            writer.flush();
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
        
        return null;
    }
    
    @RequestMapping(value = "/upload/client", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadclient(String action,
            @RequestParam MultipartFile Filedata, HttpServletRequest req) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("status", 0);
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username) {
            res.put("msg", "请重新登录！");
            return res;
        }

        if (null == Filedata || Filedata.isEmpty()) {
            res.put("msg", "图片不存在");
            return res;
        }

        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName = sdf.format(dt) + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();

            res.put("status", 1);
            res.put("msg", "上传文件成功！");
            res.put("path", "/images/" + fileName);
            res.put("thumb", "/images/" + fileName);
            res.put("name", name);
            res.put("size", Filedata.getSize());
            res.put("ext", ext.substring(1));

        } catch (Exception e) {
            res.put("status", 0);
            res.put("msg", "上传文件失败！");
        }

        return res;

    }

    @RequestMapping(value = "/editor/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editorUpload(String action, @RequestParam MultipartFile imgFile, HttpServletRequest req,HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("error", 1);

        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            res.put("msg", "请重新登录！");
            return res;
        }

        if (null == imgFile || imgFile.isEmpty() || null == imgFile.getName()) {
            res.put("msg", "图片不存在");
            return res;
        }

        String name = imgFile.getOriginalFilename();
        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = imgFile.getBytes();

            Date dt = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName = sdf.format(dt) + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();

            res.put("error", 0);
            res.put("msg", "上传文件成功！");
            res.put("url", "/images/" + fileName);

        } catch (Exception e) {
            res.put("msg", "上传文件失败！");
        }

        //马德敬改的
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
      
//        response.setContentType("text/html");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject jsonObject = JSONObject.fromObject(res);
            writer.println(jsonObject);  //想办法把map转成json
            writer.flush();
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
        return res;

    }
    
    /*===============测===============*/
    @RequestMapping(value = "/baidu/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> baiduUpload(String action, @RequestParam MultipartFile imgFile, HttpServletRequest req,HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("error", 1);

        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            res.put("msg", "请重新登录！");
            return res;
        }

        if (null == imgFile || imgFile.isEmpty() || null == imgFile.getName()) {
            res.put("msg", "图片不存在");
            return res;
        }

        String name = imgFile.getOriginalFilename();
        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = imgFile.getBytes();

            Date dt = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName = sdf.format(dt) + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();

            res.put("error", 0);
            res.put("msg", "上传文件成功！");
            res.put("url", "/images/" + fileName);

        } catch (Exception e) {
            res.put("msg", "上传文件失败！");
        }

        //马德敬改的
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
      
//        response.setContentType("text/html");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject jsonObject = JSONObject.fromObject(res);
            writer.println(jsonObject);  //想办法把map转成json
            writer.flush();
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
        return res;

    }
    
    

    
    /*===============试===============*/
    
	@RequestMapping(value = "/enterprise/upload", method = RequestMethod.POST)
    public String enterUpload(String action,Long enterpriseId, Long id,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
//          SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
          TdEnterprise enterprise = tdEnterpriseService.findOne(enterpriseId);
          //找出改企业参加活动个数。
          List<TdActivityEnterprise> aeList = tdActivityEnterpriseService.findByEnterpriseId(enterpriseId);
          List<TdActivity> activityList = new ArrayList<>();
          for (TdActivityEnterprise ae : aeList)
          {
          	TdActivity activity = tdActivityService.findOne(ae.getActivityId()); 
          	if (null != activity.getStatusId() && activity.getStatusId() == 2)
          	{
          		activityList.add(activity);
          	}
          }
          	
          Integer size = activityList.size();
          String fileName ="Num_"+tdEnterpriseService.findbyUsername(tdEnterpriseService.findOne(enterpriseId).getUsername()).getNumber()+"_"+ size + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            
            String fileUrl = enterprise.getFileUrl();
            		
            enterprise.setFileUrl(fileName);
            tdEnterpriseService.save(enterprise);
      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/Verwalter/user/edit?done="+done
        		+"&id="+id;

    }

}
