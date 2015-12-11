package com.ynyes.kjxjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdEnterprise;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdEnterpriseRepo extends
		PagingAndSortingRepository<TdEnterprise, Long>,
		JpaSpecificationExecutor<TdEnterprise> 
{ 
    Page<TdEnterprise> findByTitleContainingOrderByIdDesc(String keywords, Pageable page);
    Page<TdEnterprise> findByStatusIdAndTitleContainingOrderByIdDesc( Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByStatusIdOrderByIdDesc( Long statusId ,Pageable page);
    Page<TdEnterprise> findByAreaOrderByIdDesc(String area, Pageable page);
    
    
    Page<TdEnterprise> findByAreaAndStatusIdAndTitleContainingOrderByIdDesc(String area, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByTypeContainingAndStatusIdAndTitleContainingOrderByIdDesc(String type, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByFormTypeAndStatusIdAndTitleContainingOrderByIdDesc(Long formType, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByAreaAndTypeContainingAndStatusIdAndTitleContainingOrderByIdDesc(String Area,String type, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByAreaAndFormTypeAndStatusIdAndTitleContainingOrderByIdDesc(String Area,Long formType, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByTypeContainingAndFormTypeAndStatusIdAndTitleContainingOrderByIdDesc(String type,Long formType, Long statusId , String keywords, Pageable page);
    Page<TdEnterprise> findByAreaAndTypeContainingAndFormTypeAndStatusIdAndTitleContainingOrderByIdDesc(String Area,String type,Long formType, Long statusId , String keywords, Pageable page);
    
    Page<TdEnterprise> findByAreaAndStatusIdOrderByIdDesc(String area, Long statusId , Pageable page);
    Page<TdEnterprise> findByTypeContainingAndStatusIdOrderByIdDesc(String type,Long statusId , Pageable page);
    Page<TdEnterprise> findByFormTypeAndStatusIdOrderByIdDesc(Long formType, Long statusId , Pageable page);
    Page<TdEnterprise> findByAreaAndTypeContainingAndStatusIdOrderByIdDesc(String Area,String type,Long statusId , Pageable page);
    Page<TdEnterprise> findByAreaAndFormTypeAndStatusIdOrderByIdDesc(String Area,Long formType,Long statusId ,  Pageable page);
    Page<TdEnterprise> findByTypeContainingAndFormTypeAndStatusIdOrderByIdDesc(String type,Long formType, Long statusId , Pageable page);
    Page<TdEnterprise> findByAreaAndTypeContainingAndFormTypeAndStatusIdOrderByIdDesc(String Area,String type,Long formType,Long statusId , Pageable page);
    
    /*
     * 审核筛选
     */
    
    Page<TdEnterprise> findByStatusIdAndFormTypeAndTitleContainingOrderByIdDesc(Long statusId , Long formType,String keywords , Pageable page);
    Page<TdEnterprise> findByAreaAndFormTypeAndTitleContainingOrderByIdDesc(String area , Long formType,String keywords , Pageable page);
    Page<TdEnterprise> findByFormTypeOrderByIdDesc(Long formType, Pageable page);
    
    //新加 2015年12月9日17:08:33 zhangji
    Page<TdEnterprise> findByAreaAndFormTypeOrderByIdDesc(String area ,Long formType, Pageable page);
    Page<TdEnterprise> findByAreaAndTitleContainingOrderByIdDesc(String area , String keywords , Pageable page);
    /**
	 * @author lc
	 * @注释：
	 */
    TdEnterprise findByUsername(String username);
    TdEnterprise findByUsermobile(String usermobile);
    TdEnterprise findByNumber(String number);

}