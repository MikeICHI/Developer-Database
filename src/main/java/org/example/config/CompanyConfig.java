package org.example.config;


import lombok.extern.slf4j.Slf4j;
import org.example.company.ITCompany;
import org.example.company.employer.Employer;
import org.example.company.employer.ITRole;
import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Configuration
@ComponentScan("org.example.company")
public class CompanyConfig {

    @Bean
    @Primary
    public ITCompany getITCompany(Employer<ITRole> director){
        log.info("create singleton scoped bean");
        ITCompany company = new ITCompany("MadBrains");
        company.setDirector(director);
        return company;
    }

    @Bean("RequestScopedCompany")
    @RequestScope //жизненый цикл в рамках запроса
    public ITCompany getRequestScopeCompany(Employer<ITRole> director){
        log.info("create Request scoped bean");
        ITCompany company = new ITCompany("MadBrains");
        company.setDirector(director);
        return company;
    }
    @Bean("SessionScopedCompany")
    @SessionScope //ж.ц. в рамках сессии
    public ITCompany getSessionScopeCompany(Employer<ITRole> director){
        log.info("create Session scoped bean");
        ITCompany company = new ITCompany("MadBrains");
        company.setDirector(director);
        return company;
    }
    @Bean("PrototypeScopedCompany")
    @Scope("prototype")
    public ITCompany getPrototypeScopeCompany(Employer<ITRole> director){
        log.info("create prototype scoped bean");
        ITCompany company = new ITCompany("MadBrains");
        company.setDirector(director);
        return company;
    }

    @Bean
    public ITCompany getAnotherItCompany(){
        return new ITCompany("SomeCompany");
    }

    @Bean
    public Employer<ITRole> getDirector(){
        return new Employer<ITRole>("Oleg", 30, ITRole.Director) {
            @Override
            public void work() {
                System.out.println(this.getName()+ "is directing");
            }
        };
    }

    @Bean("CompanyName")
    public String getCompanyName(){
        return "MadBrains3";
    }

    @Bean("MaxEmployerCount")
    public int getCount(){
        return 100;
    }
}
