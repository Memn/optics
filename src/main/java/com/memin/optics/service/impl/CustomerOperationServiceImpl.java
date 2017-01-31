package com.memin.optics.service.impl;

import com.memin.optics.domain.Customer;
import com.memin.optics.domain.Payment;
import com.memin.optics.domain.Sale;
import com.memin.optics.service.CustomerOperationService;
import com.memin.optics.service.CustomerService;
import com.memin.optics.service.PaymentService;
import com.memin.optics.service.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;

@Service
@Transactional
public class CustomerOperationServiceImpl implements CustomerOperationService {

    private final Logger log = LoggerFactory.getLogger(CustomerOperationServiceImpl.class);
    @Inject
    private SaleService saleService;

    @Inject
    private CustomerService customerService;

    @Inject
    private PaymentService paymentService;

    @Override
    public Sale sale(Sale sale) {
        log.debug("Customer sale Operation: {}", sale.getAmount());
        Sale     save     = saleService.save(sale);
        Customer customer = customerService.findOne(save.getCustomer().getId());
        customer.setDebt(customer.getDebt().add(save.getAmount()));
        return save;
    }

    @Override
    public Sale updateSale(Sale sale) {

        // before save payment we need old payment amount.
        BigDecimal oldSaleAmount = saleService.findOne(sale.getId()).getAmount();
        log.debug("Customer sale Operation update old:{} new:{}", oldSaleAmount, sale.getAmount());

        Sale save = saleService.save(sale);

        // new - old
        BigDecimal priceIncreased = sale.getAmount().subtract(oldSaleAmount);

        Customer customer = customerService.findOne(sale.getCustomer().getId());
        customer.setDebt(customer.getDebt().add(priceIncreased));
        return save;
    }

    @Override
    public void deleteSale(Long id) {

        Sale       sale    = saleService.findOne(id);
        BigDecimal oldSale = sale.getAmount();
        log.debug("Customer sale Operation delete old:{}", oldSale);

        Customer customer = customerService.findOne(sale.getCustomer().getId());
        customer.setDebt(customer.getDebt().subtract(oldSale));

        saleService.delete(id);
    }

    @Override
    public Payment pay(Payment payment) {
        log.debug("Customer payment Operation: {}", payment.getAmount());
        Payment  save     = paymentService.save(payment);
        Customer customer = customerService.findOne(payment.getCustomer().getId());
        customer.setDebt(customer.getDebt().subtract(payment.getAmount()));
        return save;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        // before save payment we need old payment amount.
        BigDecimal oldPaymentAmount = paymentService.findOne(payment.getId()).getAmount();
        log.debug("Customer sale Operation update old:{} new:{}", oldPaymentAmount, payment.getAmount());

        Payment save = paymentService.save(payment);

        // new - old
        BigDecimal newExtraPayment = payment.getAmount().subtract(oldPaymentAmount);

        Customer customer = customerService.findOne(payment.getCustomer().getId());
        customer.setDebt(customer.getDebt().subtract(newExtraPayment));
        return save;
    }

    @Override
    public void deletePayment(Long id) {
        Payment    payment    = paymentService.findOne(id);
        BigDecimal oldPayment = payment.getAmount();

        log.debug("Customer payment Operation delete old:{}", oldPayment);

        Customer customer = customerService.findOne(payment.getCustomer().getId());
        customer.setDebt(customer.getDebt().add(oldPayment));
        paymentService.delete(id);
    }

}
