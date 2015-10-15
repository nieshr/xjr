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

import com.ynyes.kjxjr.entity.TdActivityAdmin;
import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdRegionAdmin;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.repository.TdRegionAdminRepo;


@Service
@Transactional
public class TdRegionAdminService {
    @Autowired
    TdRegionAdminRepo repository;
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
    public void delete(TdRegionAdmin e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }

    public void delete(List<TdRegionAdmin> entities)
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
    public TdRegionAdmin findOne(Long id)
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
    public List<TdRegionAdmin> findAll(Iterable<Long> ids)
    {
        return (List<TdRegionAdmin>) repository.findAll(ids);
    }

    
    public Page<TdRegionAdmin> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    
    public TdRegionAdmin addNewUser(String username, String password, String mobile, String email ,String inCharge , Long statusId)
    {
        if (null == username || null == password || username.isEmpty() || password.isEmpty()|| email.isEmpty() || inCharge.isEmpty())
        {
            return null;
        }
        
        if (null != repository.findByUsername(username))
        {
            return null;
        }
        
        TdRegionAdmin region = new TdRegionAdmin();
        
        region.setUsername(username);
        region.setPassword(password);
        region.setUsermobile(mobile);
        region.setEmail(email);
        region.setStatusId(statusId); 
        region.setInCharge(inCharge);
        
        return region;
    }
    public List<TdRegionAdmin> save(List<TdRegionAdmin> entities)
    {
        return (List<TdRegionAdmin>) repository.save(entities);
    }
    
    public TdRegionAdmin save(TdRegionAdmin e)
    {
        return repository.save(e);
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    public TdRegionAdmin findbyUsername(String username){
		
    	return (repository.findByUsername(username)); 	
    }
    
}