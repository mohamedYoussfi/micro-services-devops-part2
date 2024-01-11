package net.youssfi.customerservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.youssfi.customerservice.dto.CustomerDTO;
import net.youssfi.customerservice.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@WebMvcTest(CustomerRestController.class)
class CustomerRestControllerTest {
    @MockBean
   private CustomerService customerService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private List<CustomerDTO> customers;
    @BeforeEach
    public void setup(){
        this.customers = List.of(
                CustomerDTO.builder().id(1L).firstName("Mohamed").lastName("youssfi").email("med@gmail.com").build() ,
                CustomerDTO.builder().id(2L).firstName("Imane").lastName("ibrahimi").email("imane@gmail.com").build(),
                CustomerDTO.builder().id(3L).firstName("yasmine").lastName("aadil").email("yasmine@gmail.com").build()
        );
    }
    @Test
    public void shouldReturnAllCustomers() throws Exception {
        Mockito.when(customerService.findAllCustomers()).thenReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(customers.size())))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customers)));
    }
}