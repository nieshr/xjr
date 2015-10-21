package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdUserMessage;
import com.ynyes.kjxjr.entity.TdOrder;
/**
 * TdUserMessage  实体数据库操作接口
 * @author Zhangji
 *
 */
public interface TdUserMessageRepo extends 
           PagingAndSortingRepository<TdUserMessage, Long>,
           JpaSpecificationExecutor<TdUserMessage> 
{
   List<TdUserMessage> findByStatusId(Long statusId);
   List<TdUserMessage> findByStatusIdOrderByIdDesc(Long statusId);
   List<TdUserMessage> findByEnterpriseIdAndRegionAdminIdOrderByTimeAsc(Long enterpriseId,Long regionAdminId);
   
   Page<TdUserMessage> findByStatusIdOrderByIdDesc(Long statusId, Pageable page);
   Page<TdUserMessage> findByEnterpriseIdAndRegionAdminIdOrderByTimeDesc(Long enterpriseId,Long regionAdminId , Pageable page);
   Page<TdUserMessage> findByEnterpriseIdAndSpeakerOrderByTimeDesc(Long enterpriseId,Long speaker , Pageable page);
} 
