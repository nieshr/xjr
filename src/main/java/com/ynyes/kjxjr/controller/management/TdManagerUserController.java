package com.ynyes.kjxjr.controller.management;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.kjxjr.entity.TdActivityType;
import com.ynyes.kjxjr.entity.TdDemand;
import com.ynyes.kjxjr.entity.TdDiySite;
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdEnterpriseType;
import com.ynyes.kjxjr.entity.TdRegion;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.entity.TdUserComment;
import com.ynyes.kjxjr.entity.TdUserConsult;
import com.ynyes.kjxjr.entity.TdUserLevel;
import com.ynyes.kjxjr.entity.TdUserPoint;
import com.ynyes.kjxjr.entity.TdUserReturn;
import com.ynyes.kjxjr.service.TdDemandService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdEnterpriseTypeService;
import com.ynyes.kjxjr.service.TdManagerLogService;
import com.ynyes.kjxjr.service.TdRegionService;
import com.ynyes.kjxjr.service.TdUserCashRewardService;
import com.ynyes.kjxjr.service.TdUserCollectService;
import com.ynyes.kjxjr.service.TdUserCommentService;
import com.ynyes.kjxjr.service.TdUserConsultService;
import com.ynyes.kjxjr.service.TdUserLevelService;
import com.ynyes.kjxjr.service.TdUserPointService;
import com.ynyes.kjxjr.service.TdUserRecentVisitService;
import com.ynyes.kjxjr.service.TdUserReturnService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.service.TdUserSuggestionService;
import com.ynyes.kjxjr.util.SiteMagConstant;

