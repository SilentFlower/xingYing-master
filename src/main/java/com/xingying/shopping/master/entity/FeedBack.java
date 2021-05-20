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
 * 用户反馈表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-18
 */
@TableName(value = "FEED_BACK",schema = "XINGYING_SHOP")
public class FeedBack implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private String userId;

    /**
     * 反馈时间
     */
    @TableField("FEEDBACK_DATE")
    private LocalDateTime feedbackDate;

    /**
     * 反馈内容
     */
    @TableField("FEEDBACK_CONTENT")
    private String feedbackContent;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getFeedbackDate() {
            return feedbackDate;
    }

    public void setFeedbackDate(LocalDateTime feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getFeedbackContent() {
            return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
        "userId=" + userId +
        ", feedbackDate=" + feedbackDate +
        ", feedbackContent=" + feedbackContent +
        "}";
    }
}
