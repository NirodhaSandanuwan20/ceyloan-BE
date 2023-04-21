package com.exam.model;

import javax.persistence.*;

@Entity
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hId;

    private String date;

    private String category;

    private String title;

    private String fullMarks;

    private String yourMarks;

    private String savedTime;

    private Long qid;

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public UserHistory(String savedTime) {
        this.savedTime = savedTime;
    }

    public String getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(String savedTime) {
        this.savedTime = savedTime;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public UserHistory() {
    }


    public UserHistory(Long hId, String date, String category, String title, String fullMarks, String yourMarks, String savedTime, Long qid, User user) {
        this.hId = hId;
        this.date = date;
        this.category = category;
        this.title = title;
        this.fullMarks = fullMarks;
        this.yourMarks = yourMarks;
        this.savedTime = savedTime;
        this.qid = qid;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long gethId() {
        return hId;
    }

    public void sethId(Long hId) {
        this.hId = hId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String paper) {
        this.category = paper;
    }

    public String getFullMarks() {
        return fullMarks;
    }

    public void setFullMarks(String fullMarks) {
        this.fullMarks = fullMarks;
    }

    public String getYourMarks() {
        return yourMarks;
    }

    public void setYourMarks(String yourMarks) {
        this.yourMarks = yourMarks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
