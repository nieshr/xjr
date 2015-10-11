package com.ynyes.kjxjr.controller.front;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.kjxjr.entity.TdActivity;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdCouponService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdEnterpriseTypeService;
import com.ynyes.kjxjr.service.TdExpertService;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdActivityTypeService;
import com.ynyes.kjxjr.service.TdOrderService;
import com.ynyes.kjxjr.service.TdRegionService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.ClientConstant;
import com.ynyes.kjxjr.util.SMSUtil;
import com.ynyes.kjxjr.util.SiteMagConstant;

import scala.reflect.macros.internal.macroImpl;


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

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        map.addAttribute("user", user);

        return "/client/activity_list";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String activityCreate(HttpServletRequest req, ModelMap map,Integer page) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);
        
        TdActivity activity = tdActivityService.findByStatusId(0L);
        map.addAttribute("activity", activity);
        map.addAttribute("activityType_list", tdActivityTypeService.findByIsEnableTrueOrderBySortIdAsc());
        map.addAttribute("region_list", tdRegionService.findByIsEnableTrueOrderBySortIdAsc());
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        map.addAttribute("user", user);

        return "/client/activity_create";
    }
    
    /*
     * 创建活动:选项目
     * 
     */
    @RequestMapping(value = "/selectEnterprise")
    public String  selectEnterprise(HttpServletRequest req,
    		ModelMap map) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }
      
        TdActivity activity = tdActivityService.findByStatusId(0L);
       	map.addAttribute("activity", activity);
     	map.addAttribute("statusId", 0);
     	map.addAttribute("enterprise_list", tdEnterpriseService.findAllOrderBySortIdAsc(0,ClientConstant.pageSize));
     	map.addAttribute("region_list", tdRegionService.findByIsEnableTrueOrderBySortIdAsc());
     	map.addAttribute("enterpriseType_list", tdEnterpriseTypeService.findByIsEnableTrueOrderBySortIdAsc());
        return "/client/activity_selectEnterprise";
    }
    
    /*
     * 创建活动:选专家评委
     * 
     */
    @RequestMapping(value = "/selectExpert")
    public String  selectExpert(HttpServletRequest req,TdActivity tdActivity,
    		ModelMap map) {
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
            return "redirect:/login";
        }
        Long statusId = tdActivity.getStatusId();
        if (statusId == 0L)
        {
        	tdActivity.setStatusId(1L);
        }
        else if (null ==statusId)
        {
        	tdActivity.setStatusId(0L);
        }
       	tdActivityService.save(tdActivity);
       	map.addAttribute("activity", tdActivity);
     	map.addAttribute("statusId", 0);
     	map.addAttribute("expert_list", tdExpertService.findAllOrderBySortIdAsc(0,ClientConstant.pageSize));
     	map.addAttribute("region_list", tdRegionService.findByIsEnableTrueOrderBySortIdAsc());
     	map.addAttribute("enterpriseType_list", tdEnterpriseTypeService.findByIsEnableTrueOrderBySortIdAsc());
        return "/client/activity_selectExpert";
    }
    
    @RequestMapping(value = "/bufferEn", method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, Object> activityBuffer(HttpServletRequest req,
    											ModelMap map,
    											Long id,
    											String title,
    											String type,
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
        
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date dateF = sdf.parse(date);
//        Date prepareOnF = sdf.parse(prepareOn);
//        Date prepareOffF = sdf.parse(prepareOff);
//        Date eventEndF = sdf.parse(eventEnd);
        if (null!=id)
        {
        	TdActivity activity = tdActivityService.findOne(id);
        	activity.setTitle(title);
        	activity.setType(type);
        	activity.setRegion(region);
//        	activity.setDate(dateF);
        	activity.setAddress(address);
        	activity.setTheme(theme);
        	activity.setIntroduction(introduction);
//        	activity.setPrepareOn(prepareOnF);
//        	activity.setPrepareOff(prepareOffF);
//        	activity.setEventEnd(eventEndF);
        	tdActivityService.save(activity);
        }
        else
        {
        	TdActivity activity = new TdActivity();
        	activity.setTitle(title);
//        	activity.setType(type);
        	activity.setRegion(region);
//        	activity.setDate(dateF);
        	activity.setAddress(address);
        	activity.setTheme(theme);
        	activity.setIntroduction(introduction);
//        	activity.setPrepareOn(prepareOnF);
//        	activity.setPrepareOff(prepareOffF);
//        	activity.setEventEnd(eventEndF);
        	activity.setStatusId(0L);
        	tdActivityService.save(activity);
        }
       
        res.put("code", 0);
        return res;
    }
    
    
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> activitySubmit(HttpServletRequest req,TdActivity tdActivity,
    		ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("activityUsername");

        if (null == username) {
        	res.put("msg", "请先登录！");
            return res;
        }
        Long statusId = tdActivity.getStatusId();
        if (statusId == 1L)
        {
        	tdActivity.setStatusId(2L);
        }
        else
        {
        	res.put("msg", "请先选择项目和专家！");
        	return res;
        }
        tdActivity.setCreateTime(new Date());
       
       	tdActivityService.save(tdActivity);
       
        res.put("code", 0);
        return res;
    }
  
}
