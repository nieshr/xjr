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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.kjxjr.entity.TdActivity;
import com.ynyes.kjxjr.entity.TdActivityEnterprise;
import com.ynyes.kjxjr.entity.TdActivityExpert;
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdEnterpriseGrade;
import com.ynyes.kjxjr.entity.TdExpert;
import com.ynyes.kjxjr.entity.TdExpertCoachEnterprise;
import com.ynyes.kjxjr.entity.TdRegionAdmin;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.entity.TdUserMessage;
import com.ynyes.kjxjr.service.TdActivityEnterpriseService;
import com.ynyes.kjxjr.service.TdActivityExpertService;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdActivityTypeService;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdCouponService;
import com.ynyes.kjxjr.service.TdEnterpriseGradeService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdEnterpriseTypeService;
import com.ynyes.kjxjr.service.TdExpertCoachEnterpriseService;
import com.ynyes.kjxjr.service.TdExpertService;
import com.ynyes.kjxjr.service.TdOrderService;
import com.ynyes.kjxjr.service.TdRegionAdminService;
import com.ynyes.kjxjr.service.TdRegionService;
import com.ynyes.kjxjr.service.TdUserMessageService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.ClientConstant;
import com.ynyes.kjxjr.util.SiteMagConstant;


@Controller
@RequestMapping(value="/activity")
public class TdActivityController {
	
	@Autowired
	TdOrderService tdOrderService;
	
	@Autowired
	TdCommonService tdCommonService;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdActivityService tdActivityService;
	
	@Autowired
	TdCouponService tdCouponService;
	
	@Autowired
	TdActivityTypeService tdActivityTypeService;
	
	@Autowired
	TdEnterpriseTypeService tdEnterpriseTypeService;
	
	@Autowired
	TdRegionService tdRegionService;
	
	@Autowired
	TdEnterpriseService tdEnterpriseService;
	
	@Autowired
	TdExpertService tdExpertService;
	
	@Autowired
	TdActivityEnterpriseService tdActivityEnterpriseService;
	
	@Autowired
	TdActivityExpertService tdActivityExpertService;
	
	@Autowired
	TdEnterpriseGradeService tdEnterpriseGradeService;
	
	@Autowired
	TdExpertCoachEnterpriseService tdExpertCoachEnterpriseService;
	
	@Autowired
	TdRegionAdminService tdRegionAdminService;
	
	@Autowired
	TdUserMessageService tdUserMessageService;

