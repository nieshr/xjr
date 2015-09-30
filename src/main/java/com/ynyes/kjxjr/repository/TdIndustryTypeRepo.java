package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdIndustryType;

/**
 * TdIndustryType 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdIndustryTypeRepo extends
		PagingAndSortingRepository<TdIndustryType, Long>,
		JpaSpecificationExecutor<TdIndustryType> 
{
    Page<TdIndustryType> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    
    List<TdIndustryType> findByIsEnableTrueOrderBySortIdAsc();
}
