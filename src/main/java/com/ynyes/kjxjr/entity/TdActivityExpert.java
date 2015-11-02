package com.ynyes.kjxjr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动专家中间表
 * 
 * @author Sharon
 *
 */

@Entity
public class TdActivityExpert {
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
    
    // 专家
    @Column
    private String name;
    
    // 专家Id
    @Column
    private Long expertId;
    
    // 邮箱
    @Column
    private String email;
    
    // 联系电话
    @Column
    private String usermobile;
    
    // 专家用户名
    @Column
    private String username;
    
    // 创建时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsermobile() {
		return usermobile;
	}


	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}


	public Long getExpertId() {
		return expertId;
	}


	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

}
  