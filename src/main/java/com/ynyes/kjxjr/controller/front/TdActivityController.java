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

import com.ynyes.kjxjr.entity.TdCoupon;
import com.ynyes.kjxjr.entity.TdActivity;
import com.ynyes.kjxjr.entity.TdOrder;
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
    
  
}
