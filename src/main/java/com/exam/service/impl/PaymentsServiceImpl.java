package com.exam.service.impl;

import com.exam.controller.UserHistoryController;
import com.exam.model.UserPayments;
import com.exam.model.exam.Question;
import com.exam.repo.PaymentsRepository;
import com.exam.repo.QuestionRepository;
import com.exam.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService {
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    public UserPayments addPaymentsSlip(UserPayments userPayments) {
        return this.paymentsRepository.save(userPayments);
    }
}
