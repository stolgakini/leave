package com.stk.leave.services;

import com.stk.leave.LeaveStatus;
import com.stk.leave.model.Employee;
import com.stk.leave.model.Leave;
import com.stk.leave.model.PublicHoliday;
import com.stk.leave.repository.EmployeeRepository;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LeaveService {

    EmployeeRepository employeeRepository;

    @Autowired
    public LeaveService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String requestLeave(String tckn, Calendar leaveStar, Calendar leaveEnd) throws ParseException {

        System.out.println(tckn);
        System.out.println(leaveStar.toString());
        System.out.println(leaveEnd.toString());

        Employee e = employeeRepository.findEmployeeByTckn(tckn);

        if(e == null){
            return "There is no employee record";
        }

        //Total leave right from started to job
        Integer totalLeaveRight = totalLeaveRight(e.getStartDate());

        //total used days + pending days
        Integer usedDays = usedLeaved(e.getLeaveList());

        Integer requestedBusinessDay = calculateWorkDays(leaveStar, leaveEnd);

        Integer currentLeaveRight = totalLeaveRight - usedDays;

        if (requestedBusinessDay <= currentLeaveRight) {

                Leave leave = new Leave();
                leave.setStartDate(leaveStar);
                leave.setEndDate(leaveEnd);
                leave.setStatus(String.valueOf(LeaveStatus.pending));
                leave.setWorkDay(requestedBusinessDay);
                e.getLeaveList().add(leave);
                employeeRepository.save(e);

                return "Your request has been sent to manager approval";

            }else{

            if (doesFirstYear(e.getStartDate())){
                if (requestedBusinessDay < 6 - (usedDays)) {
                    Leave leave = new Leave();
                    leave.setStartDate(leaveStar);
                    leave.setEndDate(leaveEnd);
                    leave.setStatus(String.valueOf(LeaveStatus.pending));
                    leave.setWorkDay(requestedBusinessDay);
                    e.getLeaveList().add(leave);
                    employeeRepository.save(e);
                    return "Your request has been sent to manager approval";

                } else {
                    return "Your request declined because you request "
                            + requestedBusinessDay + " days but only have "
                            + String.valueOf(5 - usedDays) + " days as advance";
                }


            }

                return "Your request declined because you request "
                        + requestedBusinessDay + " days but only have "
                        + currentLeaveRight;
            }





    }

    // Calculate total number of days which employee earned
    public Integer totalLeaveRight(Calendar startDate) {

        Integer fullYears = Math.toIntExact(ChronoUnit.DAYS.between(startDate.toInstant(), Calendar.getInstance().toInstant()) / 365);

        if (fullYears < 1) {
            return 0;
        } else if (0 < fullYears && fullYears < 6) {
            return (fullYears) * 15;
        } else if (5 < fullYears && fullYears < 11) {
            return (5 * 15) + ((fullYears - 5) * 18);
        } else {
            return (5 * 15) + (5 * 18) + ((fullYears - 10) * 24);
        }

    }

    public Integer usedLeaved(List<Leave> leaves) {

        Integer usedDays = 0;

        for (int i = 0; i < leaves.size(); i++) {

            if (leaves.get(i).getStatus().equalsIgnoreCase(String.valueOf(LeaveStatus.approved))
                    || leaves.get(i).getStatus().equalsIgnoreCase(String.valueOf(LeaveStatus.pending))) {
                usedDays = usedDays + leaves.get(i).getWorkDay();
            }

        }

        return usedDays;

    }

    public Boolean doesFirstYear(Calendar startDate) {

        if ((ChronoUnit.DAYS.between(startDate.toInstant(), Calendar.getInstance().toInstant()) / 365) < 1) {
            return true;
        } else {
            return false;
        }

    }

    public Integer calculateWorkDays(Calendar startDate, Calendar endDate) throws ParseException {

        List<Calendar> holidayList = getOfficalHolidays();

        List<Integer> holidayDayOfYearList = new ArrayList<Integer>();

        for (int i = 0; i < holidayList.size(); i++) {
            holidayDayOfYearList.add(holidayList.get(i).get(Calendar.DAY_OF_YEAR));
        }

        int workDays = 0;

        if (startDate.getTimeInMillis() == endDate.getTimeInMillis()) {
            return 0;
        }

        do {
            startDate.add(Calendar.DAY_OF_MONTH, 1);
            if (startDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && startDate.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    && !holidayDayOfYearList.contains((Integer) startDate.get(Calendar.DAY_OF_YEAR))) {
                ++workDays;
            }
        } while (startDate.getTimeInMillis() < endDate.getTimeInMillis());

        return workDays;

    }

    public List<Calendar> getOfficalHolidays() throws ParseException {

        List<PublicHoliday> publicHoliday = Unirest
                .get("https://date.nager.at/api/v2/publicholidays/"
                        + String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "/tr")
                .asObject(new GenericType<List<PublicHoliday>>() {
                }).getBody();

        List<Calendar> officialHolidayList = new ArrayList<Calendar>();

        for (int i = 0; i < publicHoliday.size(); i++) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date date = sdf.parse(publicHoliday.get(i).getDate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            officialHolidayList.add(calendar);

        }

        return officialHolidayList;
    }

}
