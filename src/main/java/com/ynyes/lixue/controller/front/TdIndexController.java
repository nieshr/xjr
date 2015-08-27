package com.ynyes.lixue.controller.front;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.lixue.entity.TdAdType;
import com.ynyes.lixue.entity.TdArticleCategory;
import com.ynyes.lixue.entity.TdProductCategory;
import com.ynyes.lixue.service.TdAdService;
import com.ynyes.lixue.service.TdAdTypeService;
import com.ynyes.lixue.service.TdArticleCategoryService;
import com.ynyes.lixue.service.TdArticleService;
import com.ynyes.lixue.service.TdBrandService;
import com.ynyes.lixue.service.TdCommonService;
import com.ynyes.lixue.service.TdGoodsService;
import com.ynyes.lixue.service.TdProductCategoryService;
import com.ynyes.lixue.service.TdSiteLinkService;
import com.ynyes.lixue.util.ClientConstant;

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
        
        // 一级分类
        List<TdProductCategory> topCatList = tdProductCategoryService
                .findByParentIdIsNullOrderBySortIdAsc();
        if (null != topCatList && topCatList.size() > 0) {
            map.addAttribute("top_category_list", topCatList);

            for (int i = 0; i < topCatList.size(); i++) {
                TdProductCategory topCat = topCatList.get(i);

                if (null != topCat) {
                    map.addAttribute(
                            "top_cat_goods_page" + i,
                            tdGoodsService
                                    .findByCategoryIdAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(
                                            topCat.getId(), 0, 3));
                }
            }
        }
        return "/client/index";
    }
}
