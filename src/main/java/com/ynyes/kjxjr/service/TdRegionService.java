package com.ynyes.kjxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.kjxjr.entity.TdRegion;
import com.ynyes.kjxjr.repository.TdRegionRepo;

/**
 * TdRegionService 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdRegionService {
    @Autowired
    TdRegionRepo repository;
    
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
    
    public List<TdRegion> findAllOrderBySortIdAsc()
    {
        Sort sort = new Sort(Direction.ASC, "sortId");
        
        return (List<TdRegion>) repository.findAll(sort);
    }
    
    public List<TdRegion> findByIsEnableTrueOrderBySortIdAsc()
    {
        return repository.findByIsEnableTrueOrderBySortIdAsc();
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
     * 
     * @param e
     * @return
     */
    public TdRegion save(TdRegion e)
    {
        
        return repository.save(e);
    }
    
    public List<TdRegion> save(List<TdRegion> entities)
    {
        
        return (List<TdRegion>) repository.save(entities);
    }
}
