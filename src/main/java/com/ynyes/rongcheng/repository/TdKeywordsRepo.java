package com.ynyes.rongcheng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.rongcheng.entity.TdKeywords;

/**
 * TdKeywords 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdKeywordsRepo extends
		PagingAndSortingRepository<TdKeywords, Long>,
		JpaSpecificationExecutor<TdKeywords> 
{
    List<TdKeywords> findByIsEnableTrueOrderBySortIdAsc();
}
