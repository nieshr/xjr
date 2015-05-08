package com.ynyes.rongcheng.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.rongcheng.entity.TdAd;

/**
 * TdAd 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdAdRepo extends
		PagingAndSortingRepository<TdAd, Long>,
		JpaSpecificationExecutor<TdAd> 
{
    Page<TdAd> findByIsEnableTrueOrderBySortIdAsc(Pageable page);
    List<TdAd> findByTypeId(Long typeId);
}
