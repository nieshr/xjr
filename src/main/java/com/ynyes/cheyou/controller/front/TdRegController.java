package com.ynyes.cheyou.controller.front;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdSetting;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.entity.TdUserPoint;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdSettingService;
import com.ynyes.cheyou.service.TdUserPointService;
import com.ynyes.cheyou.service.TdUserService;
import com.ynyes.cheyou.util.SMSUtil;
import com.ynyes.cheyou.util.VerifServlet;

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
    
    @RequestMapping("/reg")
    public String reg(Integer errCode, Integer shareId, HttpServletRequest request, ModelMap map) {
        String username = (String) request.getSession().getAttribute("username");
        
        if (null != shareId)
        {
            map.addAttribute("share_id", shareId);
        }
        
        // 基本信息
        tdCommonService.setHeader(map, request);
        
        if (null == username) {
            if (null != errCode)
            {
                if (errCode.equals(1))
                {
                    map.addAttribute("error", "验证码错误");
                }
                else if (errCode.equals(2))
                {
                    map.addAttribute("error", "用户名已存在");
                }
                
                map.addAttribute("errCode", errCode);
            }
            return "/client/reg";
        }
        return "redirect:/";
    }
    
    @RequestMapping(value="/logutil")
    public String LogUtils(){
        return "/logutil";
    }
    /**
     * 
     * 注册用户保存到数据库<BR>
     * 方法名：saveUser<BR>
     * 时间：2015年2月2日-下午1:44:45 <BR>
     * @param user
     * @param name
     * @param mobile
     * @param password
     * @param newpassword
     * @return String<BR>
     * @param  [参数1]   [参数1说明]
     * @param  [参数2]   [参数2说明]
     * @exception <BR>
     * @since  1.0.0
     */
    @RequestMapping(value="/reg",method=RequestMethod.POST)
    public String reg(String username,
                String mobile,
                String password,
                String email,
                String smsCode,
                String code,
                String carCode,
                Long shareId,
                HttpServletRequest request){
        String codeBack = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
        String smsCodeSave = (String) request.getSession().getAttribute("SMSCODE");
        
        if (null == codeBack || null == smsCodeSave)
        {
            if (null == shareId)
            {
                return "redirect:/reg";
            }
            else
            {
                return "redirect:/reg?shareId=" + shareId;
            }
        }
        
        if (!codeBack.equalsIgnoreCase(code))
        {
            if (null == shareId)
            {
                return "redirect:/reg?errCode=1";
            }
            else
            {
                return "redirect:/reg?errCode=1&shareId=" + shareId;
            }
        }
        
        if (!smsCodeSave.equalsIgnoreCase(smsCode))
        {
            if (null == shareId)
            {
                return "redirect:/reg?errCode=4";
            }
            else
            {
                return "redirect:/reg?errCode=4&shareId=" + shareId;
            }
        }
        
        TdUser user = tdUserService.findByUsername(username);
        
        if (null != user)
        {
            if (null == shareId)
            {
                return "redirect:/reg?errCode=2";
            }
            else
            {
                return "redirect:/reg?errCode=2&shareId=" + shareId;
            }
        }
        
        user = tdUserService.addNewUser(null, username, password, mobile, email, carCode);
        
        if (null == user)
        {
            if (null == shareId)
            {
                return "redirect:/reg?errCode=3";
            }
            else
            {
                return "redirect:/reg?errCode=3&shareId=" + shareId;
            }
        }
        
        user = tdUserService.save(user);
        
        // 奖励分享用户
        if (null != shareId)
        {
            TdUser sharedUser = tdUserService.findOne(shareId);
            
            if (null != sharedUser && sharedUser.getRoleId().equals(0L))
            {
                TdSetting setting = tdSettingService.findTopBy();
                TdUserPoint userPoint = new TdUserPoint();
                
                if (null != setting)
                {
                    userPoint.setPoint(setting.getRegisterSharePoints());
                }
                else
                {
                    userPoint.setPoint(0L);
                }
                
                if (null != sharedUser.getTotalPoints())
                {
                    userPoint.setTotalPoint(sharedUser.getTotalPoints() + userPoint.getPoint());
                }
                else
                {
                    userPoint.setTotalPoint(userPoint.getPoint());
                }
                
                userPoint.setUsername(sharedUser.getUsername());
                userPoint.setDetail("用户分享网站成功奖励");
                
                userPoint = tdUserPointService.save(userPoint);
                
                sharedUser.setTotalPoints(userPoint.getTotalPoint()); // 积分
                tdUserService.save(sharedUser);
            }
        }
        
        request.getSession().setAttribute("username", username);
        
        String referer = (String) request.getAttribute("referer");
        
        if (null != request.getAttribute("referer"))
        {
            return "redirect:" + referer;
        }
        
        if (null == shareId)
        {
            return "redirect:/user";
        }
        
        return "redirect:/user?shareId=" + shareId;
    }
    
    @RequestMapping(value = "/code",method = RequestMethod.GET)
    public void verify(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        VerifServlet randomValidateCode = new VerifServlet();
        randomValidateCode.getRandcode(request, response);
    }
    
    @RequestMapping(value = "/reg/smscode",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> smsCode(String mobile, HttpServletResponse response, HttpServletRequest request) {
        Random random = new Random();
        
        String smscode = String.format("%04d", random.nextInt(9999));
        
        HttpSession session = request.getSession();
        
        session.setAttribute("SMSCODE", smscode);
       
        return SMSUtil.send(mobile, "15612" ,new String[]{smscode});
    }
    
}