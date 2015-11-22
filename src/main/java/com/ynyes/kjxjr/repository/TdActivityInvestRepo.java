package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdActivityInvest;

public interface TdActivityInvestRepo extends PagingAndSortingRepository<TdActivityInvest, Long>,
		JpaSpecificationExecutor<TdActivityInvest> {

	// 根据专家ID查找所有当前辅导企业
	List<TdActivityInvest> findByExpertIdAndIsGradeIsFalse(Long expertId);

	// 根据专家ID查找所有往期辅导企业
	List<TdActivityInvest> findByExpertIdAndIsGradeIsTrue(Long expertId);
	
	List<TdActivityInvest> findByEnterpriseIdOrderByExpertIdAsc(Long enterpriseId);

	TdActivityInvest findByExpertIdAndEnterpriseId(Long expertId,Long enterpriseId);
	
	TdActivityInvest findByEnterpriseId(Long enterpriseId);
}