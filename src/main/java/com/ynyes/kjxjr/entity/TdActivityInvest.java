package com.ynyes.kjxjr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 专家与企业的中间表（专家辅导企业）
 * 
 * @author dengxiao
 * 
 */
@Entity
public class TdActivityInvest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// 活动ID
	@Column
	private Long activityId;

	// 专家ID
	@Column
	private Long expertId;

	// 专家名称
	@Column
	private String expertName;

	// 活动id
	@Column
	private Long activityTitle;

	// 企业ID
	@Column
	private Long enterpriseId;

	// 企业名称
	@Column
	private String enterpriseName;

	// 企业地址
	@Column
	private String addr;

	// 专利名称
	@Column
	private String pantent;

	// 是否评分结束
	@Column
	private Boolean isGrade;
	
	//企业行业归属
	@Column
	private String type;
	
	//活动类型
	@Column
	private String activityType;
	
	//投融资价值
	@Column
	private String amount;
	
	//详情
	@Column
	private String datail;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsGrade() {
		return isGrade;
	}

	public void setIsGrade(Boolean isGrade) {
		this.isGrade = isGrade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getExpertId() {
		return expertId;
	}

	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPantent() {
		return pantent;
	}

	public void setPantent(String pantent) {
		this.pantent = pantent;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Long getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(Long activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDatail() {
		return datail;
	}

	public void setDatail(String datail) {
		this.datail = datail;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

}
