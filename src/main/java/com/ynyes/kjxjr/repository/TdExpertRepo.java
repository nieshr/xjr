package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdExpert;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdExpertRepo extends
		PagingAndSortingRepository<TdExpert, Long>,
		JpaSpecificationExecutor<TdExpert> 
{ 
    Page<TdExpert> findByNameContainingOrderBySortIdAsc(String keywords, Pageable page);
    
    /**
	 * @author lc
	 * @注释：
	 */
    TdExpert findByUsername(String username);

}