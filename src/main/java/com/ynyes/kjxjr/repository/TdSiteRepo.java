package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdSite;

/**
 * TdSite 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdSiteRepo extends
		PagingAndSortingRepository<TdSite, Long>,
		JpaSpecificationExecutor<TdSite> 
{
    TdSite findByTitle(String title);
    TdSite findByTitleAndIdNot(String title, Long id);
    
    Page<TdSite> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    List<TdSite> findByIsEnableTrue();
}
