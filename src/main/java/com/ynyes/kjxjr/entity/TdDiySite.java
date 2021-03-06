package com.ynyes.kjxjr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 角色
 * 
 * @author Sharon
 *
 */

@Entity
public class TdDiySite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 自提点名称
    @Column
    private String title;
    
    //角色id 
    @Column
    private Long roleId;
    
    // 自提点地址
    @Column
    private String address;
    
    // 【科技小巨人】投资机构
    @Column
    private String payType;
    
    //活动管理员负责人
    @Column
    private String inCharge;
    
    // 营业时间
    @Column
    private String openTimeSpan;
    
    // 客服电话
    @Column
    private String serviceTele;
    
    // 投诉电话
    @Column
    private String complainTele;
    
    // 自提点城市
    @Column
    private String city;
    
    // 是否启用
    @Column
    private Boolean isEnable;
    
    // 是否启用
    @Column
    private Long statusId;
    
    // 排序数字
    @Column
    private Long sortId;
    
    // 经度
    @Column
    private Double longitude;
    
    // 纬度
    @Column
    private Double latitude;
    
    // 描述说明
    @Column
    private String info;
    
    // 图片地址
    @Column
    private String imageUri;
    
    // 轮播展示图片，多张图片以,隔开
    @Column
    private String showPictures;
    
    // 登录名
    @Column
    private String username;
    
    // 登录密码
    @Column
    private String password;
    
    // 手机号
    @Column
    private String mobile;
    
    // 电子邮箱
 	@Column
 	private String email;
    
    // 经度
    @Column
    private Double totalCash;
    
    //区县
    @Column
    private String region;
    
    //简介
    @Column
    private String content;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOpenTimeSpan() {
        return openTimeSpan;
    }

    public void setOpenTimeSpan(String openTimeSpan) {
        this.openTimeSpan = openTimeSpan;
    }

    public String getServiceTele() {
        return serviceTele;
    }

    public void setServiceTele(String serviceTele) {
        this.serviceTele = serviceTele;
    }

    public String getComplainTele() {
        return complainTele;
    }

    public void setComplainTele(String complainTele) {
        this.complainTele = complainTele;
    }

    public String getShowPictures() {
        return showPictures;
    }

    public void setShowPictures(String showPictures) {
        this.showPictures = showPictures;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(Double totalCash) {
        this.totalCash = totalCash;
    }

	public String getInCharge() {
		return inCharge;
	}

	public void setInCharge(String inCharge) {
		this.inCharge = inCharge;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}
