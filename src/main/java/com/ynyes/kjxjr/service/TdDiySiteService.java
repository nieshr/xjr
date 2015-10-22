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

import com.ynyes.kjxjr.entity.TdActivityAdmin;
import com.ynyes.kjxjr.entity.TdDiySite;
import com.ynyes.kjxjr.entity.TdExpert;
import com.ynyes.kjxjr.entity.TdRegionAdmin;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.entity.TdUserComment;
import com.ynyes.kjxjr.repository.TdDiySiteRepo;

/**
 * TdDiySite 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdDiySiteService {
    @Autowired
    TdDiySiteRepo repository;
    
    @Autowired
    TdUserCommentService TdUserCommentService;
    
    @Autowired
    TdUserService tdUserService;
    
    @Autowired
    TdRegionAdminService tdRegionAdminService;
    
    @Autowired
    TdActivityAdminService tdActivityAdminService;
    
    @Autowired
    TdExpertService tdExpertService;
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
    public void delete(TdDiySite e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdDiySite> entities)
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
    public TdDiySite findOne(Long id)
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
    public List<TdDiySite> findAll(Iterable<Long> ids)
    {
        return (List<TdDiySite>) repository.findAll(ids);
    }
    
    public List<TdDiySite> findByIsEnableTrue()
    {
        return repository.findByIsEnableTrue();
    }
    
    public List<TdDiySite> findByCityAndIsEnableTrueOrderBySortIdAsc(String city)
    {
        if (null == city)
        {
            return null;
        }
        
        return repository.findByCityAndIsEnableTrueOrderBySortIdAsc(city);
    }
    
    public Page<TdDiySite> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdDiySite> searchAllOrderBySortIdAsc(String keywords, int page, int size)
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
    public TdDiySite save(TdDiySite e)
    {
        if (null != e.getUsername())    
        {
            TdUser user = tdUserService.findByUsername(e.getUsername());
            
            if (null == user )
            {
                user = tdUserService.addNewUser(e.getUsername(), e.getPassword(), e.getMobile(), e.getEmail(), null);
                
                user.setRoleId(e.getRoleId()); // 设置角色类型
                
                Long roleId = e.getRoleId();
                if (roleId ==2)
                {
                	TdRegionAdmin regionAdmin = tdRegionAdminService.addNewUser(e.getUsername(), e.getPassword(), e.getMobile(), e.getEmail(), e.getInCharge() , e.getStatusId(), e.getRegion());
                	tdRegionAdminService.save(regionAdmin);
                }
                
                if (roleId ==3)
                {
                	TdExpert expert = tdExpertService.addNewUser(e.getUsername(), e.getPassword(), e.getMobile(), e.getEmail(), e.getInCharge()  , e.getStatusId());

                    tdExpertService.save(expert);
}

                if (roleId == 4)
                {
                	TdActivityAdmin activity = tdActivityAdminService.addNewUser(e.getUsername(), e.getPassword(), e.getMobile(), e.getEmail(), e.getInCharge()  , e.getStatusId());
                	 tdActivityAdminService.save(activity);
                }
            }
            // 修改加盟店密码也需要修改用户密码 @author: Sharon
            else
            {
                user.setPassword(e.getPassword());
                user.setMobile(e.getMobile());
                user.setEmail(e.getEmail());
                user.setUsername(e.getUsername());
                
                Long roleId = e.getRoleId();
                if (roleId ==2)
                {
                	TdRegionAdmin item = tdRegionAdminService.findbyUsername(e.getUsername());
                	item.setUsername(e.getUsername());
                	item.setPassword(e.getPassword());
                	item.setUsermobile(e.getMobile());
                	item.setEmail(e.getEmail());
                	item.setInCharge(e.getInCharge());
                	item.setStatusId(e.getStatusId());
                	item.setTitle(e.getRegion());
                	item.setRegion(e.getRegion());
                	tdRegionAdminService.save(item);
                }
                
                if (roleId ==3)
                {
                	TdExpert item = tdExpertService.findbyUsername(e.getUsername());
                	item.setUsername(e.getUsername());
                	item.setPassword(e.getPassword());
                	item.setUsermobile(e.getMobile());
                	item.setEmail(e.getEmail());
                	item.setInCharge(e.getInCharge());
                	item.setStatusId(e.getStatusId());
                	item.setName(e.getInCharge());
                	tdExpertService.save(item);
}

                if (roleId == 4)
                {
                	TdActivityAdmin item = tdActivityAdminService.findbyUsername(e.getUsername());
                	item.setUsername(e.getUsername());
                	item.setPassword(e.getPassword());
                	item.setUsermobile(e.getMobile());
                	item.setEmail(e.getEmail());
                	item.setInCharge(e.getInCharge());
                	item.setStatusId(e.getStatusId());
                	item.setTitle(e.getInCharge());
                	tdActivityAdminService.save(item);
                }
            }
            
            tdUserService.save(user);
            
           
            
        }
        
        return repository.save(e);
    }
    
    public List<TdDiySite> save(List<TdDiySite> entities)
    {
        
        return (List<TdDiySite>) repository.save(entities);
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    public TdDiySite findbyUsername(String username){
		
    	return (repository.findByUsernameAndIsEnableTrue(username)); 	
    }
    
    /**
	 * @author lc
	 * @注释：同盟店评价信息
	 */
    public int ContdiysiteComment(Long diysiteId) {
		List<TdUserComment> tdUserComment_list = TdUserCommentService.findByDiysiteIdOrderByIdDesc(diysiteId);
		return tdUserComment_list.size();
	}
    
    public float diysiteServiceStars(Long diysiteId){
    	List<TdUserComment> tdUserComment_list = TdUserCommentService.findByDiysiteIdOrderByIdDesc(diysiteId);
    	
    	if (null != tdUserComment_list) {
    		Long[] result = new Long[20];
        	int temp = 0;
        	if (tdUserComment_list.size()==0) {
				return (float) 0.0;
			}
        	for(int i = 0; i < 20; i++){
        		result[i] = tdUserComment_list.get(Math.abs(new Random().nextInt())%tdUserComment_list.size()).getServiceStar();
        		temp = (int) (temp + result[i]);
        	}
        	return temp/20;
		}
    	
    	return (float) 0.0;
    	
    }
}
