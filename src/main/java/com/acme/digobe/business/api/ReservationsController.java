package com.acme.digobe.business.api;

import com.acme.digobe.business.domain.service.ReservationService;
import com.acme.digobe.business.mapping.ReservationMapper;
import com.acme.digobe.business.resource.ReservationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "acme")
@RestController
@RequestMapping(value = "/api/v1/reservations")
@Tag(name = "Reservations", description = "Create, read, update and delete reservations")
public class ReservationsController {

    private final ReservationService reservationService;

    private final ReservationMapper mapper;

    public ReservationsController(ReservationService reservationService, ReservationMapper mapper) {
        this.reservationService = reservationService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all reservations")
    public Page<ReservationResource> getAllReservation(Pageable pageable){
        return mapper.modelListPage(reservationService.getAll(), pageable);
    }

}
