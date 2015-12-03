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
import com.ynyes.kjxjr.entity.TdActivityInvest;
import com.ynyes.kjxjr.entity.TdActivityType;
import com.ynyes.kjxjr.entity.TdCoachContent;
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdEnterpriseGrade;
import com.ynyes.kjxjr.entity.TdExpert;
import com.ynyes.kjxjr.entity.TdExpertCoachEnterprise;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.service.TdActivityEnterpriseService;
import com.ynyes.kjxjr.service.TdActivityExpertService;
import com.ynyes.kjxjr.service.TdActivityInvestService;
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
	
	@Autowired
	TdActivityInvestService tdActivityInvestService;
	
	@RequestMapping(value = "/enterprise/list")
	public String execute(HttpServletRequest req, ModelMap map) {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return "/client/login";
		}
		 tdCommonService.setHeader(map, req);
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdActivityExpert> ae_list = tdActivityExpertService
				.findByExpertIdOrderByCreateTimeDesc(expert.getId());
		List<TdActivity> activities_list = new ArrayList<>();
		if(null != ae_list && ae_list.size()>0)
		{
			for (TdActivityExpert item : ae_list) {
				TdActivity activity = tdActivityService.findOne(item.getActivityId());
				
				/*评分时间检验*/
		        	Date now = new Date();
			        Calendar cn =  Calendar.getInstance();
			        cn.setTime(now);
		        	Date eventDate = activity.getDate();
		            Calendar eventOn =  Calendar.getInstance();
		            eventOn.setTime(eventDate);
		        	Date eventEnd = activity.getEventEnd();
		            Calendar eventOff =  Calendar.getInstance();
		            eventOff.setTime(eventEnd);
		            
		            //未开始
		            if (cn.before(eventOn))
		            {
		            	activity.setGradetimeId(0L);
		            	tdActivityService.save(activity);
		            }
		            //可评
		            else if (cn.after(eventOn)&&cn.before(eventOff))
		            {
		            	activity.setGradetimeId(1L);;
		            	tdActivityService.save(activity);
		            }
		            //过期
		            else if (cn.after(eventOff))
		            {
		            	activity.setGradetimeId(2L);
		            	tdActivityService.save(activity);
		            }
				/* 评分时间检查 end*/
		            
				if (null != activity) {
					activities_list.add(activity);
				}
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
	public String goGrade(Long activityId, Long enterpriseId , HttpServletRequest req, ModelMap map) {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return "/client/login";
		}
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdEnterpriseGrade> grade_list = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(expert.getId(),
				activityId);
		TdActivity activity = tdActivityService.findOne(activityId);
		/*评分时间检验*/
    	Date now = new Date();
        Calendar cn =  Calendar.getInstance();
        cn.setTime(now);
    	Date eventDate = activity.getDate();
        Calendar eventOn =  Calendar.getInstance();
        eventOn.setTime(eventDate);
    	Date eventEnd = activity.getEventEnd();
        Calendar eventOff =  Calendar.getInstance();
        eventOff.setTime(eventEnd);
        
        //未开始
        if (cn.before(eventOn))
        {
        	activity.setGradetimeId(0L);
        	tdActivityService.save(activity);
        	map.addAttribute("type", "check");
        	map.addAttribute("msg","活动未开始");
        }
        //可评
        else if (cn.after(eventOn)&&cn.before(eventOff))
        {
        	activity.setGradetimeId(1L);;
        	tdActivityService.save(activity);
        }
        //过期
        else if (cn.after(eventOff))
        {
        	activity.setGradetimeId(2L);
        	tdActivityService.save(activity);
        	map.addAttribute("type", "check");
        	map.addAttribute("msg","活动已结束");
        }
	/** 评分时间检查 end*/
		
		//评分改为一个一个的评 zhangji
		List<TdEnterpriseGrade> expertGradeList = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(expert.getId(), activityId);
		
		//评分跳转到指定项目
        if (null != enterpriseId)
        {
        	for (TdEnterpriseGrade expertGrade : expertGradeList)
        	{
        		expertGrade.setGradeAble(false);
        		tdEnterpriseGradeService.save(expertGrade);
        	}
        	String number = tdEnterpriseService.findOne(enterpriseId).getNumber();
        	TdEnterpriseGrade enterpriseGrade = tdEnterpriseGradeService.findByExpertIdAndActivityIdAndNumber(expert.getId(), activityId, number);
        	enterpriseGrade.setGradeAble(true);
        	tdEnterpriseGradeService.save(enterpriseGrade);
        }
		
        //初始化可评分状态：第一个可评
		if (expertGradeList.size() > 0)
		{
			int index = 0;
			int size = expertGradeList.size(); 	//要操作的对象标识
			for (TdEnterpriseGrade item : expertGradeList)
			{
				if ( null != item.getGradeAble() && item.getGradeAble() == false || null == item.getGradeAble() )
				{
					index++;
				}
				if (index == size)
				{
					expertGradeList.get(0).setGradeAble(true);
					tdEnterpriseGradeService.save(expertGradeList.get(0));
				}
				
			}
		}
        
        
		map.addAttribute("grade_list", grade_list);
		map.addAttribute("activity", activity);
		map.addAttribute("activityId", activityId);
		map.addAttribute("expertId", expert.getId());
		map.addAttribute("expert", expert);
		return "/client/expert_grade";
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
		
		//同步【活动-企业】中间表状态
		TdActivityEnterprise activityEnterprise = tdActivityEnterpriseService.findByActivityIdAndEnterpriseId(activityId, enterprise.getId());
		activityEnterprise.setIsGrade(true);
		tdActivityEnterpriseService.save(activityEnterprise);
		
		//评分改为一个一个的评 zhangji
		List<TdEnterpriseGrade> expertGradeList = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(expert.getId(), activityId);
		if (expertGradeList.size() > 0)
		{
			int index = 0;
			int i = 0; 	//要操作的对象标识
			for (TdEnterpriseGrade item : expertGradeList)
			{
				if (i==1)
				{
					item.setGradeAble(true);
					tdEnterpriseGradeService.save(item);
					break;
				}
				if (item.getId() == theGrade.getId())
				{
					if(index == (expertGradeList.size()-1))
					{
						res.put("msg", "评分完毕，谢谢！");
						item.setGradeAble(false);
						tdEnterpriseGradeService.save(item);
					}
					i = 1;
					item.setGradeAble(false);
					tdEnterpriseGradeService.save(item);
					index++;
					
				}
				else{
					item.setGradeAble(false);
					tdEnterpriseGradeService.save(item);
					index++;
				}
				
				
			}
		}

		
		
		
//		TdExpertCoachEnterprise expertCoachEnterprise = tdExpertCoachEnterpriseService.findByEnterpriseId(enterprise.getId());
//		if(null == expertCoachEnterprise){
//			expertCoachEnterprise = new TdExpertCoachEnterprise();
//		}
//		expertCoachEnterprise.setIsGrade(true);
//		tdExpertCoachEnterpriseService.save(expertCoachEnterprise);
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
		List<TdActivityInvest> enterprise_list = tdActivityInvestService
				.findByExpertId(expert.getId());
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
	public String coachSave(HttpServletRequest req, String content, Long enterpriseId , Long activityId) {
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return "/client/login";
		}
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		TdCoachContent coach = new TdCoachContent();
		coach.setCoachDate(new Date());
		coach.setContent(content);
		if (null != enterpriseId)
		{
			coach.setEnterpriseId(enterpriseId);
			coach.setExpertId(expert.getId());
			tdCoachContentService.save(coach);
			return "redirect:/expert/coach/" + enterpriseId;
		}
		else if (null != activityId)
		{
			coach.setActivityId(activityId);
			coach.setExpertId(expert.getId());
			tdCoachContentService.save(coach);
			return "redirect:/expert/coach/log/" + activityId;
		}
		return "redirect:/expert/enterprise/list";
	}
	
	@RequestMapping(value = "/lyfd")
	public String lyfd(HttpServletRequest req,ModelMap map){
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if (null == expertUsername) {
			return "/client/login";
		}
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdExpertCoachEnterprise> grade_false_list = tdExpertCoachEnterpriseService.findByExpertIdAndIsGradeIsFalse(expert.getId());
		List<TdExpertCoachEnterprise> grade_true_list = tdExpertCoachEnterpriseService.findByExpertIdAndIsGradeIsTrue(expert.getId());
		map.addAttribute("grade_false_list", grade_false_list);
		map.addAttribute("grade_true_list", grade_true_list);
		return "/client/lydf";
	}
	
	@RequestMapping(value = "/coach/log/{activityId}")
	public String coachLog(@PathVariable Long activityId,HttpServletRequest req,ModelMap map){
		String expertUsername = (String) req.getSession().getAttribute("expertUsername");
		if(null == expertUsername){
			return "/client/login";
		}
		TdExpert expert = tdExpertService.findbyUsername(expertUsername);
		List<TdCoachContent> content_list = tdCoachContentService
				.findByExpertIdAndActivityIdOrderByCoachDateAsc(expert.getId(), activityId);
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
		map.addAttribute("expert", expert);
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
	                            Long isModule,
	                            HttpServletResponse resp,
	                            HttpServletRequest req){

	        	exportUrl = SiteMagConstant.imagePath;
	    
		if (null != exportUrl) {
					List<TdEnterpriseGrade> enterpriseGradeList = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(expertId, activityId);
					TdExpert expert = tdExpertService.findOne(expertId);
					TdActivity activity = tdActivityService.findOne(activityId);
					List<TdEnterpriseGrade> grade_list = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderBySordIdAsc(expert.getId(),
							activityId);
					
					map.addAttribute("type", "check");
					map.addAttribute("grade_list", grade_list);
					map.addAttribute("activityId", activityId);
					map.addAttribute("activity", activity);
					map.addAttribute("expertId", expertId);
					
			
					if (null ==enterpriseGradeList || enterpriseGradeList.size()==0)
					{
						Long numwarn = 1L;
						map.addAttribute("numwarn", numwarn);
						return "/client/project_grade";
					}
		int columnSize = enterpriseGradeList.size();  //列数
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
	      sheet.setColumnWidth((short) 0 , 38*256);
	      sheet.setDefaultColumnWidth(5*256);
	      sheet.setDefaultRowHeightInPoints(20);  
	      for(int i=0;i<columnSize;i++)
	      {
	    	  sheet.setColumnWidth((short) i+1 , 5*256);
	      }
	      
	      
	      // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	      HSSFRow row0 = sheet.createRow((int) 0);  
	      
	      sheet.addMergedRegion(new Region((short) 0 , (short) 0 , (short) 0 , (short) columnSize));     //标题1行
	      sheet.addMergedRegion(new Region((short) 1 , (short) 0 , (short) 2 , (short) columnSize));     //标题2行
	      sheet.addMergedRegion(new Region((short) 21 , (short) 0 , (short) 23 , (short) columnSize));   //专家签字
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
	      style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
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
	      font.setFontHeightInPoints((short) 10);
	      style1.setFont(font);
	      
	      
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
	      
	      //签名
	      HSSFCellStyle bottom = wb.createCellStyle();  
	      bottom.setBorderBottom(HSSFCellStyle.BORDER_NONE);    //设置边框样式
	      bottom.setBorderRight(HSSFCellStyle.BORDER_NONE);
	      bottom.setBorderLeft(HSSFCellStyle.BORDER_NONE);  
	      bottom.setBorderTop(HSSFCellStyle.BORDER_NONE);  
	      bottom.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);//垂直下
	      bottom.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中格式
	      
	      
	      HSSFCell cell = row0.createCell((short) 0);  
	      cell.setCellValue("创投每周行路演评分表");  
	      cell.setCellStyle(title);
	      
	      HSSFRow row1 =sheet.createRow((int) 1);
	      cell = row1.createCell((short) 0);  
	      if (null == isModule)
	      {
	    	  cell.setCellValue(tdActivityService.findOne(enterpriseGradeList.get(0).getActivityId()).getTitle()+"（评委："+expert.getName()+"）");  
	      }
	      else{
	    	  cell.setCellValue(tdActivityService.findOne(enterpriseGradeList.get(0).getActivityId()).getTitle());  
	      }
	      cell.setCellStyle(title3);
	      
	      HSSFRow row3 =sheet.createRow((int) 3);
	      row3.setHeight((short) (20 * 20));  
	      cell = row3.createCell((short) 0);  
	      cell.setCellValue("序号");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row4 =sheet.createRow((int) 4);
	      row4.setHeight((short) (20 * 20));  
	      cell = row4.createCell((short) 0);  
	      cell.setCellValue("项目编号");  
	      cell.setCellStyle(style);

	      HSSFRow row5 =sheet.createRow((int) 5);
	      row5.setHeight((short) (20 * 20));  
	      cell = row5.createCell((short) 0);  
	      cell.setCellValue("核心竞争力(小计)");  
	      cell.setCellStyle(style1);
	      
	      HSSFRow row6 =sheet.createRow((int) 6);
	      row6.setHeight((short) (20 * 20));  
	      cell = row6.createCell((short) 0);  
	      cell.setCellValue("技术、产品、服务、商业模式领先性、创新性");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row7 =sheet.createRow((int) 7);
	      row7.setHeight((short) (20 * 20));  
	      cell = row7.createCell((short) 0);  
	      cell.setCellValue("专利、商标、著作登记、双软、双高证书");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row8 =sheet.createRow((int) 8);
	      row8.setHeight((short) (20 * 20));  
	      cell = row8.createCell((short) 0);  
	      cell.setCellValue("与竞争对手相比的优势程度");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row12 =sheet.createRow((int) 9);
	      row12.setHeight((short) (20 * 20));  
	      cell = row12.createCell((short) 0);  
	      cell.setCellValue("团队能力(小计)");  
	      cell.setCellStyle(style1);
	      
	      HSSFRow row13 =sheet.createRow((int) 10);
	      row13.setHeight((short) (20 * 20));  
	      cell = row13.createCell((short) 0);  
	      cell.setCellValue("核心领头人的专业能力及资源");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row14 =sheet.createRow((int) 11);
	      row14.setHeight((short) (20 * 20));  
	      cell = row14.createCell((short) 0);  
	      cell.setCellValue("团队成员的专业能力及分工是否合理");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row9 =sheet.createRow((int) 12);
	      row9.setHeight((short) (20 * 20));  
	      cell = row9.createCell((short) 0);  
	      cell.setCellValue("市场潜力(小计)");  
	      cell.setCellStyle(style1);
	      
	      HSSFRow row10 =sheet.createRow((int) 13);
	      row10.setHeight((short) (20 * 20));  
	      cell = row10.createCell((short) 0);  
	      cell.setCellValue("潜在市场规模大小及已有的市场份额");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row11 =sheet.createRow((int) 14);
	      row11.setHeight((short) (20 * 20));  
	      cell = row11.createCell((short) 0);  
	      cell.setCellValue("市场开发价值与开发成本");  
	      cell.setCellStyle(style);
	      

	      
	      HSSFRow row15 =sheet.createRow((int) 15);
	      row15.setHeight((short) (20 * 20));  
	      cell = row15.createCell((short) 0);  
	      cell.setCellValue("投资价值(小计)");  
	      cell.setCellStyle(style1);
	      
	      HSSFRow row16 =sheet.createRow((int) 16);
	      row16.setHeight((short) (20 * 20));  
	      cell = row16.createCell((short) 0);  
	      cell.setCellValue("行业环境及现有基础条件能否支撑");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row17 =sheet.createRow((int) 17);
	      row17.setHeight((short) (20 * 20));  
	      cell = row17.createCell((short) 0);  
	      cell.setCellValue("财务状况及融资条件");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row18 =sheet.createRow((int) 18);
	      row18.setHeight((short) (20 * 20));  
	      cell = row18.createCell((short) 0);  
	      cell.setCellValue("现场表现力(小计)");  
	      cell.setCellStyle(style1);
	      
	      HSSFRow row19 =sheet.createRow((int) 19);
	      row19.setHeight((short) (20 * 20));  
	      cell = row19.createCell((short) 0);  
	      cell.setCellValue("路演方式的创新程度及现场感染力");  
	      cell.setCellStyle(style);
	      
	      HSSFRow row20 =sheet.createRow((int) 20);
	      row20.setHeight((short) (20 * 20));  
	      cell = row20.createCell((short) 0);  
	      cell.setCellValue("合计");  
	      cell.setCellStyle(style1);
	      
	      HSSFRow row21 =sheet.createRow((int) 21);
	      row21.setHeight((short) (20 * 20));  
	      cell = row21.createCell((short) 0);  
	      cell.setCellValue("专家签名：                         ");  
	      cell.setCellStyle(bottom);
	      
	      HSSFRow[] array = new HSSFRow[]{row0,row0,row1,row3,row4,row5,row6,row7,row8,row9,row10,row11,row12,row13,row14,row15,row16,row17,row18,row19,row20,row21};
		
		
		
	      
				
			if (null != exportUrl) {
				if (ImportData(enterpriseGradeList, array, cell, sheet,style , isModule)) {
					download(wb, exportUrl, resp);
				}         
			}  
				}
		return "/client/project_grade";
	}

	/**
		 * @author lc
		 * @注释：将page中的订单数据存入excel表格中
		 */
	 @SuppressWarnings("deprecation")
		public boolean ImportData(List<TdEnterpriseGrade> enterpriseGradeList, HSSFRow[] array, HSSFCell cell, HSSFSheet sheet ,HSSFCellStyle style , Long isModule){
		 	
	        	for (int i = 0; i < enterpriseGradeList.size(); i++)  
	            {  
	        	 				
	                
	        		
	                TdEnterpriseGrade tdEnterpriseGrade = enterpriseGradeList.get(i);  
	                //获取用户信息
//	                TdUser tdUser = tdUserService.findByUsername(tdOrder.getUsername());
	                // 第四步，创建单元格，并设置值  
	                cell = array[3].createCell((short) i+1);
	                if(null != tdEnterpriseGrade.getNumber())
	                {
	                	 cell.setCellValue(i+1);
	                }
	                cell.setCellStyle(style); 
	                
	                cell = array[4].createCell((short) i+1);
	                if(null != tdEnterpriseGrade.getNumber())
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getNumber());
	                }
	                cell.setCellStyle(style); 
	                
	                cell = array[5].createCell((short) i+1);
	                if(null != tdEnterpriseGrade.getTotalTechnology()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getTotalTechnology());
	                }
	                cell.setCellStyle(style); 
	                
	               
	                cell = array[6].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getOneTechnology()&&null == isModule)
	                {
	                	cell.setCellValue(tdEnterpriseGrade.getOneTechnology());
	                }
	                cell.setCellStyle(style); 
	                
	               
	                cell = array[7].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getTwoTechnology()&&null == isModule)
	                {
	                	cell.setCellValue(tdEnterpriseGrade.getTwoTechnology());
	                }
	                cell.setCellStyle(style); 
	                
	               
	                cell = array[8].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getThreeTechnology()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getThreeTechnology());
	                }
	                cell.setCellStyle(style); 
	              
	                cell = array[9].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getTotalGroup()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getTotalGroup());
	                }
	                cell.setCellStyle(style); 
	                
	                cell = array[10].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getOneGroup()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getOneGroup());
	                }
	                cell.setCellStyle(style); 
	                
	                cell = array[11].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getTwoGroup()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getTwoGroup());
	                }
	                cell.setCellStyle(style); 
	               
	                cell = array[12].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getTotalFeasibility()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getTotalFeasibility());
	                }
	                cell.setCellStyle(style); 
	                
	              
	                cell = array[13].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getOneFeasibility()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getOneFeasibility());
	                }
	                cell.setCellStyle(style); 
	                
	              
	                cell = array[14].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getTwoFeasibility()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getTwoFeasibility());
	                }
	                cell.setCellStyle(style); 

	                
	                cell = array[15].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getTotalMarketValue()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getTotalMarketValue());
	                }
	                cell.setCellStyle(style); 
	                
	                cell = array[16].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getOneMarketValue()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getOneMarketValue());
	                }
	                cell.setCellStyle(style); 
	                
	                cell = array[17].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getTwoMarketValue()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getTwoMarketValue());
	                }
	                cell.setCellStyle(style); 
	                
	             
	                cell = array[18].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getTotalExpression()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getTotalExpression());
	                }
	                cell.setCellStyle(style); 
	                
	                cell = array[19].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getOneExpression()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getOneExpression());
	                }
	                cell.setCellStyle(style); 
	                
	                cell = array[20].createCell((short) i+1);
	                if (null != tdEnterpriseGrade.getTotalPoint()&&null == isModule)
	                {
	                	 cell.setCellValue(tdEnterpriseGrade.getTotalPoint());
	                }
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
		          FileOutputStream fout = new FileOutputStream(exportUrl+"cqkjxjr03.xls");  
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
					File file = new File(exportUrl + "cqkjxjr03.xls");
	              
	          if (file.exists())
	              {
	                try {
	                      resp.reset();
	                      resp.setHeader("Content-Disposition", "attachment; filename="
	                              + "cqkjxjr03.xls");
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
