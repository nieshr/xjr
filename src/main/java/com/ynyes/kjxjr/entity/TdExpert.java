package com.ynyes.kjxjr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 专家
 * 
 * @author Sharon
 *
 */

@Entity
public class TdExpert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 排序
    @Column
    private Long  sortId;
    
    // 名称
    @Column
    private String name;
    
    //名字
    @Column
    private String inCharge;
    
    // 邮箱
    @Column
    private String email;
    
    // 联系电话
    @Column
    private String telephone;
    
    // 投资机构
    @Column
    private String invest;
    
    // 简介
    @Column
    private String profile;
    
    // 封面图片
    @Column
    private String imgUrl;
    
    // 联系电话
    @Column
    private String usermobile;
  
    // 状态
    @Column
    private Long statusId;
    
    // 是否被选择
    @Column
    private Boolean isSelect;
    
    // 被选择的活动ID【评委】
    @Column
    private Long selectActivityId;
    
    // 被选择的活动ID【路演辅导】
    @Column
    private Long roadshowActivityId;
    
    // 登录名
    @Column
    private String username;
    
    // 登录密码
    @Column
    private String password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsermobile() {
		return usermobile;
	}

	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInCharge() {
		return inCharge;
	}

	public void setInCharge(String inCharge) {
		this.inCharge = inCharge;
	}

	public Boolean getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}

	public Long getSelectActivityId() {
		return selectActivityId;
	}

	public void setSelectActivityId(Long selectActivityId) {
		this.selectActivityId = selectActivityId;
	}
	

	public Long getRoadshowActivityId() {
		return roadshowActivityId;
	}

	public void setRoadshowActivityId(Long roadshowActivityId) {
		this.roadshowActivityId = roadshowActivityId;
	}

	public String getInvest() {
		return invest;
	}

	public void setInvest(String invest) {
		this.invest = invest;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "TdExpert [id=" + id + ", sortId=" + sortId + ", name=" + name + ", inCharge=" + inCharge + ", email="
				+ email + ", telephone=" + telephone + ", usermobile=" + usermobile + ", statusId=" + statusId
				+ ", isSelect=" + isSelect + ", selectActivityId=" + selectActivityId + ", username=" + username
				+ ", password=" + password + "]";
	}

	
 
}
  