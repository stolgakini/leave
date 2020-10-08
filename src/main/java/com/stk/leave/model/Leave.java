package com.stk.leave.model;



import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;


@Entity
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar endDate;

    @Column
    private Integer workDay;

    //pending , approved , disapprove
    @Column
    private String status;


    public Leave() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Integer getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Integer workDay) {
        this.workDay = workDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", workDay=" + workDay +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leave leave = (Leave) o;
        return Objects.equals(id, leave.id) &&
                Objects.equals(startDate, leave.startDate) &&
                Objects.equals(endDate, leave.endDate) &&
                Objects.equals(workDay, leave.workDay) &&
                Objects.equals(status, leave.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, workDay, status);
    }
}


