package com.ynyes.kjxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.kjxjr.entity.TdIndustryType;
import com.ynyes.kjxjr.repository.TdIndustryTypeRepo;

/**
 * TdIndustryType 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdIndustryTypeService {
    @Autowired
    TdIndustryTypeRepo repository;
    
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
    public void delete(TdIndustryType e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdIndustryType> entities)
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
    public TdIndustryType findOne(Long id)
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
    public List<TdIndustryType> findAll(Iterable<Long> ids)
    {
        return (List<TdIndustryType>) repository.findAll(ids);
    }
    
    public List<TdIndustryType> findByIsEnableTrueOrderBySortIdAsc()
    {
        return repository.findByIsEnableTrueOrderBySortIdAsc();
    }
    
    public Page<TdIndustryType> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdIndustryType> searchAllOrderBySortIdAsc(String keywords, int page, int size)
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
    public TdIndustryType save(TdIndustryType e)
    {
        
        return repository.save(e);
    }
    
    public List<TdIndustryType> save(List<TdIndustryType> entities)
    {
        
        return (List<TdIndustryType>) repository.save(entities);
    }
}
