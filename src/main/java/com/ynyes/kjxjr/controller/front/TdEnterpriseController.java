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



import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdCouponService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.SMSUtil;
import com.ynyes.kjxjr.util.SiteMagConstant;

import scala.reflect.macros.internal.macroImpl;


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
    
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String userEnterpriseCheck(HttpServletRequest req, ModelMap map) {
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
    // 订单列表
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
          HSSFSheet sheet = wb.createSheet("order");  
          // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
          HSSFRow row = sheet.createRow((int) 0);  
          // 第四步，创建单元格，并设置值表头 设置表头居中  
          HSSFCellStyle style = wb.createCellStyle();  
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
          
          row = sheet.createRow((int) 0);  
          HSSFCell cell = row.createCell((short) 0);  
          cell.setCellValue("企业名称");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getTitle());
          
          row= sheet.createRow((int) 1);  
          cell = row.createCell((short) 0);  
          cell.setCellValue("成立时间");  
          cell.setCellStyle(style);  
          row.createCell((short) 1).setCellValue(enterprise.getEstablish()); 
          
          row= sheet.createRow((int) 2); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("注册资本");  
          cell.setCellStyle(style);  
          row.createCell((short) 1).setCellValue(enterprise.getCapital()+"万元");       
        
          row= sheet.createRow((int) 3); 
          cell = row.createCell((short) 0);  
          cell.setCellValue("法定代表人");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getRepresentative()); 
         
          row= sheet.createRow((int) 4);
          cell = row.createCell((short) 0);  
          cell.setCellValue("股东结构");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getShareholder()); 
          
          row= sheet.createRow((int) 5);
          cell = row.createCell((short) 0);  
          cell.setCellValue("所在地区");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getArea()); 
          
          row= sheet.createRow((int) 6);
          cell = row.createCell((short) 0);  
          cell.setCellValue("职工人数");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getStaffNumber()+"人"); 
          
          row= sheet.createRow((int) 7);
          cell = row.createCell((short) 0);  
          cell.setCellValue("行业归属");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getType()); 
          
          row= sheet.createRow((int) 8);
          cell = row.createCell((short) 0);  
          cell.setCellValue("邮箱");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getEmail()); 
          
          row= sheet.createRow((int) 9);
          cell = row.createCell((short) 0);  
          cell.setCellValue("联系人");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getContact()); 
          
          row= sheet.createRow((int) 10);
          cell = row.createCell((short) 0);  
          cell.setCellValue("公司网站");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getWebsite()); 
          
          row= sheet.createRow((int) 11);
          cell = row.createCell((short) 0);  
          cell.setCellValue("联系电话");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getTelephone()); 
          
          row= sheet.createRow((int) 12);
          cell = row.createCell((short) 0);  
          cell.setCellValue("传真");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getFax()); 
          
          row= sheet.createRow((int) 13);
          cell = row.createCell((short) 0);  
          cell.setCellValue("QQ/MSN");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getChat()); 
          
          row= sheet.createRow((int) 14);
          cell = row.createCell((short) 0);  
          cell.setCellValue("手机");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getMobile()); 
          
          row= sheet.createRow((int) 15);
          cell = row.createCell((short) 0);  
          cell.setCellValue("企业简介");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getProfile()); 
          
          row= sheet.createRow((int) 16);
          cell = row.createCell((short) 0);  
          cell.setCellValue("公司团队");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getTeamIntroduction()); 
          
          row= sheet.createRow((int) 17);
          cell = row.createCell((short) 0);  
          cell.setCellValue("技术特点及优势");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getAdvantage()); 
          
          row= sheet.createRow((int) 18);
          cell = row.createCell((short) 0);  
          cell.setCellValue("市场规模行业地位");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getSize()); 
          
          row= sheet.createRow((int) 19);
          cell = row.createCell((short) 0);  
          cell.setCellValue("3年前总资产");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastAssets3()+"万元"); 
          
          row= sheet.createRow((int) 20);
          cell = row.createCell((short) 0);  
          cell.setCellValue("3年前净资产");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastNetAssets3()+"万元");
          
          row= sheet.createRow((int) 21);
          cell = row.createCell((short) 0);  
          cell.setCellValue("3年前销售收入");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastSale3()+"万元"); 
          
          row= sheet.createRow((int) 22);
          cell = row.createCell((short) 0);  
          cell.setCellValue("3年前毛利润");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastProfit3()+"万元"); 
          
          row= sheet.createRow((int) 23);
          cell = row.createCell((short) 0);  
          cell.setCellValue("2年前总资产");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastAssets2()+"万元"); 
          
          row= sheet.createRow((int) 24);
          cell = row.createCell((short) 0);  
          cell.setCellValue("2年前净资产");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastNetAssets2()+"万元"); 
          
          row= sheet.createRow((int) 25);
          cell = row.createCell((short) 0);  
          cell.setCellValue("2年前销售收入");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastSale2()+"万元"); 
          
          row= sheet.createRow((int) 26);
          cell = row.createCell((short) 0);  
          cell.setCellValue("2年前毛利润");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastProfit2()+"万元"); 
          
          row= sheet.createRow((int) 27);
          cell = row.createCell((short) 0);  
          cell.setCellValue("1年前总资产");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastAssets1()+"万元"); 
          
          row= sheet.createRow((int) 28);
          cell = row.createCell((short) 0);  
          cell.setCellValue("1年前净资产");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastNetAssets1()+"万元"); 
          
          row= sheet.createRow((int) 29);
          cell = row.createCell((short) 0);  
          cell.setCellValue("1年前销售收入");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastSale1()+"万元"); 
          
          row= sheet.createRow((int) 30);
          cell = row.createCell((short) 0);  
          cell.setCellValue("1年前毛利润");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getLastProfit1()+"万元"); 
          
          row= sheet.createRow((int) 31);
          cell = row.createCell((short) 0);  
          cell.setCellValue("发明专利");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getInventiPatent()); 
          
          row= sheet.createRow((int) 32);
          cell = row.createCell((short) 0);  
          cell.setCellValue("实用新型专利");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getNewPatent()); 
          
          row= sheet.createRow((int) 33);
          cell = row.createCell((short) 0);  
          cell.setCellValue("外观设计专利");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getDesignPatent()); 
          
          row= sheet.createRow((int) 34);
          cell = row.createCell((short) 0);  
          cell.setCellValue("期望股权融资时间");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getExpectEquityDate()); 
          
          row= sheet.createRow((int) 35);
          cell = row.createCell((short) 0);  
          cell.setCellValue("期望股权融资金额");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getExpectEquityAmount()+"万元"); 
          
          row= sheet.createRow((int) 36);
          cell = row.createCell((short) 0);  
          cell.setCellValue("融资用途");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getExpectEquityUse()); 
          
          row= sheet.createRow((int) 37);
          cell = row.createCell((short) 0);  
          cell.setCellValue("期望债权融资时间");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getExpectBondDate()); 
          
          row= sheet.createRow((int) 38);
          cell = row.createCell((short) 0);  
          cell.setCellValue("期望债权融资金额");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getExpectEquityAmount()+"万元"); 
          
          row= sheet.createRow((int) 39);
          cell = row.createCell((short) 0);  
          cell.setCellValue("融资用途");  
          cell.setCellStyle(style);
          row.createCell((short) 1).setCellValue(enterprise.getExpectBondUse()); 
          
          row= sheet.createRow((int) 40);
          cell = row.createCell((short) 0);  
          cell.setCellValue("是否愿意披露信息");  
          cell.setCellStyle(style);
          if (enterprise.getIsShow())
          {
         	 row.createCell((short) 1).setCellValue("是"); 
          }
          else
          {
         	 row.createCell((short) 1).setCellValue("否"); 
          }
    			
			if (null != exportUrl) {
					download(wb, username, resp);
			}  
    			}
        return "/client/enterprise_upload";
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
