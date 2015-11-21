package com.ynyes.kjxjr.controller.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.neo4j.cypher.internal.compiler.v2_1.perty.printToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.kjxjr.entity.TdActivity;
import com.ynyes.kjxjr.entity.TdArticle;
import com.ynyes.kjxjr.entity.TdArticleCategory;
import com.ynyes.kjxjr.entity.TdDemand;
import com.ynyes.kjxjr.entity.TdNavigationMenu;
import com.ynyes.kjxjr.service.TdActivityService;
import com.ynyes.kjxjr.service.TdActivityTypeService;
import com.ynyes.kjxjr.service.TdArticleCategoryService;
import com.ynyes.kjxjr.service.TdArticleService;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdDemandService;
import com.ynyes.kjxjr.service.TdDiySiteService;
import com.ynyes.kjxjr.service.TdNavigationMenuService;
import com.ynyes.kjxjr.service.TdUserRecentVisitService;
import com.ynyes.kjxjr.util.ClientConstant;
import com.ynyes.kjxjr.util.SiteMagConstant;

/**
 * 
 * 资讯
 *
 */
@Controller
@RequestMapping("/info")
public class TdInfoController {
	@Autowired 
	private TdArticleService tdArticleService;
	
	@Autowired 
    private TdArticleCategoryService tdArticleCategoryService;
	
	@Autowired 
    private TdNavigationMenuService tdNavigationMenuService;
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
    private TdUserRecentVisitService tdUserRecentVisitService;
	
	@Autowired
	private TdDemandService tdDemandService;
	
	@Autowired
	TdActivityService tdActivityService;
    
	@Autowired
	TdActivityTypeService tdActivityTypeService;
	
	@Autowired
	TdDiySiteService tdDiySiteService;
	@RequestMapping("/index")
	public String infoIndex(ModelMap map,HttpServletRequest req){
		tdCommonService.setHeader(map, req); 
		
		//新闻动态所有类别
		List<TdArticleCategory> tdArticleCategories = tdArticleCategoryService.findByMenuId(10L);
		map.addAttribute("newsCat_list", tdArticleCategories);
		
//		//通知公告
//		map.addAttribute("notice_page", tdArticleService.findByCategoryId(19L, 0, SiteMagConstant.pageSize));
//		
//	    //活动状态
//		map.addAttribute("activity_page", tdArticleService.findByCategoryId(20L, 0, SiteMagConstant.pageSize));
//		
//	    //媒体报道
//		map.addAttribute("media_page", tdArticleService.findByCategoryId(21L, 0, SiteMagConstant.pageSize));
//		
//	    //数据公布
//		map.addAttribute("data_page", tdArticleService.findByCategoryId(22L, 0, SiteMagConstant.pageSize));
//		
//	    //热点追踪
//		map.addAttribute("hot_page", tdArticleService.findByCategoryId(23L, 0, SiteMagConstant.pageSize));
//		
//	    //创业风向
//		map.addAttribute("SYB_page", tdArticleService.findByCategoryId(24L, 0, SiteMagConstant.pageSize));

        if (null != tdArticleCategories && tdArticleCategories.size() > 0) {
            for (TdArticleCategory tdCat : tdArticleCategories)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("通知公告"))
                {
                    map.addAttribute("notice_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("活动状态"))
                {
                    map.addAttribute("activity_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("媒体报道"))
                {
                    map.addAttribute("media_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("数据公布"))
                {
                    map.addAttribute("data_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("热点追踪"))
                {
                    map.addAttribute("hot_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("创业风向"))
                {
                    map.addAttribute("SYB_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
             
            }
        }
		
		Long active = 3L;
		map.addAttribute("active",active);
		
		return "/client/news_index";
	}
	
	@RequestMapping("/list/{mid}")
    public String infoList(@PathVariable Long mid, 
                            Long catId, 
    			    		String __EVENTTARGET,
    			            String __EVENTARGUMENT,
                            Integer page, 
                            ModelMap map,
                            HttpServletRequest req){	
		
	    tdCommonService.setHeader(map, req);   
	    
	    //翻页 zhangji
	    if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            
        }
	    /*翻页 end*/
	    
	    //新闻动态所有类别
	    List<TdArticleCategory> tdArticleCategories = tdArticleCategoryService.findByMenuId(10L);
	    map.addAttribute("newsCat_list", tdArticleCategories);
	    
        if (null == page)
        {
            page = 0;
        }
	    
	    if (null == mid)
	    {
	        return "/client/error_404";
	    }	   
	    
	    TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", menu.getTitle());
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
	    
	    List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
	    
	    if (null !=catList && catList.size() > 0)
	    {
	        if (null == catId || 0 == catId)
	        {
            //    catId = catList.get(0).getId();   //.get(0)表示 取catList表的第0个 zhangji
	        	
	        	//课程设置设为5条信息一页 zhangji
	        	if(menu.getTitle().equals("课程设置"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page,  ClientConstant.coursePageSize));  
	        	}
	        	else
	        	{	
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.pageSize));   //menu下所有项
	        	}
	        }
	        else
	        {	  
	        	if(menu.getTitle().equals("课程设置"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page,  ClientConstant.coursePageSize));
	        	}
	        	else
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(mid, catId, page, ClientConstant.pageSize));	    
	        	}
	        }	
	    }
	    
        if(13 == mid && null == catId)
        {
        	catId = catList.get(0).getId();   //.get(0)表示 取catList表的第0个 zhangji
        }
        	
	    
	    map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );   //找出栏目名称 zhangji
	    map.addAttribute("catId", catId);
	    map.addAttribute("mid", mid);
	    map.addAttribute("info_category_list", catList); //栏目的列表 zhangji
	    map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.pageSize));
	    
        return "/client/news_list";
    }
	
