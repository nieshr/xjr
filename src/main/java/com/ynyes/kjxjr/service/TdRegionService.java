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

import com.ynyes.kjxjr.entity.TdRegion;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.repository.TdRegionRepo;


@Service
@Transactional
public class TdRegionService {
    @Autowired
    TdRegionRepo repository;
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
    public void delete(TdRegion e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }

    public void delete(List<TdRegion> entities)
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
    public TdRegion findOne(Long id)
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
    public List<TdRegion> findAll(Iterable<Long> ids)
    {
        return (List<TdRegion>) repository.findAll(ids);
    }
    

    
    public Page<TdRegion> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdRegion> searchAllOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    /**
     * 保存
     * @param e
     * @return
     */
    public TdRegion save(TdRegion e)
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
    
    public List<TdRegion> save(List<TdRegion> entities)
    {
        return (List<TdRegion>) repository.save(entities);
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    public TdRegion findbyUsername(String username){
		
    	return (repository.findByUsername(username)); 	
    }
    
}