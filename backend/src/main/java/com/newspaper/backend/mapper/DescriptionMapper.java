package com.newspaper.backend.mapper;

import com.newspaper.backend.description.DescriptionEntity;
import com.newspaper.backend.description.DescriptionDto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DescriptionMapper implements DefaultMapper<DescriptionDto, DescriptionEntity> {
    @Override
    public DescriptionEntity dtoToEntity(DescriptionDto dto) {
        return new DescriptionEntity(dto.getTitle(),
                dto.getDescription(),
                dto.getIssueNumber(),
                new Date(),
                dto.getCoverImageLink());
    }

    @Override
    public void updateEntity(DescriptionEntity entity, DescriptionDto dto) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setIssueNumber(dto.getIssueNumber());
        entity.setCoverImageLink(dto.getCoverImageLink());
    }
}
