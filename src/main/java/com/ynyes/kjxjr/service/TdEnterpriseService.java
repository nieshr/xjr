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
    /*
     * 关键字搜索
     */
    //搜索0
    public Page<TdEnterprise> findBySearch(String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords,pageRequest);
    }
    //搜索1
    public Page<TdEnterprise> findByAreaAndSearch(String area,String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndTitleContainingOrderBySortIdAsc(area,keywords,pageRequest);
    }
    //搜索2
    public Page<TdEnterprise> findByTypeAndSearch(String Type,String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeAndTitleContainingOrderBySortIdAsc(Type,keywords,pageRequest);
    }
    //搜索3
    public Page<TdEnterprise> findByFormTypeAndSearch(Long formType,String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByFormTypeAndTitleContainingOrderBySortIdAsc(formType,keywords,pageRequest);
    }
    //搜索12
    public Page<TdEnterprise> findByAreaAndTypeAndSearch(String area,String type,String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndTypeAndTitleContainingOrderBySortIdAsc(area,type,keywords,pageRequest);
    }
    //搜索13
    public Page<TdEnterprise> findByAreaAndFormTypeAndSearch(String area,Long formType,String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndFormTypeAndTitleContainingOrderBySortIdAsc(area,formType,keywords,pageRequest);
    }
    //搜索23
    public Page<TdEnterprise> findByTypeAndFormTypeAndSearch(String type,Long formType,String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeAndFormTypeAndTitleContainingOrderBySortIdAsc(type,formType,keywords,pageRequest);
    }
    //搜索123
    public Page<TdEnterprise> findByAreaAndTypeAndFormTypeAndSearch(String area,String type,Long formType,String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndTypeAndFormTypeAndTitleContainingOrderBySortIdAsc(area,type,formType,keywords,pageRequest);
    }
    /*
     * 无关键字搜索
     */

    //搜索1
    public Page<TdEnterprise> findByArea(String area,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaOrderBySortIdAsc(area,pageRequest);
    }
    //搜索2
    public Page<TdEnterprise> findByType(String Type,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeOrderBySortIdAsc(Type,pageRequest);
    }
    //搜索3
    public Page<TdEnterprise> findByFormType(Long formType,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByFormTypeOrderBySortIdAsc(formType,pageRequest);
    }
    //搜索12
    public Page<TdEnterprise> findByAreaAndType(String area,String type,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndTypeOrderBySortIdAsc(area,type,pageRequest);
    }
    //搜索13
    public Page<TdEnterprise> findByAreaAndFormType(String area,Long formType,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndFormTypeOrderBySortIdAsc(area,formType,pageRequest);
    }
    //搜索23
    public Page<TdEnterprise> findByTypeAndFormType(String type,Long formType,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeAndFormTypeOrderBySortIdAsc(type,formType,pageRequest);
    }
    //搜索123
    public Page<TdEnterprise> findByAreaAndTypeAndFormType(String area,String type,Long formType,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndTypeAndFormTypeOrderBySortIdAsc(area,type,formType,pageRequest);
    }
    
    
    
    
    public Page<TdEnterprise> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdEnterprise> findAllOrderByCreateTimeDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "createTime"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdEnterprise> findAllOrderByNumberAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "number"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdEnterprise> findByAreaOrderByNumberAsc(String area,int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "number"));
        
        return repository.findByAreaOrderByNumberAsc(area,pageRequest);
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
                user = tdUserService.addNewUser(e.getUsername(), e.getPassword(), e.getUsermobile(), null, null);
                
                user.setRoleId(1L); // 企业用户
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