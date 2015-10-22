package com.ynyes.kjxjr.controller.front;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ynyes.kjxjr.entity.TdUser;
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
import com.ynyes.kjxjr.service.TdRegionService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.ClientConstant;


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
        TdActivity unfinish = tdActivityService.findByStatusId(0L);
        
        map.addAttribute("unfinish", unfinish);
        map.addAttribute("activity_page", tdActivityService.findByStatusIdOrderByIdDesc(1L,page, ClientConstant.pageSize));
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
        
        TdActivity activity = tdActivityService.findByStatusId(0L);
        if (null != activity)
        {
	        map.addAttribute("activity", activity);
	        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activity.getId()));
	        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(activity.getId()));
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        map.addAttribute("user", user);
        map.addAttribute("pagetype", "create");
        map.addAttribute("done", done);

        return "/client/activity_create";
    }
    
    //区县审核项目，查看详情
    @RequestMapping(value = "/enterprise/check/{id}", method = RequestMethod.GET)
    public String userEnterpriseCheck(HttpServletRequest req, ModelMap map,@PathVariable Long id) {
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

        return "/client/activity_enterprise_check";
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
	        map.addAttribute("activity", activity);
	        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(id));
	        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(id));
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        map.addAttribute("user", user);
        map.addAttribute("pagetype", "check");

        return "/client/activity_create";
    }
    
    //管理活动
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String activityEdit(HttpServletRequest req, ModelMap map,Long id) {
        String username = (String) req.getSession().getAttribute("activityUsername");
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        
        //检查是否有未完成的创建
        TdActivity unfinish = tdActivityService.findByStatusId(0L);
        if (null != unfinish && unfinish.getId() != id)
        {
        	map.addAttribute("alert", 1);
            map.addAttribute("activity_page", tdActivityService.findByStatusIdOrderByIdDesc(1L,0, ClientConstant.pageSize));
            map.addAttribute("unfinish", unfinish);
            map.addAttribute("user", user);

            return "/client/activity_list";
        }
        
        TdActivity activity = tdActivityService.findOne(id);
        if (null != activity)
        {
        	activity.setStatusId(0L);
        	tdActivityService.save(activity);
	        map.addAttribute("activity", activity);
	        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(id));
	        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(id));
        }

//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
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
        	
        	
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        
        TdActivity unfinish = tdActivityService.findByStatusId(0L);
        
        map.addAttribute("unfinish", unfinish);
        map.addAttribute("activity_page", tdActivityService.findByStatusIdOrderByIdDesc(1L,0, ClientConstant.pageSize));
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
        Long statusId = activity.getStatusId();
        if(0 == statusId)
        {
        	activity.setStatusEn(1L);

        	tdActivityService.save(activity);
        }
        map.addAttribute("activity", activity);
        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activity.getId()));
        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(activity.getId()));
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        map.addAttribute("user", user);

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

        tdCommonService.setHeader(map, req);
        List<TdActivityExpert> sum = tdActivityExpertService.findByActivityId(activityId);
        //限制数量为7个
        if (sum.size() < 7)
        {
        	res.put("msg", "请选出7个专家！");
        	return res;	
        }
        
        TdActivity activity = tdActivityService.findOne(activityId);
        if(0L ==activity.getStatusId())
        {
        	activity.setStatusEx(1L);

        	tdActivityService.save(activity);
        }
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
    @RequestMapping(value = "/selectEnterprise")
    public String  selectEnterprise(HttpServletRequest req,
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
      
       
        	
      
        
        TdActivity activity = tdActivityService.findByStatusId(0L);
        Long activityId = activity.getId();
        map.addAttribute("keywords", keywords);
        map.addAttribute("area", area);
        map.addAttribute("type", type);
        map.addAttribute("formType", formType);
       	map.addAttribute("activity", activity);
       	map.addAttribute("activityId", activityId);
     	map.addAttribute("statusId", 0);
     	map.addAttribute("enterprise_page", enterprisePage);
     	map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
     	map.addAttribute("region_list", tdRegionService.findByIsEnableTrueOrderBySortIdAsc());
     	map.addAttribute("enterpriseType_list", tdEnterpriseTypeService.findByIsEnableTrueOrderBySortIdAsc());
        return "/client/activity_selectEnterprise";
    }

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
        		tdActivityEnterpriseService.save(activityEnterprise);
        	}
        	
        }
        
        map.addAttribute("activityId",activityId);
        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
        return "/client/activity_selected_enterprise";
    }
