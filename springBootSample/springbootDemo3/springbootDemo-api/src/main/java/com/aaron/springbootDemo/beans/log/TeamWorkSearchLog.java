package com.aaron.springbootDemo.beans.log;

import com.allchips.common.base.BaseEntity;

import java.util.Date;

/**
 * <p> aaron.qiu </p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date： 2019-10-23 10:25:21 </p>
 * <p>Copyright (c) 2017 ~ 2020 Allchips版权所有</p>
 */
public class TeamWorkSearchLog extends BaseEntity {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * BOM id
     */
    private Long bomId;

    /**
     * 询价表主键id
     */
    private Long inquiryId;

    /**
     * 数据类型 10 协同 20 芯齐齐
     */
    private Integer type;

    /**
     * 查询条件
     */
    private String searchCondition;

    /**
     * 采购查询条件
     */
    private String purchaseCondition;

    /**
     * 匹配失败原因
     */
    private String failReason;

    /**
     * 匹配过程记录
     */
    private String matchingProcessRecord;

    /**
     * 创建用户id
     */
    private Integer createUserId;

    /**
     * 创建用户名称
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户id
     */
    private Integer updateUserId;

    /**
     * 更新用户名称
     */
    private String updateUserName;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBomId() {
        return bomId;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getPurchaseCondition() {
        return purchaseCondition;
    }

    public void setPurchaseCondition(String purchaseCondition) {
        this.purchaseCondition = purchaseCondition;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getMatchingProcessRecord() {
        return matchingProcessRecord;
    }

    public void setMatchingProcessRecord(String matchingProcessRecord) {
        this.matchingProcessRecord = matchingProcessRecord;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
