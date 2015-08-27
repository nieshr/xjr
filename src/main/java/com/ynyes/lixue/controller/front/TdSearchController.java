package com.ynyes.lixue.controller.front;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.lixue.entity.TdArticleCategory;
import com.ynyes.lixue.entity.TdKeywords;
import com.ynyes.lixue.service.TdArticleCategoryService;
import com.ynyes.lixue.service.TdArticleService;
import com.ynyes.lixue.service.TdCommonService;
import com.ynyes.lixue.service.TdGoodsService;
import com.ynyes.lixue.service.TdKeywordsService;
import com.ynyes.lixue.service.TdProductCategoryService;
import com.ynyes.lixue.util.ClientConstant;

/**
 * 课程检索
 * @author zhangji
 *
 */
@Controller
public class TdSearchController {
    
    @Autowired
    private TdCommonService tdCommonService;
    
    @Autowired
    private TdGoodsService tdGoodsService;
    
    @Autowired
    private TdKeywordsService tdKeywordsService;
    
    @Autowired
    private TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;
    
    @Autowired
    private TdArticleService tdArticleService;
    
 // 组成：[排序字段]-[销量排序标志]-[价格排序标志]-[上架时间排序标志]-[是否有货]-[页号]_[价格低值]-[价格高值]
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String list(String keywords, Integer page, HttpServletRequest req, ModelMap map){
        
        tdCommonService.setHeader(map, req);
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null != keywords)
        {
            TdKeywords key = tdKeywordsService.findByTitle(keywords);
            
            if (null != key)
            {
                if (null == key.getTotalSearch())
                {
                    key.setTotalSearch(1L);
                }
                else
                {
                    key.setTotalSearch(key.getTotalSearch() + 1L);
                }
                
                key.setLastSearchTime(new Date());
                
                tdKeywordsService.save(key);
            }
            
            map.addAttribute("course_page", tdArticleService.searchArticle(keywords.trim(), page, ClientConstant.pageSize));    
        }
        
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(12L);
        map.addAttribute("info_category_list", catList); //栏目的列表 zhangji     
        map.addAttribute("pageId", page);
        map.addAttribute("keywords", keywords);          
        
        return "/client/search_result";
    }
}
