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

import com.ynyes.kjxjr.entity.TdEnterprise;
import com.ynyes.kjxjr.entity.TdUser;
import com.ynyes.kjxjr.repository.TdEnterpriseRepo;

@Service
@Transactional
public class TdEnterpriseService {
	@Autowired
	TdEnterpriseRepo repository;
	@Autowired
	TdUserService tdUserService;

	/**
	 * 删除
	 * 
	 * @param id
	 *            菜单项ID
	 */
	public void delete(Long id) {
		if (null != id) {
			repository.delete(id);
		}
	}

	/**
	 * 删除
	 * 
	 * @param e
	 *            菜单项
	 */
	public void delete(TdEnterprise e) {
		if (null != e) {
			repository.delete(e);
		}
	}

	public void delete(List<TdEnterprise> entities) {
		if (null != entities) {
			repository.delete(entities);
		}
	}

	/**
	 * 查找
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	public TdEnterprise findOne(Long id) {
		if (null == id) {
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

	public List<TdEnterprise> findAll(Iterable<Long> ids) {
		return (List<TdEnterprise>) repository.findAll(ids);
	}

	/*
	 * 关键字搜索
	 */
	// 搜索0
	public Page<TdEnterprise> findByStatusIdAndSearch(Long statusId, String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByStatusIdAndTitleContainingOrderByIdDesc(statusId, keywords, pageRequest);
	}

	// 搜索1
	public Page<TdEnterprise> findByAreaAndStatusIdAndSearch(String area, Long statusId, String keywords, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByAreaAndStatusIdAndTitleContainingOrderByIdDesc(area, statusId, keywords,
				pageRequest);
	}

	// 搜索2
	public Page<TdEnterprise> findByTypeAndStatusIdAndSearch(String Type, Long statusId, String keywords, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByTypeContainingAndStatusIdAndTitleContainingOrderByIdDesc(Type, statusId, keywords,
				pageRequest);
	}

	// 搜索3
	public Page<TdEnterprise> findByFormTypeAndStatusIdAndSearch(Long formType, Long statusId, String keywords,
			int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByFormTypeAndStatusIdAndTitleContainingOrderByIdDesc(formType, statusId, keywords,
				pageRequest);
	}

	// 搜索12
	public Page<TdEnterprise> findByAreaAndTypeAndStatusIdAndSearch(String area, String type, Long statusId,
			String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByAreaAndTypeContainingAndStatusIdAndTitleContainingOrderByIdDesc(area, type, statusId,
				keywords, pageRequest);
	}

	// 搜索13
	public Page<TdEnterprise> findByAreaAndFormTypeAndStatusIdAndSearch(String area, Long formType, Long statusId,
			String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByAreaAndFormTypeAndStatusIdAndTitleContainingOrderByIdDesc(area, formType, statusId,
				keywords, pageRequest);
	}

	// 搜索23
	public Page<TdEnterprise> findByTypeAndFormTypeAndStatusIdAndSearch(String type, Long formType, Long statusId,
			String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByTypeContainingAndFormTypeAndStatusIdAndTitleContainingOrderByIdDesc(type, formType,
				statusId, keywords, pageRequest);
	}

	// 搜索123
	public Page<TdEnterprise> findByAreaAndTypeAndFormTypeAndStatusIdAndSearch(String area, String type, Long formType,
			Long statusId, String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByAreaAndTypeContainingAndFormTypeAndStatusIdAndTitleContainingOrderByIdDesc(area,
				type, formType, statusId, keywords, pageRequest);
	}
	/*
	 * 无关键字搜索
	 */

	// 搜索0
	public Page<TdEnterprise> findByStatusId(Long statusId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByStatusIdOrderByIdDesc(statusId, pageRequest);
	}

	// 搜索1
	public Page<TdEnterprise> findByAreaAndStatusId(String area, Long statusId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByAreaAndStatusIdOrderByIdDesc(area, statusId, pageRequest);
	}

	// 搜索2
	public Page<TdEnterprise> findByTypeAndStatusId(String Type, Long statusId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByTypeContainingAndStatusIdOrderByIdDesc(Type, statusId, pageRequest);
	}

