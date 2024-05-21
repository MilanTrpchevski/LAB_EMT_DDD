package mk.ukim.finki.reservation_management.services;

import mk.ukim.finki.reservation_management.domain.exceptions.ReservationIdNotExistException;
import mk.ukim.finki.reservation_management.domain.exceptions.ReservationItemIdNotExistException;
import mk.ukim.finki.reservation_management.domain.model.Reservation;
import mk.ukim.finki.reservation_management.domain.model.ReservationId;
import mk.ukim.finki.reservation_management.domain.model.ReservationItemId;
import mk.ukim.finki.reservation_management.services.forms.ReservationForm;
import mk.ukim.finki.reservation_management.services.forms.ReservationItemForm;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationId createReservation(ReservationForm reservationForm) throws ConstraintViolationException;
    List<Reservation> findAll();
    Optional<Reservation> findByid(ReservationId id);
    void addItem(ReservationId reservationId, ReservationItemForm reservationItemForm) throws
            ReservationIdNotExistException;
    void deleteItem(ReservationId reservationId, ReservationItemId reservationItemId) throws
            ReservationIdNotExistException, ReservationItemIdNotExistException;
}
