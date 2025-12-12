package com.dhrmarques.ermdemo.service;

import com.dhrmarques.ermdemo.model.TBUserProject;
import com.dhrmarques.ermdemo.repository.TBUserProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TBUserProjectServiceImpl implements TBUserProjectService {

    private final TBUserProjectRepository repository;

    @Autowired
    public TBUserProjectServiceImpl(TBUserProjectRepository repository) {this.repository = repository;}

    @Override
    public TBUserProject save(TBUserProject project) {
        return repository.save(project);
    }

    @Override
    public List<TBUserProject> findAllProjects() {
        return repository.findAll();
    }

    @Override
    public Optional<TBUserProject> findById(Long projectId) {
        return repository.findById(projectId);
    }

    @Override
    public TBUserProject updateProject(TBUserProject project) {
        return repository.save(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        repository.deleteById(projectId);
    }

    @Override
    public List<TBUserProject> findByUserId(Long userId) { return repository.findByUserId(userId); }
}
