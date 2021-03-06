package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdArticle;

/**
 * TdArticle 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdArticleRepo extends
		PagingAndSortingRepository<TdArticle, Long>,
		JpaSpecificationExecutor<TdArticle> 
{
    // 通过父类型查找
    List<TdArticle> findByMenuIdOrderBySortIdAsc(Long menuId);
    List<TdArticle> findByMenuIdOrderByCreateTimeDesc(Long menuId); //zhangji
    List<TdArticle> findByMenuId(Long menuId , Sort sort); 
    
    Page<TdArticle> findByMenuIdOrderBySortIdAsc(Long menuId, Pageable page);
    
    Page<TdArticle> findByMenuIdAndStatusIdOrderByIdDesc(Long menuId, Long statusId, Pageable page);
    Page<TdArticle> findByMenuIdAndRecommendIdOrderBySortIdAsc(Long menuId, Long recommendId, Pageable page); //zhangji
    
    Page<TdArticle> findByMenuIdAndStatusIdOrderByIdAsc(Long menuId, Long statusId, Pageable page);//顺序查找 zhangji
    
    Page<TdArticle> findByMenuIdAndCategoryIdOrderBySortIdAsc(Long menuId, Long catId, Pageable page);
    
    Page<TdArticle> findByMenuIdAndCategoryIdAndStatusIdOrderByIdDesc(Long menuId, Long catId, Long statusId, Pageable page);
    
    Page<TdArticle> findByMenuIdAndCategoryIdAndStatusIdOrderBySortIdAsc(Long menuId, Long catId, Long statusId, Pageable page);
    
    //顺序排列 zhangji
    Page<TdArticle> findByMenuIdAndCategoryIdAndStatusIdOrderByIdAsc(Long menuId, Long catId, Long statusId, Pageable page);
    
    Page<TdArticle> findByMenuIdAndCategoryIdAndSourceAndStatusIdOrderByCreateTimeDesc(Long menuId, Long catId, String source ,Long statusId, Pageable page);
    
    Page<TdArticle> findByChannelIdAndCategoryIdOrderBySortIdAsc(Long channeldId, Long catId, Pageable page);
    
    List<TdArticle> findByMenuIdAndCategoryIdAndStatusIdOrderByIdDesc(Long menuId, Long catId, Long statusId);
    
    List<TdArticle> findByCategoryIdAndChannelIdAndParamIsSearchableTrueOrderBySortIdAsc(Long catId, Long channelId);
    
    List<TdArticle> findByCategoryIdOrderBySortIdAsc(Long catId);
    Page<TdArticle> findByCategoryIdOrderBySortIdAsc(Long catId, Pageable page);
    
    List<TdArticle> findByChannelIdAndCategoryIdOrderBySortIdAsc(Long channeldId, Long catId);
    Page<TdArticle> findByChannelIdOrderBySortIdAsc(Long channeldId, Pageable page);
    
    //搜索全部 zhangji
    Page<TdArticle> findByTitleContainingIgnoreCaseAndStatusIdOrBriefContainingIgnoreCaseAndStatusId(
    		String keywords,Long statusId, String keywords1,Long statusId1,Pageable page);
    
    //搜索课程 zhangji
    Page<TdArticle> findByTitleContainingIgnoreCaseAndStatusIdAndMenuIdOrBriefContainingIgnoreCaseAndStatusIdAndMenuId(
    		String keywords,Long statusId,Long menuId, String keywords1,Long statusId1,Long menuId1, Pageable page);
    
    int countByCategoryId(Long catId);
    
    TdArticle findByRecommendIdAndMenuId(Long recommendId , Long  menuId);
    
    //根据类别查找，按时间排序
    List<TdArticle> findByMenuIdAndCategoryIdAndStatusIdOrderByCreateTimeDesc(Long menuId, Long catId, Long statusId);
    List<TdArticle> findByMenuIdAndCategoryIdAndSourceAndStatusIdOrderByCreateTimeDesc(Long menuId, Long catId, String source ,  Long statusId);
}
