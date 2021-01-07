package com.zhowin.daycognition.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : zho
 * date  ：2021/1/6
 * desc ：
 */
@Entity
public class HistoryList {


    @Id(autoincrement = true)
    public Long id;
    @NotNull
    public String contentText;
    public String createTime;
    public String exitText;

    public HistoryList(String contentText) {
        this.contentText = contentText;
    }

    public HistoryList(String contentText, String createTime) {
        this.contentText = contentText;
        this.createTime = createTime;
    }

    @Generated(hash = 1211498809)
    public HistoryList(Long id, @NotNull String contentText, String createTime,
                       String exitText) {
        this.id = id;
        this.contentText = contentText;
        this.createTime = createTime;
        this.exitText = exitText;
    }

    @Generated(hash = 2007533974)
    public HistoryList() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentText() {
        return this.contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExitText() {
        return this.exitText;
    }

    public void setExitText(String exitText) {
        this.exitText = exitText;
    }

    @Override
    public String toString() {
        return "HistoryList{" +
                "id=" + id +
                ", contentText='" + contentText + '\'' +
                ", createTime='" + createTime + '\'' +
                ", exitText='" + exitText + '\'' +
                '}';
    }
}
