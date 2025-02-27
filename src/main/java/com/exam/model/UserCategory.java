package com.exam.model;

import javax.persistence.*;

@Entity
public class UserCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userCategoryId;

    private Long cid;

    private String date;

    private String cTitle;

    private Long payments_id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private boolean isPaid;

    public UserCategory() {
    }

    public UserCategory(Long userCategoryId, Long cid, String date, String cTitle, Long payments_id, User user, boolean isPaid) {
        this.userCategoryId = userCategoryId;
        this.cid = cid;
        this.date = date;
        this.cTitle = cTitle;
        this.payments_id = payments_id;
        this.user = user;
        this.isPaid = isPaid;
    }


    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getPayments_id() {
        return payments_id;
    }

    public void setPayments_id(Long payments_id) {
        this.payments_id = payments_id;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId(Long userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    public Long getCid() {
        return cid;
    }

    public void setQid(Long Qid) {
        this.cid = cid;
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
