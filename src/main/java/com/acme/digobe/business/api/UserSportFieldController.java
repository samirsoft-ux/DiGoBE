package com.acme.digobe.business.api;

import com.acme.digobe.business.domain.service.SportFieldService;
import com.acme.digobe.business.mapping.SportFieldMapper;
import com.acme.digobe.business.resource.CreateSportFieldResource;
import com.acme.digobe.business.resource.SportFieldResource;
import com.acme.digobe.business.resource.UpdateSportFieldResource;
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
@RequestMapping("/api/v1/users/{userId}/sportFields")
@Tag(name = "Users", description = "Create, read, update and delete users")
public class UserSportFieldController {

    private final SportFieldService sportFieldService;

    private final SportFieldMapper mapper;

    public UserSportFieldController(SportFieldService sportFieldService, SportFieldMapper mapper) {
        this.sportFieldService = sportFieldService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all sport field by user ID")
    public Page<SportFieldResource> getAllSportFieldByUserId(@PathVariable Long userId, Pageable pageable){
        return mapper.modelListPage(sportFieldService.getAllByUserId(userId),pageable);
    }

    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create sport field")
    public SportFieldResource createSportField(@PathVariable Long userId,
                                               @RequestBody CreateSportFieldResource resource) {
        return mapper.toResource(sportFieldService.create(userId, mapper.toModel(resource)));
    }

    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @PutMapping("{sportFieldId}")
    @Operation(summary = "Update sport field")
    public SportFieldResource updateSportField(@PathVariable Long userId,
                                               @PathVariable Long sportFieldId,
                                               @RequestBody UpdateSportFieldResource resource) {
        return mapper.toResource(sportFieldService.update(userId, sportFieldId, mapper.toModel(resource)));
    }

    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @DeleteMapping("{sportFieldId}")
    @Operation(summary = "Delete sport field")
    public ResponseEntity<?> deleteSportField(@PathVariable Long sportFieldId,
                                              @PathVariable Long userId) {
        return sportFieldService.delete(sportFieldId, userId);
    }

}
