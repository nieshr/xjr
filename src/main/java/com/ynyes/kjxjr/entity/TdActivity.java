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
 * 活动
 * 
 * @author Sharon
 *
 */

@Entity
public class TdActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 排序
    @Column
    private Long  sortId;
    
    // 活动名称
    @Column
    private String title;
   
    // 活动类型
    @Column
    private String activityType;
    
    // 活动地区
    @Column
    private String region;
    
    // 创建时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    // 日期
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
    
    // 地址
    @Column
    private String address;
    
    // 主题
    @Column
    private String theme;
    
    //活动状态： 0未审核；1已审核；
    @Column
    private Long statusId;
    
    //活动创建-选择项目状态： 0未完成；1已完成；
    @Column
    private Long statusEn;
    
    //活动创建-选择专家状态： 0未完成；1已完成；
    @Column
    private Long statusEx;
    
    //区县管理状态： 0未完成；1已完成；
    @Column
    private Long regionStatusId;
    
    //区县管理-预选状态： 0未完成；1已完成；
    @Column
    private Long statusChoose;

    //区县管理-推荐状态： 0未完成；1已完成；
    @Column
    private Long statusRecommend;
    
    // 简介
    @Column
    @Lob
    private String introduction;
    
    // 筹备开始
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date prepareOn;
    
    // 筹备结束
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date prepareOff;
    
    // 活动结束
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date eventEnd;
    
    // 文件
    @Column
    private String fileUrl;
    
    // 下载
    @Column
    private String download;
    
    // ppt
    @Column
    private String pptUrl;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getPrepareOn() {
		return prepareOn;
	}

	public void setPrepareOn(Date prepareOn) {
		this.prepareOn = prepareOn;
	}

	public Date getPrepareOff() {
		return prepareOff;
	}

	public void setPrepareOff(Date prepareOff) {
		this.prepareOff = prepareOff;
	}

	public Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getStatusEn() {
		return statusEn;
	}

	public void setStatusEn(Long statusEn) {
		this.statusEn = statusEn;
	}

	public Long getStatusEx() {
		return statusEx;
	}

	public void setStatusEx(Long statusEx) {
		this.statusEx = statusEx;
	}

	public Long getStatusChoose() {
		return statusChoose;
	}

	public void setStatusChoose(Long statusChoose) {
		this.statusChoose = statusChoose;
	}

	public Long getStatusRecommend() {
		return statusRecommend;
	}

	public void setStatusRecommend(Long statusRecommend) {
		this.statusRecommend = statusRecommend;
	}

	public Long getRegionStatusId() {
		return regionStatusId;
	}

	public void setRegionStatusId(Long regionStatusId) {
		this.regionStatusId = regionStatusId;
	}
    
	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getPptUrl() {
		return pptUrl;
	}

	public void setPptUrl(String pptUrl) {
		this.pptUrl = pptUrl;
	}

	
}
  