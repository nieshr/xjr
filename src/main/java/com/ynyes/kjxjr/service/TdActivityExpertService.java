package com.ynyes.kjxjr.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.kjxjr.entity.TdActivityExpert;
import com.ynyes.kjxjr.repository.TdActivityExpertRepo;


@Service
@Transactional
public class TdActivityExpertService {
    @Autowired
    TdActivityExpertRepo repository;
    @Autowired
    TdUserService tdUserService;
    
    /**
     * 删除
     * 
     * @param id 菜单项ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdActivityExpert e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }

    public void delete(List<TdActivityExpert> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdActivityExpert findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    

    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdActivityExpert> findAll(Iterable<Long> ids)
    {
        return (List<TdActivityExpert>) repository.findAll(ids);
    }
    
    public TdActivityExpert findByActivityIdAndExpertId(Long activityId,Long expertId)
    {
    	if (null == expertId || null ==activityId )
    	{
    		return null;
    	}
    	return repository.findByActivityIdAndExpertId(activityId,expertId);
    }

    
    public Page<TdActivityExpert> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    public Page<TdActivityExpert> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
    
    public List<TdActivityExpert> findByActivityId(Long activityId)
    {
        return (List<TdActivityExpert>) repository.findByActivityId(activityId);
    }
    
    public List<TdActivityExpert> findByActivityIdOrderByExpertIdAsc(Long activityId)
    {
        return (List<TdActivityExpert>) repository.findByActivityIdOrderByExpertIdAsc(activityId);
    }

    
    /**
     * 保存
     * @param e
     * @return
     */
    public TdActivityExpert save(TdActivityExpert e)
    {
        return repository.save(e);
    }
    
    public List<TdActivityExpert> save(List<TdActivityExpert> entities)
    {
        return (List<TdActivityExpert>) repository.save(entities);
    }
    
    /**
	 * @author lc
	 * @注释：
	 */

    
    public List<TdActivityExpert> findByUsername(String username){
    	if(null == username){
    		return null;
    	}
    	return repository.findByUsername(username);
    }
    
    
    
    
    
    
    
    
    
    /**
     * 根据专家ID查找还未开始的活动
     * @author dengxiao
     */
    public List<TdActivityExpert> findByExpertIdAndCreateTimeAfterOrderByCreateTimeDesc(Long expertId){
    	if(null == expertId){
    		return null;
    	}
    	return repository.findByExpertIdAndCreateTimeAfterOrderByCreateTimeDesc(expertId, new Date());
    }
    
}