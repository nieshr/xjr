package com.ynyes.kjxjr.controller.front;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdRegionAdmin;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.service.TdActivityEnterpriseService;
import com.ynyes.kjxjr.service.TdActivityExpertService;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdCouponService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
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
        Long id = tdEnterprise.getId();
        String number = String.format("%04d", id);
        
        tdEnterprise.setNumber(number);
        tdEnterprise.setStatusId(0L);
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
        TdEnterprise Enterprise = tdEnterpriseService.findbyUsername(username);
        
        map.addAttribute("enterprise", Enterprise);
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
        }
        map.addAttribute("enterprise", enterprise);
        map.addAttribute("user", user);

        
        return "/client/enterprise_check";
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
    public String activityCheck(HttpServletRequest req, ModelMap map,Long id) {
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
        map.addAttribute("user", user);
        map.addAttribute("mark", "enterprise");
        map.addAttribute("pagetype", "check");
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
          
          sheet1.addMergedRegion(new Region((short) 0 , (short) 0 , (short) 0 , (short) 4));     //标题
          sheet1.addMergedRegion(new Region((short) 1 , (short) 0 , (short) 1 , (short) 4));     //标题
          sheet1.addMergedRegion(new Region((short) 2 , (short) 1 , (short) 2 , (short) 2));		//企业名称
          sheet1.addMergedRegion(new Region((short) 3 , (short) 1 , (short) 3 , (short) 2));		//注册资本
          sheet1.addMergedRegion(new Region((short) 4 , (short) 1 , (short) 4 , (short) 2));		//股东结构
          sheet1.addMergedRegion(new Region((short) 5 , (short) 1 , (short) 5 , (short) 2));		//地址
          sheet1.addMergedRegion(new Region((short) 6 , (short) 1 , (short) 7 , (short) 4));		//股东结构
          sheet1.addMergedRegion(new Region((short) 8 , (short) 1 , (short) 8 , (short) 2));		//邮箱
          sheet1.addMergedRegion(new Region((short) 9 , (short) 1 , (short) 9 , (short) 2));		//公司网站
          sheet1.addMergedRegion(new Region((short) 10 , (short) 1 , (short) 10 , (short) 2));		//QQ
          sheet1.addMergedRegion(new Region((short) 11 , (short) 1 , (short) 13 , (short) 4));		//公司团队、主要负责人
          sheet1.addMergedRegion(new Region((short) 11 , (short) 0 , (short) 13 , (short) 0));		//公司团队、主要负责人
          sheet1.addMergedRegion(new Region((short) 14 , (short) 1 , (short) 16 , (short) 4));		//企业简介
          sheet1.addMergedRegion(new Region((short) 14 , (short) 0 , (short) 16 , (short) 0));		//企业简介
          sheet1.addMergedRegion(new Region((short) 17 , (short) 1 , (short) 19 , (short) 4));		//技术特点
          sheet1.addMergedRegion(new Region((short) 17 , (short) 0 , (short) 19 , (short) 0));		//技术特点
          sheet1.addMergedRegion(new Region((short) 20 , (short) 1 , (short) 22 , (short) 4));		//市场规模
          sheet1.addMergedRegion(new Region((short) 20 , (short) 0 , (short) 22 , (short) 0));		//市场规模
          sheet1.addMergedRegion(new Region((short) 23 , (short) 0 , (short) 23 , (short) 4));		//近三年财务
          sheet1.addMergedRegion(new Region((short) 28 , (short) 0 , (short) 28 , (short) 4));		//知识产权
          sheet1.addMergedRegion(new Region((short) 29 , (short) 0 , (short) 29 , (short) 4));		//发明专利
          sheet1.addMergedRegion(new Region((short) 30 , (short) 0 , (short) 30 , (short) 4));		//新型专利
          sheet1.addMergedRegion(new Region((short) 31 , (short) 0 , (short) 31 , (short) 4));		//外观专利
          
          // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
          HSSFRow row = sheet1.createRow((int) 0);  
          // 第四步，创建单元格，并设置值表头 设置表头居中  
          HSSFCellStyle style = wb.createCellStyle();  
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
          
          HSSFCellStyle style1 = wb.createCellStyle();  
          style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
          
          HSSFCellStyle titleStyle = wb.createCellStyle();  
          
          
          row = sheet1.createRow((int) 0);  
          HSSFCell cell = row.createCell((short) 0);  
          cell.setCellValue("重庆市科技小巨人企业培育专项行动报名表");  
          cell.setCellStyle(style);
          
          if (enterprise.getFormType() == 0)
          {
	          row= sheet1.createRow((int) 1);  
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("企业组");  
	          cell.setCellStyle(style);  
          }
          else
          {
	          row= sheet1.createRow((int) 1);  
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("项目组");  
	          cell.setCellStyle(style);  
          }
         if(enterprise.getFormType() == 0)
         {
        	 row= sheet1.createRow((int) 2);  
             cell = row.createCell((short) 0);  
             cell.setCellValue("企业名称");  
             cell.setCellStyle(style);  
   	      	 cell = row.createCell((short) 1);
     		  cell.setCellValue(enterprise.getTitle()); 
     		  cell.setCellStyle(style1);  
     		  cell = row.createCell((short) 3);
     		  cell.setCellValue("成立时间");
     		  cell.setCellStyle(style);  
     		  cell = row.createCell((short) 4);
     		  cell.setCellValue(enterprise.getEstablish()); 
     		  cell.setCellStyle(style1);  
         }
         else
         {
	          row= sheet1.createRow((int) 2);  
	          cell = row.createCell((short) 0);  
	          cell.setCellValue("项目名称");  
	          cell.setCellStyle(style);  
		      cell = row.createCell((short) 1);
	  		  cell.setCellValue(enterprise.getTitle()); 
	  		  cell.setCellStyle(style1);  
	  		  cell = row.createCell((short) 3);
	  		  cell.setCellValue("成立时间");
	  		  cell.setCellStyle(style);  
	  		  cell = row.createCell((short) 4);
	  		  cell.setCellValue(enterprise.getEstablish()); 
	  		  cell.setCellStyle(style1);  
         }
         
          row= sheet1.createRow((int) 3); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("注册资本（万元）");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getCapital());     
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);
          cell.setCellValue("法定代表人");
          cell.setCellStyle(style);  
          cell =row.createCell((short) 4);
          cell.setCellValue(enterprise.getRepresentative()); 
          cell.setCellStyle(style);  
          
          row= sheet1.createRow((int) 4); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("股东结构");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 4);
          cell.setCellValue(enterprise.getShareholder());     
          cell.setCellStyle(style);  

          row= sheet1.createRow((int) 5); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("地址");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getArea());     
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);
          cell.setCellValue("职工人数（人）");
          cell.setCellStyle(style);  
          cell =row.createCell((short) 4);
          cell.setCellValue(enterprise.getStaffNumber()); 
          cell.setCellStyle(style);  
          
          row= sheet1.createRow((int) 6); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("行业归属");  
          cell.setCellStyle(style);  
          cell.setCellStyle(style1);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getType());     
          cell.setCellStyle(style);  
          cell.setCellStyle(style1);  
          
          row= sheet1.createRow((int) 8); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("邮箱");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getEmail());     
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);
          cell.setCellValue("联系人");
          cell.setCellStyle(style);  
          cell =row.createCell((short) 4);
          cell.setCellValue(enterprise.getContact()); 
          cell.setCellStyle(style);  
          
          row= sheet1.createRow((int) 9); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("公司网站");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getWebsite());     
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);
          cell.setCellValue("联系电话");
          cell.setCellStyle(style);  
          cell =row.createCell((short) 4);
          cell.setCellValue(enterprise.getTelephone()); 
          cell.setCellStyle(style);  
          
          row= sheet1.createRow((int) 10); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("QQ/MSN");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getChat());     
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);
          cell.setCellValue("手机");
          cell.setCellStyle(style);  
          cell =row.createCell((short) 4);
          cell.setCellValue(enterprise.getMobile()); 
          cell.setCellStyle(style);  
          
          if(enterprise.getFormType() == 0)
          {
        	  row= sheet1.createRow((int) 11); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("公司团队（200字内）");  
              cell.setCellStyle(style);  
              cell.setCellStyle(style1);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getTeamIntroduction());     
              cell.setCellStyle(style1);  
          }
          else
          {
        	  row= sheet1.createRow((int) 11); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("团队负责人");  
              cell.setCellStyle(style);  
              cell.setCellStyle(style1);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getInCharge());     
              cell.setCellStyle(style1);  
          }
          
          if(enterprise.getFormType() == 0)
          {
        	  row= sheet1.createRow((int) 14); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("企业简介（200字内）");  
              cell.setCellStyle(style);  
              cell.setCellStyle(style1);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getProfile());     
              cell.setCellStyle(style1);  
          }
          else
          {
        	  row= sheet1.createRow((int) 14); 
              cell = row.createCell((short) 0);  
              cell.setCellValue("团队简介（200字内）");  
              cell.setCellStyle(style);  
              cell.setCellStyle(style1);  
              cell = row.createCell((short) 1);
              cell.setCellValue(enterprise.getProfile());     
              cell.setCellStyle(style1);  
          }
         
    	  row= sheet1.createRow((int) 17); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("技术特点及优势（200字内）");  
          cell.setCellStyle(style);  
          cell.setCellStyle(style1);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getAdvantage());     
          cell.setCellStyle(style1);  

    	  row= sheet1.createRow((int) 20); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("市场规模行业地位（200字内）");  
          cell.setCellStyle(style);  
          cell.setCellStyle(style1);  
          cell = row.createCell((short) 1);
          cell.setCellValue(enterprise.getSize());     
          cell.setCellStyle(style1);  
          
    	  row= sheet1.createRow((int) 23); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("近三年财务状况（万元）");  
          cell.setCellStyle(style);  
        
          
    	  row= sheet1.createRow((int) 24); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("年限");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);  
          cell.setCellValue("总资产");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 2);  
          cell.setCellValue("净资产");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);  
          cell.setCellValue("销售收入");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 4);  
          cell.setCellValue("毛利润");  
          cell.setCellStyle(style);  
          
    	  row= sheet1.createRow((int) 25); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("2012");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);  
          cell.setCellValue(enterprise.getLastAssets3());  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 2);  
          cell.setCellValue(enterprise.getLastNetAssets3());  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);  
          cell.setCellValue(enterprise.getLastSale3());  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 4);  
          cell.setCellValue(enterprise.getLastProfit3());  
          cell.setCellStyle(style);  
          
    	  row= sheet1.createRow((int) 26); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("2013");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);  
          cell.setCellValue(enterprise.getLastAssets2());  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 2);  
          cell.setCellValue(enterprise.getLastNetAssets2());  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);  
          cell.setCellValue(enterprise.getLastSale2());  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 4);  
          cell.setCellValue(enterprise.getLastProfit2());  
          cell.setCellStyle(style);  

    	  row= sheet1.createRow((int) 27); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("2014");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);  
          cell.setCellValue(enterprise.getLastAssets1());  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 2);  
          cell.setCellValue(enterprise.getLastNetAssets1());  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);  
          cell.setCellValue(enterprise.getLastSale1());  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 4);  
          cell.setCellValue(enterprise.getLastProfit1());  
          cell.setCellStyle(style);  
          
          HSSFSheet sheet2 = wb.createSheet("第二页");  
          
          sheet2.addMergedRegion(new Region((short) 0 , (short) 0 , (short) 0 , (short) 4));     //知识产权
          sheet2.addMergedRegion(new Region((short) 1 , (short) 1 , (short) 1 , (short) 4));     //发明专利
          sheet2.addMergedRegion(new Region((short) 2 , (short) 1 , (short) 2 , (short) 4));     //新型专利
          sheet2.addMergedRegion(new Region((short) 3 , (short) 1 , (short) 3 , (short) 4));     //外观专利
          sheet2.addMergedRegion(new Region((short) 4 , (short) 0 , (short) 5 , (short) 4));     //融资信息
          sheet2.addMergedRegion(new Region((short) 6 , (short) 3 , (short) 6 , (short) 4));     
          sheet2.addMergedRegion(new Region((short) 7 , (short) 3 , (short) 7 , (short) 4));     
          sheet2.addMergedRegion(new Region((short) 8 , (short) 3 , (short) 8 , (short) 4));     
          sheet2.addMergedRegion(new Region((short) 9 , (short) 3 , (short) 9 , (short) 4));     
          sheet2.addMergedRegion(new Region((short) 10 , (short) 1 , (short) 10 , (short) 4));     //项目可供资料
          sheet2.addMergedRegion(new Region((short) 11 , (short) 1 , (short) 14 , (short) 4));     //盖章
          
          
