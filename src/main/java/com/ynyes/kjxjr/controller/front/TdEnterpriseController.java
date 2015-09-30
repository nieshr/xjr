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
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdOrder;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdCouponService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdOrderService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.SMSUtil;
import com.ynyes.kjxjr.util.SiteMagConstant;

import scala.reflect.macros.internal.macroImpl;


@Controller
@RequestMapping(value="/enterprise")
public class TdEnterpriseController {
	
	@Autowired
	TdOrderService tdOrderService;
	
	@Autowired
	TdCommonService tdCommonService;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdEnterpriseService tdEnterpriseService;
	
	@Autowired
	TdCouponService tdCouponService;
	
	   /**
     * 企业填写资料
     * @author Zhangji
     * @param req
     * @param map
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String userEnterpriseInfo(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise Enterprise = tdEnterpriseService.findbyUsername(username);
        
        map.addAttribute("enterprise", Enterprise);
        map.addAttribute("user", user);

        return "/client/enterprise_info";
    }
    
    /**
     * 资料提交
     * @author Zhangji
     * @param req
     * @param tdEnterprise
     * @param map
     * @return
     */
    @RequestMapping(value = "/info/submit", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> enterpriseInfoSubmit(HttpServletRequest req,TdEnterprise tdEnterprise,
    		ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
        	res.put("msg", "请先登录！");
            return res;
        }
        TdUser user = tdUserService.findByUsername(username);
        
        tdEnterprise.setCreateTime(new Date());
        tdEnterprise.setPassword(user.getPassword());
       	tdEnterpriseService.save(tdEnterprise);
       
        res.put("code", 0);
        return res;
    }
    
    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String userEnterprisePrint(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise Enterprise = tdEnterpriseService.findbyUsername(username);
        
        map.addAttribute("enterprise", Enterprise);
        map.addAttribute("user", user);

        return "/client/enterprise_print";
    }
    
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String userPassword(HttpServletRequest req, ModelMap map) {
        String EnterpriseUsername = (String) req.getSession().getAttribute("EnterpriseUsername");

        if (null == EnterpriseUsername) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdEnterprise EnterpriseUser = tdEnterpriseService.findbyUsername(EnterpriseUsername);

        map.addAttribute("user", EnterpriseUser);

        return "/client/Enterprise_change_password";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userPassword(HttpServletRequest req, String oldPassword,
            String newPassword, ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        String EnterpriseUsername = (String) req.getSession().getAttribute("EnterpriseUsername");

        if (null == EnterpriseUsername) {
        	res.put("msg", "请先登录！");
            return res;
        }

        TdEnterprise EnterpriseUser = tdEnterpriseService.findbyUsername(EnterpriseUsername);

        if (EnterpriseUser.getPassword().equals(oldPassword)) {
        	EnterpriseUser.setPassword(newPassword);
        }

        map.addAttribute("user", tdEnterpriseService.save(EnterpriseUser));

        res.put("code", 0);
        return res;
    }
}
