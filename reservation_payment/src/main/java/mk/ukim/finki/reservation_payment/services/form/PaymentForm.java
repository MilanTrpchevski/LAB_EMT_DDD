package mk.ukim.finki.reservation_payment.services.form;

import lombok.Data;
import mk.ukim.finki.shared_kernel.domain.financial.Money;

@Data
public class PaymentForm {

    private String productName;
    private Money price;
    private int sales;

}
