package com.ynyes.kjxjr.controller.management;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ynyes.kjxjr.service.TdArticleCategoryService;
import com.ynyes.kjxjr.service.TdArticleService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.util.SiteMagConstant;

import net.sf.json.JSONObject;

@Controller
public class TdUploadImageController 
{
    String ImageRoot = SiteMagConstant.imagePath;

    @Autowired
    TdArticleCategoryService tdArticleCategoryService;

    @Autowired
    TdArticleService tdArticleService;
    
    @Autowired
    TdEnterpriseService tdEnterpriseService;
    
	@RequestMapping(value = "/editor/upload",method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, Object> uploadImage(String action, @RequestParam MultipartFile imgFile, HttpServletRequest req, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();

        res.put("error", 1);


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
        
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
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
}
