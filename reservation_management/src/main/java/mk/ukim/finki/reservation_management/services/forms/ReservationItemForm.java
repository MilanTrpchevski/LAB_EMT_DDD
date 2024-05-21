package mk.ukim.finki.reservation_management.services.forms;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mk.ukim.finki.reservation_management.domain.valueobjects.Payment;

@Data
public class ReservationItemForm {

    @NotNull
    private Payment payment;

    @Min(1)
    private int state = 1;


}