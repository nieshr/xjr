package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdDiySite;

/**
 * TdDiySite 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdDiySiteRepo extends
		PagingAndSortingRepository<TdDiySite, Long>,
		JpaSpecificationExecutor<TdDiySite> 
{
    Page<TdDiySite> findByUsernameContainingOrInChargeContainingOrMobileContainingOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    Page<TdDiySite> findByRoleIdOrderBySortIdAsc(Long roleId, Pageable page);
    Page<TdDiySite> findByRoleIdAndStatusIdOrderBySortIdAsc(Long roleId, Long statusId ,Pageable page);
    
    List<TdDiySite> findByIsEnableTrue();
    
    List<TdDiySite> findByCityAndIsEnableTrueOrderBySortIdAsc(String city);
    
    /**
	 * @author lc
	 * @注释：
	 */
    TdDiySite findByUsernameAndIsEnableTrue(String username);
}
