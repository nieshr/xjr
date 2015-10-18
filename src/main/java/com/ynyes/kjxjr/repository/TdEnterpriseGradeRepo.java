package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdEnterpriseGrade;

public interface TdEnterpriseGradeRepo
		extends PagingAndSortingRepository<TdEnterpriseGrade, Long>, JpaSpecificationExecutor<TdEnterpriseGrade> {

	List<TdEnterpriseGrade> findByExpertIdAndActivityId(Long expertId,Long activityId);
	List<TdEnterpriseGrade> findByEnterpriseIdAndActivityIdOrderByExpertIdAsc(Long enterpriseId,Long activityId);
	
	TdEnterpriseGrade findByExpertIdAndActivityIdAndNumber(Long expertId,Long activityId,String number);
}
