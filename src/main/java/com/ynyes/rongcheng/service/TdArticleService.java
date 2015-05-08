package com.ynyes.rongcheng.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.rongcheng.entity.TdArticle;
import com.ynyes.rongcheng.repository.TdArticleRepo;

/**
 * TdArticleCategory 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdArticleService {
    @Autowired
    TdArticleRepo repository;
    
    /**
     * 查找菜单项
     * 
     * @param parentId 父菜单ID，为0时代报顶层菜单
     * @return
     */
    public List<TdArticle> findByMenuId(Long menuId)
    {
        if (null == menuId)
        {
            return null;
        }
        
        return repository.findByMenuIdOrderBySortIdAsc(menuId);
    }
    
    /**
     * 通过菜单ID查找
     * @param menuId
     * @param page
     * @param size
     * @return
     */
    public Page<TdArticle> findByMenuId(Long menuId, int page, int size)
    {
        if (null == menuId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByMenuIdOrderBySortIdAsc(menuId, pageRequest);
    }
    
    public Page<TdArticle> findByMenuIdAndIsEnableOrderByIdDesc(Long menuId, int page, int size)
    {
        if (null == menuId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByMenuIdAndStatusIdOrderByIdDesc(menuId, 0L, pageRequest);
    }
    
    /**
     * 通过菜单ID和分类ID查找
     * @param menuId
     * @param page
     * @param size
     * @return
     */
    public Page<TdArticle> findByMenuIdAndCategoryId(Long menuId, Long catId, int page, int size)
    {
        if (null == menuId && null == catId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByMenuIdAndCategoryIdOrderBySortIdAsc(menuId, catId, pageRequest);
    }
    
    public Page<TdArticle> findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(Long menuId, Long catId, int page, int size)
    {
        if (null == menuId && null == catId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByMenuIdAndCategoryIdAndStatusIdOrderByIdDesc(menuId, catId, 0L, pageRequest);
    }
    
    public Page<TdArticle> findByChannelIdAndCategoryId(Long channelId, Long catId, int page, int size)
    {
        if (null == channelId && null == catId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByChannelIdAndCategoryIdOrderBySortIdAsc(channelId, catId, pageRequest);
    }
    
    /**
     * 通过类型ID查找
     * 
     * @param catId 分类ID
     * @return
     */
    public List<TdArticle> findByCategoryId(Long catId)
    {
        if (null == catId)
        {
            return null;
        }
        
        return repository.findByCategoryIdOrderBySortIdAsc(catId);
    }
    
    public int countByCategoryId(Long catId)
    {
        if (null == catId)
        {
            return 0;
        }
        
        return repository.countByCategoryId(catId);
    }
    
    public List<TdArticle> findByCategoryIdAndChannelIdAndParamIsSearchableTrue(Long catId, Long channelId)
    {
        if (null == catId)
        {
            return null;
        }
        
        return repository.findByCategoryIdAndChannelIdAndParamIsSearchableTrueOrderBySortIdAsc(catId, channelId);
    }
    
    public Page<TdArticle> findByCategoryId(Long catId, int page, int size)
    {
        if (null == catId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByCategoryIdOrderBySortIdAsc(catId, pageRequest);
    }
    
    public Page<TdArticle> findByChannelId(Long catId, int page, int size)
    {
        if (null == catId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByChannelIdOrderBySortIdAsc(catId, pageRequest);
    }
    
    /**
     * 通过类型ID查找
     * 
     * @param catId 分类ID
     * @return
     */
    public List<TdArticle> findByChannelIdAndCategoryId(Long channelId, Long catId)
    {
        if (null == catId)
        {
            return null;
        }
        
        return repository.findByChannelIdAndCategoryIdOrderBySortIdAsc(channelId, catId);
    }
    
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
    public void delete(TdArticle e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdArticle findOne(Long id)
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
    public List<TdArticle> findAll(Iterable<Long> ids)
    {
        return (List<TdArticle>) repository.findAll(ids);
    }
    
    /**
     * 保存
     * 
     * @param article 文章
     * @return
     */
    public TdArticle save(TdArticle article)
    {
        if (null == article)
        {
            return null;
        }
        
        // 设置创建时间
        if (null == article.getCreateTime() || article.getCreateTime().equals(""))
        {
            article.setCreateTime(new Date());
        }
        
        // 更新updateTime
        article.setUpdateTime(new Date());
        
        // 设置简介
        if (null == article.getBrief() && !"".equals(article.getBrief()))
        {
            if (null != article.getContent() && article.getContent().length() > 0)
            {
                String brief = article.getContent().substring(0, 
                        article.getContent().length() > 254 ? 254 : article.getContent().length());
                article.setBrief(brief);
            }
        }
        
        return repository.save(article);
    }
}
