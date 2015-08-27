package com.ynyes.lixue.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lixue.entity.TdDemand;
import com.ynyes.lixue.entity.TdOrder;
/**
 * TdDemand  实体数据库操作接口
 * @author Zhangji
 *
 */
public interface TdDemandRepo extends 
           PagingAndSortingRepository<TdDemand, Long>,
           JpaSpecificationExecutor<TdDemand> 
{
   List<TdDemand> findByStatusId(Long statusId);
   List<TdDemand> findByStatusIdOrderByIdDesc(Long statusId);
   Page<TdDemand> findByStatusIdOrderByIdDesc(Long statusId, Pageable page);
} 
