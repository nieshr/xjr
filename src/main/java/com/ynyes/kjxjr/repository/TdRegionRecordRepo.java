package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdRegionRecord;

public interface TdRegionRecordRepo
		extends PagingAndSortingRepository<TdRegionRecord, Long>, JpaSpecificationExecutor<TdRegionRecord> {

	//根据企业ID和专家ID查询辅导内容，并按照时间排序
	List<TdRegionRecord> findByRegionAdminIdAndEnterpriseIdOrderByCoachDateAsc(Long regionAdminId,Long enterpriseId);
}
