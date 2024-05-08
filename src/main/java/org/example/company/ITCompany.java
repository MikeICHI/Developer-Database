package org.example.company;

import org.example.company.employer.Employer;
import org.example.company.employer.ITRole;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "companies")
public class ITCompany extends EmployerManager<Employer<ITRole>> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id")
    private Employer<ITRole> director;

    public ITCompany(){}

    public ITCompany(String companyName){
        super();
        this.name = companyName;
    }

    public void startWork(){

        getEmployers().forEach(worker ->{
            worker.work();
            System.out.println(worker.getName()+" is "+worker.getRole()+'\n');
        });
    }

    public String getName(){return name;}

    public void setDirector(Employer<ITRole> director){
        this.director=director;
    }

    public Employer<ITRole> getDirector(){
        return director;
    }


    @Override
    public String toString(){
        return "ITCompany{"+"'name= "+name+'\''+'}';
    }

     @Override
    public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         ITCompany company = (ITCompany) o;
         return Objects.equals(name, company.name);
     }
        @Override
                public int hashCode(){
            return Objects.hash(name);
     }


    public Integer getID() {
        return id;
    }
}
