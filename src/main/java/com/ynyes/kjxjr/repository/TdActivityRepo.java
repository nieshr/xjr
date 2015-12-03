package com.ynyes.kjxjr.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdActivity;

/**
 * TdActivity 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdActivityRepo extends
		PagingAndSortingRepository<TdActivity, Long>,
		JpaSpecificationExecutor<TdActivity> 
{ 
	List<TdActivity> findByRegion(String region);
	List<TdActivity> findByActivityTypeOrderByDateDesc(String activityactivityType);
	
    Page<TdActivity> findByTitleContainingOrderByDateDesc(String keywords, Pageable page);
    TdActivity findByStatusId(Long statusId);
    Page<TdActivity> findByStatusId(Long statusId, Pageable page);
    Page<TdActivity> findByRegionAndStatusIdAndPrepareOffAfterAndPrepareOnBefore(String region , Long statusId , Date current1 , Date current2 , Pageable page);
    Page<TdActivity> findByRegionAndPrepareOffAfterAndPrepareOnBefore(String region , Date current1 , Date current2 , Pageable page);
    Page<TdActivity> findByRegion(String region , Pageable page);
    /**
     * 活动列表条件筛选
	 * @author lc
	 * @注释：
	 */
    

    Page<TdActivity> findByRegionAndTitleContainingOrderByDateDesc(String region,  String keywords, Pageable page);
    Page<TdActivity> findByActivityTypeAndTitleContainingOrderByDateDesc(String activityType, String keywords, Pageable page);
    Page<TdActivity> findByTimeIdAndTitleContainingOrderByDateDesc(Long timeId,  String keywords, Pageable page);
    Page<TdActivity> findByRegionAndActivityTypeAndTitleContainingOrderByDateDesc(String region,String activityType, String keywords, Pageable page);
    Page<TdActivity> findByRegionAndTimeIdAndTitleContainingOrderByDateDesc(String region,Long timeId,String keywords, Pageable page);
    Page<TdActivity> findByActivityTypeAndTimeIdAndTitleContainingOrderByDateDesc(String activityType,Long timeId,  String keywords, Pageable page);
    Page<TdActivity> findByRegionAndActivityTypeAndTimeIdAndTitleContainingOrderByDateDesc(String region,String activityType,Long timeId, String keywords, Pageable page);
    
    Page<TdActivity> findByRegionOrderByDateDesc(String region,  Pageable page);
    Page<TdActivity> findByActivityTypeOrderByDateDesc(String activityType,Pageable page);
    Page<TdActivity> findByTimeIdOrderByDateDesc(Long timeId, Pageable page);
    Page<TdActivity> findByRegionAndActivityTypeOrderByDateDesc(String region,String activityType, Pageable page);
    Page<TdActivity> findByRegionAndTimeIdOrderByDateDesc(String region,Long timeId,  Pageable page);
    Page<TdActivity> findByActivityTypeAndTimeIdOrderByDateDesc(String activityType,Long timeId, Pageable page);
    Page<TdActivity> findByRegionAndActivityTypeAndTimeIdOrderByDateDesc(String region,String activityType,Long timeId,Pageable page);
    
  

}