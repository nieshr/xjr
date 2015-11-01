package com.ynyes.kjxjr.controller.front;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.kjxjr.entity.TdActivity;
import com.ynyes.kjxjr.entity.TdActivityEnterprise;
import com.ynyes.kjxjr.entity.TdArticle;
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdEnterpriseGrade;
import com.ynyes.kjxjr.entity.TdExpert;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.entity.TdUserMessage;
import com.ynyes.kjxjr.service.TdActivityEnterpriseService;
import com.ynyes.kjxjr.service.TdActivityExpertService;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdArticleCategoryService;
import com.ynyes.kjxjr.service.TdArticleService;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdCouponService;
import com.ynyes.kjxjr.service.TdEnterpriseGradeService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdExpertService;
import com.ynyes.kjxjr.service.TdUserMessageService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.ClientConstant;
import com.ynyes.kjxjr.util.SiteMagConstant;


@Controller
@RequestMapping(value="/enterprise")
public class TdEnterpriseController {
	
	
	@Autowired
	TdCommonService tdCommonService;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdEnterpriseService tdEnterpriseService;
	
	@Autowired
	TdCouponService tdCouponService;
	
	@Autowired
	TdActivityService tdActivityService;
	
	@Autowired
	TdActivityEnterpriseService tdActivityEnterpriseService;
	
	@Autowired
	TdActivityExpertService tdActivityExpertService;

	@Autowired
	TdEnterpriseGradeService tdEnterpriseGradeService;

    @Autowired
    TdArticleCategoryService tdArticleCategoryService;
    
    @Autowired
    TdArticleService tdArticleService;
    
    @Autowired
    TdUserMessageService tdUserMessageService;
    
