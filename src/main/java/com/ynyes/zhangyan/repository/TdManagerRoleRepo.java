package com.ynyes.zhangyan.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.zhangyan.entity.TdManagerRole;

/**
 * TdManagerRole 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdManagerRoleRepo extends
		PagingAndSortingRepository<TdManagerRole, Long>,
		JpaSpecificationExecutor<TdManagerRole> 
{

}
