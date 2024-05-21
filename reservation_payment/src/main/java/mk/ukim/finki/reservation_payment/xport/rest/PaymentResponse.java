package mk.ukim.finki.reservation_payment.xport.rest;


import lombok.AllArgsConstructor;
import mk.ukim.finki.reservation_payment.domain.models.Payment;
import mk.ukim.finki.reservation_payment.services.PaymentServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class PaymentResponse {

    private final PaymentServices paymentServices;

    @GetMapping
    public List<Payment> getAll() {
        return paymentServices.getAll();
    }

}

