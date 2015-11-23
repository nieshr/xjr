package com.ynyes.kjxjr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.kjxjr.entity.TdEnterpriseGrade;
import com.ynyes.kjxjr.entity.TdActivityInvest;
import com.ynyes.kjxjr.repository.TdActivityInvestRepo;

@Service
@Transactional
public class TdActivityInvestService {

	@Autowired
	private TdActivityInvestRepo repository;
	
	public TdActivityInvest save(TdActivityInvest entity){
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
	
	public void delete(List<TdActivityInvest> entities)
	{
		if (null != entities)
		{
			repository.delete(entities);
		}
	}
	
	public TdActivityInvest findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdActivityInvest> findAll(){
		return (List<TdActivityInvest>) repository.findAll();
	}
	
	/**
	 * 查找一个活动的路演辅导
	 * @param enterpriseId
	 * @return
	 */
	public List<TdActivityInvest> findByEnterpriseIdOrderByExpertIdAsc(Long enterpriseId){
		if(null == enterpriseId){
			return null;
		}
		return repository.findByEnterpriseIdOrderByExpertIdAsc(enterpriseId);
	}
	
	//总分页面根据企业id和活动id查找投资情况
	public TdActivityInvest findByEnterpriseIdAndActivityId(Long enterpriseId , Long activityId){
		if(null == enterpriseId || null == activityId){
			return null;
		}
		return repository.findByEnterpriseIdAndActivityId(enterpriseId ,activityId);
	}
	
	public List<TdActivityInvest> findByExpertIdAndIsGradeIsFalse(Long expertId){
		if(null == expertId){
			return null;
		}
		return repository.findByExpertIdAndIsGradeIsFalse(expertId);
	}
	
	public List<TdActivityInvest> findByExpertId(Long expertId){
		if(null == expertId){
			return null;
		}
		return repository.findByExpertId(expertId);
	}
	
	public List<TdActivityInvest> findByExpertIdAndIsGradeIsTrue(Long expertId){
		if(null == expertId){
			return null;
		}
		return repository.findByExpertIdAndIsGradeIsTrue(expertId);
	}
	
	/**
	 * 路演辅导改成了专家和活动的多对一
	 * @param expertId
	 * @param activityId
	 * @return
	 */
	public TdActivityInvest findByExpertIdAndEnterpriseId(Long expertId,Long enterpriseId){
		if(null == expertId||null == enterpriseId){
			return null;
		}
		return repository.findByExpertIdAndEnterpriseId(expertId, enterpriseId);
	}
	

	
	public TdActivityInvest findByEnterpriseId(Long enterpriseId){
		if(null == enterpriseId){
			return null;
		}
		return repository.findByEnterpriseId(enterpriseId);
	}
}
