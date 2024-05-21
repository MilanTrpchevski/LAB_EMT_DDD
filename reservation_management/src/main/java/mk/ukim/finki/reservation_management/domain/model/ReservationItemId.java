package mk.ukim.finki.reservation_management.domain.model;

import mk.ukim.finki.shared_kernel.domain.base.DomainObjectId;

public class ReservationItemId extends DomainObjectId {
    private ReservationItemId() {
        super(ReservationItemId.randomId(ReservationItemId.class).getId());
    }

    public ReservationItemId(String uuid) {
        super(uuid);
    }

}
