package com.ynyes.kjxjr.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdActivityExpert;

/**
 * TdActivityExpert 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdActivityExpertRepo extends
		PagingAndSortingRepository<TdActivityExpert, Long>,
		JpaSpecificationExecutor<TdActivityExpert> 
{ 
	TdActivityExpert findByActivityIdAndExpertId(Long activityId,Long ExpertId);
	List<TdActivityExpert> findByActivityId(Long activityId);
	List<TdActivityExpert> findByActivityIdOrderByExpertIdAsc(Long activityId);

	
	List<TdActivityExpert> findByExpertIdAndCreateTimeAfterOrderByCreateTimeDesc(Long expertId,Date create);
}