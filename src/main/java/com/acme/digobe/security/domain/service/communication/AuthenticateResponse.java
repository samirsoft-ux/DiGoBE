package com.acme.digobe.security.domain.service.communication;

import com.acme.digobe.security.resource.AuthenticateResource;
import com.acme.digobe.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}