//    //批量增加
//    @RequestMapping(value = "/addEnterprises")
//    public String  addEnterprises(HttpServletRequest req, 
//    		Long[] listId,
//            Integer[] listChkId,
//            Long activityId,
//    		ModelMap map) {
//        String username = (String) req.getSession().getAttribute("activityUsername");
//
//        if (null == username) {
//            return "redirect:/login";
//        }
//      
//        addEnterprise(listId, listChkId,activityId);
//        
//        
//        TdActivity activity = tdActivityService.findByStatusId(0L);
//      
//     
//       	map.addAttribute("activity", activity);
//       	map.addAttribute("activityId", activityId);
//     	map.addAttribute("statusId", 0);
//     	map.addAttribute("enterprise_page", enterprisePage);
//     	map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
//     	map.addAttribute("region_list", tdRegionService.findByIsEnableTrueOrderBySortIdAsc());
//     	map.addAttribute("enterpriseType_list", tdEnterpriseTypeService.findByIsEnableTrueOrderBySortIdAsc());
//        map.addAttribute("activityId",activityId);
//        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
//        return "/client/activity_selectEnterprise";
//    }
//    
//    private void addEnterprise(Long[] ids, Integer[] chkIds,Long activityId)
//    {
//    	if (null == ids || null == chkIds
//                || ids.length < 1 || chkIds.length < 1||null == activityId)
//        {
//            return;
//        }
//        
//        for (int chkId : chkIds)
//        {
//            if (chkId >=0 && ids.length > chkId)
//            {
//                Long id = ids[chkId];
//
//            	TdEnterprise enterprise = tdEnterpriseService.findOne(id);
//            	TdActivity activity = tdActivityService.findOne(activityId);
//            	
//            	TdActivityEnterprise ActivityEnterprise = tdActivityEnterpriseService.findByActivityIdAndEnterpriseId(activityId,id);
//            	if (null == ActivityEnterprise)
//            	{
//            		TdActivityEnterprise newEnter =new  TdActivityEnterprise();
//            		newEnter.setEnterpriseId(id);
//            		newEnter.setCreateTime(new Date());
//            		newEnter.setEnterpriseTitle(enterprise.getTitle());
//            		newEnter.setActivityId(activity.getId());
//            		newEnter.setActivityTitle(activity.getTitle());
//            		newEnter.setArea(enterprise.getArea());
//            		newEnter.setType(enterprise.getType());
//            		tdActivityEnterpriseService.save(newEnter);
//            	}
//            	else
//            	{
//            		ActivityEnterprise.setCreateTime(new Date());
//            		ActivityEnterprise.setEnterpriseTitle(enterprise.getTitle());
//            		ActivityEnterprise.setActivityTitle(activity.getTitle());
//            		ActivityEnterprise.setArea(enterprise.getArea());
//            		ActivityEnterprise.setType(enterprise.getType());
//            		tdActivityEnterpriseService.save(ActivityEnterprise);
//            	}
//            }
//        }
//    }

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
        	}
        }
        
        map.addAttribute("activityId",activityId);
        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
        return "/client/activity_selected_enterprise";
    }
    
