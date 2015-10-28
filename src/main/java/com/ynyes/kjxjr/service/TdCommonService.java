package com.ynyes.kjxjr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ynyes.kjxjr.entity.TdActivityType;
import com.ynyes.kjxjr.entity.TdAdType;
import com.ynyes.kjxjr.entity.TdArticleCategory;
import com.ynyes.kjxjr.entity.TdDemand;
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdEnterpriseType;
import com.ynyes.kjxjr.entity.TdProductCategory;
import com.ynyes.kjxjr.entity.TdRegion;
import com.ynyes.kjxjr.entity.TdRegionAdmin;
import com.ynyes.kjxjr.entity.TdSetting;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.entity.TdUserMessage;
import com.ynyes.kjxjr.util.ClientConstant;

@Service
public class TdCommonService {

    @Autowired
    private TdSettingService tdSettingService;

    @Autowired
    private TdKeywordsService tdKeywordsService;

    @Autowired
    private TdCartGoodsService tdCartGoodsService;

    @Autowired
    private TdNaviBarItemService tdNaviBarItemService;

    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;

    @Autowired
    private TdArticleService tdArticleService;

    @Autowired
    private TdProductCategoryService tdProductCategoryService;

    @Autowired
    private TdSiteLinkService tdSiteLinkService;

    @Autowired
    private TdUserService tdUserService;
    
    @Autowired
    private TdServiceItemService tdServiceItemService;
    
    @Autowired
    private TdAdTypeService tdAdTypeService;
    
    @Autowired
    private TdAdService tdAdService;
    
    @Autowired
    private TdActivityTypeService  tdActivityTypeService;
    
    @Autowired
    private TdEnterpriseTypeService  tdEnterpriseTypeService;
    
    @Autowired
    private TdRegionService  tdRegionService;
    
    @Autowired
    private TdUserMessageService tdUserMessageService;
    
    @Autowired
    private TdEnterpriseService tdEnterpriseService;
    
    @Autowired
    private TdRegionAdminService tdRegionAdminService;
    
    //团购 zhangji
    @Autowired
    private TdDemandService tdDemandService;

