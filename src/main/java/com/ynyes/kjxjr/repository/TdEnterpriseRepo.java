package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdEnterprise;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdEnterpriseRepo extends
		PagingAndSortingRepository<TdEnterprise, Long>,
		JpaSpecificationExecutor<TdEnterprise> 
{ 
    Page<TdEnterprise> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    
    List<TdEnterprise> findByIsEnableTrue();
    
    
    /**
	 * @author lc
	 * @注释：
	 */
    TdEnterprise findByUsername(String username);

}