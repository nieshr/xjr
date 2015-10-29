package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdActivityEnterprise;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdActivityEnterpriseRepo extends
		PagingAndSortingRepository<TdActivityEnterprise, Long>,
		JpaSpecificationExecutor<TdActivityEnterprise> 
{ 
	TdActivityEnterprise findByActivityIdAndEnterpriseId(Long activityId,Long enterpriseId);
	TdActivityEnterprise findByActivityIdAndSortId(Long activityId,Long sortId);
	
	List<TdActivityEnterprise> findByActivityId(Long activityId);
	List<TdActivityEnterprise> findByActivityIdAndStatusId(Long activityId , Long StatusId);
	List<TdActivityEnterprise> findByActivityIdAndStatusIdOrderBySortIdAsc(Long activityId , Long StatusId);
	List<TdActivityEnterprise> findByActivityIdAndStatusIdOrderByTotalPointAsc(Long activityId , Long StatusId);
	
	Page<TdActivityEnterprise> findByEnterpriseIdAndStatusIdOrderByIdDesc(Long enterpriseId , Long StatusId , Pageable page);
	Page<TdActivityEnterprise> findByEnterpriseIdOrderByIdDesc(Long enterpriseId , Pageable page);
	Page<TdActivityEnterprise> findByActivityIdAndEnterpriseTitleContainingOrderByIdDesc(Long activityId , String keywords , Pageable page);
	Page<TdActivityEnterprise> findByActivityIdOrderByIdDesc(Long activityId , Pageable page);
}