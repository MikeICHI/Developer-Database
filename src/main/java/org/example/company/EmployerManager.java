package org.example.company;

import org.example.company.employer.Employer;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass//для включения полей в сущность ITCompany
public class EmployerManager<T extends Employer> {
    @OneToMany(cascade = CascadeType.ALL)//в каком соотношениии будет происходить соединение сущностей
    @JoinColumn(name = "company_id")//соединенеие сущностей
    private List<T> employers;

    public EmployerManager() {
        this.employers = new ArrayList<>();
    }

    public int getSize() {
        return employers.size();
    }

    public List<T> getEmployers() {
        return employers;
    }
}
