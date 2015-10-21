package com.ynyes.kjxjr.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 站内信
 * 
 * @author zhangji
 */
@Entity
public class TdUserMessage {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//企业id
	@Column
	private Long enterpriseId;	
	
	//区县管理员id
	@Column
	private Long regionAdminId;	
	
	//标题
	@Column
	private String title;	
	
	//内容
	@Column
	private String content;
	
	//提交时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;
	
	//企业团队称呼
	@Column
	private String name;
	
	//区县
	@Column
	private String region;
	
	//发起方【0：企业；1：区县管理】
	@Column
	private Long speaker;
	
    // 状态：【0：未查看；1：已查看】
    @Column
    private Long statusId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public Long getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Long speaker) {
		this.speaker = speaker;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getRegionAdminId() {
		return regionAdminId;
	}

	public void setRegionAdminId(Long regionAdminId) {
		this.regionAdminId = regionAdminId;
	}


    
}
