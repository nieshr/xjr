package com.ynyes.kjxjr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 辅导内容
 * @author dengxiao
 */
@Entity
public class TdRegionRecord {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//区县管理ID
	@Column
	private Long regionAdminId;
	
	//企业ID
	@Column
	private Long enterpriseId;
	
	//内容
	@Column
	private String content;
	
	//时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
	private Date coachDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegionAdminId() {
		return regionAdminId;
	}

	public void setRegionAdminId(Long regionAdminId) {
		this.regionAdminId = regionAdminId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCoachDate() {
		return coachDate;
	}

	public void setCoachDate(Date coachDate) {
		this.coachDate = coachDate;
	}


	
	
}
