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
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.repository.TdActivityAdminRepo;


@Service
@Transactional
public class TdActivityAdminService {
    @Autowired
    TdActivityAdminRepo repository;
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
    public void delete(TdActivityAdmin e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }

    public void delete(List<TdActivityAdmin> entities)
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
    public TdActivityAdmin findOne(Long id)
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
    public List<TdActivityAdmin> findAll(Iterable<Long> ids)
    {
        return (List<TdActivityAdmin>) repository.findAll(ids);
    }
    

    
    public Page<TdActivityAdmin> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdActivityAdmin> searchAllOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    /**
     * 保存
     * @param e
     * @return
     */
    public TdActivityAdmin save(TdActivityAdmin e)
    {
        if (null != e.getUsername())    
        {
            TdUser user = tdUserService.findByUsername(e.getUsername());
            
            if (null == user )
            {
                user = tdUserService.addNewUser(e.getUsername(), e.getPassword(), e.getUsermobile(), null, null);
                
                user.setRoleId(4L); // 活动管理用户
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
    
    public List<TdActivityAdmin> save(List<TdActivityAdmin> entities)
    {
        return (List<TdActivityAdmin>) repository.save(entities);
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    public TdActivityAdmin findbyUsername(String username){
		
    	return (repository.findByUsername(username)); 	
    }
    
}