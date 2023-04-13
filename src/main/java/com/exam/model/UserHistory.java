package com.exam.model;

import javax.persistence.*;

@Entity
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hId;

    private String date;

    private String paper;

    private String fullMarks;

    private String yourMarks;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public UserHistory() {
    }

    public UserHistory(Long hId, String date, String paper, String fullMarks, String yourMarks, User user) {
        this.hId = hId;
        this.date = date;
        this.paper = paper;
        this.fullMarks = fullMarks;
        this.yourMarks = yourMarks;
        this.user = user;
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

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
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
