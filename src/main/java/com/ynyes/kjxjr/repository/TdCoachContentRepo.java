package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdCoachContent;

public interface TdCoachContentRepo
		extends PagingAndSortingRepository<TdCoachContent, Long>, JpaSpecificationExecutor<TdCoachContent> {

	//根据企业ID和专家ID查询辅导内容，并按照时间排序
	List<TdCoachContent> findByExpertIdAndEnterpriseIdOrderByCoachDateAsc(Long expertId,Long enterpriseId);
	List<TdCoachContent> findByExpertIdAndActivityIdOrderByCoachDateAsc(Long expertId,Long activityId);
}
