package com.ynyes.zhangyan.controller.front;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.zhangyan.entity.TdAdType;
import com.ynyes.zhangyan.entity.TdArticleCategory;
import com.ynyes.zhangyan.entity.TdProductCategory;
import com.ynyes.zhangyan.service.TdAdService;
import com.ynyes.zhangyan.service.TdAdTypeService;
import com.ynyes.zhangyan.service.TdArticleCategoryService;
import com.ynyes.zhangyan.service.TdArticleService;
import com.ynyes.zhangyan.service.TdBrandService;
import com.ynyes.zhangyan.service.TdCommonService;
import com.ynyes.zhangyan.service.TdGoodsService;
import com.ynyes.zhangyan.service.TdProductCategoryService;
import com.ynyes.zhangyan.service.TdSiteLinkService;
import com.ynyes.zhangyan.util.ClientConstant;

/**
 * 前端首页控制
 *
 */
@Controller
@RequestMapping("/")
public class TdIndexController {

    @Autowired
    private TdCommonService tdCommonService;

    @Autowired
    private TdGoodsService tdGoodsService;

    @Autowired
    private TdArticleService tdArticleService;

    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;

    @Autowired
    private TdProductCategoryService tdProductCategoryService;

    @Autowired
    private TdSiteLinkService tdSiteLinkService;

    @Autowired
    private TdAdTypeService tdAdTypeService;

    @Autowired
    private TdAdService tdAdService;

    @Autowired
    private TdBrandService tdBrandService;

    @RequestMapping
    public String index(HttpServletRequest req, Device device, ModelMap map) {        
        
        tdCommonService.setHeader(map, req);              
        
        // 新闻中心
        map.addAttribute("news_page", tdArticleService                   		
        		.findByMenuIdAndIsEnableOrderByIdDesc(8L, 0, ClientConstant.pageSize));
        
        // 相关常识
        map.addAttribute("knowledge_page", tdArticleService                   		
        		.findByMenuIdAndIsEnableOrderByIdDesc(11L, 0, ClientConstant.pageSize));              
        
        //功能信息
        List<TdArticleCategory> infoList = tdArticleCategoryService
                .findByMenuId(10L);

        if (null != infoList && infoList.size() > 0) {
            for (TdArticleCategory tdCat : infoList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("公司简介"))
                {
                    map.addAttribute("profile_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("服务项目"))
                {
                    map.addAttribute("service_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                 
                }             
            }
        }
        

       
        return "/client/index";
    }
}
