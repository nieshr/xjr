package com.ynyes.kjxjr.repository;

import java.util.List;

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
	List<TdActivityEnterprise> findByActivityId(Long activityId);

}