package com.acme.digobe.business.service;

import com.acme.digobe.business.domain.model.entity.Reservation;
import com.acme.digobe.business.domain.persistence.ReservationRepository;
import com.acme.digobe.business.domain.persistence.SportFieldRepository;
import com.acme.digobe.business.domain.service.ReservationService;
import com.acme.digobe.shared.exception.ResourceNotFoundException;
import com.acme.digobe.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final String ENTITY = "Reservation";

    private final ReservationRepository reservationRepository;

    private final Validator validator;

    private final SportFieldRepository sportFieldRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, Validator validator, SportFieldRepository sportFieldRepository) {
        this.reservationRepository = reservationRepository;
        this.validator = validator;
        this.sportFieldRepository = sportFieldRepository;
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getAllBySportFieldId(Long sportFieldId) {
        return reservationRepository.findBySportFieldId(sportFieldId);
    }

    @Override
    public Page<Reservation> getAllBySportFieldId(Long sportFieldId, Pageable pageable) {
        return reservationRepository.findBySportFieldId(sportFieldId, pageable);
    }

    @Override
    public Reservation create(Long sportFieldId, Reservation reservation) {
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);;

        return sportFieldRepository.findById(sportFieldId).map(sportField -> {
            reservation.setSportField(sportField);
            return reservationRepository.save(reservation);
        }).orElseThrow(() -> new ResourceNotFoundException("SportField", sportFieldId));
    }

    @Override
    public Reservation update(Long sportFieldId, Long reservationId, Reservation request) {
        Set<ConstraintViolation<Reservation>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!sportFieldRepository.existsById(sportFieldId))
            throw new ResourceNotFoundException("SportField", sportFieldId);

        return reservationRepository.findById(reservationId).map(existingReservation ->
                reservationRepository.save(existingReservation.withTitle(request.getTitle())
                        .withStart(request.getStart())
                        .withEnd(request.getEnd()))).orElseThrow(() -> new ResourceNotFoundException("SportField", sportFieldId));
    }

    @Override
    public ResponseEntity<?> delete(Long reservationId, Long sportFieldId) {
        return reservationRepository.findByIdAndSportFieldId(reservationId, sportFieldId).map(reservation -> {
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reservationId));
    }
}
