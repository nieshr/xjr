package com.ynyes.zhangyan.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.zhangyan.entity.TdGoodsParameter;

/**
 * TdArticle 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdGoodsParameterRepo extends
		PagingAndSortingRepository<TdGoodsParameter, Long>,
		JpaSpecificationExecutor<TdGoodsParameter> 
{
    
}
