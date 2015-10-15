package com.ynyes.kjxjr.controller.front;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.icu.text.DateFormat;
import com.ynyes.kjxjr.entity.TdActivity;
import com.ynyes.kjxjr.entity.TdActivityEnterprise;
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdRegion;
import com.ynyes.kjxjr.entity.TdRegionAdmin;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.service.TdActivityEnterpriseService;
import com.ynyes.kjxjr.service.TdActivityExpertService;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdCouponService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdRegionAdminService;
import com.ynyes.kjxjr.service.TdRegionService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.ClientConstant;
import com.ynyes.kjxjr.util.SMSUtil;
import com.ynyes.kjxjr.util.SiteMagConstant;

import scala.reflect.macros.internal.macroImpl;


@Controller
@RequestMapping(value="/region")
public class TdRegionController {
	
	
	@Autowired
	TdCommonService tdCommonService;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdRegionService tdRegionService;
	
	@Autowired
	TdEnterpriseService tdEnterpriseService;
	
	@Autowired
	TdActivityService tdActivityService;
	
	@Autowired
	TdRegionAdminService tdRegionAdminService;
	
	@Autowired
	TdActivityEnterpriseService tdActivityEnterpriseService;
	
	@Autowired
	TdActivityExpertService tdActivityExpertService;

