package mk.ukim.finki.reservation_management.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.reservation_management.domain.exceptions.ReservationIdNotExistException;
import mk.ukim.finki.reservation_management.domain.exceptions.ReservationItemIdNotExistException;
import mk.ukim.finki.reservation_management.domain.model.Reservation;
import mk.ukim.finki.reservation_management.domain.model.ReservationId;
import mk.ukim.finki.reservation_management.domain.model.ReservationItemId;
import mk.ukim.finki.reservation_management.repository.ReservationRepository;
import mk.ukim.finki.reservation_management.services.ReservationService;
import mk.ukim.finki.reservation_management.services.forms.ReservationForm;
import mk.ukim.finki.reservation_management.services.forms.ReservationItemForm;
import mk.ukim.finki.shared_kernel.domain.events.reservations.ReservationItemCreated;
import mk.ukim.finki.shared_kernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;
    @Override
    public ReservationId createReservation(ReservationForm reservationForm) {
        Objects.requireNonNull(reservationForm, "reservation must not be null");
        var constraintViolations = validator.validate(reservationForm);
        var newOrder = reservationRepository.saveAndFlush(toDomainObject(reservationForm));
        newOrder.getReservationItemList().forEach(item->domainEventPublisher.publish(new ReservationItemCreated(item.getPaymentId().getId(),item.getStatus())));
        return newOrder.getId();
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findByid(ReservationId id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void addItem(ReservationId reservationId, ReservationItemForm reservationItemForm) throws ReservationIdNotExistException {
        Reservation order = reservationRepository.findById(reservationId).orElseThrow(ReservationIdNotExistException::new);
        order.addItem(reservationItemForm.getPayment(),reservationItemForm.getState());
        reservationRepository.saveAndFlush(order);
        domainEventPublisher.publish(new ReservationItemCreated(reservationItemForm.getPayment().getId().getId(),reservationItemForm.getState()));

    }

    @Override
    public void deleteItem(ReservationId reservationId, ReservationItemId reservationItemId) throws ReservationIdNotExistException, ReservationItemIdNotExistException {
        Reservation order = reservationRepository.findById(reservationId).orElseThrow(ReservationIdNotExistException::new);
        order.removeItem(reservationItemId);
        reservationRepository.saveAndFlush(order);

    }
    private Reservation toDomainObject(ReservationForm reservationForm) {
        var reservation = new Reservation(Instant.now(),reservationForm.getCurrency());
        return reservation;
    }

}
