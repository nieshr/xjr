package com.ynyes.kjxjr.service;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdExpert;
import com.ynyes.kjxjr.entity.TdRegionAdmin;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.repository.TdExpertRepo;


@Service
@Transactional
public class TdExpertService {
    @Autowired
    TdExpertRepo repository;
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
    public void delete(TdExpert e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }

    public void delete(List<TdExpert> entities)
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
    public TdExpert findOne(Long id)
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
    public List<TdExpert> findAll(Iterable<Long> ids)
    {
        return (List<TdExpert>) repository.findAll(ids);
    }
    /*
     * 关键字搜索
     */
    //搜索0
    public Page<TdExpert> findBySearch(String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByNameContainingOrderBySortIdAsc(keywords,pageRequest);
    }

    
    public Page<TdExpert> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdExpert> searchAllOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByNameContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    
    public TdExpert addNewUser(String username, String password, String mobile, String email ,String inCharge , String invest , String profile , Long statusId)
    {
        if (null == username || null == password || username.isEmpty() || password.isEmpty()|| email.isEmpty() || inCharge.isEmpty() || invest.isEmpty() || profile.isEmpty())
        {
            return null;
        }
        
        if (null != repository.findByUsername(username))
        {
            return null;
        }
        
        TdExpert expert = new TdExpert();
        
        expert.setUsername(username);
        expert.setPassword(password);
        expert.setUsermobile(mobile);
        expert.setEmail(email);
        expert.setStatusId(statusId); // 正常
        expert.setInCharge(inCharge);
        expert.setInvest(invest);
        expert.setProfile(profile);
        expert.setName(inCharge);
        
        return expert;
    }
    
    public List<TdExpert> save(List<TdExpert> entities)
    {
        return (List<TdExpert>) repository.save(entities);
    }
    
    
    public TdExpert save(TdExpert e)
    {
        return repository.save(e);
    }
    /**
	 * @author lc
	 * @注释：
	 */
    public TdExpert findbyUsername(String username){
		
    	return (repository.findByUsername(username)); 	
    }
    
}