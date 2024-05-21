package mk.ukim.finki.reservation_payment.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.reservation_payment.domain.exceptions.PaymentNotFoundException;
import mk.ukim.finki.reservation_payment.domain.models.Payment;
import mk.ukim.finki.reservation_payment.domain.models.PaymentId;
import mk.ukim.finki.reservation_payment.repository.PaymentRepository;
import mk.ukim.finki.reservation_payment.services.PaymentServices;
import mk.ukim.finki.reservation_payment.services.form.PaymentForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor

public class PaymentServiceImpl implements PaymentServices {
    private final PaymentRepository paymentRepository;
    @Override
    public Payment findById(PaymentId id) {
        return paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);    }

    @Override
    public Payment createPayment(PaymentForm form) {
        Payment p = Payment.build(form.getProductName(),form.getPrice(),form.getSales());
        paymentRepository.save(p);
        return p;

    }

    @Override
    public Payment paymentItemCreated(PaymentId productId, int quantity) {
        Payment p = paymentRepository.findById(productId).orElseThrow(PaymentNotFoundException::new);
        p.addSales(quantity);
        paymentRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public Payment paymentItemRemoved(PaymentId paymentId, int quantity) {
        Payment p = paymentRepository.findById(paymentId).orElseThrow(PaymentNotFoundException::new);
        p.removeSales(quantity);
        paymentRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }
}
