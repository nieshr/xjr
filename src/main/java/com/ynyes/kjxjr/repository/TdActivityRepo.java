package com.ynyes.kjxjr.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdActivity;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdActivityRepo extends
		PagingAndSortingRepository<TdActivity, Long>,
		JpaSpecificationExecutor<TdActivity> 
{ 
	List<TdActivity> findByRegion(String region);
	
    Page<TdActivity> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    TdActivity findByStatusId(Long statusId);
    Page<TdActivity> findByStatusId(Long statusId, Pageable page);
    Page<TdActivity> findByRegionAndStatusIdAndPrepareOffAfterAndPrepareOnBefore(String region , Long statusId , Date current1 , Date current2 , Pageable page);
    Page<TdActivity> findByRegionAndPrepareOffAfterAndPrepareOnBefore(String region , Date current1 , Date current2 , Pageable page);
    Page<TdActivity> findByRegion(String region , Pageable page);
    /**
	 * @author lc
	 * @注释：
	 */
  

}