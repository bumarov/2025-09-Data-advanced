package com.company.jmixpmdata.repository;

import com.company.jmixpmdata.entity.Project;
import io.jmix.core.repository.JmixDataRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JmixDataRepository<Project, UUID> {
}