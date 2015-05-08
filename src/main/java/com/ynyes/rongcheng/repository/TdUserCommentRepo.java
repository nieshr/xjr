package com.ynyes.rongcheng.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.rongcheng.entity.TdUserComment;

/**
 * TdUserComment 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserCommentRepo extends
		PagingAndSortingRepository<TdUserComment, Long>,
		JpaSpecificationExecutor<TdUserComment> 
{
    // 根据状态查找
    Page<TdUserComment> findByStatusIdOrderBySortIdAsc(Long statusId, Pageable page);
    
    Page<TdUserComment> findByStatusIdOrderByIdDesc(Long statusId, Pageable page);
    
    Page<TdUserComment> findByUsernameContainingOrGoodsTitleContainingOrContentContainingOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdUserComment> findByUsernameContainingOrGoodsTitleContainingOrContentContainingOrderByIdDesc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdUserComment> findByUsernameContainingAndStatusIdOrGoodsTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderBySortIdAsc(String keywords1, 
            Long statusId1,
            String keywords2,
            Long statusId2,
            String keyword3,
            Long statusId3,
            Pageable page);
    
    Page<TdUserComment> findByUsernameContainingAndStatusIdOrGoodsTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderByIdDesc(String keywords1, 
            Long statusId1,
            String keywords2,
            Long statusId2,
            String keyword3,
            Long statusId3,
            Pageable page);
    
    List<TdUserComment> findByUsernameOrderByIdDesc(String username);
    
    Page<TdUserComment> findByUsernameOrderByIdDesc(String username, Pageable page);
    
    Page<TdUserComment> findByUsernameAndGoodsTitleContainingOrderByIdDesc(String username, String keywords, Pageable page);
    
    Page<TdUserComment> findByGoodsIdAndStatusIdOrderByIdDesc(Long goodsId, Long statusId, Pageable page);
}
