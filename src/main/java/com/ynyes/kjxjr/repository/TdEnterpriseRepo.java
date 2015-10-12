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
    Page<TdEnterprise> findByAreaOrderByNumberAsc(String area, Pageable page);
    
    List<TdEnterprise> findByIsEnableTrue();
    
    Page<TdEnterprise> findByAreaAndTitleContainingOrderBySortIdAsc(String area,String keywords, Pageable page);
    Page<TdEnterprise> findByTypeAndTitleContainingOrderBySortIdAsc(String type,String keywords, Pageable page);
    Page<TdEnterprise> findByFormTypeAndTitleContainingOrderBySortIdAsc(Long formType,String keywords, Pageable page);
    Page<TdEnterprise> findByAreaAndTypeAndTitleContainingOrderBySortIdAsc(String Area,String type,String keywords, Pageable page);
    Page<TdEnterprise> findByAreaAndFormTypeAndTitleContainingOrderBySortIdAsc(String Area,Long formType,String keywords, Pageable page);
    Page<TdEnterprise> findByTypeAndFormTypeAndTitleContainingOrderBySortIdAsc(String type,Long formType,String keywords, Pageable page);
    Page<TdEnterprise> findByAreaAndTypeAndFormTypeAndTitleContainingOrderBySortIdAsc(String Area,String type,Long formType,String keywords, Pageable page);
    
    Page<TdEnterprise> findByAreaOrderBySortIdAsc(String area, Pageable page);
    Page<TdEnterprise> findByTypeOrderBySortIdAsc(String type,Pageable page);
    Page<TdEnterprise> findByFormTypeOrderBySortIdAsc(Long formType, Pageable page);
    Page<TdEnterprise> findByAreaAndTypeOrderBySortIdAsc(String Area,String type,Pageable page);
    Page<TdEnterprise> findByAreaAndFormTypeOrderBySortIdAsc(String Area,Long formType, Pageable page);
    Page<TdEnterprise> findByTypeAndFormTypeOrderBySortIdAsc(String type,Long formType, Pageable page);
    Page<TdEnterprise> findByAreaAndTypeAndFormTypeOrderBySortIdAsc(String Area,String type,Long formType,Pageable page);
    
    
    
    /**
	 * @author lc
	 * @注释：
	 */
    TdEnterprise findByUsername(String username);

}