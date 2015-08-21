package com.ynyes.zhangyan.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ynyes.zhangyan.entity.TdAdType;
import com.ynyes.zhangyan.entity.TdArticleCategory;
import com.ynyes.zhangyan.entity.TdDemand;
import com.ynyes.zhangyan.entity.TdProductCategory;
import com.ynyes.zhangyan.entity.TdSetting;
import com.ynyes.zhangyan.util.ClientConstant;

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
    
    //团购 zhangji
    @Autowired
    private TdDemandService tdDemandService;

    public void setHeader(ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");

        // 用户名，购物车
        if (null != username) {
            map.addAttribute("username", username);
            map.addAttribute("user",
                    tdUserService.findByUsernameAndIsEnabled(username));
            map.addAttribute("cart_goods_list",
                    tdCartGoodsService.updateGoodsInfo(tdCartGoodsService.findByUsername(username)));
        } else {
            map.addAttribute("cart_goods_list",
                    tdCartGoodsService.updateGoodsInfo(tdCartGoodsService.findByUsername(req.getSession().getId())));
        }
        
        // 顶部小图广告
        TdAdType adType = tdAdTypeService.findByTitle("搜索框左侧小图广告");
        if (null != adType) {
            map.addAttribute("top_small_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }
        
        // 网站基本信息
        TdSetting setting = tdSettingService.findTopBy();
        
        // 统计访问量
        if (null != setting && null == req.getSession().getAttribute("countedTotalVisits"))
        {
            req.getSession().setAttribute("countedTotalVisits", "yes");
            if (null == setting.getTotalVisits())
            {
                setting.setTotalVisits(1L);
            }
            else
            {
                setting.setTotalVisits(setting.getTotalVisits() + 1L);
            }
            setting = tdSettingService.save(setting);
        }
        
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

        // 全部商品分类，取三级
        List<TdProductCategory> topCatList = tdProductCategoryService
                .findByParentIdIsNullOrderBySortIdAsc();
        map.addAttribute("top_cat_list", topCatList);

        if (null != topCatList && topCatList.size() > 0) 
        {
            for (int i = 0; i < topCatList.size(); i++) 
            {
                TdProductCategory topCat = topCatList.get(i);
                List<TdProductCategory> secondLevelList = tdProductCategoryService
                        .findByParentIdOrderBySortIdAsc(topCat.getId());
                map.addAttribute("second_level_" + i + "_cat_list", secondLevelList);

                if (null != secondLevelList && secondLevelList.size() > 0) 
                {
                    for (int j=0; j<secondLevelList.size(); j++)
                    {
                        TdProductCategory secondLevelCat = secondLevelList.get(j);
                        List<TdProductCategory> thirdLevelList = tdProductCategoryService
                                .findByParentIdOrderBySortIdAsc(secondLevelCat.getId());
                        map.addAttribute("third_level_" + i + j + "_cat_list", thirdLevelList);
                    }
                }
            }
        }

        // 导航菜单
        map.addAttribute("navi_item_list",
                tdNaviBarItemService.findByPCTdNaviBarItem());
        
        // 商城服务
        map.addAttribute("service_item_list", tdServiceItemService.findByIsEnableTrueOrderBySortIdAsc());

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
        
        //相关常识
        List<TdArticleCategory> knowledgeArticle = tdArticleCategoryService.findByMenuId(11L);
        map.addAttribute("konwledge_list", knowledgeArticle);
        
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
    }

}
