package com.ynyes.kjxjr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.kjxjr.entity.TdRegionRecord;
import com.ynyes.kjxjr.repository.TdRegionRecordRepo;

@Service
@Transactional
public class TdRegionRecordService {

	@Autowired
	private TdRegionRecordRepo repository;
	
	public TdRegionRecord save(TdRegionRecord entity){
		if(null == entity){
			return null;
		}
		return repository.save(entity);
	}
	
	public void delete(Long id){
		if(null != id){
			repository.delete(id);
		}
	}
	
	public TdRegionRecord findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdRegionRecord> findAll(){
		return (List<TdRegionRecord>) repository.findAll();
	}
	
	public List<TdRegionRecord> findByRegionAdminIdAndEnterpriseIdOrderByCoachDateAsc(Long regionAdminId,Long enterpriseId){
		if(null == regionAdminId||null == enterpriseId){
			return null;
		}
		return repository.findByRegionAdminIdAndEnterpriseIdOrderByCoachDateAsc(regionAdminId, enterpriseId);
	}
	
}
