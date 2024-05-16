package com.exam.service;

import com.exam.model.UserPayments;

import java.util.List;
import java.util.Optional;

public interface PaymentsService {

    UserPayments addPaymentsSlip(UserPayments userPayments);

    Optional<UserPayments> getSlip(Long payments_id);
}
