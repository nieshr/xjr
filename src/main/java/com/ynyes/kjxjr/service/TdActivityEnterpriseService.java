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

import com.ynyes.kjxjr.entity.TdActivityEnterprise;
import com.ynyes.kjxjr.repository.TdActivityEnterpriseRepo;


@Service
@Transactional
public class TdActivityEnterpriseService {
    @Autowired
    TdActivityEnterpriseRepo repository;
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
    public void delete(TdActivityEnterprise e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }

    public void delete(List<TdActivityEnterprise> entities)
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
    public TdActivityEnterprise findOne(Long id)
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
    public List<TdActivityEnterprise> findAll(Iterable<Long> ids)
    {
        return (List<TdActivityEnterprise>) repository.findAll(ids);
    }
    

    
    public Page<TdActivityEnterprise> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    public Page<TdActivityEnterprise> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
    

    
    /**
     * 保存
     * @param e
     * @return
     */
    public TdActivityEnterprise save(TdActivityEnterprise e)
    {
        return repository.save(e);
    }
    
    public List<TdActivityEnterprise> save(List<TdActivityEnterprise> entities)
    {
        return (List<TdActivityEnterprise>) repository.save(entities);
    }
    
    /**
	 * @author lc
	 * @注释：
	 */

    
}