//          row= sheet.createRow((int) 31);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("发明专利");  
//          cell.setCellStyle(style);
//          row.createCell((short) 1).setCellValue(enterprise.getInventiPatent()); 
//          
//          row= sheet.createRow((int) 32);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("实用新型专利");  
//          cell.setCellStyle(style);
//          row.createCell((short) 1).setCellValue(enterprise.getNewPatent()); 
//          
//          row= sheet.createRow((int) 33);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("外观设计专利");  
//          cell.setCellStyle(style);
//          row.createCell((short) 1).setCellValue(enterprise.getDesignPatent()); 
//          
//          row= sheet.createRow((int) 34);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("期望股权融资时间");  
//          cell.setCellStyle(style);
//          row.createCell((short) 1).setCellValue(enterprise.getExpectEquityDate()); 
//          
//          row= sheet.createRow((int) 35);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("期望股权融资金额");  
//          cell.setCellStyle(style);
//          row.createCell((short) 1).setCellValue(enterprise.getExpectEquityAmount()+"万元"); 
//          
//          row= sheet.createRow((int) 36);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("融资用途");  
//          cell.setCellStyle(style);
//          row.createCell((short) 1).setCellValue(enterprise.getExpectEquityUse()); 
//          
//          row= sheet.createRow((int) 37);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("期望债权融资时间");  
//          cell.setCellStyle(style);
//          row.createCell((short) 1).setCellValue(enterprise.getExpectBondDate()); 
//          
//          row= sheet.createRow((int) 38);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("期望债权融资金额");  
//          cell.setCellStyle(style);
//          row.createCell((short) 1).setCellValue(enterprise.getExpectEquityAmount()+"万元"); 
//          
//          row= sheet.createRow((int) 39);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("融资用途");  
//          cell.setCellStyle(style);
//          row.createCell((short) 1).setCellValue(enterprise.getExpectBondUse()); 
//          
//          row= sheet.createRow((int) 40);
//          cell = row.createCell((short) 0);  
//          cell.setCellValue("是否愿意披露信息");  
//          cell.setCellStyle(style);
//          if (enterprise.getIsShow())
//          {
//         	 row.createCell((short) 1).setCellValue("是"); 
//          }
//          else
//          {
//         	 row.createCell((short) 1).setCellValue("否"); 
//          }
//    		
			if (null != exportUrl) {
					download(wb, username, resp);
			}  
			
    			}
    		 return "redirect:/enterprise/upload";
    }
    
