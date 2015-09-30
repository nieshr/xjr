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
 * 企业/项目
 * 
 * @author Sharon
 *
 */

@Entity
public class TdEnterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 审核
    @Column
    private Boolean isEnable;
    
    // 排序
    @Column
    private Long  sortId;
    
    // 企业/项目名称
    @Column
    private String title;
    
    // 成立时间
    @Column
    private String establish;
    
    // 注册资本
    @Column
    private Double capital;
    
    // 法定代表人
    @Column
    private String representative;
    
    // 股东结构
    @Column
    @Lob
    private String shareholder;
    
    // 所在地区
    @Column
    private String area;
    
    // 职工人数
    @Column
    private Double staffNumber;
    
    // 行业归属
    @Column
    private String type;
    
    // 付款方式
    @Column
    private String payType;
    
    // 邮箱
    @Column
    private String email;
    
    // 联系人
    @Column
    private String contact;
    
    // 联系电话
    @Column
    private String telephone;
    
    // 公司网站
    @Column
    private String website;
    
    // 传真
    @Column
    private String fax;
    
    // QQ/MSN
    @Column
    private String chat;
    
    // 联系电话
    @Column
    private String mobile;
    
    // 企业简介
    @Column
    private String profile;
    
    // 公司团队
    @Column
    private String teamIntroduction;
    
    // 技术特点
    @Column
    private String advantage;
    
    // 市场规模/行业地位
    @Column
    private String size;
    
    // 去年总资产
    @Column
    private Double lastAssets1;
    
    // 前年总资产
    @Column
    private Double lastAssets2;
    
    // 3年前总资产
    @Column
    private Double lastAssets3;
    
    // 去年净资产
    @Column
    private Double lastNetAssets1;
    
    // 前年净资产
    @Column
    private Double lastNetAssets2;
    
    // 3年前净资产
    @Column
    private Double lastNetAssets3;
    
    // 去年销售收入
    @Column
    private Double lastSale1;
    
    // 前年销售收入
    @Column
    private Double lastSale2;
    
    // 3年前销售收入
    @Column
    private Double lastSale3;
    
    // 去年毛利润
    @Column
    private Double lastProfit1;
    
    // 前年毛利润
    @Column
    private Double lastProfit2;
    
    // 3年前毛利润
    @Column
    private Double lastProfit3;
    
    // 发明专利
    @Column
    private String inventiPatent;
    
    // 实用新型专利
    @Column
    private String newPatent;
    
    // 外观设计专利
    @Column
    private String designPatent;
    
    // 期望股权融资时间
    @Column
    private String expectEquityDate;
 
    // 期望股权融资金额
    @Column
    private Double expectEquityAmount;
    
    // 期望股权融资用途
    @Column
    private String expectEquityUse;
    
    // 期望债权融资时间
    @Column
    private String expectBondDate;
 
    // 期望债权融资金额
    @Column
    private Double expectBondAmount;
    
    // 期望债权融资用途
    @Column
    private String expectBondUse;
    
    //是否愿意披露
    @Column
    private Boolean isShow;
    
    // 资料填写时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    // 主要负责人
    @Column
    private String inCharge;
    
    // 表单类型
    @Column
    private Long formType;
    
    // 状态
    @Column
    private Long statusId;
    
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

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
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

	public String getEstablish() {
		return establish;
	}

	public void setEstablish(String establish) {
		this.establish = establish;
	}

	public Double getCapital() {
		return capital;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getShareholder() {
		return shareholder;
	}

	public void setShareholder(String shareholder) {
		this.shareholder = shareholder;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Double getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(Double staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getTeamIntroduction() {
		return teamIntroduction;
	}

	public void setTeamIntroduction(String teamIntroduction) {
		this.teamIntroduction = teamIntroduction;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getLastAssets1() {
		return lastAssets1;
	}

	public void setLastAssets1(Double lastAssets1) {
		this.lastAssets1 = lastAssets1;
	}

	public Double getLastAssets2() {
		return lastAssets2;
	}

	public void setLastAssets2(Double lastAssets2) {
		this.lastAssets2 = lastAssets2;
	}

	public Double getLastAssets3() {
		return lastAssets3;
	}

	public void setLastAssets3(Double lastAssets3) {
		this.lastAssets3 = lastAssets3;
	}

	public Double getLastNetAssets1() {
		return lastNetAssets1;
	}

	public void setLastNetAssets1(Double lastNetAssets1) {
		this.lastNetAssets1 = lastNetAssets1;
	}

	public Double getLastNetAssets2() {
		return lastNetAssets2;
	}

	public void setLastNetAssets2(Double lastNetAssets2) {
		this.lastNetAssets2 = lastNetAssets2;
	}

	public Double getLastNetAssets3() {
		return lastNetAssets3;
	}

	public void setLastNetAssets3(Double lastNetAssets3) {
		this.lastNetAssets3 = lastNetAssets3;
	}

	public Double getLastSale1() {
		return lastSale1;
	}

	public void setLastSale1(Double lastSale1) {
		this.lastSale1 = lastSale1;
	}

	public Double getLastSale2() {
		return lastSale2;
	}

	public void setLastSale2(Double lastSale2) {
		this.lastSale2 = lastSale2;
	}

	public Double getLastSale3() {
		return lastSale3;
	}

	public void setLastSale3(Double lastSale3) {
		this.lastSale3 = lastSale3;
	}

	public Double getLastProfit1() {
		return lastProfit1;
	}

	public void setLastProfit1(Double lastProfit1) {
		this.lastProfit1 = lastProfit1;
	}

	public Double getLastProfit2() {
		return lastProfit2;
	}

	public void setLastProfit2(Double lastProfit2) {
		this.lastProfit2 = lastProfit2;
	}

	public Double getLastProfit3() {
		return lastProfit3;
	}

	public void setLastProfit3(Double lastProfit3) {
		this.lastProfit3 = lastProfit3;
	}

	public String getInventiPatent() {
		return inventiPatent;
	}

	public void setInventiPatent(String inventiPatent) {
		this.inventiPatent = inventiPatent;
	}

	public String getNewPatent() {
		return newPatent;
	}

	public void setNewPatent(String newPatent) {
		this.newPatent = newPatent;
	}

	public String getDesignPatent() {
		return designPatent;
	}

	public void setDesignPatent(String designPatent) {
		this.designPatent = designPatent;
	}

	public String getExpectEquityDate() {
		return expectEquityDate;
	}

	public void setExpectEquityDate(String expectEquityDate) {
		this.expectEquityDate = expectEquityDate;
	}

	public Double getExpectEquityAmount() {
		return expectEquityAmount;
	}

	public void setExpectEquityAmount(Double expectEquityAmount) {
		this.expectEquityAmount = expectEquityAmount;
	}

	public String getExpectEquityUse() {
		return expectEquityUse;
	}

	public void setExpectEquityUse(String expectEquityUse) {
		this.expectEquityUse = expectEquityUse;
	}

	public String getExpectBondDate() {
		return expectBondDate;
	}

	public void setExpectBondDate(String expectBondDate) {
		this.expectBondDate = expectBondDate;
	}

	public Double getExpectBondAmount() {
		return expectBondAmount;
	}

	public void setExpectBondAmount(Double expectBondAmount) {
		this.expectBondAmount = expectBondAmount;
	}

	public String getExpectBondUse() {
		return expectBondUse;
	}

	public void setExpectBondUse(String expectBondUse) {
		this.expectBondUse = expectBondUse;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Long getFormType() {
		return formType;
	}

	public void setFormType(Long formType) {
		this.formType = formType;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

}
  