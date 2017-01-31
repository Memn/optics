package com.memin.optics.service;

import com.memin.optics.domain.Payment;
import com.memin.optics.domain.Sale;

public interface CustomerOperationService {

    Sale sale(Sale sale);

    Sale updateSale(Sale sale);

    void deleteSale(Long id);

    Payment pay(Payment payment);

    Payment updatePayment(Payment payment);

    void deletePayment(Long id);
}
