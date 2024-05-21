package mk.ukim.finki.reservation_management.repository;

import mk.ukim.finki.reservation_management.domain.model.Reservation;
import mk.ukim.finki.reservation_management.domain.model.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository  extends JpaRepository<Reservation, ReservationId> {
}
