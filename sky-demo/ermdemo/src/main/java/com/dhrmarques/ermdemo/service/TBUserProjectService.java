package com.dhrmarques.ermdemo.service;

import com.dhrmarques.ermdemo.model.TBUserProject;

import java.util.List;
import java.util.Optional;

public interface TBUserProjectService {

    TBUserProject save(TBUserProject project);

    List<TBUserProject> findAllProjects();

    Optional<TBUserProject> findById(Long projectId);

    TBUserProject updateProject(TBUserProject project);

    void deleteProject(Long projectId);

    List<TBUserProject> findByUserId(Long userId);
}
