package com.ynyes.kjxjr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.kjxjr.entity.TdCoachContent;
import com.ynyes.kjxjr.repository.TdCoachContentRepo;

@Service
@Transactional
public class TdCoachContentService {

	@Autowired
	private TdCoachContentRepo repository;
	
	public TdCoachContent save(TdCoachContent entity){
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
	
	public TdCoachContent findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdCoachContent> findAll(){
		return (List<TdCoachContent>) repository.findAll();
	}
	
	public List<TdCoachContent> findByExpertIdAndEnterpriseIdOrderByCoachDateAsc(Long expertId,Long enterpriseId){
		if(null == expertId||null == enterpriseId){
			return null;
		}
		return repository.findByExpertIdAndEnterpriseIdOrderByCoachDateAsc(expertId, enterpriseId);
	}
	
}
