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
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.kjxjr.entity.TdActivity;
import com.ynyes.kjxjr.entity.TdActivityEnterprise;
import com.ynyes.kjxjr.entity.TdActivityExpert;
import com.ynyes.kjxjr.entity.TdActivityType;
import com.ynyes.kjxjr.entity.TdCoachContent;
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdEnterpriseGrade;
import com.ynyes.kjxjr.entity.TdExpert;
import com.ynyes.kjxjr.entity.TdExpertCoachEnterprise;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.service.TdActivityEnterpriseService;
import com.ynyes.kjxjr.service.TdActivityExpertService;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdActivityTypeService;
import com.ynyes.kjxjr.service.TdCoachContentService;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdEnterpriseGradeService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdExpertCoachEnterpriseService;
import com.ynyes.kjxjr.service.TdExpertService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.SiteMagConstant;

@Controller
@RequestMapping(value = "/expert")
public class TdExpertController {

	private String filepath = SiteMagConstant.imagePath;

	@Autowired
	private TdExpertService tdExpertService;

	@Autowired
	private TdActivityExpertService tdActivityExpertService;

	@Autowired
	private TdActivityService tdActivityService;

	@Autowired
	private TdActivityTypeService tdActivityTypeService;

	@Autowired
	private TdActivityEnterpriseService tdActivityEnterpriseService;

	@Autowired
	private TdEnterpriseGradeService tdEnterpriseGradeService;

	@Autowired
	private TdExpertCoachEnterpriseService tdExpertCoachEnterpriseService;

	@Autowired
	private TdCoachContentService tdCoachContentService;

	@Autowired
	private TdEnterpriseService tdEnterpriseService;
	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdCommonService tdCommonService;
	
	@RequestMapping(value = "/enterprise/list")
	public String execute(HttpServletRequest req, ModelMap map) {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return "/client/login";
		}
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdActivityExpert> ae_list = tdActivityExpertService
				.findByExpertIdAndCreateTimeAfterOrderByCreateTimeDesc(expert.getId());
		List<TdActivity> activities_list = new ArrayList<>();
		for (TdActivityExpert item : ae_list) {
			TdActivity activity = tdActivityService.findOne(item.getActivityId());
			if (null != activity) {
				activities_list.add(activity);
			}
		}
		map.addAttribute("expert", expert);
		map.addAttribute("activities", activities_list);
		return "/client/expert_activities";
	}

	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable Long id, HttpServletRequest req, ModelMap map) {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return "/client/login";
		}
		
		tdCommonService.setHeader(map, req);
		
		TdUser user = tdUserService.findByUsernameAndIsEnabled(expertUsername);
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		
		TdActivity activity = tdActivityService.findOne(id);
        if (null != activity)
        {
        	map.addAttribute("recommend_list" , tdActivityEnterpriseService.findByActivityIdAndStatusIdOrderBySortIdAsc(id, 2L));
	        map.addAttribute("activity", activity);
	        map.addAttribute("selected_enterprise_list", tdActivityEnterpriseService.findByActivityId(id));
	        map.addAttribute("selected_expert_list", tdActivityExpertService.findByActivityId(id));
        }
		
//		List<TdActivityType> all_type = tdActivityTypeService.findAll();
//		TdActivity activity = tdActivityService.findOne(id);
//		List<TdActivityEnterprise> enterprise_resources = tdActivityEnterpriseService
//				.findByActivityId(activity.getId());
//		List<TdActivityExpert> experts = tdActivityExpertService.findByActivityId(activity.getId());
        
        map.addAttribute("user", user);
        map.addAttribute("mark", "expert");
        map.addAttribute("pagetype", "check");
        map.addAttribute("expert", expert);
        return "/client/activity_create";
        
        
