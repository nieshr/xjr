package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdUserPoint;

/**
 * TdUserPoint 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserPointRepo extends
		PagingAndSortingRepository<TdUserPoint, Long>,
		JpaSpecificationExecutor<TdUserPoint> 
{
    Page<TdUserPoint> findByUsernameOrderByIdDesc(String username, Pageable page);
    
    List<TdUserPoint> findByUsername(String username);
    
    
}
