package com.ynyes.kjxjr.controller.front;

import java.io.File;
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
		List<TdEnterpriseGrade> grade_list = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(expert.getId(),
				activityId);
		map.addAttribute("grade_list", grade_list);
		map.addAttribute("activityId", activityId);
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
		theGrade.setTwoExpression(grade.getTwoExpression());
		theGrade.setThreeExpression(grade.getThreeExpression());
		theGrade.setFourExpression(grade.getFourExpression());
		theGrade.setTotalFeasibility(grade.getTotalFeasibility());
		theGrade.setOneFeasibility(grade.getOneFeasibility());
		theGrade.setTwoFeasibility(grade.getTwoFeasibility());
		theGrade.setThreeFeasibility(grade.getThreeFeasibility());
		theGrade.setFourFeasibility(grade.getFourExpression());
		theGrade.setTotalMarketValue(grade.getTotalMarketValue());
		theGrade.setOneMarketValue(grade.getOneMarketValue());
		theGrade.setTwoMarketValue(grade.getTwoMarketValue());
		theGrade.setThreeMarketValue(grade.getThreeMarketValue());
		theGrade.setFourMarketValue(grade.getFourMarketValue());
		theGrade.setTotalTechnology(grade.getTotalTechnology());
		theGrade.setOneTechnology(grade.getOneTechnology());
		theGrade.setTwoTechnology(grade.getTwoTechnology());
		theGrade.setThreeTechnology(grade.getThreeTechnology());
		theGrade.setFourTechnology(grade.getFourTechnology());
		theGrade.setFiveTechnology(grade.getFiveTechnology());
//		theGrade.setTotalPoint(grade.getTotalPoint());   zhangji 注释掉
		theGrade.setOneGroup(grade.getOneGroup());
		theGrade.setTwoGroup(grade.getTwoGroup());
		theGrade.setThreeGroup(grade.getThreeGroup());
		theGrade.setTotalGroup(grade.getTotalGroup()); //zhangji
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
		List<TdEnterpriseGrade> grade_list = tdEnterpriseGradeService.findByExpertIdAndActivityIdOrderByNumberAsc(expert.getId(),
				activityId);
		map.addAttribute("grade_list", grade_list);
		map.addAttribute("activityId", activityId);
		return "/client/project_grade";
	}
}