//		map.addAttribute("experts", expert);
//		map.addAttribute("enterprise_resources", enterprise_resources);
//		map.addAttribute("resources", activity.getDownload());
//		map.addAttribute("all_type", all_type);
//		map.addAttribute("activity", activity);
//		return "/client/expert_activity_detail";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public void download(String name, HttpServletResponse resp, HttpServletRequest req) throws IOException {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return;
		}

		if (null == name) {
			return;
		}

		OutputStream os = resp.getOutputStream();

		File file = new File(filepath + name);

		if (file.exists()) {
			try {
				resp.reset();
				resp.setHeader("Content-Disposition", "attachment; filename=" + name);
				resp.setContentType("application/octet-stream; charset=utf-8");
				os.write(FileUtils.readFileToByteArray(file));
				os.flush();
			} finally {
				if (os != null) {
					os.close();
				}
			}
		}
	}

	@RequestMapping(value = "/files/download")
	@ResponseBody
	public void downfiles(String name, HttpServletResponse resp, HttpServletRequest req) throws IOException {
		String[] ids = name.split(",");
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return;
		}
		for (String sId : ids) {
			TdActivityEnterprise enterprise = tdActivityEnterpriseService.findOne(Long.parseLong(sId));
			if (null == enterprise.getDownload()) {
				return;
			}
			OutputStream os = resp.getOutputStream();

			File file = new File(filepath + enterprise.getDownload());
			if (file.exists()) {
				try {
					resp.reset();
					resp.setHeader("Content-Disposition", "attachment; filename=" + enterprise.getDownload());
					resp.setContentType("application/octet-stream; charset=utf-8");
					os.write(FileUtils.readFileToByteArray(file));
					os.flush();
				} finally {
					if (os != null) {
						os.close();
					}
				}
			}
		}
	}

	@RequestMapping(value = "/grade")
	public String goGrade(Long activityId, HttpServletRequest req, ModelMap map) {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return "/client/login";
		}
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdEnterpriseGrade> grade_list = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(expert.getId(),
				activityId);
		map.addAttribute("grade_list", grade_list);
		map.addAttribute("activity", tdActivityService.findOne(activityId));
		map.addAttribute("activityId", activityId);
		map.addAttribute("expertId", expert.getId());
		return "/client/project_grade";
	}

	@RequestMapping(value = "/grade/sure")
	@ResponseBody
	public Map<String, Object> gradeSure(TdEnterpriseGrade grade, String number, Long activityId,
			HttpServletRequest req) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		TdEnterprise enterprise = tdEnterpriseService.findByNumber(number);
		TdEnterpriseGrade theGrade = tdEnterpriseGradeService.findByExpertIdAndActivityIdAndNumber(expert.getId(),
				activityId, number);
		System.err.println(grade);
		theGrade.setTotalPoint(grade.getTotalExpression() + grade.getTotalFeasibility() + grade.getTotalMarketValue()
				+ grade.getTotalTechnology() + grade.getTotalGroup());
		theGrade.setExpertId(expert.getId());
		theGrade.setEnterpriseId(enterprise.getId());
		theGrade.setActivityId(activityId);
		theGrade.setNumber(number);
		theGrade.setTotalExpression(grade.getTotalExpression());
		theGrade.setOneExpression(grade.getOneExpression());
		theGrade.setTotalFeasibility(grade.getTotalFeasibility());
		theGrade.setOneFeasibility(grade.getOneFeasibility());
		theGrade.setTwoFeasibility(grade.getTwoFeasibility());
		theGrade.setTotalMarketValue(grade.getTotalMarketValue());
		theGrade.setOneMarketValue(grade.getOneMarketValue());
		theGrade.setTwoMarketValue(grade.getTwoMarketValue());
		theGrade.setTotalTechnology(grade.getTotalTechnology());
		theGrade.setOneTechnology(grade.getOneTechnology());
		theGrade.setTwoTechnology(grade.getTwoTechnology());
		theGrade.setThreeTechnology(grade.getThreeTechnology());
