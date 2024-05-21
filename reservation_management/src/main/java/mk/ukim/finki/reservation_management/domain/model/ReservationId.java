package mk.ukim.finki.reservation_management.domain.model;

import io.micrometer.common.lang.NonNull;
import mk.ukim.finki.shared_kernel.domain.base.DomainObjectId;

public class ReservationId extends DomainObjectId {
    private ReservationId() {
        super(ReservationId.randomId(ReservationId.class).getId());
    }

    public ReservationId(@NonNull String uuid) {
        super(uuid);
    }

}