	@RequestMapping("/list/content/{id}")
    public String content(@PathVariable Long id, Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == id || null == mid)
        {
            return "/client/error_404";
        }             
        
        TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
        
        map.addAttribute("menu_name", menu.getTitle());      
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
        
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
      
        map.addAttribute("info_category_list", catList);
        map.addAttribute("mid", mid);
        
        TdArticle tdArticle = tdArticleService.findOne(id);
        //浏览量 zhangji
        Long viewCount = tdArticle.getViewCount();
        if(null !=viewCount)
        {
            tdArticle.setViewCount(viewCount+1);
            tdArticleService.save(tdArticle);
        }

        //找出栏目名称 zhangji
        Long catId = tdArticle.getCategoryId();
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        map.addAttribute("catId", catId);
        
        if (null != tdArticle)
        {
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        
        // 最近添加
        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
        
        return "/client/news_detail";
    }
	
	
	@RequestMapping("/search")
    public String search(String keywords , Integer page , ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
	    if (null == page )
	    {
	    	page = 0;
	    }
     
	    Page<TdArticle> infoPage = tdArticleService.searchArticle(keywords, page, ClientConstant.pageSize);
	    map.addAttribute("info_page", infoPage);
	    map.addAttribute("keywords", keywords);

        
        return "/client/search";
    }
	
	@RequestMapping("/list/content/expert/{id}")
    public String contentExpert(@PathVariable Long id, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
     
        map.addAttribute("menu_name", "专家资源");      
//	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", "subName");//英文名称 zhangji

        //找出栏目名称 zhangji
        map.addAttribute("info_name","创业导师" );
        
        map.addAttribute("info", tdDiySiteService.findOne(id));
        
        return "/client/news_resource_detail";
    }
	
