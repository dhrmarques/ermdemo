package com.dhrmarques.ermdemo.repository;

import com.dhrmarques.ermdemo.model.TBUserProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TBUserProjectRepository extends JpaRepository<TBUserProject, Long> {

    List<TBUserProject> findByUserId(Long userId);

}