	   /**
     * 企业填写资料
     * @author Zhangji
     * @param req
     * @param map
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String activityList(HttpServletRequest req, ModelMap map,Integer page) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }

        if (null == page)
        {
        	page = 0;
        }
        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        
        //时间状态
        Date now = new Date();
        Calendar cn =  Calendar.getInstance();
        cn.setTime(now);
        List<TdActivity> allActivity = tdActivityService.findAll();
        for(TdActivity activity: allActivity )
        {
        	Date prepareOn = activity.getPrepareOn();
            Calendar on =  Calendar.getInstance();
            on.setTime(prepareOn);
        	Date prepareOff = activity.getPrepareOff();
            Calendar off =  Calendar.getInstance();
            off.setTime(prepareOff);
        	Date eventDate = activity.getDate();
            Calendar event =  Calendar.getInstance();
            event.setTime(eventDate);
        	Date eventEnd = activity.getEventEnd();
            Calendar eventOff =  Calendar.getInstance();
            eventOff.setTime(eventEnd);
            
            
            //待筹备
            if (cn.before(on))
            {
            	activity.setTimeId(0L);
            	tdActivityService.save(activity);
            }
            //筹备中
            else if (cn.after(on)&&cn.before(off))
            {
            	activity.setTimeId(1L);
            	tdActivityService.save(activity);
            }
            //超过筹备时间
            else if (cn.after(off)&&cn.before(eventOff))
            {
            	activity.setTimeId(2L);
            	tdActivityService.save(activity);
            }
            //超过活动时间
            else if (cn.after(eventOff))
            {
            	activity.setTimeId(3L);
            	tdActivityService.save(activity);
            }
        }
        
//        TdActivity unfinish = tdActivityService.findByStatusId(0L);
        
//        map.addAttribute("unfinish", unfinish);
        map.addAttribute("activity_page", tdActivityService.findAllOrderByIdDesc(page, ClientConstant.pageSize));
        map.addAttribute("user", user);

        return "/client/activity_list";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String activityCreate(HttpServletRequest req, ModelMap map , Long done) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);
        
//        TdActivity activity = tdActivityService.findByStatusId(0L);
//        if (null != activity)
//        {
//	        map.addAttribute("activity", activity);
//	        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activity.getId()));
//	        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(activity.getId()));
//        }
        


        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);

        map.addAttribute("user", user);
        map.addAttribute("pagetype", "create");
        map.addAttribute("done", done);
        map.addAttribute("mark", "activity");

        return "/client/activity_create";
    }
    
    //区县审核项目，查看详情
    @RequestMapping(value = "/enterprise/check/{id}", method = RequestMethod.GET)
    public String userEnterpriseCheck(HttpServletRequest req, ModelMap map,@PathVariable Long id ) {
//        String username = (String) req.getSession().getAttribute("activityUsername");
//
//        if (null == username) {
//            return "redirect:/login";
//        }
        
        if (null == id)
        {
        	return "/client/error_404";
        }

        tdCommonService.setHeader(map, req);

//        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
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
//        map.addAttribute("user", user);
        
//        if (null!=roleId && roleId == 2)
//        {
//        	map.addAttribute("mark", "region");
//        }
//        if (null!=roleId && roleId == 3)
//        {
//        	map.addAttribute("mark", "expert");
//        }
//        if (null!=roleId && roleId == 4)
//        {
//        	map.addAttribute("mark", "activity");
//        }
        
      

        return "/client/activity_enterprise_check";
    }
    
    //活动排序上升
    @RequestMapping(value = "/sortUp")
    @ResponseBody
    public  Map<String, Object> sortUp(HttpServletRequest req,Long id,Long activityId,
    		ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
           res.put("msg", "请先登陆");
           return res;
        }
        
        
        TdActivityEnterprise activityEnterprise = tdActivityEnterpriseService.findOne(id);
        Long sortId = activityEnterprise.getSortId(); 
        TdActivityEnterprise lastActivityEnterprise = tdActivityEnterpriseService.findByActivityIdAndSortId(activityId, sortId-1);

        if( sortId> 1)
        {
	        activityEnterprise.setSortId(sortId - 1);
	        tdActivityEnterpriseService.save(activityEnterprise);

	        lastActivityEnterprise.setSortId(sortId);
	        tdActivityEnterpriseService.save(lastActivityEnterprise);
        }
        res.put("code", 0);
        return res;
    }
    
    //活动排序下降
    @RequestMapping(value = "/sortDown")
    @ResponseBody
    public  Map<String, Object> sortDown(HttpServletRequest req,Long id,Long activityId,
    		ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
           res.put("msg", "请先登陆");
           return res;
        }
        
        
        TdActivityEnterprise activityEnterprise = tdActivityEnterpriseService.findOne(id);
        Long sortId = activityEnterprise.getSortId(); 
        TdActivityEnterprise nextActivityEnterprise = tdActivityEnterpriseService.findByActivityIdAndSortId(activityId, sortId+1);

        activityEnterprise.setSortId(sortId + 1);
        tdActivityEnterpriseService.save(activityEnterprise);

        nextActivityEnterprise.setSortId(sortId);
        tdActivityEnterpriseService.save(nextActivityEnterprise);

        res.put("code", 0);
        return res;
    }
    
    //查看活动
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String activityCheck(HttpServletRequest req, ModelMap map,Long id) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);
        
        TdActivity activity = tdActivityService.findOne(id);
        if (null != activity)
        {
        	List<TdExpertCoachEnterprise> roadshowList = tdExpertCoachEnterpriseService.findByEnterpriseIdOrderByExpertIdAsc(id);
        	if (null != roadshowList && roadshowList.size()>0)
        	{
        		map.addAttribute("roadshow_list", roadshowList);
        	}
            map.addAttribute("mark", "activity");
	        map.addAttribute("activity", activity);
	        map.addAttribute("recommend_list" , tdActivityEnterpriseService.findByActivityIdAndStatusIdOrderBySortIdAsc(id, 2L));
	        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(id));
	        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(id));
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        map.addAttribute("user", user);
        map.addAttribute("pagetype", "check");

        return "/client/activity_check";
    }
    
    
    //活动通过审核
    @RequestMapping(value = "/pass")
    @ResponseBody
    public  Map<String, Object> regionAddEnterprise(HttpServletRequest req, HttpServletResponse ressonse, Long activityId ,
    		ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
           res.put("msg", "请先登陆");
           return res;
        }
      
        if(null !=activityId)
        {
        	
        	//由于创建活动的选专家步骤就生成了一个不带企业字段空评分表，这边选推荐时需要重新刷新一下。不然万一那边又改动了，中间表就对不上了。
        	 List<TdActivityEnterprise> aenlist = tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId,2L);
        	 List<TdActivityExpert> aexlist = tdActivityExpertService.findByActivityId(activityId);
        	 
        	 //推荐的企业个数
        	 int enterpriseSize = aenlist.size();
        	 
        	 if (null != aexlist)
        	 {
             	for (TdActivityExpert aex:aexlist)
            	{
                  	 //专家短信提醒
//                   	TdExpert expert = tdExpertService.findOne(aex.getExpertId());
                   	//验证手机号格式
//                   	Pattern p = Pattern.compile("/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/");
//                   	Matcher m = p.matcher(expert.getUsermobile());
//                   	if (m.matches())
//                   	{
//                   		smsRecommend(expert.getUsermobile() ,aex.getActivityTitle() ,  expert.getName() , ressonse , req);
//                   	}
             		
             		
            		List<TdEnterpriseGrade> gradelist = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(aex.getExpertId(), activityId);
            		//清空评分表的企业数据
            		for (TdEnterpriseGrade grade:gradelist)
            		{
            			tdEnterpriseGradeService.delete(grade.getId());
//            			grade.setEnterpriseId(null);
//            			grade.setNumber(null);
//            			tdEnterpriseGradeService.save(grade);
            		}
            		//创建评分表
            		for(int i =0;i<enterpriseSize;i++)
            		{
            		TdEnterpriseGrade newEnter =new  TdEnterpriseGrade();
            		newEnter.setExpertId(aex.getExpertId());
            		newEnter.setActivityId(aex.getActivityId());
            		newEnter.setIsGrade(false);
            		tdEnterpriseGradeService.save(newEnter);
            		}
            	}	
        	 }

        	if (null != aenlist)
        	{
        		for (TdActivityEnterprise ae : aenlist)
        		{
               	 //企业短信提醒
//                	TdEnterprise enterprise = tdEnterpriseService.findOne(ae.getEnterpriseId());
                	//验证手机号格式
//                	Pattern p = Pattern.compile("/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/");
//                	Matcher m = p.matcher(enterprise.getUsermobile());
//                	if (m.matches())
//                	{
//                		smsRecommend(enterprise.getUsermobile(),ae.getActivityTitle() , enterprise.getTitle() , ressonse , req);
//                	}
                 
                	
                    //站内信
                    TdRegionAdmin admin = tdRegionAdminService.findByTitle(ae.getArea());
                    TdUserMessage message = new TdUserMessage();
                    
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分");
                    
                    message.setEnterpriseId(ae.getEnterpriseId());
                    message.setRegionAdminId(admin.getId());
                    message.setName(ae.getEnterpriseTitle());
                    message.setRegion(admin.getTitle());
                    message.setContent("【"+ae.getActivityType()+"】尊敬的"+ae.getEnterpriseTitle()+"，您被邀请参加"+admin.getRegion()+"”"+ae.getActivityTitle()+"“"+"活动。请登录查看详情！");
                    message.setTitle("活动邀请");
                    message.setStatusE(0L);
                    message.setSpeaker(1L);
                    message.setTime(new Date());
                    tdUserMessageService.save(message);
        			
        			
        			//重新填数据
                	List<TdEnterpriseGrade> enterpriseGradeList = tdEnterpriseGradeService.findByActivityIdOrderByIdAsc(activityId);
                	int i = 0;
               
                	for (TdEnterpriseGrade grade : enterpriseGradeList)
                	{
                		//每隔enterpriseSize个写入一次
                		if (  i % enterpriseSize== 0)
                			if(null == grade.getNumber())
                		{
                			grade.setNumber(ae.getNumber());
                			grade.setEnterpriseId(ae.getEnterpriseId());
                			grade.setEnterpriseTitle(ae.getEnterpriseTitle());
                			grade.setActivityId(activityId);
                			grade.setSordId(ae.getSortId());    //排序
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
        		
        	TdActivity activity = tdActivityService.findOne(activityId);
        	activity.setStatusId(1L);
        	tdActivityService.save(activity);
        }
        
        res.put("code", 0);
        return res;
    }
    
	@RequestMapping(value = "/search/grade")
	public String searchGrade(Long activityId,Long expertId,ModelMap map , HttpServletRequest req ){
		 String username = (String) req.getSession().getAttribute("activityUsername");
		 if (null == username)
		 {
			 return "redirect:/login";
		 }
		 
		TdExpert expert = tdExpertService.findOne(expertId);
		TdActivity activity = tdActivityService.findOne(activityId);
		List<TdEnterpriseGrade> grade_list = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(expert.getId(),
				activityId);
		
		map.addAttribute("print", "print");
		map.addAttribute("type", "check");
		map.addAttribute("grade_list", grade_list);
		map.addAttribute("activityId", activityId);
		map.addAttribute("activity", activity);
		map.addAttribute("expertId", expertId);
		map.addAttribute("expert", expert);
		return "/client/project_grade";
	}
    
    //活动取消审核
    @RequestMapping(value = "/cancel")
    @ResponseBody
    public  Map<String, Object> activityCancel(HttpServletRequest req, HttpServletResponse ressonse, Long activityId ,
    		ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
           res.put("msg", "请先登陆");
           return res;
        }
      
        if(null !=activityId)
        {
        	TdActivity activity = tdActivityService.findOne(activityId);
        	activity.setStatusId(0L);
        	tdActivityService.save(activity);
        }
        
        res.put("code", 0);
        return res;
    }
    
    //重置活动
    @RequestMapping(value = "/reset")
    @ResponseBody
    public Map<String, Object> activityReset(HttpServletRequest req, ModelMap map,Long activityId) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        String username = (String) req.getSession().getAttribute("activityUsername");
        
        if (null == username) {
            res.put("msg", "请先登陆");
            return res;
         }

        tdCommonService.setHeader(map, req);
        
        TdActivity activity = tdActivityService.findOne(activityId);
        if (null != activity)
        {
        	activity.setStatusId(1L);
        	tdActivityService.save(activity);
        	//重置活动相关的表
        	List<TdActivityEnterprise> activityEnterprise = tdActivityEnterpriseService.findByActivityId(activityId);
        	for(TdActivityEnterprise aen:activityEnterprise)
        	{
        		aen.setWin(null);
        		tdActivityEnterpriseService.save(aen);
        	}
        	
        	
        	List<TdEnterpriseGrade> enterpriseGrade = tdEnterpriseGradeService.findByActivityIdOrderByIdAsc(activityId);
        	for(TdEnterpriseGrade theGrade : enterpriseGrade)
        	{
        		theGrade.setTotalPoint(null);
				theGrade.setTotalExpression(null);
				theGrade.setOneExpression(null);
				theGrade.setTotalFeasibility(null);
				theGrade.setOneFeasibility(null);
				theGrade.setTwoFeasibility(null);
				theGrade.setTotalMarketValue(null);
				theGrade.setOneMarketValue(null);
				theGrade.setTwoMarketValue(null);
				theGrade.setTotalTechnology(null);
				theGrade.setOneTechnology(null);
				theGrade.setTwoTechnology(null);
				theGrade.setThreeTechnology(null);
		//		theGrade.setTotalPoint(grade.getTotalPoint());   zhangji 注释掉
				theGrade.setOneGroup(null);
				theGrade.setTwoGroup(null);
				theGrade.setTotalGroup(null); //zhangji
				theGrade.setIsGrade(null);
				theGrade.setGradeAble(null);
            	tdEnterpriseGradeService.save(theGrade);
        	}
        
        	
        }

        res.put("code", 0);
        return res;
    }
    
    
    
    //单个发短信
    @RequestMapping(value = "/sendSms")
    @ResponseBody
    public  Map<String, Object> sendSms(HttpServletRequest req, HttpServletResponse response, Long activityId , Long id , Long roleId,
    		ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
           res.put("msg", "请先登陆");
           return res;
        }
        
        if (null == id || null == activityId || null == roleId)
        {
        	res.put("msg", "数据有误，发送失败。");
        	return res;
        }
        
        if (roleId == 1)
        {
        	TdEnterprise enterprise = tdEnterpriseService.findOne(id);
        	TdActivity activity = tdActivityService.findOne(activityId);
        	smsRecommend(enterprise.getUsermobile(),activity.getTitle() , enterprise.getTitle() , response , req);
        }
        if (roleId == 3)
        {
        	TdExpert expert = tdExpertService.findOne(id);
        	TdActivity activity = tdActivityService.findOne(activityId);
        	smsRecommend(expert.getUsermobile(),activity.getTitle() , expert.getName() , response , req);
        }
        
        res.put("code", 0);
        return res;
    }
       
    
    
	//发短信【推荐】
	public Map<String, Object> smsRecommend(String mobile,  String activityTitle ,String name ,HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分");
		
		HttpSession session = request.getSession();
		String  info = "【科技小巨人】";

			 info = "尊敬的"+name +"，您被邀请参加"+"”"+activityTitle+"“活动，请登录查看详情。【科技小巨人】";

		
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
    
    //管理活动
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String activityEdit(HttpServletRequest req, ModelMap map,Long id , Long done) {
        String username = (String) req.getSession().getAttribute("activityUsername");
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        
        //检查是否有未完成的创建
//        TdActivity unfinish = tdActivityService.findByStatusId(0L);
//        if (null != unfinish && unfinish.getId() != id)
//        {
//        	map.addAttribute("alert", 1);
//            map.addAttribute("activity_page", tdActivityService.findByStatusIdOrderByIdDesc(1L,0, ClientConstant.pageSize));
//            map.addAttribute("unfinish", unfinish);
//            map.addAttribute("user", user);
//
//            return "/client/activity_list";
//        }
        
        TdActivity activity = tdActivityService.findOne(id);
        if (null != activity)
        {
//        	activity.setStatusId(0L);
//        	tdActivityService.save(activity);
	        map.addAttribute("activity", activity);
	        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(id));
	        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(id));
        }

//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        if (null != done)
        {
        	map.addAttribute("done", done);
        }
        map.addAttribute("mark","activity");
        map.addAttribute("user", user);
        map.addAttribute("pagetype", "edit");

        return "/client/activity_create";
    }
    
    //删除活动
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String activityDelete(HttpServletRequest req, ModelMap map,Long id) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);
        
        TdActivity activity = tdActivityService.findOne(id);
        if (null != activity)
        {
        	tdActivityService.delete(id);
        	//删除活动相关的表
        	List<TdActivityEnterprise> activityEnterprise = tdActivityEnterpriseService.findByActivityId(id);
        	tdActivityEnterpriseService.delete(activityEnterprise);
        	
        	List<TdActivityExpert> activityExpert = tdActivityExpertService.findByActivityId(id);
        	tdActivityExpertService.delete(activityExpert);
        	
        	List<TdEnterpriseGrade> enterpriseGrade = tdEnterpriseGradeService.findByActivityIdOrderByIdAsc(id);
        	tdEnterpriseGradeService.delete(enterpriseGrade);
        	
        	List<TdExpertCoachEnterprise> coach = tdExpertCoachEnterpriseService.findByEnterpriseIdOrderByExpertIdAsc(id);
        	tdExpertCoachEnterpriseService.delete(coach);
        
        	
        }

        //时间状态
        Date now = new Date();
        Calendar cn =  Calendar.getInstance();
        cn.setTime(now);
        List<TdActivity> allActivity = tdActivityService.findAll();
        for(TdActivity item: allActivity )
        {
        	Date prepareOn = item.getPrepareOn();
            Calendar on =  Calendar.getInstance();
            on.setTime(prepareOn);
        	Date prepareOff = item.getPrepareOff();
            Calendar off =  Calendar.getInstance();
            off.setTime(prepareOff);
        	Date eventDate = item.getDate();
            Calendar event =  Calendar.getInstance();
            event.setTime(eventDate);
            
            //待筹备
            if (cn.before(on))
            {
            	item.setTimeId(0L);
            	tdActivityService.save(item);
            }
            //筹备中
            else if (cn.after(on)&&cn.before(off))
            {
            	item.setTimeId(1L);
            	tdActivityService.save(item);
            }
            //超过筹备时间
            else if (cn.after(off)&&cn.before(event))
            {
            	item.setTimeId(2L);
            	tdActivityService.save(item);
            }
            //超过活动时间
            else if (cn.after(event))
            {
            	item.setTimeId(3L);
            	tdActivityService.save(item);
            }
        }
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        
//        TdActivity unfinish = tdActivityService.findByStatusId(0L);
        
//        map.addAttribute("unfinish", unfinish);
        map.addAttribute("activity_page", tdActivityService.findAllOrderByIdDesc(0, ClientConstant.pageSize));
        map.addAttribute("user", user);
        map.addAttribute("pagetype", "delete");

        return "/client/activity_list";
    }
    
    //完成项目选择
    @RequestMapping(value = "/enterprise/finish", method = RequestMethod.GET)
    public String enterpriseFinish(HttpServletRequest req, ModelMap map,Long id) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);
        
//        List<TdActivityExpert> sum = tdActivityExpertService.findByActivityId(id);
//        //限制数量为20个
//        if (sum.size() < 20)
//        {
//        	map.addAttribute("errormsg", "请选择20个企业！");
//			Page<TdEnterprise> enterprisePage = tdEnterpriseService.findByStatusId( 1L, 0, ClientConstant.pageSize);
//			
//			TdActivity activity = tdActivityService.findByStatusId(0L);
//			Long activityId = activity.getId();
//			map.addAttribute("activity", activity);
//			map.addAttribute("activityId", activityId);
//			map.addAttribute("statusId", 0);
//			map.addAttribute("enterprise_page", enterprisePage);
//			map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
//			map.addAttribute("region_list", tdRegionService.findByIsEnableTrueOrderBySortIdAsc());
//			map.addAttribute("enterpriseType_list", tdEnterpriseTypeService.findByIsEnableTrueOrderBySortIdAsc());
//			return "/client/activity_selectEnterprise";
//        }
        
        
        TdActivity activity = tdActivityService.findOne(id);
//        Long statusId = activity.getStatusId();
//        if(0 == statusId)
//        {
        	activity.setStatusEn(1L);

        	tdActivityService.save(activity);
//        }
        map.addAttribute("activity", activity);
        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activity.getId()));
        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(activity.getId()));
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        map.addAttribute("user", user);
        map.addAttribute("mark", "activity");
        
        return "/client/activity_create";
    }
    
    
    //完成项目选择
    @RequestMapping(value = "/expert/finish")
    @ResponseBody
    public Map<String, Object>enxpertFinish(HttpServletRequest req, ModelMap map,Long activityId) {
    	 Map<String, Object> res = new HashMap<String, Object>();
    	    res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
        	res.put("msg", "请先登录！");
            return res;
        }

//        tdCommonService.setHeader(map, req);
//        List<TdActivityExpert> sum = tdActivityExpertService.findByActivityId(activityId);
//        //限制数量为7个
//        if (sum.size() < 7)
//        {
//        	res.put("msg", "请选出7个专家！");
//        	return res;	
//        }
        
        TdActivity activity = tdActivityService.findOne(activityId);
//        if(0L ==activity.getStatusId())
//        {
        	activity.setStatusEx(1L);

        	tdActivityService.save(activity);
//    }
        map.addAttribute("activity", activity);
        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activity.getId()));
        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(activity.getId()));
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        res.put("code", 0);
        return res;
    }
    /*
     * 创建活动:选项目
     * 
     */
    @RequestMapping(value = "/selectEnterprise" )
    public String  selectEnterprise(HttpServletRequest req,
    		Long activityId,
    		String __ACTION,
            Long[] listId,
            Integer[] listChkId,
    		ModelMap map,
    		Integer page,
    		String keywords,
    		String area,
    		String type,
    		Long formType) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null != __ACTION)
        {
        	if (__ACTION.equalsIgnoreCase("candidate"))
    		{
    			selectE(activityId , listId, listChkId);
    		}
        	if (__ACTION.equalsIgnoreCase("remove"))
        	{
        		removeE(activityId , listId, listChkId);
        	}
        }
        
        if (null == page)
        {
        	page = 0;
        }
        //搜索
        Page<TdEnterprise>enterprisePage = null;
        if (null != keywords && !keywords.isEmpty())
        {
        	if(null != area && !area.isEmpty())
        	{
        		if (null != type && !type.isEmpty())
        		{
        			if(null != formType)
        			{
        				//123
        				enterprisePage = tdEnterpriseService.findByAreaAndTypeAndFormTypeAndStatusIdAndSearch(area, type, formType, 1L, keywords, page, ClientConstant.pageSize);
        			}
        			else
        			{
        				//12
        				enterprisePage = tdEnterpriseService.findByAreaAndTypeAndStatusIdAndSearch(area, type,  1L, keywords, page, ClientConstant.pageSize);
        			}
        		}
            	else
            	{
            		if(null != formType)
            		{
            			//13
            			enterprisePage = tdEnterpriseService.findByAreaAndFormTypeAndStatusIdAndSearch(area,formType,  1L, keywords, page, ClientConstant.pageSize);
            		}
            		else
            		{
            			//1
            			enterprisePage = tdEnterpriseService.findByAreaAndStatusIdAndSearch(area,  1L, keywords, page, ClientConstant.pageSize);
            		}
            	}
        	}
        	else
        	{
        		if(null != type && !type.isEmpty())
	        	{
	        		if(null != formType)
	        		{
	        			//23
	        			enterprisePage = tdEnterpriseService.findByTypeAndFormTypeAndStatusIdAndSearch(type,formType  ,1L, keywords, page, ClientConstant.pageSize);
	        		}
	        		else
	        		{
	        			//2
	        			enterprisePage = tdEnterpriseService.findByTypeAndStatusIdAndSearch(type,  1L,  keywords, page, ClientConstant.pageSize);
	        		}
	        	}
        		else
        		{
        			if (null != formType )
        			{
        				//3
        				enterprisePage = tdEnterpriseService.findByFormTypeAndStatusIdAndSearch(formType,   1L, keywords, page, ClientConstant.pageSize);
        			}
        			else
        			{
        				//0
        				enterprisePage = tdEnterpriseService.findByStatusIdAndSearch( 1L, keywords,page, ClientConstant.pageSize);
        			}
        		}
        	}
        }
        else
        {
        	if(null != area && !area.isEmpty())
        	{
        		if (null != type && !type.isEmpty())
        		{
        			if(null != formType)
        			{
        				//123
        				enterprisePage = tdEnterpriseService.findByAreaAndTypeAndFormTypeAndStatusId(area, type, formType,   1L, page, ClientConstant.pageSize);
        			}
        			else
        			{
        				//12
        				enterprisePage = tdEnterpriseService.findByAreaAndTypeAndStatusId(area, type, 1L,  page, ClientConstant.pageSize);
        			}
        		}
            	else
            	{
            		if(null != formType)
            		{
            			//13
            			enterprisePage = tdEnterpriseService.findByAreaAndFormTypeAndStatusId(area,formType,  1L, page, ClientConstant.pageSize);
            		}
            		else
            		{
            			//1
            			enterprisePage = tdEnterpriseService.findByAreaAndStatusId(area, 1L,   page,  ClientConstant.pageSize);
            		}
            	}
        	}
        	else
        	{
        		if(null != type && !type.isEmpty())
	        	{
	        		if(null != formType)
	        		{
	        			//23
	        			enterprisePage = tdEnterpriseService.findByTypeAndFormTypeAndStatusId(type,formType,  1L, page, ClientConstant.pageSize);
	        		}
	        		else
	        		{
	        			//2
	        			enterprisePage = tdEnterpriseService.findByTypeAndStatusId(type,  1L,  page, ClientConstant.pageSize);
	        		}
	        	}
        		else
        		{
        			if (null != formType )
        			{
        				//3
        				enterprisePage = tdEnterpriseService.findByFormTypeAndStatusId(formType,  1L,  page, ClientConstant.pageSize);
        			}
        			else
        			{
        				//0
        				enterprisePage = tdEnterpriseService.findByStatusId( 1L, page, ClientConstant.pageSize);
        			}
        		}
        	}
        }    	
      
       
        	
      
        
        TdActivity activity = tdActivityService.findOne(activityId);
        map.addAttribute("keywords", keywords);
        map.addAttribute("area", area);
        map.addAttribute("type", type);
        map.addAttribute("formType", formType);
       	map.addAttribute("activity", activity);
       	map.addAttribute("activityId", activityId);
    	map.addAttribute("statusId", 0);
    	map.addAttribute("page", page);
     	map.addAttribute("enterprise_page", enterprisePage);
     	map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
     	map.addAttribute("region_list", tdRegionService.findByIsEnableTrueOrderBySortIdAsc());
     	map.addAttribute("enterpriseType_list", tdEnterpriseTypeService.findByIsEnableTrueOrderBySortIdAsc());
        return "/client/activity_selectEnterprise";
    }
    
    //批量添加
    private void selectE(Long activityId ,Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds || null == activityId
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                /*添加*/
            	TdEnterprise enterprise = tdEnterpriseService.findOne(id);
            	if (null != enterprise)
            	{
            		enterprise.setIsSelect(true);
            		enterprise.setSelectActivityId(activityId);
            		tdEnterpriseService.save(enterprise);
            	}
            	
            	TdActivity activity = tdActivityService.findOne(activityId);
            	
            	TdActivityEnterprise activityEnterprise = tdActivityEnterpriseService.findByActivityIdAndEnterpriseId(activityId,id);
            	if (null == activityEnterprise)
            	{
            		TdActivityEnterprise newEnter =new  TdActivityEnterprise();
            		newEnter.setEnterpriseId(id);
            		newEnter.setCreateTime(new Date());
            		newEnter.setEnterpriseTitle(enterprise.getTitle());
            		newEnter.setActivityId(activity.getId());
            		newEnter.setActivityType(activity.getActivityType());
            		newEnter.setDate(activity.getDate());
            		newEnter.setPrepareOn(activity.getPrepareOn());
            		newEnter.setPrepareOff(activity.getPrepareOff());
            		newEnter.setActivityTitle(activity.getTitle());
            		newEnter.setArea(enterprise.getArea());
            		newEnter.setType(enterprise.getType());
            		newEnter.setNumber(enterprise.getNumber());
            		newEnter.setEnterpriseTitle(enterprise.getTitle());
            		newEnter.setContact(enterprise.getContact());
            		newEnter.setMobile(enterprise.getMobile());
            		newEnter.setQQ(enterprise.getChat());
            		newEnter.setProfile(enterprise.getProfile());
            		newEnter.setEnterpriseFileUrl(enterprise.getFileUrl());
            		newEnter.setDataBusiness(enterprise.getDataBusiness());
            		newEnter.setDataPossible(enterprise.getDataPossible());
            		newEnter.setDataOther(enterprise.getDataOther());
            		newEnter.setPptUrl(activity.getPptUrl());
            		newEnter.setFileUrl(activity.getFileUrl());
            		newEnter.setStatusId(0L);
            		tdActivityEnterpriseService.save(newEnter);
            	}
            	else
            	{
            		activityEnterprise.setCreateTime(new Date());
            		activityEnterprise.setEnterpriseTitle(enterprise.getTitle());
            		activityEnterprise.setActivityType(activity.getActivityType());
            		activityEnterprise.setDate(activity.getDate());
            		activityEnterprise.setPrepareOn(activity.getPrepareOn());
            		activityEnterprise.setPrepareOff(activity.getPrepareOff());
            		activityEnterprise.setActivityTitle(activity.getTitle());
            		activityEnterprise.setArea(enterprise.getArea());
            		activityEnterprise.setType(enterprise.getType());
            		activityEnterprise.setNumber(enterprise.getNumber());
            		activityEnterprise.setEnterpriseTitle(enterprise.getTitle());
            		activityEnterprise.setContact(enterprise.getContact());
            		activityEnterprise.setMobile(enterprise.getMobile());
            		activityEnterprise.setQQ(enterprise.getChat());
            		activityEnterprise.setProfile(enterprise.getProfile());
            		activityEnterprise.setDataBusiness(enterprise.getDataBusiness());
            		activityEnterprise.setDataPossible(enterprise.getDataPossible());
            		activityEnterprise.setDataOther(enterprise.getDataOther());
            		activityEnterprise.setEnterpriseFileUrl(enterprise.getFileUrl());
            		activityEnterprise.setPptUrl(activity.getPptUrl());
            		activityEnterprise.setFileUrl(activity.getFileUrl());
            		tdActivityEnterpriseService.save(activityEnterprise);
            	}
                /*添加 end*/
            }
        }
    }

  //批量取消
    private void removeE(Long activityId ,Long[] ids, Integer[] chkIds)
    {
      if (null == ids || null == chkIds || null == activityId
              || ids.length < 1 || chkIds.length < 1)
      {
          return;
      }
      
      for (int chkId : chkIds)
      {
          if (chkId >=0 && ids.length > chkId)
          {
              Long id = ids[chkId];
              
          	TdActivityEnterprise ActivityEnterprise = tdActivityEnterpriseService.findOne(id);
          	if (null != ActivityEnterprise)
          	{
          		tdActivityEnterpriseService.delete(ActivityEnterprise);

          		TdEnterprise enterprise = tdEnterpriseService.findOne(ActivityEnterprise.getEnterpriseId());
          		enterprise.setIsSelect(false);
          		enterprise.setSelectActivityId(null);
          		tdEnterpriseService.save(enterprise);
      
          	}
          }
      }
    }

    //预选
    @RequestMapping(value = "/addEnterprise")
    public String  addEnterprise(HttpServletRequest req,Long id,Long activityId,
    		ModelMap map) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }
      
        if(null != id&&null !=activityId)
        {
        	TdEnterprise enterprise = tdEnterpriseService.findOne(id);
        	if (null != enterprise)
        	{
        		enterprise.setIsSelect(true);
        		enterprise.setSelectActivityId(activityId);
        		tdEnterpriseService.save(enterprise);
        	}
        	
        	TdActivity activity = tdActivityService.findOne(activityId);
        	
        	TdActivityEnterprise activityEnterprise = tdActivityEnterpriseService.findByActivityIdAndEnterpriseId(activityId,id);
        	if (null == activityEnterprise)
        	{
        		TdActivityEnterprise newEnter =new  TdActivityEnterprise();
        		newEnter.setEnterpriseId(id);
        		newEnter.setCreateTime(new Date());
        		newEnter.setEnterpriseTitle(enterprise.getTitle());
        		newEnter.setActivityId(activity.getId());
        		newEnter.setActivityType(activity.getActivityType());
        		newEnter.setDate(activity.getDate());
        		newEnter.setPrepareOn(activity.getPrepareOn());
        		newEnter.setPrepareOff(activity.getPrepareOff());
        		newEnter.setActivityTitle(activity.getTitle());
        		newEnter.setArea(enterprise.getArea());
        		newEnter.setType(enterprise.getType());
        		newEnter.setNumber(enterprise.getNumber());
        		newEnter.setEnterpriseTitle(enterprise.getTitle());
        		newEnter.setContact(enterprise.getContact());
        		newEnter.setMobile(enterprise.getMobile());
        		newEnter.setQQ(enterprise.getChat());
        		newEnter.setProfile(enterprise.getProfile());
        		newEnter.setDataBusiness(enterprise.getDataBusiness());
        		newEnter.setDataPossible(enterprise.getDataPossible());
        		newEnter.setDataOther(enterprise.getDataOther());
        		newEnter.setEnterpriseFileUrl(enterprise.getFileUrl());
        		newEnter.setPptUrl(activity.getPptUrl());
        		newEnter.setFileUrl(activity.getFileUrl());
        		newEnter.setStatusId(0L);
        		tdActivityEnterpriseService.save(newEnter);
        	}
        	else
        	{
        		activityEnterprise.setCreateTime(new Date());
        		activityEnterprise.setEnterpriseTitle(enterprise.getTitle());
        		activityEnterprise.setActivityType(activity.getActivityType());
        		activityEnterprise.setDate(activity.getDate());
        		activityEnterprise.setPrepareOn(activity.getPrepareOn());
        		activityEnterprise.setPrepareOff(activity.getPrepareOff());
        		activityEnterprise.setActivityTitle(activity.getTitle());
        		activityEnterprise.setArea(enterprise.getArea());
        		activityEnterprise.setType(enterprise.getType());
        		activityEnterprise.setNumber(enterprise.getNumber());
        		activityEnterprise.setEnterpriseTitle(enterprise.getTitle());
        		activityEnterprise.setContact(enterprise.getContact());
        		activityEnterprise.setMobile(enterprise.getMobile());
        		activityEnterprise.setQQ(enterprise.getChat());
        		activityEnterprise.setProfile(enterprise.getProfile());
        		activityEnterprise.setDataBusiness(enterprise.getDataBusiness());
        		activityEnterprise.setDataPossible(enterprise.getDataPossible());
        		activityEnterprise.setDataOther(enterprise.getDataOther());
        		activityEnterprise.setEnterpriseFileUrl(enterprise.getFileUrl());
        		activityEnterprise.setPptUrl(activity.getPptUrl());
        		activityEnterprise.setFileUrl(activity.getFileUrl());
        		tdActivityEnterpriseService.save(activityEnterprise);
        	}
        	
        }
        
        map.addAttribute("activityId",activityId);
        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
        return "/client/activity_selected_enterprise";
    }


    @RequestMapping(value = "/removeEnterprise")
    public String  removeEnterprise(HttpServletRequest req,Long id,Long activityId,
    		ModelMap map) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }
      
        if(null != id)
        {
        	TdActivityEnterprise ActivityEnterprise = tdActivityEnterpriseService.findOne(id);
        	if (null != ActivityEnterprise)
        	{
        		tdActivityEnterpriseService.delete(ActivityEnterprise);
        		
        		TdEnterprise enterprise = tdEnterpriseService.findOne(ActivityEnterprise.getEnterpriseId());
        		enterprise.setIsSelect(false);
        		enterprise.setSelectActivityId(null);
        		tdEnterpriseService.save(enterprise);
        	}
        }
        
        map.addAttribute("activityId",activityId);
        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
        return "/client/activity_selected_enterprise";
    }
    

    /*
     * 创建活动:选专家评委
     * 
     */
  
    @RequestMapping(value = "/selectExpert")
    public String  selectExpert(HttpServletRequest req,
    		Long activityId,
    		ModelMap map,
    		Integer page,
    		String keywords) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null == page)
        {
        	page = 0;
        }
        //搜索
        Page<TdExpert>ExpertPage = null;
        if (null != keywords && !keywords.isEmpty())
        {
        	 ExpertPage = tdExpertService.findBySearch(keywords,page, ClientConstant.pageSize);
        }
        else
        {
			ExpertPage = tdExpertService.findAllOrderBySortIdAsc(page, ClientConstant.pageSize);
        }    	
        
        TdActivity activity = tdActivityService.findOne(activityId);
       
        map.addAttribute("keywords", keywords);
       	map.addAttribute("activity", activity);
       	map.addAttribute("activityId", activityId);
     	map.addAttribute("statusId", 0);
     	map.addAttribute("Expert_page", ExpertPage);
     	map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(activityId));
        return "/client/activity_selectExpert";
    }

    @RequestMapping(value = "/addExpert")
    @ResponseBody
    public Map<String , Object>  addExpert(HttpServletRequest req,Long id,Long activityId,
    		ModelMap map) {
    	Map <String , Object> res = new HashMap<String , Object>();
    	res.put("code",1);
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
        	res.put("msg", "请先登录！");
        	return res;
        }
        
