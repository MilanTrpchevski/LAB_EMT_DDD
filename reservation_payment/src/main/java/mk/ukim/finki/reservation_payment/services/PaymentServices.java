package mk.ukim.finki.reservation_payment.services;

import mk.ukim.finki.reservation_payment.domain.models.Payment;
import mk.ukim.finki.reservation_payment.domain.models.PaymentId;
import mk.ukim.finki.reservation_payment.services.form.PaymentForm;

import java.util.List;

public interface PaymentServices {

    Payment findById(PaymentId id);
    Payment createPayment(PaymentForm form);
    Payment paymentItemCreated(PaymentId productId, int quantity);
    Payment paymentItemRemoved(PaymentId productId, int quantity);
    List<Payment> getAll();

}
