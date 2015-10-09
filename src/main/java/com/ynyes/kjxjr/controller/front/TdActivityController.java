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
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdOrderService;
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
	
	   /**
     * 企业填写资料
     * @author Zhangji
     * @param req
     * @param map
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String activityList(HttpServletRequest req, ModelMap map,Integer page) {
        String username = (String) req.getSession().getAttribute("ActivityUsername");

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
        String username = (String) req.getSession().getAttribute("ActivityUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);
        
        TdActivity activity = tdActivityService.findByStatusId(0L);
        map.addAttribute("activity", activity);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//        Page<TdActivity> activityPage = tdActivityService.findAllOrderByIdDesc(page,  ClientConstant.pageSize);
        
//        map.addAttribute("activity_page", activityPage);
        map.addAttribute("user", user);

        return "/client/activity_create";
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
        
        tdActivity.setStatusId(1L);
        tdActivity.setCreateTime(new Date());
       
       	tdActivityService.save(tdActivity);
       
        res.put("code", 0);
        return res;
    }
  
}