	/**
	 * ajax列表选择
	 * @author Zhangji
	 * @param mid
	 * @param catId
	 * @param page
	 * @param map
	 * @param req
	 * @return
	 */
	@RequestMapping("/list/select")
    public String infoListSelect( Long mid, 
                            Long catId, 
    			    		String __EVENTTARGET,
    			            String __EVENTARGUMENT,
                            Integer page, 
                            ModelMap map,
                            HttpServletRequest req){
	    
	    tdCommonService.setHeader(map, req);
	    
	    //翻页 zhangji
	    if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                	page = Integer.parseInt(__EVENTARGUMENT);
                }
            }
            
        }
	    /*翻页 end*/
           
	    if (null == mid)
	    {
	        return "/client/error_404";
	    }
	    
	    if (null == page)
	    {
	        page = 0;
	    }
	    
	    TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", menu.getTitle());
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
	    
	    List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
	    
	    if (null !=catList && catList.size() > 0)
	    {
	    	if (null == catId || 0 == catId)
	        {
  //            catId = catList.get(0).getId();   //.get(0)表示 取catList表的第0个 zhangji
	        	
	        	//课程设置设为5条信息一页 zhangji
	        	if(menu.getTitle().equals("课程设置"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page,  ClientConstant.coursePageSize));  
	        	}
	        	else
	        	{	
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.pageSize));   //menu下所有项
	        	}
	        }
	        else
	        {	  
	        	if(menu.getTitle().equals("课程设置"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page,  ClientConstant.coursePageSize));
	        	}
	        	else
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page, ClientConstant.pageSize));	    
	        	}
	        }	      
	    }   
	    
	    map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );   //找出栏目名称 zhangji
	    map.addAttribute("catId", catId);
	    map.addAttribute("mid", mid);
	    map.addAttribute("info_category_list", catList); //栏目的列表 zhangji
	    map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.pageSize));
	    
        return "/client/info_list_detail";
    }
	
	@RequestMapping("/list/content/select")
    public String contentSelect(Long id, Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == id || null == mid)
        {
            return "/client/error_404";
        }             
        
        TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
        
        map.addAttribute("menu_name", menu.getTitle());      
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
        
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
      
        map.addAttribute("info_category_list", catList);
        map.addAttribute("mid", mid);
        
        TdArticle tdArticle = tdArticleService.findOne(id);
        
        //找出栏目名称 zhangji
        Long catId = tdArticle.getCategoryId();
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        map.addAttribute("catId", catId);
        
        //浏览量 zhangji
        Long viewCount = tdArticle.getViewCount();
        tdArticle.setViewCount(viewCount+1);
        tdArticleService.save(tdArticle);
        
        if (null != tdArticle)
        {
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        
        // 最近添加
        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
        
        return "/client/info_list_content_detail";
    }
	
	/**
	 * 服务项目ajax局部刷新
	 * @author Zhangji
	 * @param mid
	 * @param catId
	 * @param page
	 * @param map
	 * @param req
	 * @return
	 */
	@RequestMapping("/entry/select")
    public String serviceList(Long id, 
                            Long mid, 
                            Integer page, 
                            ModelMap map,
                            HttpServletRequest req){
	    
		 tdCommonService.setHeader(map, req);
		    
	        if (null == id || null == mid)
	        {
	            return "/client/error_404";
	        }      

	        map.addAttribute("mid", mid);
	        
	        TdArticle tdArticle = tdArticleService.findOne(id);
	        
	        //找出栏目名称 zhangji
	        Long catId = tdArticle.getCategoryId();
	        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
	        
	        //列表信息 zhangji
	        map.addAttribute("info_page", tdArticleService
	                .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid,
	                        catId, 0, ClientConstant.pageSize));
	        
	        if (null != tdArticle)
	        {
	            map.addAttribute("info", tdArticle);
	            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
	            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
	        }
	        
	        // 最近添加
	        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
	      
        return "/client/info_entry_content_detail";
    }
	
	/**
	 * 服务项目内容
	 * @author Zhangji
	 * @param id
	 * @param mid
	 * @param map
	 * @param req
	 * @return
	 */
	@RequestMapping("/entry/content/{id}")
    public String serviceContent(@PathVariable Long id, Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == id || null == mid)
        {
            return "/client/error_404";
        }      
        
        TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
        
        map.addAttribute("menu_name", menu.getTitle());   
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
        
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);

        map.addAttribute("info_category_list", catList);
        map.addAttribute("mid", mid);
        
        //导航栏[关于我们]跳转 zhangji
        if(-1 == id)
        {
        	 for (TdArticleCategory tdCat : catList)
             {
                 if (null != tdCat.getTitle() && tdCat.getTitle().equals("关于我们"))
                 {
                	 Long catId = tdCat.getId();
                	 List<TdArticle> profile = tdArticleService.findByCategoryId(catId);
                	 id = profile.get(0).getId();
                     break;               
                 }                     
             }
        	
        }
        //导航栏[服务项目]跳转 zhangji
        if(-2 == id)
        {
        	 for (TdArticleCategory tdCat : catList)
             {
                 if (null != tdCat.getTitle() && tdCat.getTitle().equals("服务项目"))
                 {
                	 Long catId = tdCat.getId();
                	 List<TdArticle> profile = tdArticleService.findByCategoryId(catId);
                	 id = profile.get(0).getId();
                     break;               
                 }                     
             }
        	
        }
        
        TdArticle tdArticle = tdArticleService.findOne(id);
        
        //找出栏目名称 zhangji
        Long catId = tdArticle.getCategoryId();
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        
        //列表信息 zhangji
        map.addAttribute("info_page", tdArticleService
                .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(mid,
                        catId, 0, ClientConstant.pageSize));
        

        if (null != tdArticle)
        {
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        
        // 最近添加
        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
        
        return "/client/info_entry_content";
    }
	
	/**
	 * 
	 * 课程报名
	 * @param id
	 * @param mid
	 * @param map
	 * @param req
	 * @return
	 */
	@RequestMapping("/coursechoose")
	public String coursechoose( Long id,Long mid, ModelMap map, HttpServletRequest req)
	{
		TdArticle article = tdArticleService.findOne(id);
		map.addAttribute("coursetake",article.getTitle());
		map.addAttribute("courseId",id);
		map.addAttribute("courseMid",mid);
		map.addAttribute("info", article);
		
	    TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", menu.getTitle());
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
		return "/client/info_list_detail";
	}
	
	@RequestMapping("/submit")
	@ResponseBody
    public Map<String, Object> setConsult(TdDemand userDemand, HttpServletRequest req)
    {
		Map<String, Object> res = new HashMap<String, Object>();
    	res.put("code", 1);
    	if (userDemand.getName() == null || userDemand.getName().equals(""))
    	{
			res.put("message", "用户名不能为空");
			return res;
		}
    	else if (userDemand.getMobile().equals(""))
    	{
			res.put("message", "手机号不能为空");
			return res;
		}
    	else if (userDemand.getContent() ==null|| userDemand.getContent().equals(""))
    	{
			res.put("message", "留言内容不能为空");
			return res;
		}
    	
    	userDemand.setTime(new Date());
    	userDemand.setStatusId(1L);     //zhangji
    	tdDemandService.save(userDemand);
    	res.put("code", 0);
    	return res;
    }
	
	@RequestMapping("/coursechoose/content/{id}")
    public String coursechooseContent(@PathVariable Long id, Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == id || null == mid)
        {
            return "/client/error_404";
        }             
        
        TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
        
        map.addAttribute("menu_name", "课程设置");      
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
        
	    TdArticle tdArticle = tdArticleService.findOne(id);
	    map.addAttribute("coursetake",tdArticle.getTitle());
	    map.addAttribute("courseId",id);
        //找出栏目名称 zhangji
        Long catId = tdArticle.getCategoryId();
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        
        //列表信息 zhangji
        map.addAttribute("info_page", tdArticleService
                .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(mid,
                        catId, 0, ClientConstant.pageSize));
        
	    
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
      
        map.addAttribute("info_category_list", catList);
        map.addAttribute("mid", mid);
        
        
        //找出栏目名称 zhangji
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        
        if (null != tdArticle)
        {
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        
        // 最近添加
        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
        
        return "/client/info_list";
    }
	@RequestMapping("/map")
    public String map(ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    map.addAttribute("menu_name", "交通指南");
	    map.addAttribute("menu_sub_name", "Map");//英文名称 zhangji
	    map.addAttribute("message", "地图导航");
	    map.addAttribute("back", "退出");
	    return "/client/map";
	}
	
	@RequestMapping("/contact")
    public String contact(ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
		Long active = 7L;
		map.addAttribute("active",active);

	    return "/client/contactus";
	}
	//企业项目
	@RequestMapping("/projectshow")
    public String projectshow(ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
		Long active = 4L;
		map.addAttribute("active",active);
		
		//根据栏目名称查找
        List<TdArticleCategory> showList = tdArticleCategoryService
                .findByMenuId(11L);
        map.addAttribute("show_list" , showList);

        if (null != showList && showList.size() > 0) {
            for (TdArticleCategory tdCat : showList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("企业展"))
                {
                    Page<TdArticle> teamPage =  tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(11L,
                                    tdCat.getId(), 0, ClientConstant.pageSize);
                    
                    map.addAttribute("team_page", teamPage);
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("团队展"))
                {
                    map.addAttribute("project_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(11L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("创业故事"))
                {
                    map.addAttribute("story_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(11L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                }
            }
        }
//		map.addAttribute("team_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(11L, 16L, 0, ClientConstant.pageSize));	    
//		map.addAttribute("project_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(11L, 17L, 0, ClientConstant.pageSize));	    
//		map.addAttribute("story_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(11L, 18L, 0, ClientConstant.pageSize));	    
	    return "/client/projectshow";
	}
	
	//企业项目-详情
	@RequestMapping("/projectshow/detail")
    public String projectshowDetail( Long catId , Long mid , Integer page , ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
		Long active = 4L;
		map.addAttribute("active",active);
		
		if (null == page)
		{
			page = 0;
		}
		
		//根据栏目名称查找
        List<TdArticleCategory> showList = tdArticleCategoryService
                .findByMenuId(mid);
        map.addAttribute("show_list" , showList);
		
		//根据栏目类别查找
        Page<TdArticle> showPage = tdArticleService
        		.findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(mid, catId, page, 15);
        map.addAttribute("show_page" , showPage);
        
        map.addAttribute("mid" , mid);
        map.addAttribute("catId", catId);
        map.addAttribute("menuName", tdArticleCategoryService.findOne(catId).getTitle());
    
	    return "/client/projectshow_detail";
	}
	
	//专家资源
	@RequestMapping("/resource")
    public String resource(ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
		Long active = 5L;
		map.addAttribute("active",active);
		map.addAttribute("tutor_page",tdDiySiteService.findByRoleIdOrderBySortIdAsc(3L, 0, ClientConstant.pageSize));	    
		map.addAttribute("invest_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(12L, 3L, 0, ClientConstant.pageSize));	    
	    return "/client/resource";
	}
	//专家资源列表
	@RequestMapping("/resource/{catId}")
    public String resourceList(@PathVariable Long catId, 
    			    		String __EVENTTARGET,
    			            String __EVENTARGUMENT,
                            Integer page, 
                            ModelMap map,
                            HttpServletRequest req){	
		
	    tdCommonService.setHeader(map, req);   
	    
	    //翻页 zhangji
	    if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            
        }
	    /*翻页 end*/
	    
        if (null == page)
        {
            page = 0;
        }
	    
        TdArticleCategory category = tdArticleCategoryService.findOne(catId);
        Long mid= category.getMenuId();
	    TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", menu.getTitle());
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
	    
	    List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
	    
	    if (null !=catList && catList.size() > 0)
	    {
	        if (null == catId || 0 == catId)
	        {
            //    catId = catList.get(0).getId();   //.get(0)表示 取catList表的第0个 zhangji
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.coursePageSize));   //menu下所有项
	        }
	        else
	        {	  
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page, ClientConstant.coursePageSize));	    
	        }	
	    }
	    
        if(12 == mid && null == catId)
        {
        	catId = catList.get(0).getId();   //.get(0)表示 取catList表的第0个 zhangji
        }
        	
		Long active = 5L;
		map.addAttribute("active",active);
	    map.addAttribute("info_cat",tdArticleCategoryService.findOne(catId) );   //找出栏目名称 zhangji
	    map.addAttribute("catId", catId);
	    map.addAttribute("mid", mid);
	    map.addAttribute("info_category_list", catList); //栏目的列表 zhangji
	    map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.coursePageSize));
	    
        return "/client/resource_detail";
    }
	
	//专家列表
	@RequestMapping("/resource/expert")
    public String expertList(
    			    		String __EVENTTARGET,
    			            String __EVENTARGUMENT,
                            Integer page, 
                            ModelMap map,
                            HttpServletRequest req){	
		
	    tdCommonService.setHeader(map, req);   
	    
	    //翻页 zhangji
	    if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            
        }
	    /*翻页 end*/
	    
        if (null == page)
        {
            page = 0;
        }
       
	    
	    map.addAttribute("menu_name", "专家资源");
	   
	    map.addAttribute("menu_sub_name", "sub_name" );//英文名称 zhangji

		map.addAttribute("info_page", tdDiySiteService.findByRoleIdOrderBySortIdAsc(3L, page, ClientConstant.pageSize));

        	
		Long active = 5L;
		map.addAttribute("active",active);