//		theGrade.setTotalPoint(grade.getTotalPoint());   zhangji 注释掉
		theGrade.setOneGroup(grade.getOneGroup());
		theGrade.setTwoGroup(grade.getTwoGroup());
		theGrade.setTotalGroup(grade.getTotalGroup()); //zhangji
		theGrade.setIsGrade(true);
		tdEnterpriseGradeService.save(theGrade);
		TdActivityEnterprise activityEnterprise = tdActivityEnterpriseService.findByActivityIdAndEnterpriseId(activityId, enterprise.getId());
		activityEnterprise.setIsGrade(true);
		tdActivityEnterpriseService.save(activityEnterprise);
		TdExpertCoachEnterprise expertCoachEnterprise = tdExpertCoachEnterpriseService.findByEnterpriseId(enterprise.getId());
		if(null == expertCoachEnterprise){
			expertCoachEnterprise = new TdExpertCoachEnterprise();
		}
		expertCoachEnterprise.setIsGrade(true);
		tdExpertCoachEnterpriseService.save(expertCoachEnterprise);
		res.put("status", 0);
		return res;
	}

	@RequestMapping(value = "/enterprises")
	public String enterprises(HttpServletRequest req, ModelMap map) {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return "/client/login";
		}
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdExpertCoachEnterprise> enterprise_list = tdExpertCoachEnterpriseService
				.findByExpertIdAndIsGradeIsFalse(expert.getId());
		map.addAttribute("enterprise_list", enterprise_list);
		return "/client/expert_enterprise_list";
	}

	@RequestMapping(value = "/coach/{enterpriseId}")
	public String coach(@PathVariable Long enterpriseId, HttpServletRequest req, ModelMap map) {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return "/client/login";
		}
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdCoachContent> content_list = tdCoachContentService
				.findByExpertIdAndEnterpriseIdOrderByCoachDateAsc(expert.getId(), enterpriseId);
		TdEnterprise enterprise = tdEnterpriseService.findOne(enterpriseId);
		map.addAttribute("enterprise", enterprise);
		if (null != enterprise.getType())
        {
        	String type[] = enterprise.getType().split(",");
        	map.addAttribute("enterpriseType", type);
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
        
		map.addAttribute("content_list", content_list);
		return "/client/coach_record";
	}

	@RequestMapping(value = "/coach/save")
	public String coachSave(HttpServletRequest req, String content, Long enterpriseId) {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		TdCoachContent coach = new TdCoachContent();
		coach.setCoachDate(new Date());
		coach.setContent(content);
		coach.setEnterpriseId(enterpriseId);
		coach.setExpertId(expert.getId());
		tdCoachContentService.save(coach);
		return "redirect:/expert/coach/" + enterpriseId;
	}
	
	@RequestMapping(value = "/lyfd")
	public String lyfd(HttpServletRequest req,ModelMap map){
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdExpertCoachEnterprise> grade_false_list = tdExpertCoachEnterpriseService.findByExpertIdAndIsGradeIsFalse(expert.getId());
		List<TdExpertCoachEnterprise> grade_true_list = tdExpertCoachEnterpriseService.findByExpertIdAndIsGradeIsTrue(expert.getId());
		map.addAttribute("grade_false_list", grade_false_list);
		map.addAttribute("grade_true_list", grade_true_list);
		return "/client/lydf";
	}
	
	@RequestMapping(value = "/coach/log/{enterpriseId}")
	public String coachLog(@PathVariable Long enterpriseId,HttpServletRequest req,ModelMap map){
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if(null == expertUsername){
			return "/client/login";
		}
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdCoachContent> content_list = tdCoachContentService.findByExpertIdAndEnterpriseIdOrderByCoachDateAsc(expert.getId(), enterpriseId);
		map.addAttribute("content_list", content_list);
		return "/client/coach_log";
	}
	
	@RequestMapping(value = "/search/grade")
	public String searchGrade(Long activityId,Long expertId,ModelMap map){
		TdExpert expert = tdExpertService.findOne(expertId);
		TdActivity activity = tdActivityService.findOne(activityId);
		List<TdEnterpriseGrade> grade_list = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(expert.getId(),
				activityId);
		
		map.addAttribute("type", "check");
		map.addAttribute("grade_list", grade_list);
		map.addAttribute("activityId", activityId);
		map.addAttribute("activity", activity);
		map.addAttribute("expertId", expertId);
		return "/client/project_grade";
	}
	
	
	//导出
	//创投每周行路演评分表
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/export/grade")
	public String exportGrade(
	                            Long activityId,
	                            Long expertId,
	                            ModelMap map,
	                            String exportUrl,
	                            HttpServletResponse resp,
	                            HttpServletRequest req){
	    String username = (String) req.getSession().getAttribute("regionUsername");
	    if (null == username) {
	        return "redirect:/login";
	    }
	        	exportUrl = SiteMagConstant.backupPath;
	    
		if (null != exportUrl) {
					List<TdEnterpriseGrade> enterpriseGradeList = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(expertId, activityId);
			
					if (null ==enterpriseGradeList || enterpriseGradeList.size()==0)
					{
						Long numwarn = 1L;
						return "redirect:/expert/search/grade?activityId="+activityId+"&expertId="+expertId+"&numwarn="+numwarn;
					}
		int columnSize = 1+enterpriseGradeList.size();  //列数
	    /**  
			 * @author lc
			 * @注释：根据不同条件导出excel文件
			 */
	      // 第一步，创建一个webbook，对应一个Excel文件  
	      HSSFWorkbook wb = new HSSFWorkbook();  
	      // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	      HSSFSheet sheet = wb.createSheet("grade");  
	      
	      //打印设置
	      HSSFPrintSetup ps = sheet.getPrintSetup();
	      ps.setLandscape(true); //打印方向，true:横向，false:纵向
	      ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
	      sheet.setMargin(HSSFSheet.BottomMargin, (double)0.3); //页边距（下）
	      sheet.setMargin(HSSFSheet.LeftMargin, (double)0.3); //页边距（左）
	      sheet.setMargin(HSSFSheet.RightMargin, (double)0.3); //页边距（右）
	      sheet.setMargin(HSSFSheet.TopMargin, (double)0.1); //页边距（上）
	      sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
	      sheet.setVerticallyCenter(true); //设置打印页面为垂直居中
	      
	      //列宽
	      sheet.setColumnWidth((short) 0 , 45*256);
	      sheet.setDefaultColumnWidth(10*256);
	      sheet.setDefaultRowHeightInPoints(20);  
	      
	      
	      // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	      HSSFRow row = sheet.createRow((int) 0);  
	      
	      sheet.addMergedRegion(new Region((short) 0 , (short) 0 , (short) 1 , (short) columnSize));     //标题1行
	      sheet.addMergedRegion(new Region((short) 2 , (short) 0 , (short) 2 , (short) columnSize));     //标题2行
//	      sheet.addMergedRegion(new Region((short) 3 , (short) 0 , (short) 4 , (short) 6));     //标题3行
//	      sheet.addMergedRegion(new Region((short) 5 , (short) 0 , (short) 5, (short) 2));     //公章
	      // 第四步，创建单元格，并设置值表头 设置表头居中  
	      HSSFCellStyle style = wb.createCellStyle();  
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
	      style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
	      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
	      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	      style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	      
	      
	      HSSFCellStyle style1 = wb.createCellStyle();  
	      style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
	      style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
	      style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	      style1.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	      
	      
	      HSSFCellStyle style2 = wb.createCellStyle();  
	      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
	      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
	      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
	      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	      
	      HSSFCellStyle title1 = wb.createCellStyle();  
	      title1.setBorderBottom(HSSFCellStyle.BORDER_NONE);    //设置边框样式
	      title1.setBorderRight(HSSFCellStyle.BORDER_NONE);
	      title1.setBorderLeft(HSSFCellStyle.BORDER_NONE);  
	      title1.setBorderTop(HSSFCellStyle.BORDER_NONE);  
	      title1.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);//垂直下
	      title1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中格式
	      
	      HSSFCellStyle title = wb.createCellStyle();  
	      title.setBorderBottom(HSSFCellStyle.BORDER_NONE);    //设置边框样式
	      title.setBorderRight(HSSFCellStyle.BORDER_NONE);
	      title.setBorderLeft(HSSFCellStyle.BORDER_NONE);  
	      title.setBorderTop(HSSFCellStyle.BORDER_NONE);  
	      title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
	      title.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中格式
	      
	      HSSFCellStyle title3 = wb.createCellStyle();  
	      title3.setBorderBottom(HSSFCellStyle.BORDER_NONE);    //设置边框样式
	      title3.setBorderRight(HSSFCellStyle.BORDER_NONE);
	      title3.setBorderLeft(HSSFCellStyle.BORDER_NONE);  
	      title3.setBorderTop(HSSFCellStyle.BORDER_NONE);  
	      title3.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);//垂直上
	      title3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中格式
	      
	      HSSFFont font = wb.createFont();
	      font.setFontName("黑体");
	      font.setFontHeightInPoints((short) 12);//设置字体大小
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
	      title1.setFont(font);//选择需要用到的字体格式
	      title.setFont(font);//选择需要用到的字体格式
	      title3.setFont(font);//选择需要用到的字体格式
	      
	      //盖章
	      HSSFCellStyle left = wb.createCellStyle();  
	      left.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
	      left.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 居格式
	      left.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
	      
	      //日期
	      HSSFCellStyle right = wb.createCellStyle();  
	      right.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
	      right.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 格式
	      right.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
	      
	      
	      HSSFCell cell = row.createCell((short) 0);  
	      cell.setCellValue("创投每周行路演评分表");  
	      cell.setCellStyle(title1);
	      
	      row =sheet.createRow((int) 2);
	      cell = row.createCell((short) 0);  
	      cell.setCellValue(tdActivityService.findOne(enterpriseGradeList.get(0).getActivityId()).getTitle());  
	      cell.setCellStyle(title);
	      
	      
	      row =sheet.createRow((int) 3);
	      cell = row.createCell((short) 0);  
	      cell.setCellValue("评分项目");  
	      cell.setCellStyle(style);
	      
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      String date = sdf.format(new Date());
	      cell = row.createCell((short) 6);  
	      cell.setCellValue("日期："+date);  
	      cell.setCellStyle(right);
