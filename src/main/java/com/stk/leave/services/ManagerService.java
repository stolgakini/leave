package com.stk.leave.services;

import com.stk.leave.LeaveStatus;
import com.stk.leave.model.Leave;
import com.stk.leave.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {

    LeaveRepository repository;

    @Autowired
    public ManagerService(LeaveRepository repository) {
        this.repository = repository;
    }

    public void approveLeaveRequest(Long id){

     Leave leave=repository.findById(id).get();

     leave.setStatus(String.valueOf(LeaveStatus.approved));

     repository.save(leave);

    }

    public void disapproveLeaveRequest(Long id){

        Leave leave=repository.findById(id).get();

        leave.setStatus(String.valueOf(LeaveStatus.disapproved));

        repository.save(leave);

    }

}
