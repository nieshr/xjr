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
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.repository.TdEnterpriseRepo;


@Service
@Transactional
public class TdEnterpriseService {
    @Autowired
    TdEnterpriseRepo repository;
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
    public void delete(TdEnterprise e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }

    public void delete(List<TdEnterprise> entities)
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
    public TdEnterprise findOne(Long id)
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
    public List<TdEnterprise> findAll(Iterable<Long> ids)
    {
        return (List<TdEnterprise>) repository.findAll(ids);
    }
    
    public List<TdEnterprise> findByIsEnableTrue()
    {
        return repository.findByIsEnableTrue();
    }
    
    public Page<TdEnterprise> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdEnterprise> searchAllOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    /**
     * 保存
     * @param e
     * @return
     */
    public TdEnterprise save(TdEnterprise e)
    {
        if (null != e.getUsername())    
        {
            TdUser user = tdUserService.findByUsername(e.getUsername());
            
            if (null == user )
            {
                user = tdUserService.addNewUser(e.getUsername(), e.getPassword(), e.getMobile(), null, null);
                
                user.setRoleId(0L); // 企业用户
            }
            // 修改加盟店密码也需要修改用户密码 @author: Sharon
            else
            {
                user.setPassword(e.getPassword());
            }
            
            tdUserService.save(user);
        }
        
        return repository.save(e);
    }
    
    public List<TdEnterprise> save(List<TdEnterprise> entities)
    {
        return (List<TdEnterprise>) repository.save(entities);
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    public TdEnterprise findbyUsername(String username){
		
    	return (repository.findByUsername(username)); 	
    }
    
}