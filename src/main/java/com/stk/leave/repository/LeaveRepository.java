package com.stk.leave.repository;

import com.stk.leave.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {

        Leave findLeavesByStatus(String status);

}