/**
 * 后台用户管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/user")
public class TdManagerUserController {
    
    @Autowired
    TdUserService tdUserService;
    
    @Autowired
    TdUserLevelService tdUserLevelService;
    
    @Autowired
    TdUserConsultService tdUserConsultService;
    
    @Autowired
    TdUserCommentService tdUserCommentService;
    
    @Autowired
    TdUserSuggestionService tdUserSuggestionService;  //add by zhangji
    
    @Autowired
    TdDemandService tdDemandService;  //@zhangji 2015年7月30日11:25:00
    
    @Autowired
    TdUserReturnService tdUserReturnService;
    
    @Autowired
    TdUserCollectService tdUserCollectService;
    
    @Autowired
    TdUserPointService tdUserPointService;
    
    @Autowired
    TdUserRecentVisitService tdUserRecentVisitService;
    
    @Autowired
    TdUserCashRewardService tdUserCashRewardService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    //企业 zhangji 2015年10月21日9:14:52
    @Autowired
    TdEnterpriseService tdEnterpriseService;
    
    @Autowired
    TdRegionService tdRegionService;
    
    @Autowired
    TdEnterpriseTypeService tdEnterpriseTypeService;
    
    @RequestMapping(value="/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(String param, Long id) {
        Map<String, String> res = new HashMap<String, String>();
        
        res.put("status", "n");
        
        if (null == param || param.isEmpty())
        {
            res.put("info", "该字段不能为空");
            return res;
        }
        
        if (null == id)
        {
            if (null != tdUserService.findByUsername(param))
            {
                res.put("info", "已存在同名用户");
                return res;
            }
        }
        else
        {
            if (null != tdUserService.findByUsernameAndIdNot(param, id))
            {
                res.put("info", "已存在同名用户");
                return res;
            }
        }
        
        res.put("status", "y");
        
        return res;
    }
    
    
    
    //企业添加编辑 检验重复电话
    @RequestMapping(value = "/check/mobile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateFormMobile(String param, Long id) {
        Map<String, String> res = new HashMap<String, String>();

        res.put("status", "n");

	        if (null == param || param.isEmpty()) {						
	            res.put("info", "该字段不能为空");
	            return res;
	        }
	        
	        TdUser tdUser = tdUserService.findByMobile(param);
	        
	        if (null == id) // 新增
	        {
	            if (null != tdUser) {
	                res.put("info", "该手机号不能使用");
	                return res;
	            }
	        } 
	        else // 修改，查找除当前ID的所有
	        {
	            TdUser thisUser = tdUserService.findOne(id);
	            
	            if (null == thisUser)
	            {
	                if (null != tdUser) {
	                    res.put("info", "该手机号不能使用");
	                    return res;
	                }
	            }
	            else
	            {
	                if (null != tdUser && !tdUser.getMobile().equals(thisUser.getMobile())  ) {
	                    res.put("info", "该手机号已被使用");
	                    return res;
	                }
	            }
	        }

        
        res.put("status", "y");

        return res;
    }
    
    @RequestMapping(value="/list")
    public String setting(Integer page,
                          Integer size,
                          String keywords,
                          Long roleId,
                          String __EVENTTARGET,
                          String __EVENTARGUMENT,
                          String __VIEWSTATE,
                          Long[] listId,
                          Integer[] listChkId,
                          ModelMap map,
                          HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete("user", listId, listChkId);
                tdManagerLogService.addLog("delete", "删除用户", req);
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        
        if (null != keywords)
        {
            keywords = keywords.trim();
        }
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("roleId", roleId);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        Page<TdUser> userPage = null;
        
//        if (null == roleId)
//        {
//            if (null == keywords || "".equalsIgnoreCase(keywords))
//            {
//                userPage = tdUserService.findAllOrderBySortIdAsc(page, size);
//            }
//            else
//            {
//                userPage = tdUserService.searchAndOrderByIdDesc(keywords, page, size);
//            }
//        }
//        else
//        {
//            if (null == keywords || "".equalsIgnoreCase(keywords))
//            {
//                userPage = tdUserService.findByRoleIdOrderByIdDesc(roleId, page, size);
//            }
//            else
//            {
//                userPage = tdUserService.searchAndFindByRoleIdOrderByIdDesc(keywords, roleId, page, size);
//            }
//        }
        
        //只查找企业
      if (null == keywords || "".equalsIgnoreCase(keywords))
      {
          userPage = tdUserService.findByRoleIdOrderByIdDesc(1L, page, size);
      }
      else
      {
          userPage = tdUserService.searchAndFindByRoleIdOrderByIdDesc(keywords, 1L, page, size);
      }
        
        map.addAttribute("user_page", userPage);
        
        return "/site_mag/user_list";
    }
    
    @RequestMapping(value="/edit")
    public String orderEdit(Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
      
        if (null != id)
        {
        	
            map.addAttribute("user", tdUserService.findOne(id));
            
            //查看企业资料信息
            TdEnterprise enterprise = tdEnterpriseService.findbyUsername(tdUserService.findOne(id).getUsername());
            
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
            
            //活动地区
            List<TdRegion> regionList = tdRegionService.findByIsEnableTrueOrderBySortIdAsc();
            map.addAttribute("region_list", regionList);
            
            //项目归属类别
            List<TdEnterpriseType> enterpriseTypeList = tdEnterpriseTypeService.findByIsEnableTrueOrderBySortIdAsc();
            map.addAttribute("enterpriseType_list", enterpriseTypeList);
        }
        return "/site_mag/user_edit";
    }
    
    /**
     * 超级管理员修改企业资料
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
    	
        String username = (String) req.getSession().getAttribute("manager");

        if (null == username) {
        	res.put("msg", "请先登录！");
        	res.put("check", 0);
            return res;
        }
        
//        if (null != tdEnterprise.getStatusId()&&1 == tdEnterprise.getStatusId())
//        {
//        	res.put("msg", "资料已审核，如需修改请申请重新审核！");
//        	res.put("check", 1);
//        	return res;
//        }
        
        TdUser user = tdUserService.findByUsername(tdEnterprise.getUsername());
    
        
        
        Long id = tdEnterprise.getId();
        String number = String.format("%04d", id);
        
        tdEnterprise.setNumber(number);
        tdEnterprise.setCreateTime(new Date());
        tdEnterprise.setPassword(user.getPassword());
       	tdEnterpriseService.save(tdEnterprise);
       
        res.put("code", 0);
        return res;
    }
    
    /**
     * 超级管理员修改审核状态
     * @param req
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value = "/enterprise/status", method = RequestMethod.POST)
    public  Map<String, Object> userEnterpriseStatus(HttpServletRequest req, ModelMap map, Long id , Long statusId) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
    	
        String username = (String) req.getSession().getAttribute("manager");

        if (null == username) {
        	res.put("msg", "请先登录！");
        	res.put("check", 0);
            return res;
        }
        
        if (null == id)
        {
        	return res;
        }


        
        TdEnterprise Enterprise = tdEnterpriseService.findOne(id);
        if (null != statusId)
        {
	        Enterprise.setStatusId(statusId);
	        tdEnterpriseService.save(Enterprise);
        }
        map.addAttribute("enterprise", Enterprise);

        res.put("code", 0);
        return res;
    }
    
    @RequestMapping(value="/save")
    public String orderEdit(TdUser tdUser,Long totalPoints, String totalPointsRemarks,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        /**
		 * @author lc
		 * @注释：手动修改用户积分
		 */
        if (null != totalPoints) {
			tdUser.setTotalPoints(totalPoints);
			TdUserPoint userPoint = new TdUserPoint();
	        
	        userPoint.setTotalPoint(totalPoints);
	        userPoint.setUsername(tdUser.getUsername());
	        userPoint.setPoint(totalPoints);
			if (null !=totalPointsRemarks) {	
		        userPoint.setDetail(totalPointsRemarks);   
			}
			userPoint = tdUserPointService.save(userPoint);
		}
        
        if (null == tdUser.getId())
        {
            tdManagerLogService.addLog("add", "修改用户", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改用户", req);
        }
        
        tdUserService.save(tdUser);
        
        return "redirect:/Verwalter/user/list/";
    }
    
    @RequestMapping(value="/level/edit")
    public String edit(Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null != id)
        {
            map.addAttribute("userLevelId", id);
            map.addAttribute("user_level", tdUserLevelService.findOne(id));
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        return "/site_mag/user_level_edit";
    }
    
    @RequestMapping(value="/level/save")
    public String levelSave(TdUserLevel tdUserLevel,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        if (null == tdUserLevel.getId())
        {
            tdManagerLogService.addLog("add", "修改用户等级", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改用户等级", req);
        }
        
        tdUserLevelService.save(tdUserLevel);
        
        return "redirect:/Verwalter/user/level/list";
    }
    
    @RequestMapping(value="/level/check/{type}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(@PathVariable String type, 
                                            String param,
                                            Long id) {
        Map<String, String> res = new HashMap<String, String>();
        
        res.put("status", "n");
        res.put("info", "通过");
        
        if (null != type)
        {
            if (type.equalsIgnoreCase("levelId"))
            {
                if (null == param || param.isEmpty())
                {
                    res.put("info", "该字段不能为空");
                    return res;
                }
                
                if (null == id)
                {
                    if (null != tdUserLevelService.findByLevelId(Long.parseLong(param)))
                    {
                        res.put("info", "该用户等级已存在");
                        return res;
                    }
                }
                else
                {
                    if (null != tdUserLevelService.findByLevelIdAndIdNot(Long.parseLong(param), id))
                    {
                        res.put("info", "该用户等级已存在");
                        return res;
                    }
                }
                
                res.put("status", "y");
            }
            else if (type.equalsIgnoreCase("title"))
            {
                if (null == param || param.isEmpty())
                {
                    res.put("info", "该字段不能为空");
                    return res;
                }
                
                if (null == id)
                {
                    if (null != tdUserLevelService.findByTitle(param))
                    {
                        res.put("info", "该等级用户名称已存在");
                        return res;
                    }
                }
                else
                {
                    if (null != tdUserLevelService.findByTitleAndIdNot(param, id))
                    {
                        res.put("info", "该等级用户名称已存在");
                        return res;
                    }
                }
                
                res.put("status", "y");
            }
        }
        
        return res;
    }
    
    @RequestMapping(value="/consult/edit")
    public String consultEdit(Long id,
                        Long statusId,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null != id)
        {
            map.addAttribute("userConsultId", id);
            map.addAttribute("user_consult", tdUserConsultService.findOne(id));
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("statusId", statusId);
        
        return "/site_mag/user_consult_edit";
    }
    
    @RequestMapping(value="/consult/save")
    public String consultSave(TdUserConsult tdUserConsult,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null == tdUserConsult.getIsReplied() || !tdUserConsult.getIsReplied())
        {
            tdUserConsult.setIsReplied(true);
            tdUserConsult.setReplyTime(new Date());
        }
        
        if (null == tdUserConsult.getId())
        {
            tdManagerLogService.addLog("add", "修改用户咨询", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改用户咨询", req);
        }
        
        tdUserConsultService.save(tdUserConsult);
        
        return "redirect:/Verwalter/user/consult/list?statusId=" + __VIEWSTATE;
    }
    
    @RequestMapping(value="/comment/edit")
    public String commentEdit(Long id,
                        Long statusId,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null != id)
        {
            map.addAttribute("userCommentId", id);
            map.addAttribute("user_comment", tdUserCommentService.findOne(id));
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("statusId", statusId);
        
        return "/site_mag/user_comment_edit";
    }
    
    @RequestMapping(value="/comment/save")
    public String commentSave(TdUserComment tdUserComment,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null == tdUserComment.getIsReplied() || !tdUserComment.getIsReplied())
        {
            tdUserComment.setIsReplied(true);
            tdUserComment.setReplyTime(new Date());
        }
        
        if (null == tdUserComment.getId())
        {
            tdManagerLogService.addLog("add", "修改用户评论", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改用户评论", req);
        }
        
        
        tdUserCommentService.save(tdUserComment);
        
        return "redirect:/Verwalter/user/comment/list?statusId=" + __VIEWSTATE;
    }
    
    @RequestMapping(value="/return/edit")
    public String returnEdit(Long id,
                        Long statusId,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null != id)
        {
            map.addAttribute("userReturnId", id);
            map.addAttribute("user_return", tdUserReturnService.findOne(id));
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("statusId", statusId);
        
        return "/site_mag/user_return_edit";
    }
    
    @RequestMapping(value="/return/save")
    public String returnSave(TdUserReturn tdUserReturn,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null == tdUserReturn.getId())
        {
            tdManagerLogService.addLog("add", "修改用户退换货", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改用户退换货", req);
        }
        
        
        tdUserReturnService.save(tdUserReturn);
        
        return "redirect:/Verwalter/user/return/list?statusId=" + __VIEWSTATE;
    }
    
    @RequestMapping(value="/{type}/list")
    public String list(@PathVariable String type,
                        Integer page,
                        Integer size,
                        Long userId,
                        Long statusId,
                        String keywords,
                        String __EVENTTARGET,
                        String __EVENTARGUMENT,
                        String __VIEWSTATE,
                        Long[] listId,
                        Integer[] listChkId,
                        Long[] listSortId,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(type, listId, listChkId);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(type, listId, listSortId);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnVerify"))
            {
                btnVerify(type, listId, listChkId);
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        
        if (null != keywords)
        {
            keywords = keywords.trim();
        }
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("userId", userId);
        map.addAttribute("statusId", statusId);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
            
        if (null != type)
        {
            if (type.equalsIgnoreCase("point")) // 积分
            {
                if (null == userId)
                {
                    return "/site_mag/error_404";
                }
                
                TdUser user = tdUserService.findOne(userId);
                
                if (null == user || null == user.getUsername())
                {
                    return "/site_mag/error_404";
                }
                
                map.addAttribute("user_point_page", tdUserPointService.findByUsername(user.getUsername(), page, size));
                return "/site_mag/user_point_list";
            }
            else if (type.equalsIgnoreCase("collect")) // 关注
            {
                if (null == userId)
                {
                    return "/site_mag/error_404";
                }
                
                TdUser user = tdUserService.findOne(userId);
                
                if (null == user || null == user.getUsername())
                {
                    return "/site_mag/error_404";
                }
                
                map.addAttribute("user_collect_page", tdUserCollectService.findByUsername(user.getUsername(), page, size));
                return "/site_mag/user_collect_list";
            }
            else if (type.equalsIgnoreCase("recent")) // 最近浏览
            {
                if (null == userId)
                {
                    return "/site_mag/error_404";
                }
                
                TdUser user = tdUserService.findOne(userId);
                
                if (null == user || null == user.getUsername())
                {
                    return "/site_mag/error_404";
                }
                
                map.addAttribute("user_recent_page", tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(user.getUsername(), page, size));
                return "/site_mag/user_recent_list";
            }
            else if (type.equalsIgnoreCase("reward")) // 返现
            {
                if (null == userId)
                {
                    return "/site_mag/error_404";
                }
                
                TdUser user = tdUserService.findOne(userId);
                
                if (null == user || null == user.getUsername())
                {
                    return "/site_mag/error_404";
                }
                
                map.addAttribute("user_reward_page", tdUserCashRewardService.findByUsernameOrderByIdDesc(user.getUsername(), page, size));
                return "/site_mag/user_reward_list";
            }
            else if (type.equalsIgnoreCase("level")) // 用户等级
            {
                map.addAttribute("user_level_page", tdUserLevelService.findAllOrderBySortIdAsc(page, size));
                return "/site_mag/user_level_list";
            }
            else if (type.equalsIgnoreCase("consult")) // 用户咨询
            {
                map.addAttribute("user_consult_page", findTdUserConsult(statusId, keywords, page, size));
                return "/site_mag/user_consult_list";
            }
            else if (type.equalsIgnoreCase("comment")) // 用户评论
            {
                map.addAttribute("user_comment_page", findTdUserComment(statusId, keywords, page, size));
                return "/site_mag/user_comment_list";
            }
            else if (type.equalsIgnoreCase("return")) // 退换货
            {
                map.addAttribute("user_return_page", findTdUserReturn(statusId, keywords, page, size));
                return "/site_mag/user_return_list";
            }
        }
        
        return "/site_mag/error_404";
    }
    
    @ModelAttribute
    public void getModel(@RequestParam(value = "userId", required = false) Long userId,
                    @RequestParam(value = "userLevelId", required = false) Long userLevelId,
                    @RequestParam(value = "userConsultId", required = false) Long userConsultId,
                    @RequestParam(value = "userCommentId", required = false) Long userCommentId,
                    @RequestParam(value = "userReturnId", required = false) Long userReturnId,
                        Model model) {
        if (null != userId) {
            model.addAttribute("tdUser", tdUserService.findOne(userId));
        }
        
        if (null != userLevelId) {
            model.addAttribute("tdUserLevel", tdUserLevelService.findOne(userLevelId));
        }
        
        if (null != userConsultId) {
            model.addAttribute("tdUserConsult", tdUserConsultService.findOne(userConsultId));
        }
        
        if (null != userCommentId) {
            model.addAttribute("tdUserComment", tdUserCommentService.findOne(userCommentId));
        }
        
        if (null != userReturnId) {
            model.addAttribute("tdUserReturn", tdUserReturnService.findOne(userReturnId));
        }
    }
    
    private Page<TdUserConsult> findTdUserConsult(Long statusId, String keywords, int page, int size)
    {
        Page<TdUserConsult> dataPage = null;
        
        if (null == statusId)
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                dataPage = tdUserConsultService.findAllOrderByIdDesc(page, size);
            }
            else
            {
                dataPage = tdUserConsultService.searchAndOrderByIdDesc(keywords, page, size);
            }
        }
        else
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                dataPage = tdUserConsultService.findByStatusIdOrderByIdDesc(statusId, page, size);
            }
            else
            {
                dataPage = tdUserConsultService.searchAndFindByStatusIdOrderByIdDesc(keywords, statusId, page, size);
            }
        }
        
        return dataPage;
    }
    
    private Page<TdUserComment> findTdUserComment(Long statusId, String keywords, int page, int size)
    {
        Page<TdUserComment> dataPage = null;
        
        if (null == statusId)
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                dataPage = tdUserCommentService.findAllOrderByIdDesc(page, size);
            }
            else
            {
                dataPage = tdUserCommentService.searchAndOrderByIdDesc(keywords, page, size);
            }
        }
        else
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                dataPage = tdUserCommentService.findByStatusIdOrderByIdDesc(statusId, page, size);
            }
            else
            {
                dataPage = tdUserCommentService.searchAndFindByStatusIdOrderByIdDesc(keywords, statusId, page, size);
            }
        }

        return dataPage;
    }
    
    private Page<TdUserReturn> findTdUserReturn(Long statusId, String keywords, int page, int size)
    {
        Page<TdUserReturn> dataPage = null;
        
        if (null == statusId)
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                dataPage = tdUserReturnService.findAllOrderBySortIdAsc(page, size);
            }
            else
            {
                dataPage = tdUserReturnService.searchAndOrderBySortIdAsc(keywords, page, size);
            }
        }
        else
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                dataPage = tdUserReturnService.findByStatusIdOrderBySortIdAsc(statusId, page, size);
            }
            else
            {
                dataPage = tdUserReturnService.searchAndFindByStatusIdOrderBySortIdAsc(keywords, statusId, page, size);
            }
        }
        
        return dataPage;
    }
    
    private void btnSave(String type, Long[] ids, Long[] sortIds)
    {
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1
                || null == type || "".equals(type))
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            if (type.equalsIgnoreCase("user")) // 用户
            {
                TdUser e = tdUserService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdUserService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("level")) // 用户等级
            {
                TdUserLevel e = tdUserLevelService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdUserLevelService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("consult")) // 咨询
            {
                TdUserConsult e = tdUserConsultService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdUserConsultService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("comment")) // 评论
            {
                TdUserComment e = tdUserCommentService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdUserCommentService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("return")) // 退换货
            {
                TdUserReturn e = tdUserReturnService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdUserReturnService.save(e);
                    }
                }
            }
        }
    }
    
    private void btnDelete(String type, Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1 
                || null == type || "".equals(type))
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                if (type.equalsIgnoreCase("user")) // 用户
                {
                	
                    tdUserService.delete(tdUserService.findOne(id));
                    
                }
                else if (type.equalsIgnoreCase("level")) // 用户等级
                {
                    tdUserLevelService.delete(id);
                }
                else if (type.equalsIgnoreCase("consult")) // 咨询
                {
                    tdUserConsultService.delete(id);
                }
                else if (type.equalsIgnoreCase("comment")) // 评论
                {
                    tdUserCommentService.delete(id);
                }
                else if (type.equalsIgnoreCase("suggestion")) //投诉  @ by zhangji
                {
                	tdUserSuggestionService.delete(id);
                }
                else if (type.equalsIgnoreCase("return")) // 退换货
                {
                    tdUserReturnService.delete(id);
                }
            }
        }
    }
    
    private void btnVerify(String type, Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1 
                || null == type || "".equals(type))
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                if (type.equalsIgnoreCase("consult")) // 咨询
                {
                    TdUserConsult e = tdUserConsultService.findOne(id);
                    
                    if (null != e)
                    {
                        e.setStatusId(1L);
                        tdUserConsultService.save(e);
                    }
                }
                else if (type.equalsIgnoreCase("comment")) // 评论
                {
                    TdUserComment e = tdUserCommentService.findOne(id);
                    
                    if (null != e)
                    {
                        e.setStatusId(1L);
                        tdUserCommentService.save(e);
                    }
                }
                else if(type.equalsIgnoreCase("demand"))  //团购要求      @zhangji 2015年7月30日11:23:51
                {
                	TdDemand e = tdDemandService.findOne(id);
                	
                	if (null != e)
                	{
                		e.setStatusId(1L);
                		tdDemandService.save(e);
                	}
                		
                }
                else if (type.equalsIgnoreCase("return")) // 退换货
                {
                    TdUserReturn e = tdUserReturnService.findOne(id);
                    
                    if (null != e)
                    {
                        e.setStatusId(1L);
                        tdUserReturnService.save(e);
                    }
                }
            }
        }
    }
}
