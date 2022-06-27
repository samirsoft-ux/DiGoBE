package com.acme.digobe.business.api;

import com.acme.digobe.business.domain.service.ReservationService;
import com.acme.digobe.business.mapping.ReservationMapper;
import com.acme.digobe.business.resource.CreateReservationResource;
import com.acme.digobe.business.resource.ReservationResource;
import com.acme.digobe.business.resource.UpdateReservationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "acme")
@RestController
@RequestMapping("/api/v1/sportFields/{sportFieldId}/reservations")
@Tag(name = "SportFields", description = "Create, read, update and delete sport fields")
public class SportFieldReservationController {

    private final ReservationService reservationService;

    private final ReservationMapper mapper;

    public SportFieldReservationController(ReservationService reservationService, ReservationMapper mapper) {
        this.reservationService = reservationService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all reservation by sport field ID")
    public Page<ReservationResource> getAllReservationBySportFieldId(@PathVariable Long sportFieldId, Pageable pageable){
        return mapper.modelListPage(reservationService.getAllBySportFieldId(sportFieldId), pageable);
    }

    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create reservation")
    public ReservationResource createReservation(@PathVariable Long sportFieldId,
                                                 @RequestBody CreateReservationResource resource){
        return mapper.toResource(reservationService.create(sportFieldId, mapper.toModel(resource)));
    }

    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @PutMapping("{reservationId}")
    @Operation(summary = "Update reservation")
    public ReservationResource updateReservation(@PathVariable Long sportFieldId,
                                                 @PathVariable Long reservationId,
                                                 @RequestBody UpdateReservationResource resource) {
        return mapper.toResource(reservationService.update(sportFieldId, reservationId, mapper.toModel(resource)));
    }

    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @DeleteMapping("{reservationId}")
    @Operation(summary = "Delete reservation")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId,
                                               @PathVariable Long sportFieldId) {
        return reservationService.delete(reservationId, sportFieldId);
    }

}
