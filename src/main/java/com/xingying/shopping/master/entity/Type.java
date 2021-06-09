package com.xingying.shopping.master.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * <p>
 * 星荧虚拟商品交易系统的商品种类表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@TableName(value = "TYPE",schema = "XINGYING_SHOP")
public class Type implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 商品种类ID
     */
    @TableId(value = "TYPE_ID", type = IdType.INPUT)
    private String typeId;

    /**
     * 商品种类名
     */
    @TableField("TYPE_NAME")
    private String typeName;

    /**
     * 父类ID
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 创建时间
     */
    @TableField("DATA_CREATE_TIME")
    private LocalDateTime dataCreateTime;

    /**
     * 修改时间
     */
    @TableField("DATA_EDIT_TIME")
    private LocalDateTime dataEditTime;

    /**
     * 备注
     */
    @TableField("MEMO")
    private String memo;

    /**
     * 图片
     */
    @TableField(exist = false)
    private MultipartFile pic;

    /**
     * 图片存放路径
     */
    @TableField("TYPE_PIC")
    private String typePic;

    /**
     * 是否为根节点（1为root）
     */
    @TableField("ROOT_FLAG")
    private Integer rootFlag;

    /**
     * 启用标志 1 为true
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 是否为叶子节点
     */
    @TableField(exist = false)
    private boolean leafFlag;

    public MultipartFile getPic() {
        return pic;
    }

    public void setPic(MultipartFile pic) {
        this.pic = pic;
    }

    public boolean isLeafFlag() {
        return leafFlag;
    }

    public void setLeafFlag(boolean leafFlag) {
        this.leafFlag = leafFlag;
    }

    public String getTypeName() {
            return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getDataCreateTime() {
            return dataCreateTime;
    }

    public void setDataCreateTime(LocalDateTime dataCreateTime) {
        this.dataCreateTime = dataCreateTime;
    }

    public LocalDateTime getDataEditTime() {
            return dataEditTime;
    }

    public void setDataEditTime(LocalDateTime dataEditTime) {
        this.dataEditTime = dataEditTime;
    }

    public String getMemo() {
            return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTypePic() {
            return typePic;
    }

    public void setTypePic(String typePic) {
        this.typePic = typePic;
    }

    public Integer getRootFlag() {
            return rootFlag;
    }

    public void setRootFlag(Integer rootFlag) {
        this.rootFlag = rootFlag;
    }

    public Integer getStatus() {
            return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Type{" +
        "typeId=" + typeId +
        ", typeName=" + typeName +
        ", parentId=" + parentId +
        ", dataCreateTime=" + dataCreateTime +
        ", dataEditTime=" + dataEditTime +
        ", memo=" + memo +
        ", typePic=" + typePic +
        ", rootFlag=" + rootFlag +
        ", status=" + status +
        "}";
    }
}
