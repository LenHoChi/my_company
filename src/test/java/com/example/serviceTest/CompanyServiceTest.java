package com.example.serviceTest;

import com.example.dto.CompanyDTO;
import com.example.model.Company;
import com.example.repository.CompanyRepository;
import com.example.service.CompanyService;
import com.example.utils.convert.CompanyConvert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyServiceTest {
    @MockBean
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    private CompanyConvert companyConvert;


    @Test
    public void testGetAllUsers() throws Exception {
        Company company = new Company("1","1","1","1","1");

        List<Company> companyList = Arrays.asList(company);

        when(companyRepository.findAll()).thenReturn(companyList);
        List<CompanyDTO> companyDTOList = companyService.getAllCompany();
        assertEquals(companyList.size(), companyDTOList.size());
    }

    @Test
    public void testGetCompanyById() throws Exception {
        Company company = new Company("1","1","1","1","1");

        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        Optional<CompanyDTO> companyRoot = companyService.getCompanyById(company.getId());
        assertEquals(company.getName(),companyRoot.get().getName());
    }
    @Test
    public void testCreateCompany() throws Exception {
        Company company = new Company("1","1","1","1","1");
        when(companyRepository.save(company)).thenReturn(company);
        CompanyDTO companyDTO = companyService.saveCompany(company);
        assertEquals(company.getName(), companyDTO.getName());
    }
    @Test
    public void testDeleteCompany() throws Exception {
        Company company = new Company("1","1","1","1","1");
        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        doNothing().when(companyRepository).delete(company);
        companyService.deleteCompany(company.getId());
        verify(companyRepository, times(1)).findById(company.getId());
        verify(companyRepository, times(1)).delete(company);
    }

}

