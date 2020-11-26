package com.stk.leave.rest;

import com.stk.leave.repository.EmployeeRepository;

import com.stk.leave.services.LeaveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/employee")
@Api(value = "Employee Rest Api")
@Validated
public class EmployeeRest {

    EmployeeRepository repository;

    LeaveService service;

    @Autowired
    public EmployeeRest(EmployeeRepository repository, LeaveService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping(value = "/leave")
    public ResponseEntity newLeave(@RequestParam  @NotEmpty String tckn,
                                   @RequestParam("startDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
                                   @RequestParam("endDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date endDate) throws ParseException {

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        System.out.println("test");

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        return ResponseEntity.ok(service.requestLeave(tckn,startCal,endCal));

    }

    @GetMapping(value = "/leave")
    public ResponseEntity findLeave(@RequestParam @Valid @NotEmpty @Size(min = 11,max=11) String tckn){
        return ResponseEntity.ok(repository.findEmployeeByTckn(tckn).getLeaveList());
    }





}
