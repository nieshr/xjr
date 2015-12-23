package com.ynyes.kjxjr.controller.front;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdEnterpriseService;
import com.ynyes.kjxjr.service.TdSettingService;
import com.ynyes.kjxjr.service.TdUserPointService;
import com.ynyes.kjxjr.service.TdUserService;
import com.ynyes.kjxjr.util.VerifServlet;

/**
 * 注册处理
 * 
 */
@Controller
public class TdRegController {
	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdUserPointService tdUserPointService;

	@Autowired
	private TdSettingService tdSettingService;

	@Autowired
	private TdCommonService tdCommonService;
	
	@Autowired
	private TdEnterpriseService tdEnterpriseService;

	@RequestMapping(value = "/reg/check/{type}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> validateForm(@PathVariable String type, String param,HttpServletRequest req) {
		Map<String, String> res = new HashMap<String, String>();

		res.put("status", "n");

		if (null == type) {
			res.put("info", "参数错误");
			return res;
		}

		if (type.equalsIgnoreCase("username")) {
			if (null == param || param.isEmpty()) {
				res.put("info", "用户名不能为空");
				return res;
			}

			TdUser user = tdUserService.findByUsername(param);

			if (null != user) {
				res.put("info", "该用户名已被注册");
				return res;
			}
		}
		
		/**
		 * ajax实时验证验证码，判断用户验证码是否输入正确
		 * @author dengxiao
		 */
		if(type.equalsIgnoreCase("smsCode")){
			String smsCode = (String) req.getSession().getAttribute("SMSCODE");
			if(null == smsCode){
				res.put("info", "请点击\"发送验证码\"");
				return res;
			}
			if (null == param || param.isEmpty()) {
				res.put("info", "用户名不能为空");
				return res;
			}
			if(!smsCode.equalsIgnoreCase(param)){
				res.put("info", "验证码输入错误！");
				return res;
			}
		}

		
		/**
		 * ajax实时验证 手机号查找用户 判断手机号是已否注册
		 * 
		 * @author libiao
		 */
		if (type.equalsIgnoreCase("mobile")) {
			if (null == param || param.isEmpty()) {
				res.put("info", "用户名不能为空");
				return res;
			}

			TdUser user = tdUserService.findByMobile(param);

			if (null != user) {
				res.put("info", "该手机已经注册");
				return res;
			}
		}

		res.put("status", "y");

		return res;
	}

	@RequestMapping("/reg")
	public String reg(Integer errCode, Integer shareId, String name, Long error, String carCode, HttpServletRequest request,
			ModelMap map) {
		String username = (String) request.getSession().getAttribute("username");

		if (null != shareId) {
			map.addAttribute("share_id", shareId);
		}
		// 基本信息
		tdCommonService.setHeader(map, request);

		if (null == username) {
			if (null != errCode) {
				if (errCode.equals(1)) {
					map.addAttribute("error", "验证码错误");
				}

				map.addAttribute("errCode", errCode);
			}
			map.addAttribute("error",error);
			
			map.addAttribute("username", name);
			map.addAttribute("carCode", carCode);
			return "/client/reg";

		}
		return "redirect:/";
	}

	@RequestMapping(value = "/logutil")
	public String LogUtils() {
		return "/logutil";
	}

