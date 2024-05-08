package org.example.service;

import org.example.company.ITCompany;
import org.example.company.employer.Developer;
import org.example.company.employer.Employer;
import org.example.company.employer.ITRole;
import org.example.company.employer.PM;

import java.util.List;

public interface CompanyService {


//    void addPM(PM pm);
//
//    void addDeveloper(Developer developer);

    Integer createCompany(ITCompany company);

    ITCompany getCompany(int id);

    void addDeveloper(Developer developer, int company_id);
    List<Employer> getEmployersByRole(ITRole role, int company_id);
    Employer<ITRole> getEmployerByIndex(int index);
}
