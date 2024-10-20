package com.example.timesheet.repository;

import com.example.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet,Long> {
//    List<Timesheet> findByProjectId(Long projectId);
    List<Timesheet> findTimesheetsByEmployeeId(Long id);

    @Query("select t from Timesheet t where t.projectId = :projectId order by t.createdAt desc")
    List<Timesheet> findByProjectId(Long projectId);

    List<Timesheet> findByCreatedAtBetween(LocalDate min, LocalDate max);
}
