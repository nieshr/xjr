package com.ynyes.kjxjr.service;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.kjxjr.entity.TdActivity;
import com.ynyes.kjxjr.repository.TdActivityRepo;


@Service
@Transactional
public class TdActivityService {
    @Autowired
    TdActivityRepo repository;
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
    public void delete(TdActivity e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }

    public void delete(List<TdActivity> entities)
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
    public TdActivity findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    /**
     * 查找未完成的那个活动
     * @author Administrator
     */
    public TdActivity findByStatusId(Long statusId)
    {
        if (null == statusId)
        {
            return null;
        }
        
        return repository.findByStatusId(statusId);
    }
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdActivity> findAll(Iterable<Long> ids)
    {
        return (List<TdActivity>) repository.findAll(ids);
    }
    
    /**
     * 区县根据地区查找所有
     * @param region
     * @return
     */
    public List<TdActivity> findByRegion(String region)
    {
        return (List<TdActivity>) repository.findByRegion(region);
    }

    /**
     * 活动管理员查找全部活动
     * @return
     */
    public List<TdActivity> findAll()
    {
        return (List<TdActivity>) repository.findAll();
    }
    
    /**
     * 查找全部，以时间倒序排序
     * @return
     */
    public List<TdActivity> findAllOrderByDateDesc()
    {
    	 Sort sort = new Sort(Direction.DESC, "date");
        return (List<TdActivity>) repository.findAll(sort);
    }
    
    public Page<TdActivity> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    public Page<TdActivity> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdActivity> findByStatusIdOrderByIdDesc(Long statusId,int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findByStatusId(statusId,pageRequest);
    }
    
    
    public Page<TdActivity> findByRegionAndStatusIdAndPrepareOffAfterAndPrepareOnBeforeOrderByIdDesc(String region , Long statusId  , int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findByRegionAndStatusIdAndPrepareOffAfterAndPrepareOnBefore(region , statusId , new Date() ,new Date() , pageRequest);
    }
    
    /**
     * 查找该区县有效期内的活动
     * @param region
     * @param page
     * @param size
     * @return
     */
    public Page<TdActivity> findByRegionAndPrepareOffAfterAndPrepareOnBeforeOrderByIdDesc(String region , int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findByRegionAndPrepareOffAfterAndPrepareOnBefore(region , new Date() ,new Date() , pageRequest);
    }
    
    public Page<TdActivity> findByRegionOrderByIdDesc(String region , int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findByRegion(region , pageRequest);
    }
    
    public Page<TdActivity> searchAllOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    /**
     * 保存
     * @param e
     * @return
     */
    public TdActivity save(TdActivity e)
    {
        return repository.save(e);
    }
    
    public List<TdActivity> save(List<TdActivity> entities)
    {
        return (List<TdActivity>) repository.save(entities);
    }
    
    /**
	 * @author lc
	 * @注释：
	 */

    
}