package org.example.company.dto;

import lombok.Data;
import org.example.company.ITCompany;
import org.example.company.employer.Employer;
import org.example.company.employer.ITRole;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CompanyDTO {
    private String name;
    private ITEmployerDTO director;
    private List<ITEmployerDTO> employers;


    public static CompanyDTO from(ITCompany company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setDirector(ITEmployerDTO.from(company.getDirector()));
        companyDTO.setName(company.getName());

        List<ITEmployerDTO> itEmployerDTOList = company.getEmployers().stream()
                .map(ITEmployerDTO::from)
                .collect(Collectors.toList());
        companyDTO.setEmployers(itEmployerDTOList);
        return companyDTO;
    }

    public ITCompany toCompany() {
        ITCompany company = new ITCompany(this.name);
        company.setDirector(this.director.toEmployer());
        company.getDirector().setCompany(company);

        if (!CollectionUtils.isEmpty(this.employers)) {
            List<Employer<ITRole>> employers = this.employers.stream()
                    .map(ITEmployerDTO::toEmployer)
                    .peek(e -> e.setCompany(company))
                    .collect(Collectors.toList());
            company.getEmployers().addAll(employers);
        }
        return company;
    }
}
