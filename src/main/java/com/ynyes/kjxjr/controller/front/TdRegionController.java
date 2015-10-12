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
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdRegion;
import com.ynyes.kjxjr.entity.TdRegionAdmin;
import com.ynyes.kjxjr.entity.TdUser;
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
        
        Enterprise.setStatusId(0L);
        tdEnterpriseService.save(Enterprise);
        
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
        Page<TdActivity> activityPage = tdActivityService.findByRegionOrderByIdDesc(regionAdmin.getTitle(),page, ClientConstant.pageSize);
        
        
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

        return "/client/activity_create";
    }
}
