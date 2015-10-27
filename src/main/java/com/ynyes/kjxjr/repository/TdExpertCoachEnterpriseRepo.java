package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdExpertCoachEnterprise;

public interface TdExpertCoachEnterpriseRepo extends PagingAndSortingRepository<TdExpertCoachEnterprise, Long>,
		JpaSpecificationExecutor<TdExpertCoachEnterprise> {

	// 根据专家ID查找所有当前辅导企业
	List<TdExpertCoachEnterprise> findByExpertIdAndIsGradeIsFalse(Long expertId);

	// 根据专家ID查找所有往期辅导企业
	List<TdExpertCoachEnterprise> findByExpertIdAndIsGradeIsTrue(Long expertId);

	TdExpertCoachEnterprise findByExpertIdAndEnterpriseId(Long expertId,Long enterpriseId);
	
	TdExpertCoachEnterprise findByEnterpriseId(Long enterpriseId);
}