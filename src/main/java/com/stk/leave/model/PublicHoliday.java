package com.stk.leave.model;

import java.util.Arrays;
import java.util.Objects;

public class PublicHoliday {

    public String date;
    public String localName;
    public String name;
    public String countryCode;
    public Boolean fixed;
    public Boolean countyOfficialHoliday;
    public Boolean countyAdministrationHoliday;
    public Boolean global;
    public String[] counties;
    public int  launchYear;

    public PublicHoliday() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Boolean getFixed() {
        return fixed;
    }

    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }

    public Boolean getCountyOfficialHoliday() {
        return countyOfficialHoliday;
    }

    public void setCountyOfficialHoliday(Boolean countyOfficialHoliday) {
        this.countyOfficialHoliday = countyOfficialHoliday;
    }

    public Boolean getCountyAdministrationHoliday() {
        return countyAdministrationHoliday;
    }

    public void setCountyAdministrationHoliday(Boolean countyAdministrationHoliday) {
        this.countyAdministrationHoliday = countyAdministrationHoliday;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    public String[] getCounties() {
        return counties;
    }

    public void setCounties(String[] counties) {
        this.counties = counties;
    }

    public int getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(int launchYear) {
        this.launchYear = launchYear;
    }

    @Override
    public String toString() {
        return "PublicHoliday{" +
                "date='" + date + '\'' +
                ", localName='" + localName + '\'' +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", fixed=" + fixed +
                ", countyOfficialHoliday=" + countyOfficialHoliday +
                ", countyAdministrationHoliday=" + countyAdministrationHoliday +
                ", global=" + global +
                ", counties=" + Arrays.toString(counties) +
                ", launchYear=" + launchYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicHoliday that = (PublicHoliday) o;
        return launchYear == that.launchYear &&
                Objects.equals(date, that.date) &&
                Objects.equals(localName, that.localName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(fixed, that.fixed) &&
                Objects.equals(countyOfficialHoliday, that.countyOfficialHoliday) &&
                Objects.equals(countyAdministrationHoliday, that.countyAdministrationHoliday) &&
                Objects.equals(global, that.global) &&
                Arrays.equals(counties, that.counties);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(date, localName, name, countryCode, fixed, countyOfficialHoliday, countyAdministrationHoliday, global, launchYear);
        result = 31 * result + Arrays.hashCode(counties);
        return result;
    }
}
