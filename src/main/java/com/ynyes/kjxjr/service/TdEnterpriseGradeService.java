package com.ynyes.kjxjr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.kjxjr.entity.TdEnterpriseGrade;
import com.ynyes.kjxjr.repository.TdEnterpriseGradeRepo;

@Service
@Transactional
public class TdEnterpriseGradeService {
	
	@Autowired
	private TdEnterpriseGradeRepo repository;

	/**
	 * 增/改
	 * @author dengxiao
	 */
	public TdEnterpriseGrade save(TdEnterpriseGrade entity){
		if(null == entity){
			return null;
		}
		return repository.save(entity);
	}
	
	/**
	 * 删
	 * @author dengxiao
	 */
	public void delete(Long id){
		if(null != id){
			repository.delete(id);
		}
	}
	
	/**
	 * 查询一个
	 * @author dengxiao
	 */
	public TdEnterpriseGrade findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	/**
	 * 查询全部
	 * @author dengxiao
	 */
	public List<TdEnterpriseGrade> findAll(){
		return (List<TdEnterpriseGrade>) repository.findAll();
	}
	
	/**
	 * 根据专家ID和活动ID查找评分表
	 * @author dengxiao
	 */
	public List<TdEnterpriseGrade> findByExpertIdAndActivityId(Long expertId,Long activityId){
		if(null == expertId || null == activityId){
			return null;
		}
		return repository.findByExpertIdAndActivityId(expertId, activityId);
	}
}
