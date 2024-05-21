package mk.ukim.finki.reservation_payment.repository;

import mk.ukim.finki.reservation_payment.domain.models.Payment;
import mk.ukim.finki.reservation_payment.domain.models.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
}