//    /**
// 	 * @author lc
// 	 * @注释：将page中的订单数据存入excel表格中
// 	 */
//     @SuppressWarnings("deprecation")
// 	public boolean ImportData(TdEnterprise enterprise, HSSFRow row, HSSFCell cell, HSSFSheet sheet){
//
//             row = sheet.createRow(1);  
//            
//             //获取用户信息
//             TdUser tdUser = tdUserService.findByUsername(enterprise.getUsername());
//             // 第四步，创建单元格，并设置值  
//             row= sheet.createRow((int) 0);
//             row.createCell((short) 1).setCellValue(enterprise.getTitle());
//             row= sheet.createRow((int) 1);
//             row.createCell((short) 1).setCellValue(enterprise.getEstablish()); 
//             row= sheet.createRow((int) 2);
//             row.createCell((short) 1).setCellValue(enterprise.getCapital()+"万元");       
//             row= sheet.createRow((int) 3);
//             row.createCell((short) 1).setCellValue(enterprise.getRepresentative()); 
//             row= sheet.createRow((int) 4);
//             row.createCell((short) 1).setCellValue(enterprise.getShareholder()); 
//             row= sheet.createRow((int) 5);
//             row.createCell((short) 1).setCellValue(enterprise.getArea()); 
//             row= sheet.createRow((int) 6);
//             row.createCell((short) 1).setCellValue(enterprise.getStaffNumber()); 
//             row= sheet.createRow((int) 7);
//             row.createCell((short) 1).setCellValue(enterprise.getType()); 
//             row= sheet.createRow((int) 8);
//             row.createCell((short) 1).setCellValue(enterprise.getEmail()); 
//             row= sheet.createRow((int) 9);
//             row.createCell((short) 1).setCellValue(enterprise.getContact()); 
//             row= sheet.createRow((int) 10);
//             row.createCell((short) 1).setCellValue(enterprise.getWebsite()); 
//             row= sheet.createRow((int) 11);
//             row.createCell((short) 1).setCellValue(enterprise.getTelephone()); 
//             row= sheet.createRow((int) 12);
//             row.createCell((short) 1).setCellValue(enterprise.getFax()); 
//             row= sheet.createRow((int) 13);
//             row.createCell((short) 1).setCellValue(enterprise.getChat()); 
//             row= sheet.createRow((int) 14);
//             row.createCell((short) 1).setCellValue(enterprise.getMobile()); 
//             row= sheet.createRow((int) 15);
//             row.createCell((short) 1).setCellValue(enterprise.getProfile()); 
//             row= sheet.createRow((int) 16);
//             row.createCell((short) 1).setCellValue(enterprise.getTeamIntroduction()); 
//             row= sheet.createRow((int) 17);
//             row.createCell((short) 1).setCellValue(enterprise.getAdvantage()); 
//             row= sheet.createRow((int) 18);
//             row.createCell((short) 1).setCellValue(enterprise.getSize()); 
//             row= sheet.createRow((int) 19);
//             row.createCell((short) 1).setCellValue(enterprise.getLastAssets3()+"万元"); 
//             row= sheet.createRow((int) 20);
//             row.createCell((short) 1).setCellValue(enterprise.getLastNetAssets3()+"万元"); 
//             row= sheet.createRow((int) 21);
//             row.createCell((short) 1).setCellValue(enterprise.getLastSale3()+"万元"); 
//             row= sheet.createRow((int) 22);
//             row.createCell((short) 1).setCellValue(enterprise.getLastProfit3()+"万元"); 
//             row= sheet.createRow((int) 23);
//             row.createCell((short) 1).setCellValue(enterprise.getLastAssets2()+"万元"); 
//             row= sheet.createRow((int) 24);
//             row.createCell((short) 1).setCellValue(enterprise.getLastNetAssets2()+"万元"); 
//             row= sheet.createRow((int) 25);
//             row.createCell((short) 1).setCellValue(enterprise.getLastSale2()+"万元"); 
//             row= sheet.createRow((int) 26);
//             row.createCell((short) 1).setCellValue(enterprise.getLastProfit2()+"万元"); 
//             row= sheet.createRow((int) 27);
//             row.createCell((short) 1).setCellValue(enterprise.getLastAssets1()+"万元"); 
//             row= sheet.createRow((int) 28);
//             row.createCell((short) 1).setCellValue(enterprise.getLastNetAssets1()+"万元"); 
//             row= sheet.createRow((int) 29);
//             row.createCell((short) 1).setCellValue(enterprise.getLastSale1()+"万元"); 
//             row= sheet.createRow((int) 30);
//             row.createCell((short) 1).setCellValue(enterprise.getLastProfit1()+"万元"); 
//             row= sheet.createRow((int) 31);
//             row.createCell((short) 1).setCellValue(enterprise.getInventiPatent()); 
//             row= sheet.createRow((int) 32);
//             row.createCell((short) 1).setCellValue(enterprise.getNewPatent()); 
//             row= sheet.createRow((int) 33);
//             row.createCell((short) 1).setCellValue(enterprise.getDesignPatent()); 
//             row= sheet.createRow((int) 34);
//             row.createCell((short) 1).setCellValue(enterprise.getExpectEquityDate()); 
//             row= sheet.createRow((int) 35);
//             row.createCell((short) 1).setCellValue(enterprise.getExpectEquityAmount()+"万元"); 
//             row= sheet.createRow((int) 36);
//             row.createCell((short) 1).setCellValue(enterprise.getExpectEquityUse()); 
//             row= sheet.createRow((int) 37);
//             row.createCell((short) 1).setCellValue(enterprise.getExpectBondDate()); 
//             row= sheet.createRow((int) 38);
//             row.createCell((short) 1).setCellValue(enterprise.getExpectEquityAmount()+"万元"); 
//             row= sheet.createRow((int) 39);
//             row.createCell((short) 1).setCellValue(enterprise.getExpectBondUse()); 
//             row= sheet.createRow((int) 40);
//             if (enterprise.getIsShow())
//             {
//            	 row.createCell((short) 1).setCellValue("是"); 
//             }
//             else
//             {
//            	 row.createCell((short) 1).setCellValue("否"); 
//             }
//
//     	return true;
//     }
     /**
 	 * @author lc
 	 * @注释：文件写入和下载
 	 */
     public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp){
     	 try  
          {  
 	          FileOutputStream fout = new FileOutputStream(exportUrl+"enterprise.xls");  
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
 				File file = new File(exportUrl + "enterprise.xls");
                  
              if (file.exists())
                  {
                    try {
                          resp.reset();
                          resp.setHeader("Content-Disposition", "attachment; filename="
                                  + "enterprise.xls");
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
