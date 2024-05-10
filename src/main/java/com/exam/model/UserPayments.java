package com.exam.model;


import com.exam.model.exam.ImageModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_payments")
public class UserPayments {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentsId;

    private Long categoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "payments_slips",
            joinColumns = {
                    @JoinColumn(name = "paymentsId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "slip_id")
            }
    )

    private Set<ImageModel> slipImages;

    public UserPayments() {
    }

    public UserPayments(Long paymentsId, Long categoryId, User user, Set<ImageModel> slipImages) {
        this.paymentsId = paymentsId;
        this.categoryId = categoryId;
        this.user = user;
        this.slipImages = slipImages;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPaymentsId() {
        return paymentsId;
    }

    public void setPaymentsId(Long paymentsId) {
        this.paymentsId = paymentsId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ImageModel> getSlipImages() {
        return slipImages;
    }

    public void setSlipImages(Set<ImageModel> slipImages) {
        this.slipImages = slipImages;
    }




}