	/**
	 * 
	 * 注册用户保存到数据库<BR>
	 * 方法名：saveUser<BR>
	 * 时间：2015年2月2日-下午1:44:45 <BR>
	 * 
	 * @param user
	 * @param name
	 * @param mobile
	 * @param password
	 * @param newpassword
	 * @return String<BR>
	 * @param [参数1]
	 *            [参数1说明]
	 * @param [参数2]
	 *            [参数2说明]
	 * @exception <BR>
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String reg(String username, String mobile, String password, String email, String smsCode, String code,
			String carCode, Long shareId, HttpServletRequest request) {
		
		if (null == username ||username.equals("")
				|| null == mobile || mobile.equals("")
				|| null == password || password.equals("")
				|| null == email || email.equals("")
				)
		{
			Long error = 1L;
			return "redirect:/reg?error="+error;
		}
		
		TdUser user = new TdUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setStatusId(1L);
		user.setRegisterTime(new Date());
		user.setRoleId(1L);
		tdUserService.save(user);
		
		TdEnterprise enterprise = new TdEnterprise();
		enterprise.setUsername(username);
		enterprise.setPassword(password);
		enterprise.setUseremail(email);
		enterprise.setUsermobile(mobile);
		tdEnterpriseService.save(enterprise);
		request.getSession().setAttribute("enterpriseUsername", username);
		request.getSession().setAttribute("username", username);
		
		return "redirect:/enterprise/info";
//		if (null == smsCodeSave) {
//			if (null == shareId) {
//				return "redirect:/reg?name= " + username + "&carCode=" + carCode;
//			} else {
//				return "redirect:/reg?shareId=" + shareId + "&name= " + username + "&carCode=" + carCode;
//			}
//		}
//
//		if (!codeBack.equalsIgnoreCase(code)) {
//			if (null == shareId) {
//				return "redirect:/reg?errCode=1&name= " + username + "&carCode=" + carCode;
//			} else {
//				return "redirect:/reg?errCode=1&shareId=" + shareId + "&name= " + username + "&carCode=" + carCode;
//			}
//		}
//
//		if (!smsCodeSave.equalsIgnoreCase(smsCode)) {
//			if (null == shareId) {
//				return "redirect:/reg?errCode=4&name= " + username + "&mobile=" + mobile;
//			} else {
//				return "redirect:/reg?errCode=4&shareId=" + shareId + "&name= " + username + "&carCode=" + carCode;
//			}
//		}
//
//		TdUser user = tdUserService.addNewUser(username, password, mobile, email, carCode);
//
//		if (null == user) {
//			if (null == shareId) {
//				return "redirect:/reg?errCode=3";
//			} else {
//				return "redirect:/reg?errCode=3&shareId=" + shareId;
//			}
//		}
//
//		user = tdUserService.save(user);
//
//		// 奖励分享用户
//		if (null != shareId) {
//			TdUser sharedUser = tdUserService.findOne(shareId);
//
//			if (null != sharedUser && sharedUser.getRoleId().equals(0L)) {
//				TdSetting setting = tdSettingService.findTopBy();
//				TdUserPoint userPoint = new TdUserPoint();
//
//				if (null != setting) {
//					userPoint.setPoint(setting.getRegisterSharePoints());
//				} else {
//					userPoint.setPoint(0L);
//				}
//
//				if (null != sharedUser.getTotalPoints()) {
//					userPoint.setTotalPoint(sharedUser.getTotalPoints() + userPoint.getPoint());
//				} else {
//					userPoint.setTotalPoint(userPoint.getPoint());
//				}
//
//				userPoint.setUsername(sharedUser.getUsername());
//				userPoint.setDetail("用户分享网站成功奖励");
//
//				userPoint = tdUserPointService.save(userPoint);
//
//				sharedUser.setTotalPoints(userPoint.getTotalPoint()); // 积分
//				tdUserService.save(sharedUser);
//			}
//		}
//
//		request.getSession().setAttribute("username", username);
//
//		String referer = (String) request.getAttribute("referer");
//
//		if (null != request.getAttribute("referer")) {
//			return "redirect:" + referer;
//		}
//
//		if (null == shareId) {
//			return "redirect:/user";
//		}
//
//		return "redirect:/user?shareId=" + shareId;
	}

	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public void verify(HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		VerifServlet randomValidateCode = new VerifServlet();
		randomValidateCode.getRandcode(request, response);
		// QRCodeUtils qr = new QRCodeUtils();
		// qr.getQRCode("weixin://wxpay/bizpayurl?appid=wx2421b1c4370ec43b&mch_id=10000100&nonce_str=f6808210402125e30663234f94c87a8c&product_id=1&time_stamp=1415949957&sign=512F68131DD251DA4A45DA79CC7EFE9D",
		// 125, response);
	}
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
		}
	
	@RequestMapping(value = "/reg/smscode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> smsCode(String mobile, HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);
		if (!isMobileNO(mobile))
		{
			res.put("message","手机号不正确");
			return res;
		}
		TdUser user = tdUserService.findByMobile(mobile);

		if (null != user) {
			res.put("msg", "该手机已经注册");
			return res;
		}
		
		Random random = new Random();
		String smscode = random.nextInt(9000) + 1000 + "";
		HttpSession session = request.getSession();
		session.setAttribute("SMSCODE", smscode);
		String info = "您的验证码为" + smscode + "，请在页面中输入以完成验证。【科技小巨人】";
		System.err.println(smscode);
		String content = null;
		try {
			content = URLEncoder.encode(info, "GB2312");
			System.err.println(content);
		} catch (Exception e) {
			e.printStackTrace();
			res.put("message", "验证码生成失败！");
			return res;
		}
		String url = "http://www.ht3g.com/htWS/BatchSend.aspx?CorpID=CQDL00059&Pwd=644705&Mobile=" + mobile
				+ "&Content=" + content;
		StringBuffer fanhui = null;
		try {
			URL u = new URL(url);
			URLConnection connection = u.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			httpConn.setRequestProperty("Content-type", "text/html");
			httpConn.setRequestProperty("Accept-Charset", "utf-8");
			httpConn.setRequestProperty("contentType", "utf-8");
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader reader = null;
			StringBuffer resultBuffer = new StringBuffer();
			String tempLine = null;

			if (httpConn.getResponseCode() >= 300) {
				res.put("message", "HTTP Request is not success, Response code is " + httpConn.getResponseCode());
				return res;
			}

			try {
				inputStream = httpConn.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);

				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
				}

				fanhui = resultBuffer;

			} finally {

				if (reader != null) {
					reader.close();
				}

				if (inputStreamReader != null) {
					inputStreamReader.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			res.put("message", "验证码生成失败！");
			return res;
		}
		res.put("status", 0);
		res.put("message", fanhui);
		return res;
	}

}