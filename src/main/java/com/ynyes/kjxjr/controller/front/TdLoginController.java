package com.ynyes.kjxjr.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.VerifServlet;

/**
 * 登录
 *
 */
@Controller
public class TdLoginController {
	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdEnterpriseService tdEnterpriseService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");

		String referer = req.getHeader("referer");

		// 基本信息
		tdCommonService.setHeader(map, req);

		if (null == username) {
			return "/client/login";
		}

		if (null == referer) {
			referer = "/";
		}
		/**
		 * @author lc
		 * @注释：同盟店登录
		 */
		String diysiteUsername = (String) req.getSession().getAttribute("diysiteUsername");

		TdUser tdUser = tdUserService.findByUsername(diysiteUsername);
		if (null != tdUser) {
			if (null != tdUser.getRoleId() && tdUser.getRoleId().equals(2L)) {
				return "redirect:/user/diysite/order/list/0";
			}
		}

		return "redirect:" + referer;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String username, String password, String alipayuser_id, String type, String code,
			Boolean isSave, HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();
		String codeBack = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");

		res.put("code", 1);

		/**
		 * 按账号查找登录验证 密码验证 修改最后登录时间
		 * 
		 * @author libiao
		 */
		TdUser user = tdUserService.findByUsername(username);

		if (null != user) {
			if (!user.getPassword().equals(password)) {
				res.put("msg", "密码错误");
				res.put("username", username);
				return res;
			}
			if (codeBack == null || !codeBack.equalsIgnoreCase(code)) {
				res.put("msg", "验证码错误");
				return res;
			}
			user.setLastLoginTime(new Date());
			user = tdUserService.save(user);

			res.put("code", 0);

			/**
			 * @author lichong
			 * @注释：判断用户类型
			 */
			// if(null != user.getRoleId() && user.getRoleId().equals(2L)){
			// res.put("role", 2);
			// request.getSession().setAttribute("diysiteUsername",
			// user.getUsername());
			// return res;
			// }
			// request.getSession().setAttribute("username",
			// user.getUsername());
			// request.getSession().setAttribute("usermobile",
			// user.getMobile());
			// return res;

			System.err.println(user);
			Integer roleId = user.getRoleId().intValue();
			request.getSession().setMaxInactiveInterval(60 * 60 * 2);
			switch (roleId) {
			// 企业项目
			case 1:
				res.put("role", 1);
				request.getSession().setAttribute("enterpriseUsername", user.getUsername());
				request.getSession().setAttribute("enterpriseUsermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				Long statusId = tdEnterpriseService.findbyUsername(username).getStatusId();
				res.put("statusId", statusId);
				break;
			// 区县管理
			case 2:
				res.put("role", 2);
				request.getSession().setAttribute("regionUsername", user.getUsername());
				request.getSession().setAttribute("regionUsermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				break;
			// 专家
			case 3:
				res.put("role", 3);
				request.getSession().setAttribute("expertUsername", user.getUsername());
				request.getSession().setAttribute("expertUsermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				break;
			// 活动管理
			case 4:
				res.put("role", 4);
				request.getSession().setAttribute("activityUsername", user.getUsername());
				request.getSession().setAttribute("activityUsermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				break;
			default:
				res.put("role", 0);
				request.getSession().setAttribute("username", user.getUsername());
				request.getSession().setAttribute("usermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				break;
			}

			return res;
		}
		/**
		 * 如果账号验证未通过，再进行手机登录验证 密码验证 修改最后登录时间
		 * 
		 * @author libiao
		 */
		user = tdUserService.findByMobileAndIsEnabled(username);
		if (null != user) {
			if (!user.getPassword().equals(password)) {
				res.put("msg", "密码错误");
				return res;
			}
			user.setLastLoginTime(new Date());

			user = tdUserService.save(user);

			res.put("code", 0);

			/**
			 * @author lichong
			 * @注释：判断用户类型
			 */
			// if (user.getRoleId() == 2L) {
			// res.put("role", 2);
			// request.getSession().setAttribute("diysiteUsername",
			// user.getUsername());
			// return res;
			// }
			// request.getSession().setAttribute("username",
			// user.getUsername());
			// request.getSession().setAttribute("usermobile",
			// user.getMobile());
			// return res;

			Integer roleId = user.getRoleId().intValue();
			switch (roleId) {
			case 1:
				res.put("role", 1);
				request.getSession().setAttribute("enterpriseUsername", user.getUsername());
				request.getSession().setAttribute("enterpriseUsermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				res.put("statusId", tdEnterpriseService.findbyUsermobile(username).getStatusId());
				break;
			case 2:
				res.put("role", 2);
				request.getSession().setAttribute("regionUsername", user.getUsername());
				request.getSession().setAttribute("regionUsermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				break;
			case 3:
				res.put("role", 3);
				request.getSession().setAttribute("expertUsername", user.getUsername());
				request.getSession().setAttribute("expertUsermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				break;
			case 4:
				res.put("role", 4);
				request.getSession().setAttribute("activityUsername", user.getUsername());
				request.getSession().setAttribute("activityUsermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				break;
			default:
				res.put("role", 0);
				request.getSession().setAttribute("username", user.getUsername());
				request.getSession().setAttribute("usermobile", user.getMobile());
				break;
			}
			return res;
		} else { // 账号-手机都未通过验证，则用户不存在
			res.put("msg", "不存在该用户");
			return res;
		}

	}

	/**
	 * @author Zhangji 验证码检验
	 */
	@RequestMapping(value = "/login/check/{type}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> validateForm(@PathVariable String type, String param, HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();

		res.put("status", "n");

		if (null == type) {
			res.put("info", "参数错误");
			return res;
		}

		if (type.equalsIgnoreCase("code")) {
			if (null == param || param.isEmpty()) {
				res.put("info", "请输入验证码");
				return res;
			}

			TdUser user = tdUserService.findByUsername(param);
			String codeBack = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
			if (!codeBack.equalsIgnoreCase(param)) {
				res.put("info", "验证码错误");
				return res;
			}
		}

		res.put("status", "y");

		return res;
	}

	/**
	 * @author lc
	 * @return
	 * @注释：密码找回
	 */
	@RequestMapping(value = "/login/password_retrieve", method = RequestMethod.GET)
	public String Retrieve(HttpServletRequest req, ModelMap map) {
		tdCommonService.setHeader(map, req);
		return "/client/user_retrieve_step1";
	}

	@RequestMapping(value = "/login/password_retrieve", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> Check(String username, String code, HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);

		if (username.isEmpty()) {
			res.put("msg", "用户名不能为空");
		}

		String codeBack = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
		if (!codeBack.equalsIgnoreCase(code)) {
			res.put("msg", "验证码错误");
			return res;
		}

		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		if (null != user) {

			request.getSession().setAttribute("retrieve_username", user.getUsername());

			res.put("code", 0);
		} else {
			res.put("msg", "用户不存在");
		}

		return res;
	}

	@RequestMapping(value = "/login/retrieve_step2", method = RequestMethod.GET)
	public String Step2(Integer errCode, HttpServletRequest req, ModelMap map) {
		tdCommonService.setHeader(map, req);
		String username = (String) req.getSession().getAttribute("retrieve_username");
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		if (null != errCode) {
			if (errCode.equals(1)) {
				map.addAttribute("error", "验证码错误");
			}

			map.addAttribute("errCode", errCode);
		}

		map.put("user", user);

		return "/client/user_retrieve_step2";
	}

	@RequestMapping(value = "/login/retrieve_step2", method = RequestMethod.POST)
	public String Step2(String smsCode, HttpServletRequest req, ModelMap map) {
		if (null == smsCode) {
			return "redirect:/login/retrieve_step2?errCode=4";
		}
		String smsCodeSave = (String) req.getSession().getAttribute("SMSCODE");
		if (!smsCodeSave.equalsIgnoreCase(smsCode)) {
			return "redirect:/login/retrieve_step2?errCode=4";
		}
		String username = (String) req.getSession().getAttribute("retrieve_username");
		map.put("retrieve_username", username);
		tdCommonService.setHeader(map, req);

		return "/client/user_retrieve_step3";
	}

	@RequestMapping(value = "/login/retrieve_step3", method = RequestMethod.POST)
	public String Step3(String password, HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("retrieve_username");
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		if (null != password) {
			user.setPassword(password);
			tdUserService.save(user);
			tdCommonService.setHeader(map, req);
			req.getSession().setAttribute("username", user.getUsername());
			req.getSession().setAttribute("usermobile", user.getMobile());
			return "/client/user_retrieve_ok";
		}
		return "/client/error_404";
	}

	/**
	 * @author lc
	 * @注释：登录手机验证
	 */
	@RequestMapping(value = "/login/mobile_accredit", method = RequestMethod.POST)
	public String mobileVerification(String username, String mobile, String type, String typeId,
			HttpServletRequest request, ModelMap map) {
		if (null == username) {
			return "client/error_404";
		}
		if (null == mobile) {
			return "client/error_404";
		}
		TdUser user = tdUserService.addNewUser(username, "123456", null, null, null);
		if (null != user) {
			// if("qq".equals(type)){
			// //QQ登录新建账号
			// user.setQqUserId(typeId);
			// }else{
			// //支付宝登录新建账号
			// user.setAlipayUserId(typeId);
			// }
			user.setMobile(mobile);
			user.setLastLoginTime(new Date());
			tdUserService.save(user);
			request.getSession().setAttribute("username", user.getUsername());
			request.getSession().setAttribute("usermobile", user.getMobile());
			return "redirect:/";
		}
		return "client/error_404";
	}

	@RequestMapping("/logout")
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public void verify(HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		VerifServlet randomValidateCode = new VerifServlet();
		randomValidateCode.getRandcode(request, response);
	}
}
