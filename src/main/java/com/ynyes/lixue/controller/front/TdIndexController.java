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
        
        // 普通课程
        map.addAttribute("course_page0", tdArticleService                   		
        		.findByMenuIdAndRecommendIdOrderBySortIdAsc(12L,0L, 0, ClientConstant.pageSize));
        
        // 金牌课程
        map.addAttribute("course_page1", tdArticleService                   		
        		.findByMenuIdAndRecommendIdOrderBySortIdAsc(12L,1L, 0, ClientConstant.pageSize));
        
        // 热门课程
        map.addAttribute("course_page2", tdArticleService                   		
        		.findByMenuIdAndRecommendIdOrderBySortIdAsc(12L,2L, 0, ClientConstant.pageSize));   
        
        //最新课程
        map.addAttribute("course_list", tdArticleService                   		
        		.findByMenuIdOrderByCreateTime(12L));   
      
        //课程信息
        List<TdArticleCategory> courseInfoList = tdArticleCategoryService
                .findByMenuId(12L);
        map.addAttribute("course_category_list",courseInfoList);        
        
        //前几个课程分类的内容    
        map.addAttribute("course_category_page0",tdArticleService
        		.findByCategoryId(courseInfoList.get(0).getId(), 0, ClientConstant.pageSize));        
        map.addAttribute("course_category_page1",tdArticleService
        		.findByCategoryId(courseInfoList.get(1).getId(), 0, ClientConstant.pageSize)); 
        map.addAttribute("course_category_page2",tdArticleService
        		.findByCategoryId(courseInfoList.get(2).getId(), 0, ClientConstant.pageSize)); 
        
        //新闻
        map.addAttribute("news_list", tdArticleService                   		
        		.findByMenuIdOrderByCreateTime(8L));   
        
        //招聘信息
//        /////////
//        map.addAttribute("join_list", tdArticleService                   		
//        		.findByMenuIdOrderByCreateTime(13L));   
//        /////////
        List<TdArticleCategory> joinList = tdArticleCategoryService
                .findByMenuId(13L);

        if (null != joinList && joinList.size() > 0) {
            for (TdArticleCategory tdCat : joinList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("人才招聘"))
                {
                    map.addAttribute("join_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(13L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("交通指南"))
                {
                    map.addAttribute("map_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(13L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
            }
        }
        
        

        //下载
        map.addAttribute("download_list", tdArticleService                   		
        		.findByMenuIdOrderByCreateTime(83L));   
        
        //学校信息
        List<TdArticleCategory> infoList = tdArticleCategoryService
                .findByMenuId(10L);

        if (null != infoList && infoList.size() > 0) {
            for (TdArticleCategory tdCat : infoList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("关于我们"))
                {
                    map.addAttribute("about_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
            }
        }
        //名师精英
        List<TdArticleCategory> teacherList = tdArticleCategoryService
                .findByMenuId(11L);

        if (null != teacherList && teacherList.size() > 0) {
            for (TdArticleCategory tdCat : teacherList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("名师精英"))
                {
                    map.addAttribute("teacher_page", tdArticleService
                            .findByMenuIdAndCategoryId(11L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
            }
        }
        
        // 首页大图轮播广告
        TdAdType adType = tdAdTypeService.findByTitle("首页轮播大图广告");
        map.addAttribute("adtype", adType);   //zhangji
        
        
        if (null != adType) {
            map.addAttribute("big_scroll_ad_list", tdAdService
                    .findByTypeIdOrderBySortIdAsc(adType.getId()));
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
