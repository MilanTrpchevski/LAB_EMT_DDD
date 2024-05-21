package mk.ukim.finki.reservation_management.services.forms;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Data
public class ReservationForm {

    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<ReservationForm> items = new ArrayList<>();

}
