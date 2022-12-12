package com.newspaper.backend.description;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class DescriptionService {
    private final DescriptionRepository descriptionRepository;

    public void setTitle(Long id, String title) {
        descriptionRepository.findById(id)
                .ifPresent(description -> description.setTitle(title));
    }

    public void setIssueNumber(Long id, Long issueNumber) {
        descriptionRepository.findById(id)
                .ifPresent(description -> description.setIssueNumber(issueNumber));
    }

    public void setIssueDate(Long id, Date issueDate) {
        descriptionRepository.findById(id)
                .ifPresent(description -> description.setIssueDate(issueDate));
    }

    public void setCoverImageLink(Long id, String link) {
        descriptionRepository.findById(id)
                .ifPresent(description -> description.setCoverImageLink(link));
    }
}
