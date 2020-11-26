package com.stk.leave.rest;

import com.stk.leave.LeaveStatus;
import com.stk.leave.repository.LeaveRepository;
import com.stk.leave.services.ManagerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/manager")
@Api(value = "Manager Rest Api")
public class ManagerRest {

    LeaveRepository leaveRepository;

    ManagerService managerService;


    Object obj;

    @Autowired
    public ManagerRest(LeaveRepository leaveRepository, ManagerService managerService) {
        this.leaveRepository = leaveRepository;
        this.managerService = managerService;
    }

    @GetMapping(value = "/leave")
    public ResponseEntity findPendingLeaves() {
        return ResponseEntity.ok(leaveRepository.findLeavesByStatus(String.valueOf(LeaveStatus.pending)));
    }

    @PostMapping(value = "/approve")
    public ResponseEntity approveLeave(@RequestParam @Valid @NotEmpty Long id) {

        managerService.approveLeaveRequest(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/disapprove")
    public ResponseEntity disapproveLeave(@RequestParam @Valid @NotEmpty Long id) {

        managerService.disapproveLeaveRequest(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