	// 搜索3
	public Page<TdEnterprise> findByFormTypeAndStatusId(Long formType, Long statusId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByFormTypeAndStatusIdOrderByIdDesc(formType, statusId, pageRequest);
	}

	// 搜索12
	public Page<TdEnterprise> findByAreaAndTypeAndStatusId(String area, String type, Long statusId, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByAreaAndTypeContainingAndStatusIdOrderByIdDesc(area, type, statusId, pageRequest);
	}

	// 搜索13
	public Page<TdEnterprise> findByAreaAndFormTypeAndStatusId(String area, Long formType, Long statusId, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByAreaAndFormTypeAndStatusIdOrderByIdDesc(area, formType, statusId, pageRequest);
	}

	// 搜索23
	public Page<TdEnterprise> findByTypeAndFormTypeAndStatusId(String type, Long formType, Long statusId, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByTypeContainingAndFormTypeAndStatusIdOrderByIdDesc(type, formType, statusId,
				pageRequest);
	}

	// 搜索123
	public Page<TdEnterprise> findByAreaAndTypeAndFormTypeAndStatusId(String area, String type, Long formType,
			Long statusId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByAreaAndTypeContainingAndFormTypeAndStatusIdOrderByIdDesc(area, type, formType,
				statusId, pageRequest);
	}

	public Page<TdEnterprise> findAllOrderByIdDesc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));

		return repository.findAll(pageRequest);
	}

	public Page<TdEnterprise> findAllOrderByCreateTimeDesc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "createTime"));

		return repository.findAll(pageRequest);
	}

	public Page<TdEnterprise> findAllOrderByNumberAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "number"));

		return repository.findAll(pageRequest);
	}

	public Page<TdEnterprise> findByAreaOrderByIdDesc(String area, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "number"));

		return repository.findByAreaOrderByIdDesc(area, pageRequest);
	}

	public Page<TdEnterprise> searchAllOrderByIdDesc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByTitleContainingOrderByIdDesc(keywords, pageRequest);
	}
	
	/**
	 * 审核筛选
	 * @author Zhangji
	 */
	public Page<TdEnterprise> findBySearchOrderByIdDesc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByTitleContainingOrderByIdDesc(keywords, pageRequest);
	}
	
	//12
	public Page<TdEnterprise> findByStatusIdAndFormTypeAndSearchOrderByIdDesc(Long statusId , Long formType ,String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByStatusIdAndFormTypeAndTitleContainingOrderByIdDesc(statusId , formType , keywords, pageRequest);
	}
	
	public Page<TdEnterprise> findByAreaAndFormTypeAndSearch(String area , Long formType ,String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByAreaAndFormTypeAndTitleContainingOrderByIdDesc(area , formType , keywords, pageRequest);
	}
	
	public Page<TdEnterprise> findByFormType(Long formType, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByFormTypeOrderByIdDesc(formType,  pageRequest);
	}

	/**
	 * 保存
	 * 
	 * @param e
	 * @return
	 */
	public TdEnterprise save(TdEnterprise e) {
		if (null != e.getUsername()) {
			TdUser user = tdUserService.findByUsername(e.getUsername());

			if (null == user) {
				user = tdUserService.addNewUser(e.getUsername(), e.getPassword(), e.getUsermobile(), null, null);

				if (null != user) {
					user.setRoleId(1L); // 企业用户
				}
			}
			// 修改加盟店密码也需要修改用户密码 @author: Sharon
			else {
				user.setPassword(e.getPassword());
			}

			tdUserService.save(user);
		}

		return repository.save(e);
	}

	public List<TdEnterprise> save(List<TdEnterprise> entities) {
		return (List<TdEnterprise>) repository.save(entities);
	}

	/**
	 * @author lc @注释：
	 */
	public TdEnterprise findbyUsername(String username) {

		return (repository.findByUsername(username));
	}

	/**
	 * 手机登陆使用
	 * 
	 * @param usermobile
	 * @return
	 */
	public TdEnterprise findbyUsermobile(String usermobile) {

		return (repository.findByUsermobile(usermobile));
	}

	public TdEnterprise findByNumber(String number) {
		if (null == number) {
			return null;
		}
		return repository.findByNumber(number);
	}

}