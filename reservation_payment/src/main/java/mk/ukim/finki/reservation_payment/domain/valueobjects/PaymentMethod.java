package mk.ukim.finki.reservation_payment.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import mk.ukim.finki.shared_kernel.domain.base.ValueObject;

@Embeddable
@Getter
public class PaymentMethod implements ValueObject {
    private final String method;

    protected PaymentMethod(){
        this.method = null;
    }
}
