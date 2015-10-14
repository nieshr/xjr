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
    Page<TdEnterprise> findByStatusIdAndTitleContainingOrderBySortIdAsc( Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByStatusIdOrderBySortIdAsc( Long statusId ,Pageable page);
    Page<TdEnterprise> findByAreaOrderByNumberAsc(String area, Pageable page);
    
    
    Page<TdEnterprise> findByAreaAndStatusIdAndTitleContainingOrderBySortIdAsc(String area, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(String type, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByFormTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(Long formType, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByAreaAndTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(String Area,String type, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByAreaAndFormTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(String Area,Long formType, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByTypeAndFormTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(String type,Long formType, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByAreaAndTypeAndFormTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(String Area,String type,Long formType, Long statusId , String keywords, Pageable page);
    
    Page<TdEnterprise> findByAreaAndStatusIdOrderBySortIdAsc(String area, Long statusId , Pageable page);
    Page<TdEnterprise> findByTypeAndStatusIdOrderBySortIdAsc(String type,Long statusId , Pageable page);
    Page<TdEnterprise> findByFormTypeAndStatusIdOrderBySortIdAsc(Long formType, Long statusId , Pageable page);
    Page<TdEnterprise> findByAreaAndTypeAndStatusIdOrderBySortIdAsc(String Area,String type,Long statusId , Pageable page);
    Page<TdEnterprise> findByAreaAndFormTypeAndStatusIdOrderBySortIdAsc(String Area,Long formType,Long statusId ,  Pageable page);
    Page<TdEnterprise> findByTypeAndFormTypeAndStatusIdOrderBySortIdAsc(String type,Long formType, Long statusId , Pageable page);
    Page<TdEnterprise> findByAreaAndTypeAndFormTypeAndStatusIdOrderBySortIdAsc(String Area,String type,Long formType,Long statusId , Pageable page);
    
    
    
    /**
	 * @author lc
	 * @注释：
	 */
    TdEnterprise findByUsername(String username);

}