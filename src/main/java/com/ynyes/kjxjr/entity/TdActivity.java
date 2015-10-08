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
    private Long type;
    
    // 活动地区
    @Column
    private String region;
    
    // 日期
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date Date;
    
    // 地址
    @Column
    private String address;
    
    // 主题
    @Column
    private String theme;
    
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

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
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

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}
    
    
	
}
  