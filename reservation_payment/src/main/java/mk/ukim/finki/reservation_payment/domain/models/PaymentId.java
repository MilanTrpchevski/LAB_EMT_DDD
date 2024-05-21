package mk.ukim.finki.reservation_payment.domain.models;


import io.micrometer.common.lang.NonNull;
import mk.ukim.finki.shared_kernel.domain.base.DomainObjectId;

public class PaymentId extends DomainObjectId {
    private PaymentId() {
        super(PaymentId.randomId(PaymentId.class).getId());
    }

    public PaymentId(@NonNull String uuid) {
        super(uuid);
    }

    public static PaymentId of(String uuid) {
        PaymentId p = new PaymentId(uuid);
        return p;
    }

}
