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
    

    /*
     * 关键字搜索
     */
    //搜索0
    public Page<TdEnterprise> findByStatusIdAndSearch( Long statusId , String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdAndTitleContainingOrderBySortIdAsc(statusId , keywords,pageRequest);
    }
    //搜索1
    public Page<TdEnterprise> findByAreaAndStatusIdAndSearch(String area, Long statusId , String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndStatusIdAndTitleContainingOrderBySortIdAsc(area,statusId , keywords,pageRequest);
    }
    //搜索2
    public Page<TdEnterprise> findByTypeAndStatusIdAndSearch(String Type, Long statusId , String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeContainingAndStatusIdAndTitleContainingOrderBySortIdAsc(Type,statusId , keywords,pageRequest);
    }
    //搜索3
    public Page<TdEnterprise> findByFormTypeAndStatusIdAndSearch(Long formType, Long statusId , String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByFormTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(formType, statusId , keywords,pageRequest);
    }
    //搜索12
    public Page<TdEnterprise> findByAreaAndTypeAndStatusIdAndSearch(String area,String type, Long statusId , String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndTypeContainingAndStatusIdAndTitleContainingOrderBySortIdAsc(area,type, statusId , keywords,pageRequest);
    }
    //搜索13
    public Page<TdEnterprise> findByAreaAndFormTypeAndStatusIdAndSearch(String area,Long formType, Long statusId , String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndFormTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(area,formType, statusId , keywords,pageRequest);
    }
    //搜索23
    public Page<TdEnterprise> findByTypeAndFormTypeAndStatusIdAndSearch(String type,Long formType, Long statusId , String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeContainingAndFormTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(type,formType, statusId , keywords,pageRequest);
    }
    //搜索123
    public Page<TdEnterprise> findByAreaAndTypeAndFormTypeAndStatusIdAndSearch(String area,String type,Long formType, Long statusId , String keywords,int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndTypeContainingAndFormTypeAndStatusIdAndTitleContainingOrderBySortIdAsc(area,type,formType, statusId , keywords,pageRequest);
    }
    /*
     * 无关键字搜索
     */

    //搜索0
    public Page<TdEnterprise> findByStatusId(Long statusId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderBySortIdAsc( statusId , pageRequest);
    }
    //搜索1
    public Page<TdEnterprise> findByAreaAndStatusId(String area, Long statusId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndStatusIdOrderBySortIdAsc(area, statusId , pageRequest);
    }
    //搜索2
    public Page<TdEnterprise> findByTypeAndStatusId(String Type, Long statusId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeContainingAndStatusIdOrderBySortIdAsc(Type, statusId , pageRequest);
    }
    //搜索3
    public Page<TdEnterprise> findByFormTypeAndStatusId(Long formType, Long statusId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByFormTypeAndStatusIdOrderBySortIdAsc(formType , statusId , pageRequest);
    }
    //搜索12
    public Page<TdEnterprise> findByAreaAndTypeAndStatusId(String area,String type, Long statusId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndTypeContainingAndStatusIdOrderBySortIdAsc(area,type, statusId , pageRequest);
    }
    //搜索13
    public Page<TdEnterprise> findByAreaAndFormTypeAndStatusId(String area,Long formType, Long statusId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndFormTypeAndStatusIdOrderBySortIdAsc(area,formType, statusId , pageRequest);
    }
    //搜索23
    public Page<TdEnterprise> findByTypeAndFormTypeAndStatusId(String type,Long formType, Long statusId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeContainingAndFormTypeAndStatusIdOrderBySortIdAsc(type,formType, statusId , pageRequest);
    }
    //搜索123
    public Page<TdEnterprise> findByAreaAndTypeAndFormTypeAndStatusId(String area,String type,Long formType, Long statusId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByAreaAndTypeContainingAndFormTypeAndStatusIdOrderBySortIdAsc(area,type,formType, statusId , pageRequest);
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
    
    /**
     * 手机登陆使用
     * @param usermobile
     * @return
     */
    public TdEnterprise findbyUsermobile(String usermobile){
		
    	return (repository.findByUsermobile(usermobile)); 	
    }
    
    public TdEnterprise findByNumber(String number){
    	if(null == number){
    		return null;
    	}
    	return repository.findByNumber(number);
    }
    
}