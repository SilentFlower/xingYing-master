package com.xingying.shopping.master.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 星荧商城个人消息表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-18
 */
@TableName(value = "MESSAGE",schema = "XINGYING_SHOP")
public class Message implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 个人消息ID
     */
    @TableId(value = "MSG_ID", type = IdType.INPUT)
    private String msgId;

    /**
     * 消息的类型
     */
    @TableField("MSG_TYPE")
    private String msgType;

    /**
     * 消息的内容
     */
    @TableField("MSG_CONTENT")
    private String msgContent;

    /**
     * 消息的时间
     */
    @TableField("MSG_DATE")
    private LocalDateTime msgDate;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 消息状态(0 未读)
     */
    @TableField("MSG_STATUS")
    private Integer msgStatus;

    /**
     * 发送人ID
     */
    @TableField("SEND_ID")
    private String sendId;

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgType() {
            return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgContent() {
            return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public LocalDateTime getMsgDate() {
            return msgDate;
    }

    public void setMsgDate(LocalDateTime msgDate) {
        this.msgDate = msgDate;
    }

    public Message() {
    }

    public Message(String msgId, String msgType, String msgContent, LocalDateTime msgDate, String userId, Integer msgStatus) {
        this.msgId = msgId;
        this.msgType = msgType;
        this.msgContent = msgContent;
        this.msgDate = msgDate;
        this.userId = userId;
        this.msgStatus = msgStatus;
    }

    @Override
    public String toString() {
        return "Message{" +
        "msgId=" + msgId +
        ", msgType=" + msgType +
        ", msgContent=" + msgContent +
        ", msgDate=" + msgDate +
        ", userId=" + userId +
        "}";
    }
}
