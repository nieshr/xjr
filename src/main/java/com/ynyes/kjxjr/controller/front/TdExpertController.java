package com.ynyes.kjxjr.controller.front;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
import com.ynyes.kjxjr.entity.TdExpert;
import com.ynyes.kjxjr.service.TdActivityEnterpriseService;
import com.ynyes.kjxjr.service.TdActivityExpertService;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdActivityTypeService;
import com.ynyes.kjxjr.service.TdExpertService;
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
			activities_list.add(activity);
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
		List<TdActivityType> all_type = tdActivityTypeService.findAll();
		TdActivity activity = tdActivityService.findOne(id);
		List<TdActivityEnterprise> enterprise_resources = tdActivityEnterpriseService
				.findByActivityId(activity.getId());
		List<TdActivityExpert> experts = tdActivityExpertService.findByActivityId(activity.getId());
		map.addAttribute("experts", experts);
		map.addAttribute("enterprise_resources", enterprise_resources);
		map.addAttribute("resources", activity.getDownload());
		map.addAttribute("all_type", all_type);
		map.addAttribute("activity", activity);
		return "/client/expert_activity_detail";
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
}
