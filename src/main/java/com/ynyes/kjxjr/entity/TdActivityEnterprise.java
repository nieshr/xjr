package com.ynyes.kjxjr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动、项目中间表
 * 
 * @author Sharon
 *
 */

@Entity
public class TdActivityEnterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 排序
    @Column
    private Long  sortId;
    
    // 活动名称
    @Column
    private String activityTitle;
    
    // 活动Id
    @Column
    private Long activityId;
    
    // 项目/企业
    @Column
    private String enterpriseTitle;
    
    // 项目Id
    @Column
    private Long enterpriseId;
    
    // 项目地区
    @Column
    private String area;
    
    // 项目行业归属
    @Column
    private String type;
    
    
    // 创建时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getEnterpriseTitle() {
		return enterpriseTitle;
	}

	public void setEnterpriseTitle(String enterpriseTitle) {
		this.enterpriseTitle = enterpriseTitle;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
  