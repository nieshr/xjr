package com.ynyes.rongcheng.controller.management;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.rongcheng.entity.AdvertisementType;
import com.ynyes.rongcheng.service.AdvertisementTypeService;
import com.ynyes.rongcheng.util.ManagementConstant;

/**
 * 品牌管理控制器
 * 
 * @author Sharon
 * 
 */
@Controller
@RequestMapping(value="/admin/ad/type")
public class ManagerAdvertisementTypeController {
    
    @Autowired
    AdvertisementTypeService advertisementTypeService;
    
    @RequestMapping
    public String adType(ModelMap map){
        
        Page<AdvertisementType> adTypePage = advertisementTypeService.findAll(0, ManagementConstant.pageSize, "desc", "id");
        
        
        if (null != adTypePage)
        {
            map.addAttribute("ad_type_list", adTypePage.getContent());
        }
        
        return "/management/adType";
    }
    
    /**
     * 获取指定页号的广告类型
     * 
     * @param map
     * @param pageIndex 页号
     * @return
     */
    @RequestMapping(value="/page/{pageIndex}")
    public String page(ModelMap map, @PathVariable Integer pageIndex) {
        
        if (null != pageIndex && pageIndex.intValue() >= 0)
        {
            Page<AdvertisementType> adTypePage = advertisementTypeService.findAll(pageIndex, ManagementConstant.pageSize, "desc", "id");
            
            if (null != adTypePage)
            {
                map.addAttribute("ad_type_list", adTypePage.getContent());
            }
        }
        
        return "/management/ad/type_tbody";
    }
    
    @RequestMapping(value="/modify/{id}",method = RequestMethod.POST)
    public String modify(ModelMap map, @PathVariable Long id){
        if (null != id)
        {
            map.addAttribute("ad_type", advertisementTypeService.findOne(id));
        }
        
        return "/management/ad/type_modify";
    }
    
    @RequestMapping(value="/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(ModelMap map, 
                                AdvertisementType adType){
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        if (null == adType)
        {
            res.put("message", "参数有误");
            return res;
        }
        
        if (null == adType.getName() || "".equals(adType.getName()))
        {
            res.put("message", "类型名为空");
            return res;
        }
        
        if (null != advertisementTypeService.findByName(adType.getName()))
        {
            res.put("message", "同名类型已存在");
            return res;
        }
        
        advertisementTypeService.save(adType);
        
        res.put("code", 0);
        
        return res;
    }
    
    /**
     * 删除
     * 
     * @param map
     * @param id 品牌ID
     * @return
     */
    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    @ResponseBody
    public void delete(ModelMap map, @PathVariable Long id) {
        
        if (null != id)
        {
            advertisementTypeService.delete(id);
        }
    }
    
    /**
     * 存在id字段时先查找出对应的实体
     * 
     * @param id
     * @param model
     */
    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("adType", advertisementTypeService.findOne(id));
        }
    }
    
}
