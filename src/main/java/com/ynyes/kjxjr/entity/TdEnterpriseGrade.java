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
	
	//现场表现力总分
	@Column(scale=2)
	private Double totalExpression;
	
	//现场表现力的第一项（DEMO发表方式的创新程度）得分
	@Column(scale=2)
	private Double oneExpression;
	
	//现场表现力的第二项（团队解说效果）得分
	@Column(scale=2)
	private Double twoExpression;
	
	//现场表现力的第三项得分（现场营造效果）得分
	@Column(scale=2)
	private Double threeExpression;
	
	//现场表现力的第四项（项目吸引力）得分
	@Column(scale=2)
	private Double fourExpression;
	
	//项目实施性总得分
	@Column(scale=2)
	private Double totalFeasibility;
	
	//项目实施性的第一项（行业环境）得分
	@Column(scale=2)
	private Double oneFeasibility;
	
	//项目实施性的第二项（现有基础条件能否支撑）得分
	@Column(scale=2)
	private Double twoFeasibility;
	
	//项目实施性第三项（技术可实现性）得分
	@Column(scale=2)
	private Double threeFeasibility;
	
	//项目实施性第四项（市场接受度）得分
	@Column(scale=2)
	private Double fourFeasibility;
	
	//市场价值总得分
	@Column(scale=2)
	private Double totalMarketValue;
	
	//市场价值第一项（潜在市场规模大小与技术开发成本的评估）得分
	@Column(scale=2)
	private Double oneMarketValue;
	
	//市场价值第二项（现行成本与市场现状的评估）得分
	@Column(scale=2)
	private Double twoMarketValue;
	
	//市场价值第三项（创意是否具有市场开发价值）得分
	@Column(scale=2)
	private Double threeMarketValue;
	
	//市场价值第四项（转成创业机会）得分
	@Column(scale=2)
	private Double fourMarketValue;
	
	//技术开发总得分
	@Column(scale=2)
	private Double totalTechnology;
	
	//技术开发第一项（技术创新性与现有同类技术的差异性）得分
	@Column(scale=2)
	private Double oneTechnology;
	
	//技术开发第二项（自身与竞争对手的SWOT分析评估）得分
	@Column(scale=2)
	private Double twoTechnology;
	
	//技术开发第三项（深度分析竞争对手的发展程度）得分
	@Column(scale=2)
	private Double threeTechnology;
	
	//技术开发第四项（自身竞争优势）得分
	@Column(scale=2)
	private Double fourTechnology;
	
	//技术开发第五项（其差异化程度与具有市场需求度的相互影响）得分
	@Column(scale=2)
	private Double fiveTechnology;
	
	//团队能力总得分
	@Column(scale=2)
	private Double totalGroup;
	
	//团队能力第一项（团队人员分工是否合理及其专业能力）得分
	@Column(scale=2)
	private Double oneGroup;
	
	//团队能力第二项（创业家精神及企业愿景）得分
	@Column(scale=2)
	private Double twoGroup;
	
	//团队能力第三项（团队人员的沟通、信任和凝聚力）得分
	@Column(scale=2)
	private Double threeGroup;
	
	//总分
	@Column(scale=2)
	private Double totalPoint;
	
	//是否已经打分
	@Column
	private Boolean isGrade;
	
	//排序号
	@Column
	private Long sordId;

	public Long getSordId() {
		return sordId;
	}

	public void setSordId(Long sordId) {
		this.sordId = sordId;
	}

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

	public Double getTwoExpression() {
		return twoExpression;
	}

	public void setTwoExpression(Double twoExpression) {
		this.twoExpression = twoExpression;
	}

	public Double getThreeExpression() {
		return threeExpression;
	}

	public void setThreeExpression(Double threeExpression) {
		this.threeExpression = threeExpression;
	}

	public Double getFourExpression() {
		return fourExpression;
	}

	public void setFourExpression(Double fourExpression) {
		this.fourExpression = fourExpression;
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

	public Double getThreeFeasibility() {
		return threeFeasibility;
	}

	public void setThreeFeasibility(Double threeFeasibility) {
		this.threeFeasibility = threeFeasibility;
	}

	public Double getFourFeasibility() {
		return fourFeasibility;
	}

	public void setFourFeasibility(Double fourFeasibility) {
		this.fourFeasibility = fourFeasibility;
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

	public Double getThreeMarketValue() {
		return threeMarketValue;
	}

	public void setThreeMarketValue(Double threeMarketValue) {
		this.threeMarketValue = threeMarketValue;
	}

	public Double getFourMarketValue() {
		return fourMarketValue;
	}

	public void setFourMarketValue(Double fourMarketValue) {
		this.fourMarketValue = fourMarketValue;
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

	public Double getFourTechnology() {
		return fourTechnology;
	}

	public void setFourTechnology(Double fourTechnology) {
		this.fourTechnology = fourTechnology;
	}

	public Double getFiveTechnology() {
		return fiveTechnology;
	}

	public void setFiveTechnology(Double fiveTechnology) {
		this.fiveTechnology = fiveTechnology;
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

	public Double getThreeGroup() {
		return threeGroup;
	}

	public void setThreeGroup(Double threeGroup) {
		this.threeGroup = threeGroup;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}
