package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.company.ITCompany;
import org.example.company.dto.CompanyDTO;
import org.example.company.dto.ITEmployerDTO;
import org.example.company.employer.Developer;
import org.example.company.employer.Employer;
import org.example.company.employer.ITRole;
import org.example.company.employer.PM;
import org.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j//создал приватный логер
@RestController//выдает в json виде
@RequestMapping("/company")//присваиваем название ссылке
public class CompanyController {
    @Autowired
    private CompanyService companyService;

//    @PostMapping("/employers/PMs")
//    public ResponseEntity addEmployer(@RequestBody PM pm){
//        companyService.addPM(pm);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping
    public Integer createCompany(@RequestBody CompanyDTO companyDTO){
        return companyService.createCompany(companyDTO.toCompany());
    }

    @GetMapping("/{id}")
    public CompanyDTO company(@PathVariable int id){

        return CompanyDTO.from(companyService.getCompany(id));
    }

    @PostMapping("/{id}/employers/developers")
    public ResponseEntity addEmployer(@RequestBody Developer developer, @PathVariable(name = "id") int company_id){
        log.info("add developer");
        companyService.addDeveloper(developer, company_id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employers/{index}")
    public ResponseEntity<Employer<ITRole>> getEmployerByIndex(@PathVariable int index) {
        log.info("get employer by index = "+ index);

        try {

            return ResponseEntity.ok(companyService.getEmployerByIndex(index));
        } catch (IndexOutOfBoundsException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/employers/find")
    public ResponseEntity<List<ITEmployerDTO>> getEmployerByRole(
            @RequestParam(name = "role") ITRole role,
            @PathVariable(name = "id") int company_id){
       log.info("get employer by role = "+ role);

        List<ITEmployerDTO> result = companyService.getEmployersByRole(role, company_id)
                .stream()
                .map(ITEmployerDTO::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
