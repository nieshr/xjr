package com.ynyes.kjxjr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TdEnterpriseGrade {

	//编号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//活动ID
	@Column
	private Long activityId;
	
	//专家ID，代表是属于哪个专家的评分
	@Column
	private Long expertId;
	
	//项目ID，代表是哪个项目的得分
	@Column
	private Long enterpriseId;
	
	//项目编码
	@Column
	private String number;
	
	//核心竞争力总得分
	@Column(scale=2)
	private Double totalTechnology;
	
	//核心竞争力第一项（技术、产品、服务、商业模式领先性、创新性）得分
	@Column(scale=2)
	private Double oneTechnology;
	
	//核心竞争力第二项（专利、商标、著作登记、双软、双高证书）得分
	@Column(scale=2)
	private Double twoTechnology;
	
	//核心竞争力第三项（与竞争对手相比的优势程度）得分
	@Column(scale=2)
	private Double threeTechnology;
	
	//市场潜力总得分
	@Column(scale=2)
	private Double totalFeasibility;
	
	//市场潜力的第一项（潜在市场规模大小及已有的市场份额）得分
	@Column(scale=2)
	private Double oneFeasibility;
	
	//市场潜力的第二项（市场开发价值与开发成本）得分
	@Column(scale=2)
	private Double twoFeasibility;
	
	//团队能力总得分
	@Column(scale=2)
	private Double totalGroup;
	
	//团队能力第一项（核心领头人的专业能力及资源）得分
	@Column(scale=2)
	private Double oneGroup;
	
	//团队能力第二项（团队成员的专业能力及分工是否合理）得分
	@Column(scale=2)
	private Double twoGroup;
	
	//投资价值总得分
	@Column(scale=2)
	private Double totalMarketValue;
	
	//投资价值第一项（行业环境及现有基础条件能否支撑）得分
	@Column(scale=2)
	private Double oneMarketValue;
	
	//投资价值第二项（财务状况及融资条件）得分
	@Column(scale=2)
	private Double twoMarketValue;
	
	//现场表现力总分
	@Column(scale=2)
	private Double totalExpression;
	
	//现场表现力的第一项（路演方式的创新程度及现场感染力）得分
	@Column(scale=2)
	private Double oneExpression;
	
	//总分
	@Column(scale=2)
	private Double totalPoint;
	
	//是否已经打分
	@Column
	private Boolean isGrade;
	
	//排序号
	@Column
	private Long sordId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getExpertId() {
		return expertId;
	}

	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getTotalTechnology() {
		return totalTechnology;
	}

	public void setTotalTechnology(Double totalTechnology) {
		this.totalTechnology = totalTechnology;
	}

	public Double getOneTechnology() {
		return oneTechnology;
	}

	public void setOneTechnology(Double oneTechnology) {
		this.oneTechnology = oneTechnology;
	}

	public Double getTwoTechnology() {
		return twoTechnology;
	}

	public void setTwoTechnology(Double twoTechnology) {
		this.twoTechnology = twoTechnology;
	}

	public Double getThreeTechnology() {
		return threeTechnology;
	}

	public void setThreeTechnology(Double threeTechnology) {
		this.threeTechnology = threeTechnology;
	}

	public Double getTotalFeasibility() {
		return totalFeasibility;
	}

	public void setTotalFeasibility(Double totalFeasibility) {
		this.totalFeasibility = totalFeasibility;
	}

	public Double getOneFeasibility() {
		return oneFeasibility;
	}

	public void setOneFeasibility(Double oneFeasibility) {
		this.oneFeasibility = oneFeasibility;
	}

	public Double getTwoFeasibility() {
		return twoFeasibility;
	}

	public void setTwoFeasibility(Double twoFeasibility) {
		this.twoFeasibility = twoFeasibility;
	}

	public Double getTotalGroup() {
		return totalGroup;
	}

	public void setTotalGroup(Double totalGroup) {
		this.totalGroup = totalGroup;
	}

	public Double getOneGroup() {
		return oneGroup;
	}

	public void setOneGroup(Double oneGroup) {
		this.oneGroup = oneGroup;
	}

	public Double getTwoGroup() {
		return twoGroup;
	}

	public void setTwoGroup(Double twoGroup) {
		this.twoGroup = twoGroup;
	}

	public Double getTotalMarketValue() {
		return totalMarketValue;
	}

	public void setTotalMarketValue(Double totalMarketValue) {
		this.totalMarketValue = totalMarketValue;
	}

	public Double getOneMarketValue() {
		return oneMarketValue;
	}

	public void setOneMarketValue(Double oneMarketValue) {
		this.oneMarketValue = oneMarketValue;
	}

	public Double getTwoMarketValue() {
		return twoMarketValue;
	}

	public void setTwoMarketValue(Double twoMarketValue) {
		this.twoMarketValue = twoMarketValue;
	}

	public Double getTotalExpression() {
		return totalExpression;
	}

	public void setTotalExpression(Double totalExpression) {
		this.totalExpression = totalExpression;
	}

	public Double getOneExpression() {
		return oneExpression;
	}

	public void setOneExpression(Double oneExpression) {
		this.oneExpression = oneExpression;
	}

	public Double getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Double totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Boolean getIsGrade() {
		return isGrade;
	}

	public void setIsGrade(Boolean isGrade) {
		this.isGrade = isGrade;
	}

	public Long getSordId() {
		return sordId;
	}

	public void setSordId(Long sordId) {
		this.sordId = sordId;
	}

	
}
