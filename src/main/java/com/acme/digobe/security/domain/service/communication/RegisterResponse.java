package com.acme.digobe.security.domain.service.communication;

import com.acme.digobe.security.resource.UserResource;
import com.acme.digobe.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}