//	      cell = row.createCell((short) 6);  
//	      cell.setCellValue(date);  
//	      cell.setCellStyle(left);
	      
	      
	      
	      row =sheet.createRow((int) 6);
	      row.setHeight((short) (20 * 20));  
	      cell = row.createCell((short) 0);  
	      cell.setCellValue("序号");  
	      cell.setCellStyle(style);
	      
	      cell = row.createCell((short) 1);  
	      cell.setCellValue("编号");  
	      cell.setCellStyle(style);  
	      
	      cell = row.createCell((short) 2);  
	      cell.setCellValue("公司（团队）名称");  
	      cell.setCellStyle(style);  
	      
	      cell = row.createCell((short) 3);  
	      cell.setCellValue("联系人");  
	      cell.setCellStyle(style);  
	      
	      cell = row.createCell((short) 4);  
	      cell.setCellValue("手机");  
	      cell.setCellStyle(style);  
	    
	      cell = row.createCell((short) 5);  
	      cell.setCellValue("QQ/MSN");  
	      cell.setCellStyle(style);  
	      
//	      cell = row.createCell((short) 6);  
//	      cell.setCellValue("项目简介");  
//	      cell.setCellStyle(style);  
	      
	      cell = row.createCell((short) 6);  
	      cell.setCellValue("推荐理由");  
	      cell.setCellStyle(style);  
	      
				
			if (null != exportUrl) {
				if (ImportData(activityEnterpriseList, row, cell, sheet,style)) {
					download(wb, exportUrl, resp);
				}         
			}  
				}
				return "redirect:/region/recommendEnterprise?id="+activityId;
	}

	/**
		 * @author lc
		 * @注释：将page中的订单数据存入excel表格中
		 */
	 @SuppressWarnings("deprecation")
		public boolean ImportData(List<TdActivityEnterprise> activityEnterpriseList, HSSFRow row, HSSFCell cell, HSSFSheet sheet ,HSSFCellStyle style){
		 	
	        	for (int i = 0; i < activityEnterpriseList.size(); i++)  
	            {  
	        	 				
	                row = sheet.createRow((int) i + 7);  
	                row.setHeight((short) (20 * 20));  
	                TdActivityEnterprise tdActivityEnterprise = activityEnterpriseList.get(i);  
	                //获取用户信息
//	                TdUser tdUser = tdUserService.findByUsername(tdOrder.getUsername());
	                // 第四步，创建单元格，并设置值  
	                cell = row.createCell((short) 0);
	                cell.setCellValue(i+1);
	                cell.setCellStyle(style); 
	                cell = row.createCell((short) 1);
	                cell.setCellValue(tdActivityEnterprise.getNumber());
	                cell.setCellStyle(style);
	                cell = row.createCell((short) 2);
	                cell.setCellStyle(style);
	                cell.setCellValue(tdActivityEnterprise.getEnterpriseTitle()); 
	                cell = row.createCell((short) 3);
	                cell.setCellStyle(style);
	                cell.setCellValue(tdActivityEnterprise.getContact()); 
	                cell = row.createCell((short) 4);
	                cell.setCellStyle(style);
	                cell.setCellValue(tdActivityEnterprise.getMobile());
	                cell = row.createCell((short) 5);
	                cell.setCellStyle(style);
	                cell.setCellValue(tdActivityEnterprise.getQQ());
//	                cell = row.createCell((short) 6);
//	                cell.setCellStyle(style);
//	                cell.setCellValue(tdActivityEnterprise.getProfile());
	                cell = row.createCell((short) 6);
	                cell.setCellValue(tdActivityEnterprise.getReason()); 
	                cell.setCellStyle(style);
	             
	            } 
	 	return true;
	 }
	 /**
		 * @author lc
		 * @注释：文件写入和下载
		 */
	 public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp){
	 	 try  
	      {  
		          FileOutputStream fout = new FileOutputStream(exportUrl+"cqkjxjr02.xls");  
//		          OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");	                       	     
		          wb.write(fout);  
		          fout.close();
	      }catch (Exception e)  
	      {  
	          e.printStackTrace();  
	      } 
	 	 OutputStream os;
			 try {
					os = resp.getOutputStream();
					File file = new File(exportUrl + "cqkjxjr02.xls");
	              
	          if (file.exists())
	              {
	                try {
	                      resp.reset();
	                      resp.setHeader("Content-Disposition", "attachment; filename="
	                              + "cqkjxjr02.xls");
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
