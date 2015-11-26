package com.ynyes.kjxjr.controller.front;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.util.SiteMagConstant;

@Controller
@RequestMapping(value = "/client")
public class TdClientUploadController {

	String ImageRoot = SiteMagConstant.imagePath;
	
	@Autowired
	TdActivityService tdActivityService;
	@Autowired
	TdEnterpriseService tdEnterpriseService;
	
	@Autowired
	TdActivityEnterpriseService tdActivityEnterpriseService;
	
	@RequestMapping(value = "/recommend/upload", method = RequestMethod.POST)
    public String upload(String action,Long activityId,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("regionUsername");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));
        //限制文件类型
        if(!ext.equalsIgnoreCase(".jpg") && !ext.equalsIgnoreCase(".pdf"))
        {
            Long done = 2L;
            return "redirect:/region/recommendEnterprise?id="+activityId
            		+"&isDone="+done;
        }

        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName ="recommend"+activityId/*+ "_" + sdf.format(dt) */+ ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            TdActivity tdActivity = tdActivityService.findOne(activityId);
            tdActivity.setFileUrl(fileName);
            tdActivity.setStatusId(0L);
            tdActivityService.save(tdActivity);
      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/region/recommendEnterprise?id="+activityId
        		+"&isDone="+done;

    }
	
	@RequestMapping(value = "/enterprise/upload", method = RequestMethod.POST)
    public String enterUpload(String action,Long id,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("enterpriseUsername");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));

        //限制文件类型
        if(!ext.equalsIgnoreCase(".jpg") && !ext.equalsIgnoreCase(".pdf") && !ext.equalsIgnoreCase(".png"))
        {
            Long done = 2L;
            return "redirect:/enterprise/upload?done="+done
            		+"&id="+id;
        }
        
        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
//            SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
            TdEnterprise enterprise = tdEnterpriseService.findOne(id);
            //找出改企业参加活动个数。
            List<TdActivityEnterprise> aeList = tdActivityEnterpriseService.findByEnterpriseId(id); 
            Integer size = aeList.size();
            String fileName ="Num_"+tdEnterpriseService.findbyUsername(username).getNumber()+"_"+ size + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            
            String fileUrl = enterprise.getFileUrl();
            		
            enterprise.setFileUrl(fileName);
            enterprise.setStatusId(0L);
            tdEnterpriseService.save(enterprise);
      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/enterprise/upload?done="+done
        		+"&id="+id;

    }
	
	//企业商业计划书
	@RequestMapping(value = "/dataBusiness/upload", method = RequestMethod.POST)
    public String dataBusiness(String action,Long id ,Long activityId,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("enterpriseUsername");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = Filedata.getBytes();
            
            Date dt = new Date(System.currentTimeMillis());
//            SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
            TdEnterprise enterprise = tdEnterpriseService.findOne(id);
            //找出改企业参加活动个数。
            List<TdActivityEnterprise> aeList = tdActivityEnterpriseService.findByEnterpriseId(id); 
            Integer size = aeList.size();
            String fileName ="DataBusiness_"+tdEnterpriseService.findbyUsername(username).getNumber()+"_"+ size + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            
            
            enterprise.setDataBusiness(fileName);
            tdEnterpriseService.save(enterprise);
            
            TdActivityEnterprise ae = tdActivityEnterpriseService.findByActivityIdAndEnterpriseId(activityId, id);
            ae.setDataBusiness(fileName);
            tdActivityEnterpriseService.save(ae);
      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/enterprise/data?done="+done
        		+"&activityId="+activityId;
    }
	
	//企业可行性报告
	@RequestMapping(value = "/dataPossible/upload", method = RequestMethod.POST)
    public String dataPossible(String action,Long id,Long activityId,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("enterpriseUsername");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
//            SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
            TdEnterprise enterprise = tdEnterpriseService.findOne(id);
            //找出改企业参加活动个数。
            List<TdActivityEnterprise> aeList = tdActivityEnterpriseService.findByEnterpriseId(id); 
            Integer size = aeList.size();
            String fileName ="DataPossible_"+tdEnterpriseService.findbyUsername(username).getNumber()+"_"+ size + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            
            enterprise.setDataPossible(fileName);
            tdEnterpriseService.save(enterprise);
      
            TdActivityEnterprise ae = tdActivityEnterpriseService.findByActivityIdAndEnterpriseId(activityId, id);
            ae.setDataPossible(fileName);
            tdActivityEnterpriseService.save(ae);
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/enterprise/data?done="+done
        		+"&activityId="+activityId;
    }
	
	//企业其他
	@RequestMapping(value = "/dataOther/upload", method = RequestMethod.POST)
    public String dataOther(String action,Long id,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("enterpriseUsername");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
//            SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
            TdEnterprise enterprise = tdEnterpriseService.findOne(id);
            //找出改企业参加活动个数。
            List<TdActivityEnterprise> aeList = tdActivityEnterpriseService.findByEnterpriseId(id); 
            Integer size = aeList.size();
            String fileName ="DataOther_"+tdEnterpriseService.findbyUsername(username).getNumber()+"_"+ size + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            
            enterprise.setDataOther(fileName);
            tdEnterpriseService.save(enterprise);
            
            TdActivityEnterprise ae = tdActivityEnterpriseService.findByActivityIdAndEnterpriseId(activityId, id);
            ae.setDataOther(fileName);
            tdActivityEnterpriseService.save(ae);

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/enterprise/data?done="+done;
    }
	
	@RequestMapping(value = "/activity/upload", method = RequestMethod.POST)
    public String activytyUpload(String action,Long id,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("activityUsername");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));

        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName = "activity"+id+"_"+sdf.format(dt) + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            TdActivity activity = tdActivityService.findOne(id);
            if(null == activity){
            	activity = new TdActivity();
            }
            activity.setDownload(fileName);
            tdActivityService.save(activity);
      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/activity/edit?done="+done
        		+"&id="+id;

    }
	
	//上传ppt模板
	@RequestMapping(value = "/activity/pptupload", method = RequestMethod.POST)
    public String activityPptUpload(String action,Long id,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("activityUsername");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));
        
        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName = "PPTmodule"+id+"_"+ ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            TdActivity activity = tdActivityService.findOne(id);
            if(null == activity){
            	activity = new TdActivity();
            }
            activity.setPptUrl(fileName);
            tdActivityService.save(activity);
      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/activity/edit?done="+done
        		+"&id="+id;

    }	
	
	
	@RequestMapping(value = "/enterprise/pptupload", method = RequestMethod.POST)
    public String enterprisePptUpload(String action,Long id, 
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("enterpriseUsername");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));
        //限制文件类型
        if(!ext.equalsIgnoreCase(".ppt") && !ext.equalsIgnoreCase(".pdf"))
        {
            Long done = 2L;
            return "redirect:/enterprise/data?done="+done;
        }
        
        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName = "PPT_"+id+"_"+activityId + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            TdEnterprise enterprise = tdEnterpriseService.findOne(id);
            if(null == enterprise){
            	enterprise = new TdEnterprise();
            }
//            if (enterprise.getStatusId()==1 &&null != enterprise.getIsSelect() && enterprise.getIsSelect()==true)
//            {
                enterprise.setPptUrl(fileName);
                tdEnterpriseService.save(enterprise);
//            }
            
            TdActivityEnterprise ae = tdActivityEnterpriseService.findByActivityIdAndEnterpriseId(activityId, id);
            ae.setPptUrl(fileName);
            tdActivityEnterpriseService.save(ae);
      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/enterprise/data?done="+done;

    }	
}
