package com.ynyes.kjxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.kjxjr.entity.TdUserMessage;
import com.ynyes.kjxjr.entity.TdOrder;
import com.ynyes.kjxjr.repository.TdUserMessageRepo;


/**
 * TdUserMessage 服务类
 * @author Zhangji
 *
 */

@Service
@Transactional
public class TdUserMessageService {
	@Autowired
	TdUserMessageRepo repository;

	public List<TdUserMessage> findAll(){
	        return (List<TdUserMessage>) repository.findAll();
	}
	
	public List<TdUserMessage> findByNameOrderByTimeDesc(){
		
		return (List<TdUserMessage>) repository.findAll();		
	}
	
	/**
	 * 留言板
	 * @param enterpriseId
	 * @param regionAdminId
	 * @return
	 */
    public List<TdUserMessage> findByEnterpriseIdAndRegionAdminIdOrderByTimeAsc(Long enterpriseId , Long regionAdminId )
    {
        return repository.findByEnterpriseIdAndRegionAdminIdOrderByTimeAsc(enterpriseId , regionAdminId);
    }
    
	
    public List<TdUserMessage> findBystatusE(Long statusE){
    	return repository.findByStatusE(statusE);
    }

    public Page<TdUserMessage> findBystatusEOrderByIdDesc(long statusE, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusEOrderByIdDesc(statusE, pageRequest);
    }
    
    /**
     * 根据企业ID和区县管理员ID查找站内信页面
     * @param enterpriseId
     * @param regionAdminId
     * @param page
     * @param size
     * @return
     */
    public Page<TdUserMessage> findByEnterpriseIdAndRegionAdminIdOrderByTimeDesc(Long enterpriseId , Long regionAdminId ,int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "time"));
        
        return repository.findByEnterpriseIdAndRegionAdminIdOrderByTimeDesc(enterpriseId , regionAdminId ,pageRequest);
    }
    
    /**
     * 根据发起方和企业id查找
     * @param enterpriseId
     * @param speaker
     * @param page
     * @param size
     * @return
     */
    public Page<TdUserMessage> findByEnterpriseIdAndSpeakerOrderByTimeDesc(Long enterpriseId , Long speaker ,int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "time"));
        
        return repository.findByEnterpriseIdAndSpeakerOrderByTimeDesc(enterpriseId , speaker ,pageRequest);
    }
    
    public Page<TdUserMessage> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
	
	//筛选statusE为1L的
    public List<TdUserMessage> findByStatusEAndIsShowable()
    {

       
        
        return repository.findByStatusEOrderByIdDesc(1L);
    }
    

	public TdUserMessage findOne(Long id)
    {
		if(null == id)
		{
			return null;
		}
		
        return repository.findOne(id);
    }
	
	public Page<TdUserMessage> findAllOrderByTimeDesc(int page,int size){

		PageRequest pageRequest = new PageRequest(page,size,new Sort(Direction.DESC,"time"));
		return repository.findAll(pageRequest);
	}
	



	/**
	 * 添加
	 */

	public void save(TdUserMessage e){
		repository.save(e);
	}
	
	 public List<TdUserMessage> save(List<TdUserMessage> entities)
	 {
	        
	     return (List<TdUserMessage>) repository.save(entities);
  }
	/**
	 * 删除
	 */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }



}