//        List<TdActivityExpert> expertList = tdActivityExpertService.findByActivityId(activityId);
//        if (expertList.size() > 6)
//        {
//        	res.put("msg", "最大添加人数为7人！！");
//        	return res;
//          
//        }
        
      
        if(null != id&&null !=activityId)
        {
        	TdExpert expert = tdExpertService.findOne(id);
        	if (null != expert)
        	{
        		expert.setIsSelect(true);
        		expert.setSelectActivityId(activityId);
        		tdExpertService.save(expert);
        	}
        	TdActivity activity = tdActivityService.findOne(activityId);
        	
        	TdActivityExpert ActivityExpert = tdActivityExpertService.findByActivityIdAndExpertId(activityId,id);
        	if (null == ActivityExpert)
        	{
        		TdActivityExpert newEnter =new  TdActivityExpert();
        		newEnter.setExpertId(id);
        		newEnter.setCreateTime(activity.getDate());
        		newEnter.setName(expert.getName());
        		newEnter.setActivityId(activity.getId());
        		newEnter.setActivityTitle(activity.getTitle());
        		newEnter.setEmail(expert.getEmail());
        		newEnter.setUsermobile(expert.getUsermobile());
        		newEnter.setUsername(expert.getUsername());
        		tdActivityExpertService.save(newEnter);
        	}
        	else
        	{
        		ActivityExpert.setCreateTime(activity.getDate());
        		ActivityExpert.setName(expert.getName());
        		ActivityExpert.setActivityTitle(activity.getTitle());
        		ActivityExpert.setEmail(expert.getEmail());
        		ActivityExpert.setUsermobile(expert.getUsermobile());
        		ActivityExpert.setUsername(expert.getUsername());
        		tdActivityExpertService.save(ActivityExpert);
        	}
        	
        	//同时创建评分表数据
//        	TdEnterpriseGrade enterpriseGrade = tdEnterpriseGradeService.findByExpertIdAndActivityId(activityId,id);
//        	if (null == enterpriseGrade)
//        	{
//        		for(int i =0;i<20;i++)
//        		{
//        		TdEnterpriseGrade newEnter =new  TdEnterpriseGrade();
//        		newEnter.setExpertId(id);
//        		newEnter.setActivityId(activity.getId());
//        		newEnter.setIsGrade(false);
//        		tdEnterpriseGradeService.save(newEnter);
//        		}
//        	}
//        	else
//        	{
//        		enterpriseGrade.setExpertId(id);
//        		tdActivityExpertService.save(ActivityExpert);
//        	}
        	
        }
        
        map.addAttribute("activityId",activityId);
        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(activityId));
        res.put("code", 0);
        return res;
    }

    @RequestMapping(value = "/removeExpert")
    @ResponseBody
    public Map<String ,Object>  removeExpert(HttpServletRequest req,Long id,Long activityId,
    		ModelMap map) {
    	Map <String , Object> res = new HashMap<String , Object>();
    	 res.put("code", 1);
    	 
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
         	res.put("msg", "请先登录！");
         	return res;
        }
      
        if(null != id)
        {
        	TdActivityExpert ActivityExpert = tdActivityExpertService.findOne(id);
        	if (null != ActivityExpert)
        	{
        		tdActivityExpertService.delete(ActivityExpert);
        	}
        	
        	TdExpert expert = tdExpertService.findOne(ActivityExpert.getExpertId());
        	expert.setSelectActivityId(null);
        	tdExpertService.save(expert);
        	
        	//同时删除评分表数据
//        	List<TdEnterpriseGrade> enterpriseGradeList = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(ActivityExpert.getExpertId() , activityId);
//        	if (null != enterpriseGradeList)
//        	{
//        		for(TdEnterpriseGrade item : enterpriseGradeList)
//        		{
//        			tdEnterpriseGradeService.delete(item.getId());
//        		}
//        	}
       
        }
        map.addAttribute("activityId",activityId);
        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(activityId));
        res.put("code", 0);
        return res;
    }
  /////////////////////////////////////选择专家  end 、、、/////////////////////////
    /*
     * 创建活动:选专家评委
     * 
     */
    
    @RequestMapping(value = "/bufferEn", method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, Object> activityBuffer(HttpServletRequest req,
    											ModelMap map,
    											Long id,
    											String title,
    											String activityType,
    											String region,
    											String date,
    											String address,
    											String theme,
    											String introduction,
    											String prepareOn,
    											String prepareOff,
    											String eventEnd) throws java.text.ParseException {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
        	res.put("msg", "请先登录！");
            return res;
        }
        
        if (null == title || title.equals("") || null == activityType || activityType.equals("") || null == region || region.equals("")
        	|| null == date || null == address || address.equals("") || null == theme || theme.equals("")  || null == introduction || introduction.equals("") 
        	||null == prepareOn || null == prepareOff ||null == eventEnd)
        {
        	res.put("msg", "请先填写完整资料！");
        	return res;
        }
        

    	TdActivity activity = null;
    	if (null == id)
    	{
    		activity = new TdActivity();
    	}
    	else
    	{
    		activity = tdActivityService.findOne(id);
    		
    	}
    	if (null != date)
    	{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateF = sdf.parse(date);
            Date prepareOnF = sdf.parse(prepareOn);
            Date prepareOffF = sdf.parse(prepareOff);
            Date eventEndF = sdf.parse(eventEnd);
        	activity.setTitle(title);
        	activity.setActivityType(activityType);
        	activity.setRegion(region);
        	activity.setDate(dateF);
        	activity.setAddress(address);
        	activity.setTheme(theme);
        	activity.setIntroduction(introduction);
        	activity.setPrepareOn(prepareOnF);
        	activity.setPrepareOff(prepareOffF);
        	activity.setEventEnd(eventEndF);
//        	activity.setStatusId(0L);
        	tdActivityService.save(activity);
        	
    		//同步企业活动的表
    		List<TdActivityEnterprise> aeList = tdActivityEnterpriseService.findByActivityId(id);
    		if (null != aeList && aeList.size()>0)
    		{
    			for (TdActivityEnterprise aeitem : aeList)
    			{
    				aeitem.setActivityTitle(title);
    				aeitem.setActivityType(activityType);
    				aeitem.setArea(region);
    				aeitem.setDate(dateF);
    				aeitem.setPrepareOn(prepareOnF);
    				aeitem.setPrepareOff(prepareOffF);
    				aeitem.setPptUrl(activity.getPptUrl());
    				aeitem.setFileUrl(activity.getFileUrl());
    				tdActivityEnterpriseService.save(aeitem);
    			}
    		}
    	}


//        }
       
        res.put("activityId", activity.getId());	
        res.put("code", 0);
        return res;
    }
    
    //区县审核项目，查看详情
    @RequestMapping(value = "/enterprise/detail/{id}", method = RequestMethod.GET)
    public String activityEnterpriseCheck(HttpServletRequest req, ModelMap map,@PathVariable Long id) {
        String username = (String) req.getSession().getAttribute("activityUsername");

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
        map.addAttribute("usertype", "activity");
        
        return "/client/region_enterprise_check";
    }
    
    @RequestMapping(value = "/submit")
    @ResponseBody
    public  Map<String, Object> activitySubmit(HttpServletRequest req,
    		Long id,
    		Long statusEx,
    		Long statusEn,
    		String title,
    		String activityType,
    		String region,
    		String date,
    		String address,
    		String theme,
    		String introduction,
    		String prepareOn,
    		String prepareOff,
    		String eventEnd,
    		ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
        	res.put("msg", "请先登录！");
            return res;
        }
        
        TdActivity tdActivity = tdActivityService.findOne(id);
        if (null == tdActivity)
        {
        	tdActivity = new TdActivity();
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date date1 = new Date();
        Date prepareOn1 = new Date();
        Date prepareOff1 = new Date();
        Date eventEnd1 = new Date();
        
        try {
			date1 = sdf.parse(date);
			prepareOn1 = sdf.parse(prepareOn);
			prepareOff1 = sdf.parse(prepareOff);
			eventEnd1 = sdf.parse(eventEnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        tdActivity.setCreateTime(new Date());
        tdActivity.setStatusEx(statusEx);
        tdActivity.setStatusEn(statusEn);
        tdActivity.setTitle(title);
        tdActivity.setActivityType(activityType);
        tdActivity.setRegion(region);
        tdActivity.setDate(date1);
        tdActivity.setAddress(address);
        tdActivity.setTheme(theme);
        tdActivity.setIntroduction(introduction);
        tdActivity.setPrepareOn(prepareOn1);
        tdActivity.setPrepareOff(prepareOff1);
        tdActivity.setEventEnd(eventEnd1);
       
       	tdActivityService.save(tdActivity);
       	
		//同步企业活动的表
		List<TdActivityEnterprise> aeList = tdActivityEnterpriseService.findByActivityId(id);
		if (null != aeList && aeList.size()>0)
		{
			for (TdActivityEnterprise aeitem : aeList)
			{
				aeitem.setActivityTitle(title);
				aeitem.setActivityType(activityType);
				aeitem.setArea(region);
				aeitem.setDate(date1);
				aeitem.setPrepareOn(prepareOn1);
				aeitem.setPrepareOff(prepareOff1);
				aeitem.setPptUrl(tdActivity.getPptUrl());
				aeitem.setFileUrl(tdActivity.getFileUrl());
				tdActivityEnterpriseService.save(aeitem);
			}
		}
       
        res.put("code", 0);
        return res;
    }
  
    @RequestMapping(value = "/getGrade")
    public String getGrade(Long activityId, String mark , Integer orderId , ModelMap map){
    	List<TdActivityEnterprise> activityEnterpriseList = tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId, 2L);
    	if(null != activityEnterpriseList){
    		for (TdActivityEnterprise ae : activityEnterpriseList)
    		{
    			Long totalPoint = 0L;
    			Long totalTechnology = 0L;
    			Long totalFeasibility = 0L;
    			Long totalGroup = 0L;
    			Long totalMarketValue = 0L;
    			Long totalExpression = 0L;
    			//找出每一个企业对应的得分列表
    			List<TdEnterpriseGrade> enterpriseGradeList = tdEnterpriseGradeService.findByEnterpriseIdAndActivityId(ae.getEnterpriseId(), activityId);
    			if (null != enterpriseGradeList)
    			{
    				//把每一个专家的评分加起来
    				for (TdEnterpriseGrade grade : enterpriseGradeList)
    				{
    					if(null != grade.getTotalPoint())
    					{
    						totalPoint = (long)grade.getTotalPoint().intValue()+totalPoint;
    						totalTechnology = (long)grade.getTotalTechnology().intValue()+totalTechnology;
    						totalFeasibility = (long)grade.getTotalFeasibility().intValue()+totalFeasibility;
    						totalGroup = (long)grade.getTotalGroup().intValue()+totalGroup;
    						totalMarketValue = (long)grade.getTotalMarketValue().intValue()+totalMarketValue;
    						totalExpression = (long)grade.getTotalExpression().intValue()+totalExpression;
    					}
    				}
    				
    			}
    			ae.setTotalPoint(totalPoint);
    			ae.setTotalTechnology(totalTechnology);
    			ae.setTotalFeasibility(totalFeasibility);
    			ae.setTotalGroup(totalGroup);
    			ae.setTotalMarketValue(totalMarketValue);
    			ae.setTotalExpression(totalExpression);
    			tdActivityEnterpriseService.save(ae);
    		}
    	//总分排序
    		if (null == orderId)
    		{
    			orderId = 0 ;
    		}
    		Page<TdActivityEnterprise> gradePage =  tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId, 2L , orderId , 0 , 30);
    		
    		
    		map.addAttribute("grade_list", gradePage);
    		map.addAttribute("orderId",orderId);
    		if (null != gradePage)
    		{
    			int index = 0 ;
    			for (TdActivityEnterprise sortEnterprise : gradePage.getContent())
    			{
    				List<TdEnterpriseGrade> expertList = tdEnterpriseGradeService.findByEnterpriseIdAndActivityId(sortEnterprise.getEnterpriseId(), activityId);
    				map.addAttribute("expert_list_"+index, expertList);
    				index++;
    			}
    		}
    		
    		List<TdActivityExpert> expertList = tdActivityExpertService.findByActivityIdOrderByExpertIdAsc(activityId);
    		map.addAttribute("aex_list" ,expertList);
    		
    		TdActivity activity = tdActivityService.findOne(activityId);
    		map.addAttribute("activityId", activityId);
    		map.addAttribute("activity", activity);
    		map.addAttribute("mark", mark);
    		
    		/*----------------------------------dengxiao **************************------------------*/
//    		if(0 < activity_experts.size()){
//    			List<TdEnterpriseGrade> experts0_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(activity_experts.get(0).getExpertId(), activityId);
//    			map.addAttribute("expert0_grade", experts0_grade);
//    			map.addAttribute("expert0", activity_experts.get(0).getName());
//    		}
//    		
//			if(1 < activity_experts.size()){
//				List<TdEnterpriseGrade> experts1_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(activity_experts.get(1).getExpertId(), activityId);
//    			map.addAttribute("expert1_grade", experts1_grade);
//    			map.addAttribute("expert1", activity_experts.get(1).getName());		
//    		}
//			
//			if(2 < activity_experts.size()){
//				List<TdEnterpriseGrade> experts2_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(activity_experts.get(2).getExpertId(), activityId);
//    			map.addAttribute("expert2_grade", experts2_grade);
//    			map.addAttribute("expert2", activity_experts.get(2).getName());
//			}
//			
//			if(3 < activity_experts.size()){
//				List<TdEnterpriseGrade> experts3_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(activity_experts.get(3).getExpertId(), activityId);
//    			map.addAttribute("expert3_grade", experts3_grade);
//    			map.addAttribute("expert3", activity_experts.get(3).getName());
//			}
//			
//			if(4 < activity_experts.size()){
//				List<TdEnterpriseGrade> experts4_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(activity_experts.get(4).getExpertId(), activityId);
//    			map.addAttribute("expert4_grade", experts4_grade);
//    			map.addAttribute("expert4", activity_experts.get(4).getName());
//			}
//			
//			if(5 < activity_experts.size()){
//				List<TdEnterpriseGrade> experts5_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(activity_experts.get(5).getExpertId(), activityId);
//    			map.addAttribute("expert5_grade", experts5_grade);
//    			map.addAttribute("expert5", activity_experts.get(5).getName());
//			}
//			
//			if(6 < activity_experts.size()){
//				List<TdEnterpriseGrade> experts6_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(activity_experts.get(6).getExpertId(), activityId);
//    			map.addAttribute("expert6_grade", experts6_grade);
//    			map.addAttribute("expert6", activity_experts.get(6).getName());
//			}
			/*----------------------------------dengxiao **************************------------------*/
    	}
    	return "/client/total_grade";
    }
    
    @RequestMapping(value = "/getCoach")
    public String getCoach(Long activityId,ModelMap map,HttpServletRequest req,String keywords,Integer page){
    	String activityUsername = (String) req.getSession().getAttribute("activityUsername");
    	if(null == activityUsername){
    		return "/client/login";
    	}
    	
    	if(null == page){
    		page = 0;
    	}
    	
    	Page<TdExpert>ExpertPage = null;
        if (null != keywords && !keywords.isEmpty())
        {
        	 ExpertPage = tdExpertService.findBySearch(keywords,page, ClientConstant.pageSize);
        }
        else
        {
			ExpertPage = tdExpertService.findAllOrderBySortIdAsc(page, ClientConstant.pageSize);
        }
        map.addAttribute("activity", tdActivityService.findOne(activityId));
    	map.addAttribute("keywords", keywords);
    	map.addAttribute("page", page);
    	map.addAttribute("ExpertPage", ExpertPage);
    	map.addAttribute("activityId", activityId);
    	return "/client/activity_coach_expert";
    }
    
    @RequestMapping(value="/addCoach")
    @ResponseBody
    public  Map<String, Object> addCoach(Long expertId,Long activityId,HttpServletRequest req){
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
    	String activityUsername = (String) req.getSession().getAttribute("activityUsername");
    	if(null == activityUsername){
        	res.put("msg", "请先登录！");
            return res;
    	}
    	
    	TdExpert expert = tdExpertService.findOne(expertId);
    	expert.setRoadshowActivityId(activityId);
    	tdExpertService.save(expert);
    	TdExpertCoachEnterprise coach = new TdExpertCoachEnterprise();
    	coach.setEnterpriseId(activityId);    //活动id
    	coach.setEnterpriseName(tdActivityService.findOne(activityId).getTitle());		//活动名称
    	coach.setIsGrade(false);
    	coach.setExpertName(expert.getName());
    	coach.setExpertId(expertId);
       
    	tdExpertCoachEnterpriseService.save(coach);
        res.put("code", 0);
        return res;
    }
    //取消路演辅导
    @RequestMapping(value="/removeCoach")
    @ResponseBody
    public  Map<String, Object> removeCoach(Long expertId,Long activityId,HttpServletRequest req){
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
    	String activityUsername = (String) req.getSession().getAttribute("activityUsername");
    	if(null == activityUsername){
        	res.put("msg", "请先登录！");
            return res;
    	}
    	
    	TdExpert expert = tdExpertService.findOne(expertId);
    	expert.setRoadshowActivityId(null);
    	tdExpertService.save(expert);
    	TdExpertCoachEnterprise coach = tdExpertCoachEnterpriseService.findByExpertIdAndEnterpriseId(expertId, activityId);
    	tdExpertCoachEnterpriseService.delete(coach.getId());
        res.put("code", 0);
        return res;
    }
    
    /**
     * 选出入选胜出的项目
     * @param req
     * @param activityId
     * @param __ACTION
     * @param listId
     * @param listChkId
     * @param map
     * @return
     */
    @RequestMapping(value = "/win" )
    public String  selectWinner(HttpServletRequest req,
    		Long activityId,
    		String __ACTION,
            Long[] listId,
            Integer[] listChkId,
    		ModelMap map) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null != __ACTION)
        {
        	if (__ACTION.equalsIgnoreCase("win"))
    		{
    			selectW(activityId , listId, listChkId);
    		}
        }
        
        return "redirect:/activity/getGrade?activityId="+activityId+"&mark=activity";
    }
    
    
    private void selectW(Long activityId ,Long[] ids, Integer[] chkIds)
    {
      if (null == ids || null == chkIds || null == activityId
              || ids.length < 1 || chkIds.length < 1)
      {
          return;
      }
      //先把获胜状态清空，为了可以多次选择入选企业
      List<TdActivityEnterprise> aeList = tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId, 2L);
      for(TdActivityEnterprise aen : aeList)
      {
    	  if (null != aen.getWin() && aen.getWin() == activityId)
    	  {
    		  aen.setWin(null);
    		  tdActivityEnterpriseService.save(aen);
    	  }
    	  TdEnterprise e = tdEnterpriseService.findOne(aen.getEnterpriseId());
    	  if(null != e.getWin() && e.getWin() == activityId)
    	  {
    		  e.setWin(null);
    		  tdEnterpriseService.save(e);
    	  }
      }

      
      
      TdActivity activity = tdActivityService.findOne(activityId);
      activity.setStatusId(2L);
      tdActivityService.save(activity);
      
     
      
      for (int chkId : chkIds)
      {
          if (chkId >=0 && ids.length > chkId)
          {
              Long id = ids[chkId];
              
          	TdActivityEnterprise ActivityEnterprise = tdActivityEnterpriseService.findOne(id);
          	if (null != ActivityEnterprise)
          	{
          		ActivityEnterprise.setWin(activityId); 
          		tdActivityEnterpriseService.save(ActivityEnterprise);
          	}
          	TdEnterprise enterprise = tdEnterpriseService.findOne(ActivityEnterprise.getEnterpriseId());
          	if (null != enterprise)
          	{
          		enterprise.setWin(activityId); 
          		tdEnterpriseService.save(enterprise);
          	}
          	
      
          	
          }
      }
    }

 
 //评分汇总表导出
 @SuppressWarnings("deprecation")
 @RequestMapping(value="/export/totalGrade")
 public String exportGrade(
                             Long activityId,
                             ModelMap map,
                             String exportUrl,
                             HttpServletResponse resp,
                             HttpServletRequest req){

	 /*------------------------------------*/

	 
	 /*-------------------------------------*/
	 

         	exportUrl = SiteMagConstant.backupPath;
     
 			if (null != exportUrl) {
 			   	List<TdActivityEnterprise> activityEnterpriseList = tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId, 2L);
 		    	if(null != activityEnterpriseList){
 		    		for (TdActivityEnterprise ae : activityEnterpriseList)
 		    		{
 		    			Long totalPoint = 0L;
 		    			//找出每一个企业对应的得分列表
 		    			List<TdEnterpriseGrade> enterpriseGradeList = tdEnterpriseGradeService.findByEnterpriseIdAndActivityId(ae.getEnterpriseId(), activityId);
 		    			if (null != enterpriseGradeList)
 		    			{
 		    				//把每一个专家的评分加起来
 		    				for (TdEnterpriseGrade grade : enterpriseGradeList)
 		    				{
 		    					if(null != grade.getTotalPoint())
 		    					{
 		    						totalPoint = (long)grade.getTotalPoint().intValue()+totalPoint;
 		    					}
 		    				}
 		    				
 		    			}
 		    			ae.setTotalPoint(totalPoint);
 		    			tdActivityEnterpriseService.save(ae);
 		    		}
 		    		
 		    	//总分排序
 		    	    List<List<TdEnterpriseGrade>> expertGradeList = new ArrayList<List<TdEnterpriseGrade>>();    
// 		    		List<Object> expertGradeList = new ArrayList<Object>();     //Object 可以装任何类型；【评委评分表的列表】
 		    	   Page<TdActivityEnterprise> gradeList = tdActivityEnterpriseService.findByActivityIdAndStatusId(activityId, 2L , 0, 0 , 30);
 		    		map.addAttribute("grade_list", gradeList);
 		    		if (null != gradeList)
 		    		{  
 		    			int index = 0 ;
 		    		
 		    			for (TdActivityEnterprise sortEnterprise : gradeList)
 		    			{
 		    				List<TdEnterpriseGrade> expertList = tdEnterpriseGradeService.findByEnterpriseIdAndActivityId(sortEnterprise.getEnterpriseId(), activityId);
 		    				map.addAttribute("expert_list_"+index, expertList);
 		    				expertGradeList.add(expertList);
 		    				index++;
 		    			}
 		    			
 		    		}
 		    		
 		    		List<TdActivityExpert> expertList = tdActivityExpertService.findByActivityIdOrderByExpertIdAsc(activityId);
 		    		map.addAttribute("aex_list" ,expertList);
 		    		
 		    		TdActivity activity = tdActivityService.findOne(activityId);
 		    		map.addAttribute("activityId", activityId);
 		    		map.addAttribute("activity", activity);
 		    		
 		  int  expertSize = expertList.size();
 		
     /**  
 		 * @author lc
 		 * @注释：根据不同条件导出excel文件
 		 */
       // 第一步，创建一个webbook，对应一个Excel文件  
       HSSFWorkbook wb = new HSSFWorkbook();  
       // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
       HSSFSheet sheet = wb.createSheet("totalGrade");  
       
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
       sheet.setDefaultColumnWidth(6*256);
       sheet.setColumnWidth((short) 0 , 6*256);
       sheet.setColumnWidth((short) 1 , 6*256);
       sheet.setColumnWidth((short) 2 , 6*256);
       sheet.setColumnWidth((short) 3 , 46*256);
       
       
       // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
       HSSFRow row = sheet.createRow((int) 0);  
       
       sheet.addMergedRegion(new Region((short) 0 , (short) 0 , (short) 1 , (short) (4+expertSize)));     //标题
       sheet.addMergedRegion(new Region((short) 2 , (short) 0 , (short) 2 , (short) (4+expertSize)));     //标题
       // 第四步，创建单元格，并设置值表头 设置表头居中  
       HSSFCellStyle style = wb.createCellStyle();  
       style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
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
       
       HSSFCellStyle title2 = wb.createCellStyle();  
       title2.setBorderBottom(HSSFCellStyle.BORDER_NONE);    //设置边框样式
       title2.setBorderRight(HSSFCellStyle.BORDER_NONE);
       title2.setBorderLeft(HSSFCellStyle.BORDER_NONE);  
       title2.setBorderTop(HSSFCellStyle.BORDER_NONE);  
       title2.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);//垂直居中
       title2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中格式
       
       HSSFFont font = wb.createFont();
       font.setFontName("黑体");
       font.setFontHeightInPoints((short) 12);//设置字体大小
       font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
       title.setFont(font);//选择需要用到的字体格式
       title2.setFont(font);
       font.setFontHeightInPoints((short) 10);//设置字体大小
       style2.setFont(font);
       
       //盖章
       HSSFCellStyle left = wb.createCellStyle();  
       left.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
       left.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 居格式
       
       //日期
       HSSFCellStyle right = wb.createCellStyle();  
       right.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
       right.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 格式
       
       
       HSSFCell cell = row.createCell((short) 0);  
       cell.setCellValue("项目得分汇总表");  
       cell.setCellStyle(title);
      
       row =sheet.createRow((int) 2);
       cell = row.createCell((short) 0);
       cell.setCellValue(activity.getTitle());  
       cell.setCellStyle(title);
       
       row =sheet.createRow((int) 3);
       row.setHeight((short) (20 * 20));  
       cell = row.createCell((short) 0);  
       cell.setCellValue("胜出");
       cell.setCellStyle(style2);
       cell = row.createCell((short) 1);
       cell.setCellValue("排名");
       cell.setCellStyle(style2);
       cell = row.createCell((short) 2);
       cell.setCellValue("编号");
       cell.setCellStyle(style2);
       cell = row.createCell((short) 3);
       cell.setCellValue("名称");
       cell.setCellStyle(style2);
       cell = row.createCell((short) 4);
       cell.setCellValue("总分");
       cell.setCellStyle(style2);
       if (null != expertList || expertList.size() > 0)
       {
    	   int i = 0;
    	   for (TdActivityExpert ae : expertList)
           {
               cell = row.createCell((short) 5+i);
               cell.setCellValue(ae.getName());
               cell.setCellStyle(style2);
               i++;
           }
       }
 			
 		if (null != exportUrl) {
 			if (ImportData(expertGradeList , expertSize ,gradeList , row, cell, sheet,style)) {
 				download(wb, exportUrl, resp);
 			}         
 		}  
 			}
 }
     return "/client/total_grade";
 }

 /**
 	 * @author lc
 	 * @注释：将page中的订单数据存入excel表格中
 	 */
  @SuppressWarnings("deprecation")
 	public boolean ImportData(List<List<TdEnterpriseGrade>> expertGradeList, int expertSize ,Page<TdActivityEnterprise> gradeList , HSSFRow row, HSSFCell cell, HSSFSheet sheet ,HSSFCellStyle style){
 	 	
	  		int i = 0 ; 
         	for (TdActivityEnterprise grade : gradeList )  
             {  
                 row = sheet.createRow((int) i + 4);  
                 row.setHeight((short) (20 * 20));  
                 cell = row.createCell((short) 0);
                 if (null != grade.getWin() && grade.getWin() == grade.getActivityId())
                 {
                	 cell.setCellValue("入选");
                 }
                 cell.setCellStyle(style); 
                 cell = row.createCell((short) 1);
                 cell.setCellValue(i+1);
                 cell.setCellStyle(style);
                 cell = row.createCell((short) 2);
                 cell.setCellValue(grade.getNumber()); 
                 cell.setCellStyle(style);
                 cell = row.createCell((short) 3);
                 cell.setCellValue(grade.getEnterpriseTitle()); 
                 cell.setCellStyle(style);
                 cell = row.createCell((short) 4);
                 cell.setCellValue(grade.getTotalPoint());
                 cell.setCellStyle(style);
                
                for (int j = 0 ; j<expertSize ; j++)
                {
                    cell = row.createCell((short) j+5);
                    if (null != expertGradeList.get(i).get(j).getTotalPoint())
                    {
                    	cell.setCellValue(expertGradeList.get(i).get(j).getTotalPoint());
                    }
                    cell.setCellStyle(style);
                }
              i++;
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
// 	          OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");	                       	     
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
