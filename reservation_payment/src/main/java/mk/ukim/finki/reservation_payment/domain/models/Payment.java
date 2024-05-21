package mk.ukim.finki.reservation_payment.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.reservation_payment.domain.valueobjects.PaymentMethod;
import mk.ukim.finki.shared_kernel.domain.base.AbstractEntity;
import mk.ukim.finki.shared_kernel.domain.financial.Money;

@Entity
@Table(name="payment")
@Getter
public class Payment extends AbstractEntity<PaymentId> {

    private String payment;

    private int sales = 0;

    private PaymentMethod paymentMethod;
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="price_currency"))
    })
    private Money price;

    private Payment() {
        super(PaymentId.randomId(PaymentId.class));
    }
    public static Payment build(String payment, Money price, int sales) {
        Payment p = new Payment();
        p.price = price;
        p.payment = payment;
        return p;
    }

    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }

}