    @RequestMapping(value = "/enterprise/list", method = RequestMethod.GET)
    public String EnterpriseList(HttpServletRequest req, ModelMap map,Integer page) {
        String username = (String) req.getSession().getAttribute("regionUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null ==page)
        {
        	page = 0;
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdRegionAdmin regionAdmin = tdRegionAdminService.findbyUsername(username);
        Page<TdEnterprise> enterprisePage = tdEnterpriseService.findByAreaOrderByNumberAsc(regionAdmin.getTitle(),page, ClientConstant.pageSize);
        
        
        map.addAttribute("enterprise_page", enterprisePage);
        map.addAttribute("user", user);

        return "/client/region_enterprise_list";
    }
    
    //区县审核项目，查看详情
    @RequestMapping(value = "/enterprise/check/{id}", method = RequestMethod.GET)
    public String userEnterpriseCheck(HttpServletRequest req, ModelMap map,@PathVariable Long id) {
        String username = (String) req.getSession().getAttribute("regionUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null == id)
        {
        	return "/client/error_404";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise Enterprise = tdEnterpriseService.findOne(id);
        
        map.addAttribute("enterprise", Enterprise);
        map.addAttribute("user", user);

        return "/client/region_enterprise_check";
    }
    
    //通过审核
    @RequestMapping(value = "/enterprise/pass/{id}", method = RequestMethod.GET)
    public String userEnterprisePass(HttpServletRequest req, ModelMap map,@PathVariable Long id) {
        String username = (String) req.getSession().getAttribute("regionUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null == id)
        {
        	return "/client/error_404";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise Enterprise = tdEnterpriseService.findOne(id);
        
        Enterprise.setStatusId(1L);
        tdEnterpriseService.save(Enterprise);
        
        map.addAttribute("enterprise", Enterprise);
        map.addAttribute("user", user);

        return "redirect:/region/enterprise/list";
    }
    
    //取消审核
    @RequestMapping(value = "/enterprise/cancel/{id}", method = RequestMethod.GET)
    public String userEnterpriseCancel(HttpServletRequest req, ModelMap map,@PathVariable Long id) {
        String username = (String) req.getSession().getAttribute("regionUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null == id)
        {
        	return "/client/error_404";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise Enterprise = tdEnterpriseService.findOne(id);
        if (Enterprise.getStatusId()==1)
        {
	        Enterprise.setStatusId(0L);
	        tdEnterpriseService.save(Enterprise);
        }
        map.addAttribute("enterprise", Enterprise);
        map.addAttribute("user", user);

        return "redirect:/region/enterprise/list";
    }
    
    //重新审核
    @RequestMapping(value = "/enterprise/recall/{id}", method = RequestMethod.GET)
    public String userEnterpriseRecall(HttpServletRequest req, ModelMap map,@PathVariable Long id) {
        String username = (String) req.getSession().getAttribute("regionUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null == id)
        {
        	return "/client/error_404";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise Enterprise = tdEnterpriseService.findOne(id);
        if (Enterprise.getStatusId()==2)
        {
	        Enterprise.setStatusId(0L);
	        tdEnterpriseService.save(Enterprise);
        }
        map.addAttribute("enterprise", Enterprise);
        map.addAttribute("user", user);

        return "redirect:/region/enterprise/list";
    }
  
    @RequestMapping(value = "/activity/list", method = RequestMethod.GET)
    public String ActivitytList(HttpServletRequest req, ModelMap map,Integer page) {
        String username = (String) req.getSession().getAttribute("regionUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null ==page)
        {
        	page = 0;
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdRegionAdmin regionAdmin = tdRegionAdminService.findbyUsername(username);
        Page<TdActivity> activityPage = tdActivityService.findByRegionAndStatusIdAndPrepareOffAfterAndPrepareOnBeforeOrderByIdDesc(regionAdmin.getTitle() , 1L ,page, ClientConstant.pageSize);
        
        
        map.addAttribute("activity_page", activityPage);
        map.addAttribute("user", user);

        return "/client/region_activity_list";
    }
    
    @RequestMapping(value = "/activity/detail", method = RequestMethod.GET)
    public String ActivitytDetail(HttpServletRequest req, ModelMap map,Long id) {
        String username = (String) req.getSession().getAttribute("regionUsername");

        if (null == username) {
            return "redirect:/login";
        }
        

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdActivity activity = tdActivityService.findOne(id);
        
        map.addAttribute("mark", "region");
        map.addAttribute("pagetype", "check");
        map.addAttribute("activity", activity);
        map.addAttribute("user", user);
        
        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activity.getId()));
        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(activity.getId()));

        return "/client/activity_create";
    }
    
/**
 * 预选
 */
@RequestMapping(value = "/chooseEnterprise")
public String  regionSelectEnterprise(HttpServletRequest req,
		ModelMap map,
		Integer page,
		Long id,
		String keywords) {
    String username = (String) req.getSession().getAttribute("regionUsername");

	    if (null == username) {
	        return "redirect:/login";
	    }
	    
	    if (null == page)
	    {
	    	page = 0;
	    }
	    //搜索
	    Page<TdActivityEnterprise>enterprisePage = null;
	    if (null != keywords && !keywords.isEmpty())
	    {
	    	enterprisePage =tdActivityEnterpriseService.findByActivityIdAndSearch(id,keywords,page, ClientConstant.pageSize);
	    }
	    else
	    {
	    	enterprisePage =tdActivityEnterpriseService.findByActivityId(id,page, ClientConstant.pageSize);
	    }    	
	    
	    TdActivity activity = tdActivityService.findOne(id);
	    Long activityId = activity.getId();
	    Long statusId = 1L;
	    
	    map.addAttribute("keywords", keywords);
	   	map.addAttribute("activity", activity);
	   	map.addAttribute("activityId", activityId);
		map.addAttribute("statusId", statusId);   //类型   1：预选；2：推荐
	 	map.addAttribute("enterprise_page", enterprisePage);
	 	map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId,1L));
	    return "/client/region_selectEnterprise";
}

/**
 * 推荐
 */
@RequestMapping(value = "/recommendEnterprise")
public String  recommendEnterprise(HttpServletRequest req,
		ModelMap map,
		Integer page,
		Long id,
		Long isDone,
		String keywords) {
    String username = (String) req.getSession().getAttribute("regionUsername");

	    if (null == username) {
	        return "redirect:/login";
	    }
	    
	    if (null == page)
	    {
	    	page = 0;
	    }
	    //搜索
	    Page<TdActivityEnterprise>enterprisePage = null;
	    if (null != keywords && !keywords.isEmpty())
	    {
	    	enterprisePage =tdActivityEnterpriseService.findByActivityIdAndSearch(id,keywords,page, ClientConstant.pageSize);
	    }
	    else
	    {
	    	enterprisePage =tdActivityEnterpriseService.findByActivityId(id,page, ClientConstant.pageSize);
	    }    	
	    
	    TdActivity activity = tdActivityService.findOne(id);
	    Long activityId = activity.getId();
	    Long statusId = 2L;
	    
	    if (null != isDone && isDone == 1)
	    {
	    	map.addAttribute("done", isDone);
	    }
	    map.addAttribute("keywords", keywords);
	   	map.addAttribute("activity", activity);
	   	map.addAttribute("activityId", activityId);
		map.addAttribute("statusId", statusId);   //选择类型   1：预选；2：推荐
	 	map.addAttribute("enterprise_page", enterprisePage);
	 	map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId,2L));
	    return "/client/region_selectEnterprise";
}


@RequestMapping(value = "/addEnterprise")
public String  regionAddEnterprise(HttpServletRequest req,Long id,Long activityId,Long statusId,String reason,
		ModelMap map) {
    String username = (String) req.getSession().getAttribute("regionUsername");

    if (null == username) {
        return "redirect:/login";
    }
	List<TdActivityEnterprise> selectedEnterpriseList = tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId,statusId);
    
	if (selectedEnterpriseList.size() >19)
	{
		map.addAttribute("msg", "最多添加20个项目！！");
	    map.addAttribute("statusId", statusId);
	    map.addAttribute("activityId",activityId);
	    map.addAttribute("selected_enterprise_list", selectedEnterpriseList);
	    return "/client/region_selected_enterprise";
	}
  
    if(null != id&&null !=activityId&&null !=statusId)
    {
    	TdActivityEnterprise activityenterprise = tdActivityEnterpriseService.findOne(id);
    	TdEnterprise enterprise = tdEnterpriseService.findOne(activityenterprise.getEnterpriseId());
    	TdActivity activity = tdActivityService.findOne(activityId);
    	
    	
    	if (null != activityenterprise)
    	{
    		activityenterprise.setCreateTime(new Date());
    		activityenterprise.setEnterpriseTitle(enterprise.getTitle());
    		activityenterprise.setActivityTitle(activity.getTitle());
    		activityenterprise.setArea(enterprise.getArea());
    		activityenterprise.setType(enterprise.getType());
    		activityenterprise.setStatusId(statusId);
    		activityenterprise.setReason(reason);
    		tdActivityEnterpriseService.save(activityenterprise);
    	}
    	
    }
    
    map.addAttribute("statusId", statusId);
    map.addAttribute("activityId",activityId);
    map.addAttribute("selected_enterprise_list",  tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId,statusId));
    return "/client/region_selected_enterprise";
}


@RequestMapping(value = "/removeEnterprise")
public String  regionRemoveEnterprise(HttpServletRequest req,Long id,Long activityId,Long statusId,
		ModelMap map) {
    String username = (String) req.getSession().getAttribute("regionUsername");

    if (null == username) {
        return "redirect:/login";
    }
  
    if(null != id)
    {
    	TdActivityEnterprise activityEnterprise = tdActivityEnterpriseService.findOne(id);
    	if (null != activityEnterprise)
    	{
    		activityEnterprise.setStatusId(0L);
    		tdActivityEnterpriseService.save(activityEnterprise);
    	}
    }
    
    map.addAttribute("statusId",statusId);
    map.addAttribute("activityId",activityId);
    map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId,statusId));
    return "/client/region_selected_enterprise";
}

//完成项目选择
@RequestMapping(value = "/enterprise/finish", method = RequestMethod.GET)
	public String enterpriseFinish(HttpServletRequest req, ModelMap map,Long id,Long statusId) {
			    String username = (String) req.getSession().getAttribute("regionUsername");
			
			    if (null == username) {
			        return "redirect:/login";
			    }
			
			    tdCommonService.setHeader(map, req);
			    
			    TdActivity activity = tdActivityService.findOne(id);
			    
			    if(1 == statusId)
			    {
			    	activity.setStatusChoose(1L);
			
			    	tdActivityService.save(activity);
			    }
			    else  if(2 == statusId)
			    {
			    	activity.setStatusRecommend(1L);
			    	tdActivityService.save(activity);
			    }
			    
			    if (null != activity.getStatusChoose() && 1 == activity.getStatusChoose()&&null != activity.getStatusRecommend() && 1 == activity.getStatusRecommend())
			    {
			    	activity.setRegionStatusId(1L);
			    	tdActivityService.save(activity);
			    }
			    map.addAttribute("activity", activity);
			    
			    TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
			    map.addAttribute("user", user);
			
			    return "redirect:/region/activity/list";
		}
    
//导出
// 区县科委推荐项目汇总表
@SuppressWarnings("deprecation")
@RequestMapping(value="/export/recommend")
public String exportRecommend(
                            Long activityId,
                            ModelMap map,
                            String exportUrl,
                            HttpServletResponse resp,
                            HttpServletRequest req){
    String username = (String) req.getSession().getAttribute("regionUsername");
    if (null == username) {
        return "redirect:/login";
    }

        	exportUrl = SiteMagConstant.backupPath;
    
			if (null != exportUrl) {
				List<TdActivityEnterprise> activityEnterpriseList = tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId, 2L);
		
    /**  
		 * @author lc
		 * @注释：根据不同条件导出excel文件
		 */
      // 第一步，创建一个webbook，对应一个Excel文件  
      HSSFWorkbook wb = new HSSFWorkbook();  
      // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
      HSSFSheet sheet = wb.createSheet("activityEnterprise");  
      //列宽
      
      sheet.setColumnWidth((short) 0 , 1000);
      sheet.setColumnWidth((short) 1 , 1500);
      sheet.setColumnWidth((short) 2 , 10000);
      sheet.setColumnWidth((short) 3 , 2000);
      sheet.setColumnWidth((short) 4 , 4000);
      sheet.setColumnWidth((short) 5 , 4000);
      sheet.setColumnWidth((short) 6 , 20000);
      sheet.setColumnWidth((short) 7 , 10000);
      
      
      
      
      // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
      HSSFRow row = sheet.createRow((int) 0);  
      
      sheet.addMergedRegion(new Region((short) 0 , (short) 0 , (short) 1 , (short) 7));     //标题
      sheet.addMergedRegion(new Region((short) 2 , (short) 0 , (short) 2 , (short) 2));     //公章
      // 第四步，创建单元格，并设置值表头 设置表头居中  
      HSSFCellStyle style = wb.createCellStyle();  
      style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
      style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);    //设置边框样式
      style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
      style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);  
      style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);  
      
      
      HSSFCellStyle style1 = wb.createCellStyle();  
      style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
      style1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);    //设置边框样式
      style1.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
      style1.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);  
      style1.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);  
      
      
      HSSFCellStyle style2 = wb.createCellStyle();  
      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
      style2.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);    //设置边框样式
      style2.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
      style2.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);  
      style2.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);  
      
      HSSFCellStyle title = wb.createCellStyle();  
      title.setBorderBottom(HSSFCellStyle.BORDER_NONE);    //设置边框样式
      title.setBorderRight(HSSFCellStyle.BORDER_NONE);
      title.setBorderLeft(HSSFCellStyle.BORDER_NONE);  
      title.setBorderTop(HSSFCellStyle.BORDER_NONE);  
      title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
      title.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中格式
      
      //盖章
      HSSFCellStyle left = wb.createCellStyle();  
      left.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);    //设置边框样式
      left.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 居格式
      
      //日期
      HSSFCellStyle right = wb.createCellStyle();  
      right.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);    //设置边框样式
      right.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 格式
      
      
      HSSFCell cell = row.createCell((short) 0);  
      cell.setCellValue("区县科委推荐项目汇总表"+"（"+activityEnterpriseList.get(0).getArea()+activityEnterpriseList.get(0).getActivityTitle()+"）");  
      cell.setCellStyle(title);
      
      row =sheet.createRow((int) 2);
      cell = row.createCell((short) 0);  
      cell.setCellValue("推荐单位（章）");  
      cell.setCellStyle(left);
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String date = sdf.format(new Date());
      cell = row.createCell((short) 6);  
      cell.setCellValue("日期：");  
      cell.setCellStyle(right);
      cell = row.createCell((short) 7);  
      cell.setCellValue(date);  
      cell.setCellStyle(style);
      
      
      
      row =sheet.createRow((int) 3);
      cell = row.createCell((short) 0);  
      cell.setCellValue("序号");  
      cell.setCellStyle(style);
      
      cell = row.createCell((short) 1);  
      cell.setCellValue("编号");  
      cell.setCellStyle(style);  
      
      cell = row.createCell((short) 2);  
      cell.setCellValue("公司（团队）名称");  
      cell.setCellStyle(style);  
      
      cell = row.createCell((short) 3);  
      cell.setCellValue("联系人");  
      cell.setCellStyle(style);  
      
      cell = row.createCell((short) 4);  
      cell.setCellValue("手机");  
      cell.setCellStyle(style);  
    
      cell = row.createCell((short) 5);  
      cell.setCellValue("QQ/MSN");  
      cell.setCellStyle(style);  
      
      cell = row.createCell((short) 6);  
      cell.setCellValue("项目简介");  
      cell.setCellStyle(style);  
      
      cell = row.createCell((short) 7);  
      cell.setCellValue("推荐理由");  
      cell.setCellStyle(style);  
      
			
		if (null != exportUrl) {
			if (ImportData(activityEnterpriseList, row, cell, sheet,style)) {
				download(wb, username, resp);
			}         
		}  
			}
    return "/redirect:/region/activity/list";
}