    public void setHeader(ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");
        
        //站内信
        TdUser user = tdUserService.findByUsername(username);
        //企业用户
        if (null != user && user.getRoleId()==1)
        {
        	TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
        	List<TdUserMessage> messageList = tdUserMessageService.findByEnterpriseIdAndStatusEOrderByTimeAsc(enterprise.getId(), 0L);
        	Long num =  (long) messageList.size();
        	map.addAttribute("message_num" ,num );
        }
        //区县管理员
        if (null != user && user.getRoleId()==2)
        {
        	TdRegionAdmin regionAdmin = tdRegionAdminService.findbyUsername(username);
        	List<TdUserMessage> messageList = tdUserMessageService.findByRegionAdminIdAndStatusROrderByTimeAsc(regionAdmin.getId(), 0L);
        	Long num =  (long) messageList.size();
        	map.addAttribute("message_num" ,num );
        }

        
        //活动类型
        List<TdActivityType> activityTypeList = tdActivityTypeService.findByIsEnableTrueOrderBySortIdAsc();
        map.addAttribute("activityType_list", activityTypeList);
        
        //活动地区
        List<TdRegion> regionList = tdRegionService.findByIsEnableTrueOrderBySortIdAsc();
        map.addAttribute("region_list", regionList);
        
        //项目归属类别
        List<TdEnterpriseType> enterpriseTypeList = tdEnterpriseTypeService.findByIsEnableTrueOrderBySortIdAsc();
        map.addAttribute("enterpriseType_list", enterpriseTypeList);
        
//        // 顶部小图广告
//        TdAdType adType = tdAdTypeService.findByTitle("搜索框左侧小图广告");
//        if (null != adType) {
//            map.addAttribute("top_small_ad_list", tdAdService
//                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
//        }
        
        // 网站基本信息
        TdSetting setting = tdSettingService.findTopBy();
        
        // 统计访问量
//        if (null != setting && null == req.getSession().getAttribute("countedTotalVisits"))
//        {
//            req.getSession().setAttribute("countedTotalVisits", "yes");
//            if (null == setting.getTotalVisits())
//            {
//                setting.setTotalVisits(1L);
//            }
//            else
//            {
//                setting.setTotalVisits(setting.getTotalVisits() + 1L);
//            }
//            setting = tdSettingService.save(setting);
//        }
        
//         统计在线人数
        if (null != setting && null == req.getSession().getAttribute("countedTotalOnlines"))
        {
            req.getSession().setAttribute("countedTotalOnlines", "yes");
            if (null == setting.getTotalOnlines())
            {
                setting.setTotalOnlines(1L);
            }
            else
            {
                setting.setTotalOnlines(setting.getTotalOnlines() + 1L);
            }
            setting = tdSettingService.save(setting);
        }

        map.addAttribute("site", setting);
        map.addAttribute("keywords_list",
                tdKeywordsService.findByIsEnableTrueOrderBySortIdAsc());
//
//        // 全部商品分类，取三级
//        List<TdProductCategory> topCatList = tdProductCategoryService
//                .findByParentIdIsNullOrderBySortIdAsc();
//        map.addAttribute("top_cat_list", topCatList);
//
//        if (null != topCatList && topCatList.size() > 0) 
//        {
//            for (int i = 0; i < topCatList.size(); i++) 
//            {
//                TdProductCategory topCat = topCatList.get(i);
//                List<TdProductCategory> secondLevelList = tdProductCategoryService
//                        .findByParentIdOrderBySortIdAsc(topCat.getId());
//                map.addAttribute("second_level_" + i + "_cat_list", secondLevelList);
//
//                if (null != secondLevelList && secondLevelList.size() > 0) 
//                {
//                    for (int j=0; j<secondLevelList.size(); j++)
//                    {
//                        TdProductCategory secondLevelCat = secondLevelList.get(j);
//                        List<TdProductCategory> thirdLevelList = tdProductCategoryService
//                                .findByParentIdOrderBySortIdAsc(secondLevelCat.getId());
//                        map.addAttribute("third_level_" + i + j + "_cat_list", thirdLevelList);
//                    }
//                }
//            }
//        }

        // 导航菜单
        map.addAttribute("navi_item_list",
                tdNaviBarItemService.findByPCTdNaviBarItem());
        
        // 商城服务
//        map.addAttribute("service_item_list", tdServiceItemService.findByIsEnableTrueOrderBySortIdAsc());

        // 帮助中心
        Long helpId = 12L;

        map.addAttribute("help_id", helpId);

        List<TdArticleCategory> level0HelpList = tdArticleCategoryService
                .findByMenuIdAndParentId(helpId, 0L);

        map.addAttribute("help_level0_cat_list", level0HelpList);

        if (null != level0HelpList) {

            for (int i = 0; i < level0HelpList.size() && i < 4; i++) {
                TdArticleCategory articleCat = level0HelpList.get(i);
                map.addAttribute("help_" + i + "_cat_list",
                        tdArticleCategoryService.findByMenuIdAndParentId(
                                helpId, articleCat.getId()));
            }
        }

        // 友情链接
        map.addAttribute("site_link_list",
                tdSiteLinkService.findByIsEnableTrueOrderBySortIdAsc());
        
        //团购留言     
//        List<TdDemand> tdDemand = tdDemandService.findByStatusIdAndIsShowable();
//        map.addAttribute("demand_list",tdDemand);
        
        //新闻中心
        List<TdArticleCategory> newsArticle = tdArticleCategoryService.findByMenuId(8L);
        map.addAttribute("news_list", newsArticle);
        
//        // 金牌课程
//        map.addAttribute("course_page1", tdArticleService                   		
//        		.findByMenuIdAndRecommendIdOrderBySortIdAsc(12L,1L, 0, ClientConstant.pageSize));
//        
//        // 热门课程
//        map.addAttribute("course_page2", tdArticleService                   		
//        		.findByMenuIdAndRecommendIdOrderBySortIdAsc(12L,2L, 0, ClientConstant.pageSize));   
        
        //功能信息
//        List<TdArticleCategory> infoList = tdArticleCategoryService
//                .findByMenuId(10L);
//
//        if (null != infoList && infoList.size() > 0) {
//            for (TdArticleCategory tdCat : infoList)
//            {
//                if (null != tdCat.getTitle() && tdCat.getTitle().equals("公司简介"))
//                {
//                    map.addAttribute("profile_page", tdArticleService
//                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(10L,
//                                    tdCat.getId(), 0, ClientConstant.pageSize));
//                    
//                }
//                if (null != tdCat.getTitle() && tdCat.getTitle().equals("服务项目"))
//                {
//                    map.addAttribute("service_page", tdArticleService
//                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(10L,
//                                    tdCat.getId(), 0, ClientConstant.pageSize));
//                 
//                }             
//            }
//        }
    }

}
