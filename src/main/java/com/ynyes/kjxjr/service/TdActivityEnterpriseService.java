package com.ynyes.kjxjr.service;
import java.util.List;

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
    
    public TdActivityEnterprise findByActivityIdAndEnterpriseId(Long activityId,Long enterpriseId)
    {
    	if (null == enterpriseId || null ==activityId )
    	{
    		return null;
    	}
    	return repository.findByActivityIdAndEnterpriseId(activityId,enterpriseId);
    }
    
    public TdActivityEnterprise findByActivityIdAndSortId(Long activityId,Long sortId)
    {
    	if (null == sortId || null ==activityId )
    	{
    		return null;
    	}
    	return repository.findByActivityIdAndSortId(activityId,sortId);
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
    
    //创建活动，已选择列表
    public List<TdActivityEnterprise> findByActivityId(Long activityId)
    {
        return (List<TdActivityEnterprise>) repository.findByActivityId(activityId);
    }
    
    //企业修改资料，同步到预选列表用
    public List<TdActivityEnterprise> findByEnterpriseId(Long enterpriseId)
    {
        return (List<TdActivityEnterprise>) repository.findByEnterpriseId(enterpriseId);
    }
    
    public List<TdActivityEnterprise> findByEnterpriseIdAndStatusId(Long activityId , Long statusId)
    {
    	 return (List<TdActivityEnterprise>) repository.findByEnterpriseIdAndStatusIdOrderByIdDesc(activityId , statusId );
    }
    
    //企业资料页面筛选活动用，按时间倒序
    public List<TdActivityEnterprise> findByEnterpriseIdOrderByCreateTimeDesc(Long enterpriseId)
    {
        return (List<TdActivityEnterprise>) repository.findByEnterpriseIdOrderByCreateTimeDesc(enterpriseId);
    }
    
    //区县管理，预选列表
    public List<TdActivityEnterprise> findByActivityIdAndStatusId(Long activityId , Long statusId)
    {
        return (List<TdActivityEnterprise>) repository.findByActivityIdAndStatusId(activityId , statusId);
    }
    
    //区县管理，推荐列表排序
    public List<TdActivityEnterprise> findByActivityIdAndStatusIdOrderBySortIdAsc(Long activityId , Long statusId)
    {
        return (List<TdActivityEnterprise>) repository.findByActivityIdAndStatusIdOrderBySortIdAsc(activityId , statusId);
    }
    
    
    
    //活动管理，评分列表排序0
    public Page<TdActivityEnterprise> findByActivityIdAndStatusId(Long activityId , Long statusId , Integer orderId , int page, int size)
    {
    	PageRequest pageRequest =  null;
    	switch (orderId)
    	{
    		case 0 :
    			pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "totalPoint")
																					.and(new Sort(Direction.DESC , "totalTechnology"))
																					.and(new Sort(Direction.DESC , "totalGroup"))
																					.and(new Sort(Direction.DESC , "totalFeasibility"))
																					.and(new Sort(Direction.DESC , "totalMarketValue"))
																					.and(new Sort(Direction.DESC , "totalExpression")
																					.and(new Sort(Direction.ASC, "sortId")))
																					);
    			break;
    		case 1:
    			pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "totalTechnology")	);
    			break;
    		case 2:
    			pageRequest = new PageRequest(page, size, new Sort(Direction.DESC , "totalFeasibility"));
    			break;
    		case 3:
    		 	 pageRequest = new PageRequest(page, size, new Sort(Direction.DESC , "totalGroup"));
    		 	break;
    		case 4:
    			  pageRequest = new PageRequest(page, size, new Sort(Direction.DESC , "totalMarketValue"));
    			  break;
    		case 5 :
    			  pageRequest = new PageRequest(page, size, new Sort(Direction.DESC , "totalExpression"));
    			  break;
    		default:
    			pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "totalPoint")
																					.and(new Sort(Direction.DESC , "totalTechnology"))
																					.and(new Sort(Direction.DESC , "totalGroup"))
																					.and(new Sort(Direction.DESC , "totalFeasibility"))
																					.and(new Sort(Direction.DESC , "totalMarketValue"))
																					.and(new Sort(Direction.DESC , "totalExpression"))
						);
    			break;
    	}
    	
        return repository.findByActivityIdAndStatusId(activityId , statusId ,   pageRequest);
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
    
    //企业查找自己被推荐或被预选的活动
    public Page<TdActivityEnterprise> findByEnterpriseIdAndStatusIdOrderByIdDesc(Long activityId , Long statusId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	 
    	 return repository.findByEnterpriseIdAndStatusIdOrderByIdDesc(activityId , statusId , pageRequest);
    }
    
    //企业查找自己所有相关的活动
    public Page<TdActivityEnterprise> findByEnterpriseIdOrderByIdDesc(Long activityId , int page, int size)
    {
    	 PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	
        return repository.findByEnterpriseIdOrderByIdDesc(activityId , pageRequest);
    }
    
    //区县管理，项目预选搜索
    public Page<TdActivityEnterprise> findByActivityIdAndSearch(Long activityId,String keywords,int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findByActivityIdAndEnterpriseTitleContainingOrderByIdDesc(activityId,keywords,pageRequest);
    }
    //区县管理，项目预选
    public Page<TdActivityEnterprise> findByActivityId(Long activityId , int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findByActivityIdOrderByIdDesc(activityId , pageRequest);
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