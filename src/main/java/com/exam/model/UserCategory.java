package com.exam.model;

import javax.persistence.*;

@Entity
public class UserCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userCategoryId;

    private Long qid;

    private boolean isPaid;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    public UserCategory() {
    }

    public UserCategory(Long userCategoryId, Long qid, boolean isPaid, User user) {
        this.userCategoryId = userCategoryId;
        this.qid = qid;
        this.isPaid = isPaid;
        this.user = user;
    }

    public Long getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId(Long userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
