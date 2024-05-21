package mk.ukim.finki.reservation_management.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.shared_kernel.domain.base.ValueObject;
import mk.ukim.finki.shared_kernel.domain.financial.Currency;
import mk.ukim.finki.shared_kernel.domain.financial.Money;

@Getter
public class Payment implements ValueObject {
    private final PaymentId id;
    private final String name;
    private final Money price;
    private final int sales;

    private Payment() {
        this.id=PaymentId.randomId(PaymentId.class);
        this.name= "";
        this.price = Money.valueOf(Currency.MKD,0);
        this.sales = 0;
    }

    @JsonCreator
    public Payment(@JsonProperty("id") PaymentId id,
                   @JsonProperty("productName") String name,
                   @JsonProperty("price") Money price,
                   @JsonProperty("sales") int sales) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
    }

}