//    //批量取消
//    @RequestMapping(value = "/removeEnterprises")
//    public String  removeEnterprises(HttpServletRequest req,
//    		Long activityId,
//    		Long[] listId,
//            Integer[] listChkId,
//    		ModelMap map) {
//        String username = (String) req.getSession().getAttribute("activityUsername");
//
//        if (null == username) {
//            return "redirect:/login";
//        }
//        removeEnterprise(listId, listChkId);
//        
//        map.addAttribute("activityId",activityId);
//        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(activityId));
//        return "/client/activity_selectEnterprise";
//    }
//    
//    private void removeEnterprise(Long[] ids, Integer[] chkIds)
//    {
//    	if (null == ids || null == chkIds
//                || ids.length < 1 || chkIds.length < 1)
//        {
//            return;
//        }
//        
//        for (int chkId : chkIds)
//        {
//            if (chkId >=0 && ids.length > chkId)
//            {
//                Long id = ids[chkId];
//                
//                tdActivityEnterpriseService.delete(id);
//            }
//        }
//    }
    /*
     * 创建活动:选专家评委
     * 
     */
  
    @RequestMapping(value = "/selectExpert")
    public String  selectExpert(HttpServletRequest req,
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
        
        TdActivity activity = tdActivityService.findByStatusId(0L);
        Long activityId = activity.getId();
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
        
        List<TdActivityExpert> expertList = tdActivityExpertService.findByActivityId(activityId);
        if (expertList.size() > 6)
        {
        	res.put("msg", "最大添加人数为7人！！");
        	return res;
          
        }
        
      
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
        		tdActivityExpertService.save(newEnter);
        	}
        	else
        	{
        		ActivityExpert.setCreateTime(activity.getDate());
        		ActivityExpert.setName(expert.getName());
        		ActivityExpert.setActivityTitle(activity.getTitle());
        		ActivityExpert.setEmail(expert.getEmail());
        		ActivityExpert.setUsermobile(expert.getUsermobile());
        		tdActivityExpertService.save(ActivityExpert);
        	}
        	
        	//同时创建评分表数据
        	TdEnterpriseGrade enterpriseGrade = tdEnterpriseGradeService.findByExpertIdAndActivityId(activityId,id);
        	if (null == enterpriseGrade)
        	{
        		for(int i =0;i<20;i++)
        		{
        		TdEnterpriseGrade newEnter =new  TdEnterpriseGrade();
        		newEnter.setExpertId(id);
        		newEnter.setActivityId(activity.getId());
        		newEnter.setIsGrade(false);
        		tdEnterpriseGradeService.save(newEnter);
        		}
        	}
        	else
        	{
        		enterpriseGrade.setExpertId(id);
        		tdActivityExpertService.save(ActivityExpert);
        	}
        	
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
        	List<TdEnterpriseGrade> enterpriseGradeList = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(ActivityExpert.getExpertId() , activityId);
        	if (null != enterpriseGradeList)
        	{
        		for(TdEnterpriseGrade item : enterpriseGradeList)
        		{
        			tdEnterpriseGradeService.delete(item.getId());
        		}
        	}
       
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
        
        if (null == title || null == activityType || null == region 
        	|| null == date || null == address || null == theme || null == introduction 
        	||null == prepareOn || null == prepareOff ||null == eventEnd)
        {
        	res.put("msg", "请先填写完整资料！");
        	return res;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateF = sdf.parse(date);
        Date prepareOnF = sdf.parse(prepareOn);
        Date prepareOffF = sdf.parse(prepareOff);
        Date eventEndF = sdf.parse(eventEnd);
        
        TdActivity unfinish = tdActivityService.findByStatusId(0L);
        if (null!=unfinish)
        {
        	TdActivity activity = tdActivityService.findOne(id);
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
        	activity.setStatusId(0L);
        	tdActivityService.save(activity);
        }
        else
        {
        	TdActivity activity = new TdActivity();
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
        	activity.setStatusId(0L);
        	tdActivityService.save(activity);
        }
       
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
        
        
        if (null == tdActivity.getStatusEn()|| null ==  tdActivity.getStatusEx())
        {
        	res.put("msg", "请先选择项目和专家！");
        	return res;
        }
        else if (1 == tdActivity.getStatusEn()&&1 == tdActivity.getStatusEx())
    	{
    		tdActivity.setStatusId(1L);     //同时选择过项目和专家后，创建活动成功
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
  
    @RequestMapping(value = "/getGrade")
    public String getGrade(Long activityId,ModelMap map){
    	List<TdActivityExpert> activity_experts = tdActivityExpertService.findByActivityId(activityId);
    	if(null != activity_experts){
    		if(0 < activity_experts.size()){
    			List<TdEnterpriseGrade> experts0_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(activity_experts.get(0).getExpertId(), activityId);
    			map.addAttribute("expert0_grade", experts0_grade);
    			map.addAttribute("expert0", activity_experts.get(0).getName());
    		}
    		
			if(1 < activity_experts.size()){
				List<TdEnterpriseGrade> experts1_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(activity_experts.get(1).getExpertId(), activityId);
    			map.addAttribute("expert1_grade", experts1_grade);
    			map.addAttribute("expert1", activity_experts.get(1).getName());		
    		}
			
			if(2 < activity_experts.size()){
				List<TdEnterpriseGrade> experts2_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(activity_experts.get(2).getExpertId(), activityId);
    			map.addAttribute("expert2_grade", experts2_grade);
    			map.addAttribute("expert2", activity_experts.get(2).getName());
			}
			
			if(3 < activity_experts.size()){
				List<TdEnterpriseGrade> experts3_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(activity_experts.get(3).getExpertId(), activityId);
    			map.addAttribute("expert3_grade", experts3_grade);
    			map.addAttribute("expert3", activity_experts.get(3).getName());
			}
			
			if(4 < activity_experts.size()){
				List<TdEnterpriseGrade> experts4_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(activity_experts.get(4).getExpertId(), activityId);
    			map.addAttribute("expert4_grade", experts4_grade);
    			map.addAttribute("expert4", activity_experts.get(4).getName());
			}
			
			if(5 < activity_experts.size()){
				List<TdEnterpriseGrade> experts5_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(activity_experts.get(5).getExpertId(), activityId);
    			map.addAttribute("expert5_grade", experts5_grade);
    			map.addAttribute("expert5", activity_experts.get(5).getName());
			}
			
			if(6 < activity_experts.size()){
				List<TdEnterpriseGrade> experts6_grade = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(activity_experts.get(6).getExpertId(), activityId);
    			map.addAttribute("expert6_grade", experts6_grade);
    			map.addAttribute("expert6", activity_experts.get(6).getName());
			}
    	}
    	return "/client/total_grade";
    }
    
    @RequestMapping(value = "/getCoach")
    public String getCoach(Long enterpriseId,Long activityId,ModelMap map,HttpServletRequest req,String keywords,Integer page){
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
    	map.addAttribute("keywords", keywords);
    	map.addAttribute("page", page);
    	map.addAttribute("ExpertPage", ExpertPage);
    	map.addAttribute("enterpriseId", enterpriseId);
    	map.addAttribute("activityId", activityId);
    	return "/client/activity_coach_expert";
    }
    
    @RequestMapping(value="/addCoach")
    public String addCoach(Long expertId,Long enterpriseId,Long activityId,HttpServletRequest req){
    	String activityUsername = (String) req.getSession().getAttribute("activityUsername");
    	if(null == activityUsername){
    		return "/client/login";
    	}
    	
    	TdEnterprise enterprise = tdEnterpriseService.findOne(enterpriseId);
    	TdExpert expert = tdExpertService.findOne(expertId);
    	TdExpertCoachEnterprise coach = new TdExpertCoachEnterprise();
    	coach.setEnterpriseId(enterpriseId);
    	coach.setEnterpriseName(enterprise.getTitle());
    	coach.setIsGrade(false);
    	coach.setAddr(enterprise.getAddress());
    	coach.setExpertName(expert.getName());
    	tdExpertCoachEnterpriseService.save(coach);
    	return "redirect:/activity/edit?id="+activityId;
    }
}
