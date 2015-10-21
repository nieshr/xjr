package com.ynyes.kjxjr.controller.front;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
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
import com.ynyes.kjxjr.entity.TdEnterpriseGrade;
import com.ynyes.kjxjr.entity.TdRegion;
import com.ynyes.kjxjr.entity.TdRegionAdmin;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.entity.TdUserMessage;
import com.ynyes.kjxjr.service.TdActivityEnterpriseService;
import com.ynyes.kjxjr.service.TdActivityExpertService;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdCouponService;
import com.ynyes.kjxjr.service.TdEnterpriseGradeService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdRegionAdminService;
import com.ynyes.kjxjr.service.TdRegionService;
import com.ynyes.kjxjr.service.TdUserMessageService;
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
	TdEnterpriseGradeService tdEnterpriseGradeService;
	
	@Autowired
	TdActivityExpertService tdActivityExpertService;
	
	@Autowired
	TdUserMessageService tdUserMessageService;

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
        TdEnterprise enterprise = tdEnterpriseService.findOne(id);
        
        //行业所属是多选。。。。
        if (null != enterprise.getType())
        {
        	String type[] = enterprise.getType().split(",");
        	map.addAttribute("enterpriseType", type);
        }
        
        if (null != enterprise.getDataAble())
        {
        	String dataAble[] = enterprise.getDataAble().split(",");
        	map.addAttribute("dataAble", dataAble);
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        Date lastyear1 = calendar.getTime();
        map.addAttribute("lastyear1", lastyear1);
        
        calendar.add(Calendar.YEAR, -1);
        Date lastyear2 = calendar.getTime();
        map.addAttribute("lastyear2", lastyear2);
        
        calendar.add(Calendar.YEAR, -1);
        Date lastyear3 = calendar.getTime();
        map.addAttribute("lastyear3", lastyear3);
        
        map.addAttribute("enterprise", enterprise);
        map.addAttribute("user", user);
        map.addAttribute("usertype", "region");
        
        return "/client/region_enterprise_check";
    }
    
    //通过审核
    @RequestMapping(value = "/enterprise/pass/{id}", method = RequestMethod.GET)
    public String userEnterprisePass(HttpServletRequest req,HttpServletResponse res , ModelMap map,@PathVariable Long id) {
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
        if (Enterprise.getStatusId()==0||Enterprise.getStatusId()==3)
        {
            Enterprise.setStatusId(1L);
            tdEnterpriseService.save(Enterprise);
            
            //短信提醒
            smsPass(Enterprise.getUsermobile(),res,req);
            
            //站内信
            TdRegionAdmin admin = tdRegionAdminService.findbyUsername(username);
            TdUserMessage message = new TdUserMessage();
            message.setEnterpriseId(id);
            message.setRegionAdminId(admin.getId());
            message.setContent("【审核】恭喜您通过"+admin.getRegion()+"地区的审核，请登录个人中心查看详情！");
            message.setTitle("通过审核");
            message.setStatusId(0L);
            message.setSpeaker(1L);
            message.setTime(new Date());
            tdUserMessageService.save(message);
            
        }
        
        map.addAttribute("enterprise", Enterprise);
        map.addAttribute("user", user);

        return "redirect:/region/enterprise/list";
    }
    
    //发短信【过审核】
	public Map<String, Object> smsPass(String mobile, HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);


		HttpSession session = request.getSession();

		String info = "您已通过区县审核，查看详情请登录个人中心。【科技小巨人】";
		System.err.println("errormsg");
		String content = null;
		try {
			content = URLEncoder.encode(info, "GB2312");
			System.err.println(content);
		} catch (Exception e) {
			e.printStackTrace();
			res.put("message", "发送失败！");
			return res;
		}
		String url = "http://www.ht3g.com/htWS/BatchSend.aspx?CorpID=CQDL00059&Pwd=644705&Mobile=" + mobile
				+ "&Content=" + content;
		StringBuffer fanhui = null;
		try {
			URL u = new URL(url);
			URLConnection connection = u.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			httpConn.setRequestProperty("Content-type", "text/html");
			httpConn.setRequestProperty("Accept-Charset", "utf-8");
			httpConn.setRequestProperty("contentType", "utf-8");
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader reader = null;
			StringBuffer resultBuffer = new StringBuffer();
			String tempLine = null;

			if (httpConn.getResponseCode() >= 300) {
				res.put("message", "HTTP Request is not success, Response code is " + httpConn.getResponseCode());
				return res;
			}

			try {
				inputStream = httpConn.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);

				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
				}

				fanhui = resultBuffer;

			} finally {

				if (reader != null) {
					reader.close();
				}

				if (inputStreamReader != null) {
					inputStreamReader.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			res.put("message", "发送失败！");
			return res;
		}
		res.put("status", 0);
		res.put("message", fanhui);
		return res;
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
        if (Enterprise.getStatusId()==1 ||Enterprise.getStatusId()==0)
        {
	        Enterprise.setStatusId(3L);
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
        if (Enterprise.getStatusId()==1 ||Enterprise.getStatusId()==2)
        {
	        Enterprise.setStatusId(4L);
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
		Long numwarn,
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
	    
	   if ( tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId,2L).size() ==20)
	   {
		   map.addAttribute("numfull", 1);
		   if (null != numwarn&& numwarn == 1)
		   {
			   map.addAttribute("numwarn", 1);
		   }
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
    
	if (statusId == 2 &&selectedEnterpriseList.size() >19)
	{
		Long numwarn = 1L;
	    return "redirect:/region/recommendEnterprise"+"?id="+activityId+"&numwarn="+numwarn;
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
    		
    		if (2 == statusId)
    		{
	        	List<TdEnterpriseGrade> enterpriseGradeList = tdEnterpriseGradeService.findByActivityIdOrderByIdAsc(activityId);
	        	int i = 0;
	       
	        	for (TdEnterpriseGrade grade : enterpriseGradeList)
	        	{
	        		
	        		if (  i % 20== 0)
	        			if(null == grade.getNumber())
	        		{
	        			grade.setNumber(enterprise.getNumber());
	        			grade.setEnterpriseId(activityenterprise.getEnterpriseId());
	        			grade.setActivityId(activityId);
	        			tdEnterpriseGradeService.save(grade);
	        		}
	        		else if (null != grade.getNumber())
	        		{
	        			i = i-1;
	        		}
	        		i = i+1; 
	        	}
    		}
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
    	
		if (2 == statusId)
		{
        	List<TdEnterpriseGrade> enterpriseGradeList = tdEnterpriseGradeService.findByEnterpriseIdAndActivityId(activityEnterprise.getEnterpriseId(), activityId);
        
        	for (TdEnterpriseGrade grade:enterpriseGradeList)
        	{
        			grade.setNumber(null);
        			grade.setEnterpriseId(null);
        			tdEnterpriseGradeService.save(grade);
        	}
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

    List<TdActivityEnterprise> selectedEnter = tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId, 2L);
    if (selectedEnter.size() != 20)
    {
    	map.addAttribute("warnmsg", "请选出20个推荐项目！");
    	map.addAttribute("statusId", 2);
    	return "redirect:/region/recommendEnterprise"+"?id="+activityId;
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
      
      //打印设置
      HSSFPrintSetup ps = sheet.getPrintSetup();
      ps.setLandscape(true); //打印方向，true:横向，false:纵向
      ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
      sheet.setMargin(HSSFSheet.BottomMargin, (double)0.3); //页边距（下）
      sheet.setMargin(HSSFSheet.LeftMargin, (double)0.3); //页边距（左）
      sheet.setMargin(HSSFSheet.RightMargin, (double)0.3); //页边距（右）
      sheet.setMargin(HSSFSheet.TopMargin, (double)0.3); //页边距（上）
      sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
      sheet.setVerticallyCenter(true); //设置打印页面为垂直居中
      
      //列宽
      sheet.setColumnWidth((short) 0 , 4*256);
      sheet.setColumnWidth((short) 1 , 6*256);
      sheet.setColumnWidth((short) 2 , 30*256);
      sheet.setColumnWidth((short) 3 , 8*256);
      sheet.setColumnWidth((short) 4 , 13*256);
      sheet.setColumnWidth((short) 5 , 14*256);
      sheet.setColumnWidth((short) 6 , 50*256);
      sheet.setColumnWidth((short) 7 , 16*256);
      
      
      
      
      // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
      HSSFRow row = sheet.createRow((int) 0);  
      
      sheet.addMergedRegion(new Region((short) 0 , (short) 0 , (short) 1 , (short) 7));     //标题
      sheet.addMergedRegion(new Region((short) 2 , (short) 0 , (short) 2 , (short) 2));     //公章
      // 第四步，创建单元格，并设置值表头 设置表头居中  
      HSSFCellStyle style = wb.createCellStyle();  
      style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
      style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
      
      
      HSSFCellStyle style1 = wb.createCellStyle();  
      style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
      style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
      style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
      style1.setBorderTop(HSSFCellStyle.BORDER_THIN);  
      
      
      HSSFCellStyle style2 = wb.createCellStyle();  
      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
      
      HSSFCellStyle title = wb.createCellStyle();  
      title.setBorderBottom(HSSFCellStyle.BORDER_NONE);    //设置边框样式
      title.setBorderRight(HSSFCellStyle.BORDER_NONE);
      title.setBorderLeft(HSSFCellStyle.BORDER_NONE);  
      title.setBorderTop(HSSFCellStyle.BORDER_NONE);  
      title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
      title.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中格式
      HSSFFont font = wb.createFont();
      font.setFontName("黑体");
      font.setFontHeightInPoints((short) 12);//设置字体大小
      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
      title.setFont(font);//选择需要用到的字体格式
      
      //盖章
      HSSFCellStyle left = wb.createCellStyle();  
      left.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
      left.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 居格式
      
      //日期
      HSSFCellStyle right = wb.createCellStyle();  
      right.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
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
      cell.setCellStyle(left);
      
      
      
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
	          FileOutputStream fout = new FileOutputStream(exportUrl+"cqkjxjr02.xls");  
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
				File file = new File(exportUrl + "cqkjxjr02.xls");
              
          if (file.exists())
              {
                try {
                      resp.reset();
                      resp.setHeader("Content-Disposition", "attachment; filename="
                              + "cqkjxjr02.xls");
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
