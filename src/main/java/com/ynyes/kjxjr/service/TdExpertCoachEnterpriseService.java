package com.ynyes.kjxjr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.kjxjr.entity.TdExpertCoachEnterprise;
import com.ynyes.kjxjr.repository.TdExpertCoachEnterpriseRepo;

@Service
@Transactional
public class TdExpertCoachEnterpriseService {

	@Autowired
	private TdExpertCoachEnterpriseRepo repository;
	
	public TdExpertCoachEnterprise save(TdExpertCoachEnterprise entity){
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
	
	public TdExpertCoachEnterprise findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdExpertCoachEnterprise> findAll(){
		return (List<TdExpertCoachEnterprise>) repository.findAll();
	}
	
	public List<TdExpertCoachEnterprise> findByExpertIdAndIsGradeIsFalse(Long expertId){
		if(null == expertId){
			return null;
		}
		return repository.findByExpertIdAndIsGradeIsFalse(expertId);
	}
	
	public List<TdExpertCoachEnterprise> findByExpertIdAndIsGradeIsTrue(Long expertId){
		if(null == expertId){
			return null;
		}
		return repository.findByExpertIdAndIsGradeIsTrue(expertId);
	}
}
