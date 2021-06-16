package com.xingying.shopping.master.entity.response;

import java.math.BigDecimal;

/**
 * @author SiletFlower
 * @date 2021/6/14 17:00:31
 * @description
 */
public class AppealNum {
    private Integer allNum;
    private Integer done;
    private Integer processing;
    private BigDecimal frozenAmount;

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Integer getProcessing() {
        return processing;
    }

    public void setProcessing(Integer processing) {
        this.processing = processing;
    }
}
