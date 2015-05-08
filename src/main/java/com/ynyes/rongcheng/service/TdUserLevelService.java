package com.ynyes.rongcheng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.rongcheng.entity.TdUserLevel;
import com.ynyes.rongcheng.repository.TdUserLevelRepo;

/**
 * TdUserLevel 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserLevelService {
    
    @Autowired
    TdUserLevelRepo repository;
    
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
    
    public TdUserLevel findByLevelIdAndIsEnableTrue(Long levelId)
    {
        return repository.findByLevelIdAndIsEnableTrue(levelId);
    }
    
    public TdUserLevel findByLevelId(Long levelId)
    {
        return repository.findByLevelId(levelId);
    }
    
    public TdUserLevel findByLevelIdAndIdNot(Long levelId, Long id)
    {
        return repository.findByLevelIdAndIdNot(levelId, id);
    }
    
    public TdUserLevel findByTitle(String title)
    {
        return repository.findByTitle(title);
    }
    
    public TdUserLevel findByTitleAndIdNot(String title, Long id)
    {
        return repository.findByTitleAndIdNot(title, id);
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdUserLevel e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdUserLevel> entities)
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
    public TdUserLevel findOne(Long id)
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
    public List<TdUserLevel> findAll(Iterable<Long> ids)
    {
        return (List<TdUserLevel>) repository.findAll(ids);
    }
    
    public Page<TdUserLevel> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdUserLevel save(TdUserLevel e)
    {
        
        return repository.save(e);
    }
    
    public List<TdUserLevel> save(List<TdUserLevel> entities)
    {
        
        return (List<TdUserLevel>) repository.save(entities);
    }
}
