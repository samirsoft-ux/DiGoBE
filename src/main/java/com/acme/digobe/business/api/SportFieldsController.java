package com.acme.digobe.business.api;

import com.acme.digobe.business.domain.service.SportFieldService;
import com.acme.digobe.business.mapping.SportFieldMapper;
import com.acme.digobe.business.resource.SportFieldResource;
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
@RequestMapping("/api/v1/sportFields")
@Tag(name = "SportFields", description = "Create, read, update and delete sport fields")
public class SportFieldsController {

    private final SportFieldService sportFieldService;

    private final SportFieldMapper mapper;

    public SportFieldsController(SportFieldService sportFieldService, SportFieldMapper mapper) {
        this.sportFieldService = sportFieldService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all sport fields")
    public Page<SportFieldResource> getAllSportField(Pageable pageable){
        return mapper.modelListPage(sportFieldService.getAll(), pageable);
    }
}
