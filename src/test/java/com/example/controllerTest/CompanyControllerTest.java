package com.example.controllerTest;

import com.example.controller.CompanyController;
import com.example.dto.CompanyDTO;
import com.example.model.Company;
import com.example.service.CompanyService;
import com.example.utils.convert.CompanyConvert;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllCompanies() throws Exception {
        CompanyDTO company = new CompanyDTO("1","1","1","1");
        List<CompanyDTO> companyDTOList = Arrays.asList(company);
        //when(userService.getAllUsers()).thenReturn(userDTOList);
        given(companyService.getAllCompany()).willReturn(companyDTOList);
        mockMvc.perform(get("/api/company"))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.message").value("success"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)));
        verify(companyService, times(1)).getAllCompany();
        verifyNoMoreInteractions(companyService);
    }

    @Test
    public void testGetCompany() throws Exception {
        CompanyDTO company = new CompanyDTO("1","1","1","1");
        when(companyService.getCompanyById(company.getId())).thenReturn(Optional.of(company));
        mockMvc.perform(get("/api/company/{id}", company.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
        verify(companyService, times(1)).getCompanyById(company.getId());
        verifyNoMoreInteractions(companyService);
    }
    @Test
    public void testCreateCompany() throws Exception {
        CompanyDTO company = new CompanyDTO("1","1","1","0931111114");

        when(companyService.saveCompany(Mockito.any(Company.class))).thenReturn(company);
        // given(userService.saveUser(any(UserDTO.class))).willReturn(userDTO);
        mockMvc.perform(post("/api/company")
                .content(asJsonString(company))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
        verify(companyService, times(1)).saveCompany(CompanyConvert.dtoToModel(company));
        verifyNoMoreInteractions(companyService);
    }
    @Test
    public void testDeleteCompany() throws Exception {
        CompanyDTO company = new CompanyDTO("1","1","1","1");
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete ok", Boolean.TRUE);
        //doNothing().when(userService.deleteUser(userDTO.getEmail()));
        when(companyService.deleteCompany(company.getId())).thenReturn(response);
        MvcResult len = mockMvc.perform(delete("/api/company/{id}", company.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        verify(companyService, times(1)).deleteCompany(company.getId());
        verifyNoMoreInteractions(companyService);
    }

}
