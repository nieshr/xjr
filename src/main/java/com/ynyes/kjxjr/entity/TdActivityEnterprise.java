package com.ynyes.kjxjr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    
    // 项目联系人
    @Column
    private String contact;
    
    // 项目编号
    @Column
    private String number;
    
    // 项目联系人手机
    @Column
    private String mobile;
    
    // 项目联系人QQ
    @Column
    private String QQ;
    
    // 项目简介
    @Column
    private String profile;
    
    
    // 项目地区
    @Column
    private String area;
    
    // 项目行业归属
    @Column
    private String type;

    //活动中企业状态 :    0：创建活动选择；1：区县预选；2：区县推荐；
    @Column
    private Long statusId;

    //下载
    @Column
    private String download;
    
	// 创建时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    

    //推荐理由
    @Column
    private String reason;

    public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}


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

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	
}
  