/**
	 * @author lc
	 * @注释：将page中的订单数据存入excel表格中
	 */
 @SuppressWarnings("deprecation")
	public boolean ImportData(List<TdActivityEnterprise> activityEnterpriseList, HSSFRow row, HSSFCell cell, HSSFSheet sheet ,HSSFCellStyle style){
	 	
        	for (int i = 0; i < activityEnterpriseList.size(); i++)  
            {  
        	 				
                row = sheet.createRow((int) i + 4);  
                TdActivityEnterprise tdActivityEnterprise = activityEnterpriseList.get(i);  
                //获取用户信息
//                TdUser tdUser = tdUserService.findByUsername(tdOrder.getUsername());
                // 第四步，创建单元格，并设置值  
                cell = row.createCell((short) 0);
                cell.setCellValue(i+1);
                cell.setCellStyle(style); 
                cell = row.createCell((short) 1);
                cell.setCellValue(tdActivityEnterprise.getNumber());
                cell.setCellStyle(style);
                cell = row.createCell((short) 2);
                cell.setCellStyle(style);
                cell.setCellValue(tdActivityEnterprise.getEnterpriseTitle()); 
                cell = row.createCell((short) 3);
                cell.setCellStyle(style);
                cell.setCellValue(tdActivityEnterprise.getContact()); 
                cell = row.createCell((short) 4);
                cell.setCellStyle(style);
                cell.setCellValue(tdActivityEnterprise.getMobile());
                cell = row.createCell((short) 5);
                cell.setCellStyle(style);
                cell.setCellValue(tdActivityEnterprise.getQQ());
                cell = row.createCell((short) 6);
                cell.setCellStyle(style);
                cell.setCellValue(tdActivityEnterprise.getProfile());
                cell = row.createCell((short) 7);
                cell.setCellValue(tdActivityEnterprise.getReason()); 
                cell.setCellStyle(style);
             
            } 
 	return true;
 }
 /**
	 * @author lc
	 * @注释：文件写入和下载
	 */
 public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp){
 	 try  
      {  
	          FileOutputStream fout = new FileOutputStream(exportUrl+"activityEnterprise.xls");  
//	          OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");	                       	     
	          wb.write(fout);  
	          fout.close();
      }catch (Exception e)  
      {  
          e.printStackTrace();  
      } 
 	 OutputStream os;
		 try {
				os = resp.getOutputStream();
				File file = new File(exportUrl + "activityEnterprise.xls");
              
          if (file.exists())
              {
                try {
                      resp.reset();
                      resp.setHeader("Content-Disposition", "attachment; filename="
                              + "activityEnterprise.xls");
                      resp.setContentType("application/octet-stream; charset=utf-8");
                      os.write(FileUtils.readFileToByteArray(file));
                      os.flush();
                  } finally {
                      if (os != null) {
                          os.close();
                      }
                  }
          }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		 return true;	
 }

}