    @Autowired
    TdExpertService tdExpertService;

	
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
        TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
        
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
        	res.put("check", 0);
            return res;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tdEnterprise.getEstablish());
        Date establish = tdEnterprise.getEstablish();
        Date today = new Date();
        if (null != establish&& null != today &&null != tdEnterprise.getFormType())
        {
        	if (tdEnterprise.getFormType()==0 && establish.after(today)){
        		res.put("msg", "成立时间不能超过当前日期！");
        		return res;
        	}
	    	if (tdEnterprise.getFormType()==1 && establish.before(today)){
	    		res.put("msg", "（拟）成立时间不能早于当前日期！");
	    		return res;
	    	}
        }
        		
        
        
        
        
        if (null != tdEnterprise.getStatusId()&&1 == tdEnterprise.getStatusId())
        {
        	res.put("msg", "资料已审核，如需修改请申请重新审核！");
        	res.put("check", 1);
        	return res;
        }
        
        TdUser user = tdUserService.findByUsername(username);
        Long id = tdEnterprise.getId();
        String number = String.format("%04d", id);
        
        tdEnterprise.setNumber(number);
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
        TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
        
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

        return "/client/enterprise_print";
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String userEnterpriseUpload(HttpServletRequest req, ModelMap map ,Long done) {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise Enterprise = tdEnterpriseService.findbyUsername(username);
        
        
        
        map.addAttribute("enterprise", Enterprise);
        map.addAttribute("id", Enterprise.getId());
        map.addAttribute("user", user);
        map.addAttribute("done", done);

        return "/client/enterprise_upload";
    }
    
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String userEnterpriseCheck(HttpServletRequest req, ModelMap map ) {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
        
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

        
        return "/client/enterprise_check";
    }
    
    //申请重新审核
    @RequestMapping(value = "/recall", method = RequestMethod.GET)
    public String userEnterpriseRecall(HttpServletRequest req, ModelMap map ) {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
        if (null != enterprise)
        {
        	enterprise.setStatusId(2L);
        	tdEnterpriseService.save(enterprise);
            if (null != enterprise.getType())
            {
            	String type[] = enterprise.getType().split(",");
            	map.addAttribute("enterpriseType", type);
            }
        }
        
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

        
        return "/client/enterprise_check";
    }
    
    //查看评分
    @RequestMapping(value = "/grade")
    public String enterGrade(HttpServletRequest req, ModelMap map,Long activityId , Long enterpriseId) {
        tdCommonService.setHeader(map, req);
        
        
        List<TdEnterpriseGrade> gradeList = tdEnterpriseGradeService.findByEnterpriseIdAndActivityId(enterpriseId, activityId);
        
        List<TdExpert> experts = new ArrayList<>(); 
        
        for (TdEnterpriseGrade item : gradeList) {
			TdExpert expert = tdExpertService.findOne(item.getExpertId());
			experts.add(expert);
		}
        
        map.addAttribute("experts", experts);
        map.addAttribute("grade_list", gradeList);
        	
        return "/client/enterprise_grade";
    }
    
    @RequestMapping(value = "/activity/list", method = RequestMethod.GET)
    public String ActivitytList(HttpServletRequest req, ModelMap map,Integer page , Long statusId)  {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null ==page)
        {
        	page = 0;
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
        
        if (null == statusId)
        {
        	 Page<TdActivityEnterprise> ae = tdActivityEnterpriseService.findByEnterpriseIdOrderByIdDesc(enterprise.getId() , page , ClientConstant.pageSize);
        	 map.addAttribute("activity_page", ae);
        }
        else
        {
        	Page<TdActivityEnterprise> ae = tdActivityEnterpriseService.findByEnterpriseIdAndStatusIdOrderByIdDesc(enterprise.getId(), statusId , page , ClientConstant.pageSize);
        	map.addAttribute("activity_page", ae);
        }
        
        map.addAttribute("statusId", statusId);
        map.addAttribute("user", user);

        return "/client/enterprise_activity_list";
    }
    
    //查看活动
    @RequestMapping(value = "/activity/check", method = RequestMethod.GET)
    public String activityCheck(HttpServletRequest req, ModelMap map,Long id , Long done , Long upload) {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

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
        if (null !=done)
        {
            map.addAttribute("done", done);
        }
        if (null !=upload)
        {
            map.addAttribute("upload", upload);
        }
   
        map.addAttribute("user", user);
        map.addAttribute("mark", "enterprise");
        map.addAttribute("pagetype", "check");
        map.addAttribute("enterprise", tdEnterpriseService.findbyUsername(username));
        return "/client/activity_create";
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
    
    @RequestMapping(value="/project")
    public String project(HttpServletRequest req,ModelMap map){
//   	 String EnterpriseUsername = (String) req.getSession().getAttribute("EnterpriseUsername");
//
//        if (null == EnterpriseUsername) {
//            return "redirect:/login";
//        }
    	
    	tdCommonService.setHeader(map, req);
    	
    	map.addAttribute("category_list", tdArticleCategoryService.findByMenuId(11L));
    	return "/client/activity_askshow";
    }
    
    @RequestMapping(value="/article",method=RequestMethod.POST)
    public String activty(TdArticle article,HttpServletRequest req,ModelMap map){
//    	 String EnterpriseUsername = (String) req.getSession().getAttribute("EnterpriseUsername");
//
//         if (null == EnterpriseUsername) {
//             return "redirect:/login";
//         }
         article.setChannelId(1L);
         article.setStatusId(1L);
         article.setSource("本站");
         article.setMenuId(11L);
         
         tdArticleService.save(article);
         
         map.addAttribute("category_list", tdArticleCategoryService.findByMenuId(11L));
    	return "/client/activity_askshow";
    }
    /**
     * 站内信息页面
     * @param req
     * @param map
     * @return
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String userEnterpriseMessage(HttpServletRequest req, ModelMap map , Integer page) {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == page)
        {
        	page =0;
        }
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
        
        Page<TdUserMessage> messagePage = tdUserMessageService.findByEnterpriseIdAndSpeakerOrderByTimeDesc(enterprise.getId(), 1L, page, ClientConstant.pageSize);
        map.addAttribute("message_page", messagePage);

        map.addAttribute("enterprise", enterprise);
        map.addAttribute("user", user);

        
        return "/client/enterprise_message";
    }
    
    /**
     * 站内信息详情
     * @param req
     * @param map
     * @return
     */
    @RequestMapping(value = "/message/detail", method = RequestMethod.GET)
    public String MessageDetail(HttpServletRequest req, ModelMap map , Long id) {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
        
        //处理新信息
        TdUserMessage message = tdUserMessageService.findOne(id);
        message.setStatusE(1L);
        tdUserMessageService.save(message);
        
        //根据企业id和区县id查找信息页面
        List<TdUserMessage> messageList = tdUserMessageService.findByEnterpriseIdAndRegionAdminIdOrderByTimeAsc(enterprise.getId(), message.getRegionAdminId());
        for (TdUserMessage item:messageList)
        {
        	item.setStatusE(1L);
        	tdUserMessageService.save(item);
        }
        	
        map.addAttribute("message_list", messageList);
        map.addAttribute("message", message);
        map.addAttribute("enterprise", enterprise);
        map.addAttribute("user", user);

        
        return "/client/enterprise_message_detail";
    }
    /**
     * 企业站内信息回复
     * @param req
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value = "/message/reply", method = RequestMethod.GET)
    public String MessageReply(HttpServletRequest req, ModelMap map , Long regionAdminId , TdUserMessage message) {
        String username = (String) req.getSession().getAttribute("enterpriseUsername");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
        
        //创建新信息
        message.setStatusR(0L);
        message.setStatusE(1L);
        message.setName(enterprise.getTitle());
        message.setEnterpriseId(enterprise.getId());
        message.setTime(new Date());
        tdUserMessageService.save(message);
        
        //根据企业id和区县id查找信息页面
        List<TdUserMessage> messageList = tdUserMessageService.findByEnterpriseIdAndRegionAdminIdOrderByTimeAsc(enterprise.getId(), regionAdminId);
        for (TdUserMessage item:messageList)
        {
        	item.setStatusE(1L);
        	tdUserMessageService.save(item);
        }
        	
        map.addAttribute("message_list", messageList);
        map.addAttribute("message", message);
        map.addAttribute("enterprise", enterprise);
        map.addAttribute("user", user);

        
        return "redirect:/enterprise/message/detail?id="+message.getId();
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
    
    //导出
    // 企业项目资料
    @SuppressWarnings("deprecation")
	@RequestMapping(value="/export/enter")
    public String goodsListDialog(String keywords,
                                Long[] listId,
                                Integer[] listChkId,
                                ModelMap map,
                                String exportUrl,
                                HttpServletResponse resp,
                                HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("enterpriseUsername");
        if (null == username) {
            return "redirect:/login";
        }

            	exportUrl = SiteMagConstant.backupPath;
        
    			if (null != exportUrl) {
    				TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
    		
        /**  
  		 * @author lc
  		 * @注释：根据不同条件导出excel文件
  		 */
          // 第一步，创建一个webbook，对应一个Excel文件  
          HSSFWorkbook wb = new HSSFWorkbook();  
          // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
          
          //第一页
          HSSFSheet sheet1 = wb.createSheet("第一页");  
          
          if (enterprise.getFormType() == 0)
          {
              sheet1.addMergedRegion(new Region((short) 0 , (short) 0 , (short) 0 , (short) 4));     //标题
              sheet1.addMergedRegion(new Region((short) 1 , (short) 0 , (short) 1 , (short) 4));     //标题
              sheet1.addMergedRegion(new Region((short) 2 , (short) 1 , (short) 2 , (short) 2));		//企业名称
              sheet1.addMergedRegion(new Region((short) 3 , (short) 1 , (short) 3 , (short) 2));		//注册资本
              sheet1.addMergedRegion(new Region((short) 4 , (short) 1 , (short) 6 , (short) 4));		//股东结构
              sheet1.addMergedRegion(new Region((short) 4 , (short) 0 , (short) 6 , (short) 0));		//股东结构
              sheet1.addMergedRegion(new Region((short) 7 , (short) 1 , (short) 7 , (short) 2));		//地址
              sheet1.addMergedRegion(new Region((short) 8 , (short) 1 , (short) 8 , (short) 4));		//行业归属
              sheet1.addMergedRegion(new Region((short) 8 , (short) 0 , (short) 8 , (short) 0));		//行业归属
              sheet1.addMergedRegion(new Region((short) 9 , (short) 1 , (short) 9 , (short) 2));		//邮箱
              sheet1.addMergedRegion(new Region((short) 10 , (short) 1 , (short) 10 , (short) 2));		//公司网站
              sheet1.addMergedRegion(new Region((short) 11 , (short) 1 , (short) 11 , (short) 2));		//QQ
              sheet1.addMergedRegion(new Region((short) 12, (short) 1 , (short) 17 , (short) 4));		//公司团队、主要负责人
              sheet1.addMergedRegion(new Region((short) 12 , (short) 0 , (short) 17 , (short) 0));		//公司团队、主要负责人
              sheet1.addMergedRegion(new Region((short) 18 , (short) 1 , (short) 23 , (short) 4));		//企业简介
              sheet1.addMergedRegion(new Region((short) 18 , (short) 0 , (short) 23 , (short) 0));		//企业简介
              sheet1.addMergedRegion(new Region((short) 24 , (short) 1 , (short) 29 , (short) 4));		//技术特点
              sheet1.addMergedRegion(new Region((short) 24 , (short) 0 , (short) 29 , (short) 0));		//技术特点
              sheet1.addMergedRegion(new Region((short) 30 , (short) 1 , (short) 35 , (short) 4));		//市场规模
              sheet1.addMergedRegion(new Region((short) 30 , (short) 0 , (short) 35 , (short) 0));		//市场规模
	          sheet1.addMergedRegion(new Region((short) 36 , (short) 0 , (short) 36 , (short) 4));		//近三年财务
	          sheet1.addMergedRegion(new Region((short) 41 , (short) 0 , (short) 41 , (short) 4));     //知识产权
	          sheet1.addMergedRegion(new Region((short) 42 , (short) 0 , (short) 43 , (short) 4));     //知识产权
	          sheet1.addMergedRegion(new Region((short) 44 , (short) 0 , (short) 45 , (short) 4));     //融资信息
	          sheet1.addMergedRegion(new Region((short) 46 , (short) 3 , (short) 46 , (short) 4));     
	          sheet1.addMergedRegion(new Region((short) 47 , (short) 3 , (short) 48 , (short) 4));     
	          sheet1.addMergedRegion(new Region((short) 47 , (short) 0 , (short) 48 , (short) 0));    
	          sheet1.addMergedRegion(new Region((short) 47 , (short) 1 , (short) 48 , (short) 1));
	          sheet1.addMergedRegion(new Region((short) 47 , (short) 2 , (short) 48 , (short) 2));    
	          sheet1.addMergedRegion(new Region((short) 49 , (short) 3 , (short) 49 , (short) 4));  
	          sheet1.addMergedRegion(new Region((short) 50 , (short) 0 , (short) 51 , (short) 0));  
	          sheet1.addMergedRegion(new Region((short) 50 , (short) 1 , (short) 51 , (short) 1));  
	          sheet1.addMergedRegion(new Region((short) 50 , (short) 2 , (short) 51 , (short) 2));  
	          sheet1.addMergedRegion(new Region((short) 50 , (short) 3 , (short) 51 , (short) 4));     
	          sheet1.addMergedRegion(new Region((short) 52 , (short) 1 , (short) 52 , (short) 4));     //项目可供资料
	          sheet1.addMergedRegion(new Region((short) 53 , (short) 0 , (short) 58 , (short) 0));     //是否同意披露信息
	          sheet1.addMergedRegion(new Region((short) 53 , (short) 1 , (short) 58 , (short) 4));     //盖章
          }
          else if (enterprise.getFormType() == 1)
          {
              sheet1.addMergedRegion(new Region((short) 0 , (short) 0 , (short) 0 , (short) 4));     //标题
              sheet1.addMergedRegion(new Region((short) 1 , (short) 0 , (short) 1 , (short) 4));     //标题
              sheet1.addMergedRegion(new Region((short) 2 , (short) 1 , (short) 2 , (short) 2));		//企业名称
              sheet1.addMergedRegion(new Region((short) 3 , (short) 1 , (short) 3 , (short) 2));		//注册资本
              sheet1.addMergedRegion(new Region((short) 4 , (short) 1 , (short) 6 , (short) 4));		//股东结构
              sheet1.addMergedRegion(new Region((short) 4 , (short) 0 , (short) 6 , (short) 0));		//股东结构
              sheet1.addMergedRegion(new Region((short) 7 , (short) 1 , (short) 7 , (short) 2));		//地址
              sheet1.addMergedRegion(new Region((short) 8 , (short) 1 , (short) 8 , (short) 4));		//行业归属
              sheet1.addMergedRegion(new Region((short) 8 , (short) 0 , (short) 8 , (short) 0));		//行业归属
              sheet1.addMergedRegion(new Region((short) 9 , (short) 1 , (short) 9 , (short) 2));		//邮箱
              sheet1.addMergedRegion(new Region((short) 10 , (short) 1 , (short) 10 , (short) 2));		//公司网站
              sheet1.addMergedRegion(new Region((short) 11 , (short) 1 , (short) 11 , (short) 2));		//QQ
              sheet1.addMergedRegion(new Region((short) 12, (short) 1 , (short) 12 , (short) 2));		//邮箱
              sheet1.addMergedRegion(new Region((short) 13 , (short) 1 , (short) 18 , (short) 4));		//团队简介
              sheet1.addMergedRegion(new Region((short) 13 , (short) 0 , (short) 18 , (short) 0));		//团队简介
              sheet1.addMergedRegion(new Region((short) 19 , (short) 1 , (short) 24 , (short) 4));		//技术特点
              sheet1.addMergedRegion(new Region((short) 19 , (short) 0 , (short) 24 , (short) 0));		//技术特点
              sheet1.addMergedRegion(new Region((short) 25 , (short) 1 , (short) 30 , (short) 4));		//市场规模
              sheet1.addMergedRegion(new Region((short) 25 , (short) 0 , (short) 30 , (short) 0));		//市场规模
              sheet1.addMergedRegion(new Region((short) 31 , (short) 0 , (short) 31 , (short) 4));     //知识产权
              sheet1.addMergedRegion(new Region((short) 32 , (short) 0 , (short) 33 , (short) 4));     //知识产权
              sheet1.addMergedRegion(new Region((short) 34 , (short) 0 , (short) 35 , (short) 4));     //融资信息
              sheet1.addMergedRegion(new Region((short) 36 , (short) 3 , (short) 36 , (short) 4));    
              sheet1.addMergedRegion(new Region((short) 37 , (short) 0 , (short) 38 , (short) 0));     
              sheet1.addMergedRegion(new Region((short) 37 , (short) 1 , (short) 38 , (short) 1));     
              sheet1.addMergedRegion(new Region((short) 37 , (short) 2 , (short) 38 , (short) 2));     
              sheet1.addMergedRegion(new Region((short) 37,  (short) 3 , (short) 38, (short) 4));     
              sheet1.addMergedRegion(new Region((short) 39 , (short) 3 , (short) 39 , (short) 4));     
              sheet1.addMergedRegion(new Region((short) 40 , (short) 0 , (short) 41 , (short) 0));     
              sheet1.addMergedRegion(new Region((short) 40 , (short) 1 , (short) 41 , (short) 1));     
              sheet1.addMergedRegion(new Region((short) 40 , (short) 2 , (short) 41 , (short) 2));     
              sheet1.addMergedRegion(new Region((short) 40 , (short) 3 , (short) 41 , (short) 4));     
              sheet1.addMergedRegion(new Region((short) 42 , (short) 1 , (short) 42 , (short) 4));     //项目可供资料
              sheet1.addMergedRegion(new Region((short) 43 , (short) 0 , (short) 48 , (short) 0));     //是否同意披露信息
              sheet1.addMergedRegion(new Region((short) 43 , (short) 1 , (short) 48 , (short) 4));     //盖章
          }
          
          //打印设置
          HSSFPrintSetup ps = sheet1.getPrintSetup();
          ps.setLandscape(false); //打印方向，true:横向，false:纵向
          ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
          sheet1.setMargin(HSSFSheet.BottomMargin, (double)0.3); //页边距（下）
          sheet1.setMargin(HSSFSheet.LeftMargin, (double)0.3); //页边距（左）
          sheet1.setMargin(HSSFSheet.RightMargin, (double)0.3); //页边距（右）
          sheet1.setMargin(HSSFSheet.TopMargin, (double)0.3); //页边距（上）
          sheet1.setHorizontallyCenter(true); //设置打印页面为水平居中
          sheet1.setVerticallyCenter(true); //设置打印页面为垂直居中
          
          //列宽
          
          sheet1.setColumnWidth((short) 0 , 18*256);
          sheet1.setColumnWidth((short) 1 , 18*256);
          sheet1.setColumnWidth((short) 2 , 18*256);	
          sheet1.setColumnWidth((short) 3 , 18*256);
          sheet1.setColumnWidth((short) 4 , 18*256);
         
          sheet1.setDefaultRowHeight((short)(51*20));
          sheet1.setDefaultRowHeightInPoints(80);  //设置默认行高
          
          // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
          HSSFRow row = sheet1.createRow((int) 0);  
          // 第四步，创建单元格，并设置值表头 设置表头居中  
          HSSFCellStyle style = wb.createCellStyle();       //单元项目标题
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          HSSFFont subtitlefont  = wb.createFont();     
          subtitlefont.setFontHeightInPoints((short) 14);// 字号  
          subtitlefont.setFontName("宋体");
          subtitlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗  
          style.setFont(subtitlefont);   
          
          HSSFCellStyle fillin = wb.createCellStyle();       //单元项目内容
          fillin.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
          fillin.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
          fillin.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
          fillin.setBorderRight(HSSFCellStyle.BORDER_THIN);
          fillin.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          fillin.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          HSSFFont fillinfont = wb.createFont();
          fillinfont.setFontName("宋体");
          fillinfont.setFontHeightInPoints((short) 10);//设置字体大小
          fillinfont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);//粗体显示
          fillin.setFont(fillinfont);//选择需要用到的字体格式
          
          
          HSSFCellStyle styledate = wb.createCellStyle();  
          styledate.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
          styledate.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
          styledate.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
          styledate.setBorderRight(HSSFCellStyle.BORDER_THIN);
          styledate.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          styledate.setBorderTop(HSSFCellStyle.BORDER_THIN); 
          HSSFDataFormat dataFormat =  wb.createDataFormat();  
          short date = dataFormat.getFormat("yyyy年MM月dd日");
          styledate.setDataFormat(date);  
          
          HSSFCellStyle styleyear = wb.createCellStyle();  
          styleyear.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
          styleyear.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
          styleyear.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
          styleyear.setBorderRight(HSSFCellStyle.BORDER_THIN);
          styleyear.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          styleyear.setBorderTop(HSSFCellStyle.BORDER_THIN); 
          HSSFDataFormat dataFormat2 =  wb.createDataFormat();  
          short year = dataFormat.getFormat("yyyy");
          styleyear.setDataFormat(year);  
          
          HSSFCellStyle style1 = wb.createCellStyle();  
          style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
          style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
          style1.setWrapText(true); //自动换行
          style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
          style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
          style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          style1.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          HSSFFont subtitlefont1 = wb.createFont();
          subtitlefont1.setFontName("楷体");
          subtitlefont1.setFontHeightInPoints((short) 10);//设置字体大小
          subtitlefont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
          style1.setFont(subtitlefont1);//选择需要用到的字体格式
          
          
          HSSFCellStyle style2 = wb.createCellStyle();  
          style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
          style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
          style2.setWrapText(true); //自动换行
          style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
          style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
          style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          HSSFFont subtitlefont2 = wb.createFont();
          subtitlefont2.setFontName("楷体");
          subtitlefont2.setFontHeightInPoints((short) 10);//设置字体大小
         
          style2.setFont(subtitlefont2);//选择需要用到的字体格式
          
          
          HSSFCellStyle style3 = wb.createCellStyle();
          style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
          style3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
          style3.setWrapText(true);
          
          HSSFCellStyle title = wb.createCellStyle();  
          title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
          title.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
          title.setBorderRight(HSSFCellStyle.BORDER_THIN);
          title.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          title.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          HSSFFont font = wb.createFont();
          font.setFontName("黑体");
          font.setFontHeightInPoints((short) 12);//设置字体大小
          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
          title.setFont(font);//选择需要用到的字体格式
          
          HSSFCellStyle title2 = wb.createCellStyle();  
          title2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
          title2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
          title2.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
          HSSFFont title2font = wb.createFont();
          title2font.setFontName("黑体");
          title2font.setFontHeightInPoints((short) 10);//设置字体大小
          title2font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
          title2.setFont(title2font);//选择需要用到的字体格式
          title2.setBorderRight(HSSFCellStyle.BORDER_THIN);
          title2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          
          HSSFCellStyle content = wb.createCellStyle();  
          content.setAlignment(HSSFCellStyle.ALIGN_LEFT); //水平左对齐
          content.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);  //垂直上对齐
          content.setWrapText(true); //自动换行
          content.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
          content.setBorderRight(HSSFCellStyle.BORDER_THIN);
          content.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
          content.setBorderTop(HSSFCellStyle.BORDER_THIN); 
          HSSFFont contentfont = wb.createFont();
          contentfont.setFontName("宋体");
          contentfont.setFontHeightInPoints((short) 10);//设置字体大小
          content.setFont(contentfont);//选择需要用到的字体格式
       
          row = sheet1.createRow((int) 0);  
          HSSFCell cell = row.createCell((short) 0);  
          cell.setCellValue("重庆市科技小巨人企业培育专项行动报名表");  
          cell.setCellStyle(title);
          cell = row.createCell((short) 1);
          cell.setCellStyle(title);
          cell = row.createCell((short) 2);
          cell.setCellStyle(title);
          cell = row.createCell((short) 3);  
          cell.setCellStyle(title);
          cell = row.createCell((short) 4);  
          cell.setCellStyle(title);
          if (enterprise.getFormType() == 0)
          {
	          row= sheet1.createRow((int) 1);  
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("（企业组）");  
	          cell.setCellStyle(title2);  
	          cell = row.createCell((short) 1);
	          cell.setCellStyle(title2);
	          cell = row.createCell((short) 2);
	          cell.setCellStyle(title2);
	          cell = row.createCell((short) 3);  
	          cell.setCellStyle(title2);
	          cell = row.createCell((short) 4);  
	          cell.setCellStyle(title2);
          }
          else
          {
	          row= sheet1.createRow((int) 1);  
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("（项目团队）");  
	          cell.setCellStyle(title2);  
	          cell = row.createCell((short) 1);
	          cell.setCellStyle(title2);
	          cell = row.createCell((short) 2);
	          cell.setCellStyle(title2);
	          cell = row.createCell((short) 3);  
	          cell.setCellStyle(title2);
	          cell = row.createCell((short) 4);  
	          cell.setCellStyle(title2);
          }
         if(enterprise.getFormType() == 0)
         {
        	 row= sheet1.createRow((int) 2);  
             cell = row.createCell((short) 0);  
             cell.setCellValue("企业名称");  
             cell.setCellStyle(style2);  
   	      	 cell = row.createCell((short) 1);
     		  cell.setCellValue(enterprise.getTitle()); 
     		  cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 2);
	          cell.setCellStyle(fillin);
     		  cell = row.createCell((short) 3);
     		  cell.setCellValue("成立时间");
     		  cell.setCellStyle(style2);  
     		  cell = row.createCell((short) 4);
     		  cell.setCellValue(enterprise.getEstablish()); 
     		  cell.setCellStyle(styledate);  
     		  
              
              row= sheet1.createRow((int) 3); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("注册资本");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getCapital() + "（万元）");     
              cell.setCellStyle(fillin);  
              cell = row.createCell((short) 2);
              cell.setCellStyle(style);  
              cell = row.createCell((short) 3);
              cell.setCellValue("法定代表人");
              cell.setCellStyle(style2);  
              cell =row.createCell((short) 4);
              cell.setCellValue(enterprise.getRepresentative()); 
              cell.setCellStyle(fillin);  
              
              row= sheet1.createRow((int) 4); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("股东结构");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getShareholder());     
              cell.setCellStyle(content);  
              cell = row.createCell((short) 2);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 3);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
         }
         else
         {
	          row= sheet1.createRow((int) 2);  
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("项目名称");  
	          cell.setCellStyle(style2);  
		      cell = row.createCell((short) 1);
	  		  cell.setCellValue(enterprise.getTitle()); 
	  		  cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 2);
	          cell.setCellStyle(fillin);
	  		  cell = row.createCell((short) 3);
	  		  cell.setCellValue("（拟）成立时间");
	  		  cell.setCellStyle(style2);  
	  		  cell = row.createCell((short) 4);
	  		  cell.setCellValue(enterprise.getEstablish()); 
	  		  cell.setCellStyle(styledate);  
	  		  
	          
	          row= sheet1.createRow((int) 3); 
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("（拟）注册资本");  
	          cell.setCellStyle(style2);  
	          cell = row.createCell((short) 1);
	          cell.setCellValue(enterprise.getCapital() + "（万元）");     
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 2);
	          cell.setCellStyle(style);  
	          cell = row.createCell((short) 3);
	          cell.setCellValue("（拟）法定代表人");
	          cell.setCellStyle(style2);  
	          cell =row.createCell((short) 4);
	          cell.setCellValue(enterprise.getRepresentative()); 
	          cell.setCellStyle(fillin);  
	          
	          row= sheet1.createRow((int) 4); 
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("（拟）股东结构");  
	          cell.setCellStyle(style2);  
	          cell = row.createCell((short) 1);
	          cell.setCellValue(enterprise.getShareholder());     
	          cell.setCellStyle(content);  
	          cell = row.createCell((short) 2);
	          cell.setCellStyle(content);  
	          cell = row.createCell((short) 3);
	          cell.setCellStyle(content);  
	          cell = row.createCell((short) 4);
	          cell.setCellStyle(content);
         }

          
          row= sheet1.createRow((int) 5); 
          cell = row.createCell((short) 0);  
          cell.setCellStyle(style2);  
          cell = row.createCell((short) 1);
          cell.setCellStyle(content);  
          cell = row.createCell((short) 2);
          cell.setCellStyle(content);  
          cell = row.createCell((short) 3);
          cell.setCellStyle(content);  
          cell = row.createCell((short) 4);
          cell.setCellStyle(content);  
          
          row= sheet1.createRow((int) 6); 
          cell = row.createCell((short) 0);  
          cell.setCellStyle(style2);  
          cell = row.createCell((short) 1);
          cell.setCellStyle(content);  
          cell = row.createCell((short) 2);
          cell.setCellStyle(content);  
          cell = row.createCell((short) 3);
          cell.setCellStyle(content);  
          cell = row.createCell((short) 4);
          cell.setCellStyle(content);  
          

          row= sheet1.createRow((int) 7); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("地址");  
          cell.setCellStyle(style2);  
          cell = row.createCell((short) 1);
          if (null !=enterprise.getAddress())
          {
        	  cell.setCellValue(enterprise.getAddress());     
          }
          cell.setCellStyle(fillin);  
          cell = row.createCell((short) 2);
          cell.setCellStyle(style);  
          if (enterprise.getFormType()==0)
          {
              cell = row.createCell((short) 3);
              cell.setCellValue("职工人数（人）");
              cell.setCellStyle(style2);  
          }
          else
          {
              cell = row.createCell((short) 3);
              cell.setCellValue("团队人数（人）");
              cell.setCellStyle(style2);  
          }
          cell =row.createCell((short) 4);
          cell.setCellValue(enterprise.getStaffNumber()); 
          cell.setCellStyle(fillin);  
          
          row= sheet1.createRow((int) 8); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("行业归属");  
          cell.setCellStyle(style2);  
          cell = row.createCell((short) 1);
          if (null != enterprise.getType())
          {
        	 cell.setCellValue(enterprise.getType()); 
          }
          cell.setCellStyle(fillin);  
          cell = row.createCell((short) 2);
          cell.setCellStyle(fillin);  
          cell = row.createCell((short) 3);
          cell.setCellStyle(fillin);  
          cell = row.createCell((short) 4);
          cell.setCellStyle(fillin);  
          
          
          row= sheet1.createRow((int) 9); 
          cell = row.createCell((short) 0);  
          if (enterprise.getFormType() == 0)
          {
        	  cell.setCellValue("邮箱");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getEmail());     
              cell.setCellStyle(fillin);  
          }
          else  if (enterprise.getFormType() == 1)
          {
        	  cell.setCellValue("主要负责人");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getInCharge());     
              cell.setCellStyle(fillin);  
          }
          cell = row.createCell((short) 2);
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);
          cell.setCellValue("联系人");
          cell.setCellStyle(style2);  
          cell =row.createCell((short) 4);
          cell.setCellValue(enterprise.getContact()); 
          cell.setCellStyle(fillin);  
          
          row= sheet1.createRow((int) 10); 
          cell = row.createCell((short) 0);  
          if (enterprise.getFormType() == 0)
          {
        	     cell.setCellValue("公司网站");  
          }
          else if (enterprise.getFormType() == 1)
          {
        	     cell.setCellValue("网站");  
          }
     
          cell.setCellStyle(style2);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getWebsite());     
          cell.setCellStyle(fillin);  
          cell = row.createCell((short) 2);
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);
          cell.setCellValue("联系电话");
          cell.setCellStyle(style2);  
          cell =row.createCell((short) 4);
          cell.setCellValue(enterprise.getTelephone()); 
          cell.setCellStyle(fillin);  
          
          row= sheet1.createRow((int) 11); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("QQ/MSN");  
          cell.setCellStyle(style2);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getChat());     
          cell.setCellStyle(fillin);  
          cell = row.createCell((short) 2);
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);
          cell.setCellValue("手机");
          cell.setCellStyle(style2);  
          cell =row.createCell((short) 4);
          cell.setCellValue(enterprise.getMobile()); 
          cell.setCellStyle(fillin);  
          
          if(enterprise.getFormType() == 0)
          {
        	  row= sheet1.createRow((int) 12); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("公司团队                    （200字内）");  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getTeamIntroduction()); 
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 3);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);  
              
        	  row= sheet1.createRow((int) 13); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 2);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 3);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);  
              
        	  row= sheet1.createRow((int) 14); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 2);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 3);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);   
              
        	  row= sheet1.createRow((int) 15); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 2);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 3);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);  
              
        	  row= sheet1.createRow((int) 16); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 2);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 3);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);  
              
        	  row= sheet1.createRow((int) 17); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 2);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 3);
              cell.setCellStyle(content);  
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);  
              
              row= sheet1.createRow((int) 18); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("企业简介               （200字内）");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getProfile());     
              cell.setCellStyle(content);
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
             
        	  row= sheet1.createRow((int) 19); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 20); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
        	  row= sheet1.createRow((int) 21); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
          
        	  row= sheet1.createRow((int) 22); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
        	  row= sheet1.createRow((int) 23); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
              row= sheet1.createRow((int) 24); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("技术特点及优势（200字内）");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getAdvantage());     
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
          	  row= sheet1.createRow((int) 25); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 26); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 27); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
        	  row= sheet1.createRow((int) 28); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
           	  row= sheet1.createRow((int) 29); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              

        	  row= sheet1.createRow((int) 30); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("市场规模行业地位（200字内）");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getSize());     
              cell.setCellStyle(content);  
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
          	  row= sheet1.createRow((int) 31); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 32); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 33); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
        	  row= sheet1.createRow((int) 34); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 35); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
              row= sheet1.createRow((int) 36); 
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("近三年财务状况（万元）");  
	          cell.setCellStyle(style1);  
	          cell = row.createCell((short) 1);
	          cell.setCellStyle(style1); 
	          cell = row.createCell((short) 2);
	          cell.setCellStyle(style1); 
	          cell = row.createCell((short) 3);
	          cell.setCellStyle(style1); 
	          cell = row.createCell((short) 4);
	          cell.setCellStyle(style1); 
	          
	    	  row= sheet1.createRow((int) 37); 
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("年限");  
	          cell.setCellStyle(style2);  
	          cell = row.createCell((short) 1);  
	          cell.setCellValue("总资产");  
	          cell.setCellStyle(style2);  
	          cell = row.createCell((short) 2);  
	          cell.setCellValue("净资产");  
	          cell.setCellStyle(style2);  
	          cell = row.createCell((short) 3);  
	          cell.setCellValue("销售收入");  
	          cell.setCellStyle(style2);  
	          cell = row.createCell((short) 4);  
	          cell.setCellValue("毛利润");  
	          cell.setCellStyle(style2);  
	          
	          Calendar calendar = Calendar.getInstance();
	          calendar.setTime(new Date());
	          calendar.add(Calendar.YEAR, -1);
	          Date lastyear1 = calendar.getTime();
	          
	          calendar.add(Calendar.YEAR, -1);
	          Date lastyear2 = calendar.getTime();
	          
	          calendar.add(Calendar.YEAR, -1);
	          Date lastyear3 = calendar.getTime();
	          
	    	  row= sheet1.createRow((int) 38); 
	          cell = row.createCell((short) 0);  
	          cell.setCellValue(lastyear3);  
	          cell.setCellStyle(styleyear);  
	          cell = row.createCell((short) 1);  
	          if (null != enterprise.getLastAssets3())
	          {
	        	  cell.setCellValue(enterprise.getLastAssets3());  
	          }
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 2);  
	          if (null != enterprise.getLastNetAssets3())
	          {
	        	  cell.setCellValue(enterprise.getLastNetAssets3());  
	          }
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 3);  
	          if (null != enterprise.getLastSale3())
	          {
	        	  cell.setCellValue(enterprise.getLastSale3());  
	          }
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 4);  
	          if (null != enterprise.getLastProfit3())
	          {
	        	  cell.setCellValue(enterprise.getLastProfit3());  
	          }
	          cell.setCellStyle(fillin);  
	          
	    	  row= sheet1.createRow((int) 39); 
	          cell = row.createCell((short) 0);  
	          cell.setCellValue(lastyear2);  
	          cell.setCellStyle(styleyear);  
	          cell = row.createCell((short) 1);  
	          if (null != enterprise.getLastAssets2())
	          {
	        	  cell.setCellValue(enterprise.getLastAssets2());  
	          }
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 2);  
	          if (null != enterprise.getLastNetAssets2())
	          {
	        	  cell.setCellValue(enterprise.getLastNetAssets2());  
	          }
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 3);  
	          if (null != enterprise.getLastSale2())
	          {
	        	  cell.setCellValue(enterprise.getLastSale2());  
	          }
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 4);  
	          if (null != enterprise.getLastProfit2())
	          {
	        	  cell.setCellValue(enterprise.getLastProfit2());  
	          }
	          cell.setCellStyle(fillin);  
	
	    	  row= sheet1.createRow((int) 40); 
	          cell = row.createCell((short) 0);  
	          cell.setCellValue(lastyear1);  
	          cell.setCellStyle(styleyear);  
	          cell = row.createCell((short) 1);  
	          if (null != enterprise.getLastAssets1())
	          {
	        	  cell.setCellValue(enterprise.getLastAssets1());  
	          }
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 2);  
	          if (null != enterprise.getLastNetAssets3())
	          {
	        	  cell.setCellValue(enterprise.getLastNetAssets1());  
	          }
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 3);  
	          if (null != enterprise.getLastSale1())
	          {
	        	  cell.setCellValue(enterprise.getLastSale1());  
	          }
	          cell.setCellStyle(fillin);  
	          cell = row.createCell((short) 4);  
	          if (null != enterprise.getLastProfit1())
	          {
	        	  cell.setCellValue(enterprise.getLastProfit1());  
	          }
	          cell.setCellStyle(fillin);  
	          
              

              row = sheet1.createRow((int) 41);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("知识产权基本情况");  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style1);
              

              
              row = sheet1.createRow((int) 42);  
              cell = row.createCell((short) 0);  
              if (null != enterprise.getInventiPatent() && null != enterprise.getNewPatent() && null != enterprise.getDesignPatent())
              {
            	  cell.setCellValue("发明专利："+enterprise.getInventiPatent()+"项，"+"实用新型专利："+enterprise.getNewPatent()+"项，"+"外观设计专利："+enterprise.getDesignPatent()+"项，");   
              }
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(fillin);
              
              
              row = sheet1.createRow((int) 43);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 44);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("融资信息（万元）");
              cell.setCellStyle(style1);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style1);
              
              
              row = sheet1.createRow((int) 45);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
             
             
              row = sheet1.createRow((int) 46);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("期望融资方式");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellValue("（一）股权融资");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellValue("期望获得资金的时间");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              if (null != enterprise.getExpectEquityDate())
              {
            	  cell.setCellValue(enterprise.getExpectEquityDate()); 
              }
              cell.setCellStyle(styledate);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(styledate);
              
              
              row = sheet1.createRow((int) 47);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("期望融资金额");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              if (null != enterprise.getExpectEquityAmount() )
              {
            	  cell.setCellValue(enterprise.getExpectEquityAmount());  
              }
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 2);  
              cell.setCellValue("融资用途");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              if (null != enterprise.getExpectEquityUse())
              {
            	  cell.setCellValue(enterprise.getExpectEquityUse()); 
              }
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(fillin);
              
	          row = sheet1.createRow((int) 48);  
	          cell = row.createCell((short) 0);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 1);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 2);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 3);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 4);  
	          cell.setCellStyle(style);
              
              row = sheet1.createRow((int) 49);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("期望融资方式");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellValue("（二）债权融资");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellValue("期望获得资金的时间");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              if (null != enterprise.getExpectBondDate())
              {
            	  cell.setCellValue(enterprise.getExpectBondDate()); 
              }
              cell.setCellStyle(styledate);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(styledate);
              
              row = sheet1.createRow((int) 50);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("期望融资金额");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              if (null != enterprise.getExpectBondAmount() )
              {
            	  cell.setCellValue(enterprise.getExpectBondAmount());  
              }
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 2);  
              cell.setCellValue("融资用途");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              if (null != enterprise.getExpectBondUse())
              {
            	  cell.setCellValue(enterprise.getExpectBondUse()); 
              }
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(fillin);
         
	          row = sheet1.createRow((int) 51);  
	          cell = row.createCell((short) 0);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 1);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 2);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 3);  
	          cell.setCellStyle(style2);
	          cell = row.createCell((short) 4);  
	          cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 52);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("项目可供资料");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellValue(enterprise.getDataAble());  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(fillin);
              
              row = sheet1.createRow((int) 53);  
              cell = row.createCell((short) 0);  
              if (enterprise.getFormType() == 0)
              {
            	  cell.setCellValue("是否愿意将贵公司所填以上信息向投资金融平台披露");  
              }
              else if (enterprise.getFormType() == 1)
              {
            	  cell.setCellValue("是否愿意将团队所填以上信息向投资金融平台披露");  
              }
              cell.setCellStyle(style2);
              if (true == enterprise.getIsShow())
              {
                  cell = row.createCell((short) 1);  
                  if (enterprise.getFormType() == 0)
                  {
                	  cell.setCellValue("同意请加公司公章");  
                  }
                  else if (enterprise.getFormType() == 1)
                  {
                	  cell.setCellValue("同意请签名");  
                  }
                 
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 2);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 3);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 4);  
                  cell.setCellStyle(style2);
              }
              else
              {
                  cell = row.createCell((short) 1);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 2);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 3);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 4);  
                  cell.setCellStyle(style2);
              }

              row = sheet1.createRow((int) 54);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 55);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 56);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 57);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 58);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
          }
          else
          {
              
              row = sheet1.createRow((int) 12);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("邮箱");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getEmail());  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellValue("传真 ");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);
              cell.setCellValue(enterprise.getFax());  
              cell.setCellStyle(fillin);
        	  
        	  
        	  row= sheet1.createRow((int) 13); 
              cell = row.createCell((short) 0);  
              cell.setCellValue(" 团队简介                   （200字内）");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getProfile());     
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
           	  row= sheet1.createRow((int) 14); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 15); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
        	  row= sheet1.createRow((int) 16); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
          
        	  row= sheet1.createRow((int) 17); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
        	  row= sheet1.createRow((int) 18); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
              row= sheet1.createRow((int) 19); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("技术特点及优势（200字内）");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getAdvantage());     
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
          	  row= sheet1.createRow((int) 20); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 21); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 22); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
        	  row= sheet1.createRow((int) 23); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
           	  row= sheet1.createRow((int) 24); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              

        	  row= sheet1.createRow((int) 25); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("预期市场前景            （200字内）");  
              cell.setCellStyle(style2);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getSize());     
              cell.setCellStyle(content);  
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
          	  row= sheet1.createRow((int) 26); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 27); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 28); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content);
              
        	  row= sheet1.createRow((int) 29); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
              
        	  row= sheet1.createRow((int) 30); 
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2); 
              cell = row.createCell((short) 1);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 2);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 3);
              cell.setCellStyle(content); 
              cell = row.createCell((short) 4);
              cell.setCellStyle(content); 
          

              row = sheet1.createRow((int) 31);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("知识产权基本情况");  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style1);
              
              
              row = sheet1.createRow((int) 32);  
              cell = row.createCell((short) 0);  
              if (null != enterprise.getInventiPatent() && null != enterprise.getNewPatent() && null != enterprise.getDesignPatent())
              {
            	  cell.setCellValue("发明专利："+enterprise.getInventiPatent()+"项，"+"实用新型专利："+enterprise.getNewPatent()+"项，"+"外观设计专利："+enterprise.getDesignPatent()+"项，");   
              }
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(fillin);
              
              row = sheet1.createRow((int) 33);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 34);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("融资信息（万元）");
              cell.setCellStyle(style1);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style1);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style1);
              
              
              row = sheet1.createRow((int) 35);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
             
             
              row = sheet1.createRow((int) 36);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("期望融资方式");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellValue("（一）股权融资");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellValue("期望获得资金的时间");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              if (null != enterprise.getExpectEquityDate())
              {
            	  cell.setCellValue(enterprise.getExpectEquityDate()); 
              }
              cell.setCellStyle(styledate);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(styledate);
              
              row = sheet1.createRow((int) 37);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("期望融资金额");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              if (null != enterprise.getExpectEquityAmount() )
              {
            	  cell.setCellValue(enterprise.getExpectEquityAmount());  
              }
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 2);  
              cell.setCellValue("融资用途");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              if (null != enterprise.getExpectEquityUse())
              {
            	  cell.setCellValue(enterprise.getExpectEquityUse()); 
              }
              cell.setCellStyle(content);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(content);
         
	          row = sheet1.createRow((int) 38);  
	          cell = row.createCell((short) 0);  
	          cell.setCellStyle(style2);
	          cell = row.createCell((short) 1);  
	          cell.setCellStyle(style2);
	          cell = row.createCell((short) 2);  
	          cell.setCellStyle(style2);
	          cell = row.createCell((short) 3);  
	          cell.setCellStyle(content);
	          cell = row.createCell((short) 4);  
	          cell.setCellStyle(content);
              
              row = sheet1.createRow((int) 39);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("期望融资方式");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellValue("（二）债权融资");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellValue("期望获得资金的时间");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              if (null != enterprise.getExpectBondDate())
              {
            	  cell.setCellValue(enterprise.getExpectBondDate()); 
              }
              cell.setCellStyle(styledate);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(styledate);
              
              row = sheet1.createRow((int) 40);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("期望融资金额");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              if (null != enterprise.getExpectBondAmount() )
              {
            	  cell.setCellValue(enterprise.getExpectBondAmount());  
              }
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 2);  
              cell.setCellValue("融资用途");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              if (null != enterprise.getExpectBondUse())
              {
            	  cell.setCellValue(enterprise.getExpectBondUse()); 
              }
              cell.setCellStyle(content);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(content);
         
	          row = sheet1.createRow((int) 41);  
	          cell = row.createCell((short) 0);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 1);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 2);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 3);  
	          cell.setCellStyle(style);
	          cell = row.createCell((short) 4);  
	          cell.setCellStyle(style);
              
              row = sheet1.createRow((int) 42);  
              cell = row.createCell((short) 0);  
              cell.setCellValue("项目可供资料");  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 1);  
              cell.setCellValue(enterprise.getDataAble());  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(fillin);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(fillin);
              
              row = sheet1.createRow((int) 43);  
              cell = row.createCell((short) 0);  
              if (enterprise.getFormType() == 0)
              {
            	  cell.setCellValue("是否愿意将贵公司所填以上信息向投资金融平台披露");  
              }
              else if (enterprise.getFormType() == 1)
              {
            	  cell.setCellValue("是否愿意将团队所填以上信息向投资金融平台披露");  
              }
              cell.setCellStyle(style2);
              if (true == enterprise.getIsShow())
              {
                  cell = row.createCell((short) 1);  
                  if (enterprise.getFormType() == 0)
                  {
                	  cell.setCellValue("同意请加公司公章");  
                  }
                  else if (enterprise.getFormType() == 1)
                  {
                	  cell.setCellValue("同意请签名");  
                  }
                 
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 2);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 3);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 4);  
                  cell.setCellStyle(style2);
              }
              else
              {
                  cell = row.createCell((short) 1);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 2);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 3);  
                  cell.setCellStyle(style2);
                  cell = row.createCell((short) 4);  
                  cell.setCellStyle(style2);
              }

              row = sheet1.createRow((int) 44);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 45);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 46);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 47);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
              
              row = sheet1.createRow((int) 48);  
              cell = row.createCell((short) 0);  
              cell.setCellStyle(content);
              cell = row.createCell((short) 1);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 2);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 3);  
              cell.setCellStyle(style2);
              cell = row.createCell((short) 4);  
              cell.setCellStyle(style2);
          }
          

			if (null != exportUrl) {
					download(wb, exportUrl, resp);
			}  
			
    			}
    		 return "redirect:/enterprise/upload";
    }
    

     /**
 	 * @author lc
 	 * @注释：文件写入和下载
 	 */
     public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp){
     	 try  
          {  
 	          FileOutputStream fout = new FileOutputStream(exportUrl+"cqkjxjr01.xls");  
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
 				File file = new File(exportUrl + "cqkjxjr01.xls");
                  
              if (file.exists())
                  {
                    try {
                          resp.reset();
                          resp.setHeader("Content-Disposition", "attachment; filename="
                                  + "cqkjxjr01.xls");
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
