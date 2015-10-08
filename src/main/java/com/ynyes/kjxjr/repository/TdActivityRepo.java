package com.ynyes.kjxjr.repository;

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
    Page<TdActivity> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    
    /**
	 * @author lc
	 * @注释：
	 */
  

}