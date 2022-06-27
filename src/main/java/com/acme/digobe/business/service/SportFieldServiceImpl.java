package com.acme.digobe.business.service;

import com.acme.digobe.business.domain.model.entity.SportField;
import com.acme.digobe.business.domain.persistence.SportFieldRepository;
import com.acme.digobe.business.domain.service.SportFieldService;
import com.acme.digobe.security.domain.persistence.UserRepository;
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
public class SportFieldServiceImpl implements SportFieldService {

    private static final String ENTITY = "SportField";

    private final SportFieldRepository sportFieldRepository;

    private final Validator validator;

    private final UserRepository userRepository;

    public SportFieldServiceImpl(SportFieldRepository sportFieldRepository, Validator validator, UserRepository userRepository) {
        this.sportFieldRepository = sportFieldRepository;
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @Override
    public List<SportField> getAll() {
        return sportFieldRepository.findAll();
    }

    @Override
    public List<SportField> getAllByUserId(Long userId) {
        return sportFieldRepository.findByUserId(userId);
    }

    @Override
    public Page<SportField> getAllByUserId(Long userId, Pageable pageable) {
        return sportFieldRepository.findByUserId(userId, pageable);
    }

    @Override
    public SportField create(Long userId, SportField sportField) {
        Set<ConstraintViolation<SportField>> violations = validator.validate(sportField);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(user -> {
            sportField.setUser(user);
            return sportFieldRepository.save(sportField);
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    @Override
    public SportField update(Long userId, Long sportFieldId, SportField request) {
        Set<ConstraintViolation<SportField>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", userId);

        return sportFieldRepository.findById(sportFieldId).map(existingSportField ->
                sportFieldRepository.save(existingSportField.withName(request.getName())
                        .withImg(request.getImg())
                        .withAddress(request.getAddress())
                        .withDescription(request.getDescription())
                        .withPrice(request.getPrice())
                        .withSports(request.getSports())))
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    @Override
    public ResponseEntity<?> delete(Long sportFieldId, Long userId) {
        return sportFieldRepository.findByIdAndUserId(sportFieldId, userId).map(sportField -> {
            sportFieldRepository.delete(sportField);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, sportFieldId));
    }
}
