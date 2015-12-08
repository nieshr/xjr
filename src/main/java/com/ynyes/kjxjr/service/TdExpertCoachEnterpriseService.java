package com.ynyes.kjxjr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.kjxjr.entity.TdEnterpriseGrade;
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
	
	public void delete(List<TdExpertCoachEnterprise> entities)
	{
		if (null != entities)
		{
			repository.delete(entities);
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
	
	/**
	 * 查找一个活动的路演辅导
	 * @param enterpriseId
	 * @return
	 */
	public List<TdExpertCoachEnterprise> findByEnterpriseIdOrderByExpertIdAsc(Long enterpriseId){
		if(null == enterpriseId){
			return null;
		}
		return repository.findByEnterpriseIdOrderByExpertIdAsc(enterpriseId);
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
	
	public List<TdExpertCoachEnterprise> findByEnterpriseIdOrderByEnterpriseIdAsc(Long enterpriseId){
		if(null == enterpriseId){
			return null;
		}
		return repository.findByEnterpriseIdOrderByEnterpriseIdAsc(enterpriseId);
	}
	
	/**
	 * 路演辅导改成了专家和活动的多对一
	 * @param expertId
	 * @param activityId
	 * @return
	 */
	public TdExpertCoachEnterprise findByExpertIdAndEnterpriseId(Long expertId,Long enterpriseId){
		if(null == expertId||null == enterpriseId){
			return null;
		}
		return repository.findByExpertIdAndEnterpriseId(expertId, enterpriseId);
	}
	

	
	public TdExpertCoachEnterprise findByEnterpriseId(Long enterpriseId){
		if(null == enterpriseId){
			return null;
		}
		return repository.findByEnterpriseId(enterpriseId);
	}
}