//	    map.addAttribute("info_cat",tdArticleCategoryService.findOne(catId) );   //找出栏目名称 zhangji
//	    map.addAttribute("catId", catId);
//	    map.addAttribute("mid", mid);
//	    map.addAttribute("info_category_list", catList); //栏目的列表 zhangji
//	    map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.coursePageSize));
	    
        return "/client/resource_detail";
    }
	
	
	
	// 合作机构
	@RequestMapping(value="/organization")
	public String organization(HttpServletRequest req,ModelMap map){
		tdCommonService.setHeader(map, req);
		
		List<TdArticleCategory> category_list = tdArticleCategoryService.findByMenuId(8L);
		
		map.addAttribute("actegory_list", category_list);
		for (int i = 0; i < category_list.size(); i++) {
			TdArticleCategory category = category_list.get(i);
			map.addAttribute("article_"+i+"_list", tdArticleService.findByMenuIdAndCategoryId(8L,category.getId(),0,ClientConstant.pageSize).getContent());
		}
		
		
		return "/client/organization";
	}
	
	//专项行动
	@RequestMapping(value="/activity/list")
	public String activityList(String activityType , Integer page , HttpServletRequest req,ModelMap map){
		tdCommonService.setHeader(map, req);

		if(null == page)
		{
			page = 0;
		}
		
        List<TdArticleCategory> systemList = tdArticleCategoryService
                .findByMenuId(13L);

        if (null != systemList && systemList.size() > 0) {
            for (TdArticleCategory tdCat : systemList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("培育活动"))
                {
            		if (null == activityType || activityType.equals(""))
            		{
	                    List<TdArticle> activityList =  tdArticleService
	                    			.findByMenuIdAndCategoryIdAndIsEnableOrderByCreateTimeDesc(13L, tdCat.getId());
	                           
	                    map.addAttribute("activity_list", activityList);
            		}
            		else
            		{
	                    Page<TdArticle> infoPage =  tdArticleService
                    			.findByMenuIdAndCategoryIdAndSourceAndIsEnableOrderByCreateTimeDesc(13L, tdCat.getId() , activityType ,  page, ClientConstant.pageSize);
                           
	                    map.addAttribute("info_page", infoPage);
	            		Long active = 2L;
	            		map.addAttribute("active",active);
	            		
	            		map.addAttribute("activityType", activityType);
	            		map.addAttribute("activityType_list" , tdActivityTypeService.findAllOrderBySortIdAsc());
	            		
	            		return "/client/news_activity_category";
	                    
            		}

                }
            }
        }
	

		Long active = 2L;
		map.addAttribute("active",active);
		
		map.addAttribute("activityType", activityType);
		map.addAttribute("activityType_list" , tdActivityTypeService.findAllOrderBySortIdAsc());
		
		return "/client/news_activity_list";
	}
	
	//活动详情
	@RequestMapping("/activity/detail/{activityId}")
    public String activityDetail(@PathVariable Long activityId, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == activityId )
        {
            return "/client/error_404";
        }             
        
        TdActivity activity = tdActivityService.findOne(activityId);
        
		Long active = 2L;
		map.addAttribute("active",active);
        map.addAttribute("info", activity);      
        
        return "/client/news_activity_detail";
    }
	
	//活动简介
	@RequestMapping("/aIn")
    public String activityIntro(
                            ModelMap map,
                            HttpServletRequest req){	
		
	    tdCommonService.setHeader(map, req);   
	    
	    List<TdArticle> articleList = tdArticleService.findByMenuId(13L);
    	List<TdArticle> intro1 = new ArrayList<>();
    	List<TdArticle> intro2 = new ArrayList<>();
	    for(TdArticle item : articleList)
	    {
	    	if (item.getTitle().equalsIgnoreCase("专项行动简介"))
	    	{
	    		map.addAttribute("article",item);
	    	}
	    	
	    	if (item.getTitle().equalsIgnoreCase("3年目标任务"))
	    	{
	    		intro1.add(item);
	    	}
	    	if (item.getTitle().equalsIgnoreCase("组织体系"))
	    	{
	    		intro1.add(item);
	    	}
	    	if (item.getTitle().equalsIgnoreCase("培育对象"))
	    	{
	    		intro1.add(item);
	    	}
	    	
	    	if (item.getTitle().equalsIgnoreCase("活动形式"))
	    	{
	    		intro2.add(item);
	    	}
	    	if (item.getTitle().equalsIgnoreCase("保障措施"))
	    	{
	    		intro2.add(item);
	    	}
	    	if (item.getTitle().equalsIgnoreCase("评委组成"))
	    	{
	    		intro2.add(item);
	    	}
	    	if (item.getTitle().equalsIgnoreCase("奖扶政策"))
	    	{
	    		intro2.add(item);
	    	}
	    	
	    }
	    map.addAttribute("intro1",intro1);
	    map.addAttribute("intro2",intro2);
	    
	       List<TdArticleCategory> systemList = tdArticleCategoryService
	                .findByMenuId(13L);

	        if (null != systemList && systemList.size() > 0) {
	            for (TdArticleCategory tdCat : systemList)
	            {
	                if (null != tdCat.getTitle() && tdCat.getTitle().equals("专项轮播"))
	                {
	                    map.addAttribute("breed_page", tdArticleService
	                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(13L,
	                                    tdCat.getId(), 0, ClientConstant.pageSize));
	                }
	                if (null != tdCat.getTitle() && tdCat.getTitle().equals("行动概况"))
	                {
	                    map.addAttribute("aboutActivity_page", tdArticleService
	                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(13L,
	                                    tdCat.getId(), 0, ClientConstant.pageSize));
	                }
	            }
	        }
	    Long active = 1L;
	    map.addAttribute("active" , active);
	        
        return "/client/news_activity_introduction";
    }	
	
	//合作机构
	@RequestMapping("/host")
    public String host(
                            ModelMap map,
                            HttpServletRequest req){	
		
	    tdCommonService.setHeader(map, req);   
	    
        List<TdArticleCategory> articleList = tdArticleCategoryService
                .findByMenuId(8L);
    	List<TdArticle> intro1 = null;
    	List<TdArticle> intro2 = null;
    	List<TdArticle> intro3 = null;
    	List<TdArticle> intro4 = null;
    	List<TdArticle> intro5 = null;
    	List<TdArticle> intro6 = null;
	    for(TdArticleCategory item : articleList)
	    {
	    	if (item.getTitle().equalsIgnoreCase("指导单位"))
	    	{
	    		intro1 = tdArticleService.findByCategoryId(item.getId());
	    		map.addAttribute("intro1" , intro1);
	    	}
	    	
	    	if (item.getTitle().equalsIgnoreCase("主办单位"))
	    	{
	    		intro2 = tdArticleService.findByCategoryId(item.getId());
	    		map.addAttribute("intro2" , intro2);
	    	}
	    	if (item.getTitle().equalsIgnoreCase("承办单位"))
	    	{
	    		intro3 = tdArticleService.findByCategoryId(item.getId());
	    		map.addAttribute("intro3" , intro3);
	    	}
	    	if (item.getTitle().equalsIgnoreCase("支持单位"))
	    	{
	    		intro4 = tdArticleService.findByCategoryId(item.getId());
	    		map.addAttribute("intro4" , intro4);
	    	}
	    	
	    	if (item.getTitle().equalsIgnoreCase("金融机构"))
	    	{
	    		intro5 = tdArticleService.findByCategoryId(item.getId());
	    		map.addAttribute("intro5" , intro5);
	    	}
	    	if (item.getTitle().equalsIgnoreCase("支持媒体"))
	    	{
	    		intro6 = tdArticleService.findByCategoryId(item.getId());
	    		map.addAttribute("intro6" , intro6);
	    	}
	    	
	    }

	    
	    map.addAttribute("menu_name1","指导单位");
	    map.addAttribute("menu_name2","主办单位");
	    map.addAttribute("menu_name3","承办单位");
	    map.addAttribute("menu_name4","支持单位");
	    map.addAttribute("menu_name5","金融机构");
	    map.addAttribute("menu_name6","支持媒体");
	    map.addAttribute("article_list",articleList);
	    
	    List<List<TdArticle>> hostList = new ArrayList<>();
	    hostList.add(intro1);
	    hostList.add(intro2);
	    hostList.add(intro3);
	    hostList.add(intro4);
	    hostList.add(intro5);
	    hostList.add(intro6);
	    map.addAttribute("host_list" , hostList);
	    
	    Long active = 6L;
	    map.addAttribute("active", active);
	    
        return "/client/news_host";
    }	
	
}
