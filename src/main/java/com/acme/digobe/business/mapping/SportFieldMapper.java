package com.acme.digobe.business.mapping;

import com.acme.digobe.business.domain.model.entity.SportField;
import com.acme.digobe.business.resource.CreateSportFieldResource;
import com.acme.digobe.business.resource.SportFieldResource;
import com.acme.digobe.business.resource.UpdateSportFieldResource;
import com.acme.digobe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SportFieldMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public SportFieldResource toResource(SportField model){
        return mapper.map(model, SportFieldResource.class);
    }

    public Page<SportFieldResource> modelListPage(List<SportField> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, SportFieldResource.class), pageable, modelList.size());
    }

    public SportField toModel(CreateSportFieldResource resource){
        return mapper.map(resource, SportField.class);
    }

    public SportField toModel(UpdateSportFieldResource resource){
        return mapper.map(resource, SportField.class);
    }
}
