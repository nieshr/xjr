package com.ynyes.kjxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.kjxjr.entity.TdActivityType;
import com.ynyes.kjxjr.repository.TdActivityTypeRepo;

/**
 * TdMallService 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdActivityTypeService {
    @Autowired
    TdActivityTypeRepo repository;
    
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
    public void delete(TdActivityType e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdActivityType> entities)
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
    public TdActivityType findOne(Long id)
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
    public List<TdActivityType> findAll(Iterable<Long> ids)
    {
        return (List<TdActivityType>) repository.findAll(ids);
    }
    
    public List<TdActivityType> findAllOrderBySortIdAsc()
    {
        Sort sort = new Sort(Direction.ASC, "sortId");
        
        return (List<TdActivityType>) repository.findAll(sort);
    }
    
    public List<TdActivityType> findByIsEnableTrueOrderBySortIdAsc()
    {
        return repository.findByIsEnableTrueOrderBySortIdAsc();
    }
    
    public Page<TdActivityType> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdActivityType> searchAllOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdActivityType save(TdActivityType e)
    {
        
        return repository.save(e);
    }
    
    public List<TdActivityType> save(List<TdActivityType> entities)
    {
        
        return (List<TdActivityType>) repository.save(entities);
    }
}
