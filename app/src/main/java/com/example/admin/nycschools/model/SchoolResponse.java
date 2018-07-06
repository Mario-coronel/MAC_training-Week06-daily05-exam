package com.example.admin.nycschools.model;

import java.util.List;

public class SchoolResponse {

    List<School> schools;

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public SchoolResponse(List<School> schools) {
        this.schools = schools;
    }
}
