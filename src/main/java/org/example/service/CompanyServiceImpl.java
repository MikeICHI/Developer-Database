package org.example.service;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.example.company.ITCompany;
import org.example.company.employer.Developer;
import org.example.company.employer.Employer;
import org.example.company.employer.ITRole;
import org.example.company.employer.PM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    //@Qualifier("SessionScopedCompany")
    private ITCompany company;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Integer createCompany(ITCompany company) {
        entityManager.persist(company);
        entityManager.flush();
        return company.getID();
    }

    @Override
    public ITCompany getCompany(int id) {

        return entityManager.find(ITCompany.class, id);
    }

    @Override
    @Transactional
    public void addDeveloper(Developer developer, int company_id) {
        developer.setCompany(getCompany(company_id));
        entityManager.persist(developer);//persist сохранение в базу данных
        entityManager.flush();

    }

//    @Override
//    public void addPM(PM pm) {
//        company.getEmployers().add(pm);
//    }

    @Override
    public List<Employer> getEmployersByRole(ITRole role, int company_id) {
//        List<Employer<ITRole>> employers = company.getEmployers().stream()
//                .filter(employer -> employer.getRole().equals(role))
//                .collect(Collectors.toList());
        List<Employer> employers = entityManager.createQuery("select e from Employer e where e.role = : role and e.company =: company", Employer.class)
                .setParameter("role", role)
                .setParameter("company", getCompany(company_id))
                .getResultList();


        return employers;
    }

    private void getResultList() {
    }

    @Override
    @Transactional
    public Employer<ITRole> getEmployerByIndex(int index) {
        //Employer<ITRole> employer = company.getEmployers().get(index);
        Developer developer = entityManager.find(Developer.class, index);
        log.info("persistence contains object" + entityManager.contains(developer));
        entityManager.detach(developer);
        log.info("persistence contains object" + entityManager.contains(developer));
        return developer;
    }
}
