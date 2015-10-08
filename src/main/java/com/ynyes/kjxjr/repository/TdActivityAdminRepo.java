package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdActivityAdmin;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdActivityAdminRepo extends
		PagingAndSortingRepository<TdActivityAdmin, Long>,
		JpaSpecificationExecutor<TdActivityAdmin> 
{ 
    Page<TdActivityAdmin> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    
    /**
	 * @author lc
	 * @注释：
	 */
    TdActivityAdmin findByUsername(String username);

}