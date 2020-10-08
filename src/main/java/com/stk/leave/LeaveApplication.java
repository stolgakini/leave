
package com.stk.leave;

import com.stk.leave.config.SmartLocaleResolver;
import com.stk.leave.model.Employee;
import com.stk.leave.model.Leave;
import com.stk.leave.repository.EmployeeRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class LeaveApplication {

    @Autowired
    EmployeeRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(LeaveApplication.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/mes");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds((int) TimeUnit.HOURS.toSeconds(1));
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new SmartLocaleResolver();
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {

            Calendar calendarStart = Calendar.getInstance();
            calendarStart.set(Calendar.YEAR, 2020);
            calendarStart.set(Calendar.MONTH, 06);
            calendarStart.set(Calendar.DATE, 10);

            Employee e=new Employee();
            e.setAddress("istanbul");
            e.setEmail("abc@gmail.com");
            e.setGender("male");
            e.setName("isim");
            e.setSurname("soyisim");
            e.setPhoneNumber("5110001122");
            e.setRole("employee");
            e.setTckn("12345678901");
            e.setStartDate(calendarStart);


            Leave leave=new Leave();
            leave.setStatus(String.valueOf(LeaveStatus.approved));
            leave.setWorkDay(3);


            List<Leave> leaveList=new ArrayList<Leave>();
            leaveList.add(leave);

            e.setLeaveList(leaveList);

            repository.save(e);
        };
    }



}