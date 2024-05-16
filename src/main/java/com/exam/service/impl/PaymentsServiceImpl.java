package com.exam.service.impl;

import com.exam.model.UserPayments;
import com.exam.repo.PaymentsRepository;
import com.exam.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentsServiceImpl implements PaymentsService {
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    public UserPayments addPaymentsSlip(UserPayments userPayments) {
        return this.paymentsRepository.save(userPayments);
    }

    @Override
    public Optional<UserPayments> getSlip(Long payments_id) {
        return this.paymentsRepository.findById(payments_id);
    }
}
