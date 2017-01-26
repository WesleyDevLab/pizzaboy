package dev.ansuro.rest;

import dev.ansuro.domain.Customer;
import dev.ansuro.domain.User;
import dev.ansuro.repository.CustomerRepository;
import dev.ansuro.repository.OrderRepository;
import dev.ansuro.repository.PizzaRepository;
import dev.ansuro.repository.UserRepository;
import dev.ansuro.rest.dto.OrderDTO;
import dev.ansuro.rest.dto.OrderItemDTO;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import org.slf4j.Logger;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

/**
 *
 * @author Andy
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRegisterAndOrder {
    @Autowired
    private Logger log;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Autowired
    private WebApplicationContext applicationContext;
    
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    
    private MockMvc mockMvc;
    
    private String user1;
    private Customer customer;
    private OrderDTO order;
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        userRepository.deleteAll();
        
        user1 = "{\"mail\": \"a@b.de\", \"password\": \"1234\"}";

        customer = new Customer();
        customer.setFirstname("firstname");
        customer.setLastname("lastname");
        customer.setStreet("fakestreet");
        customer.setHousenumber("13b");
        customer.setCity("fakecity");
        customer.setZip(55558);
        customer.setPhone("555-nose");
        
        order = new OrderDTO();
        order.setItems(Arrays.asList(new OrderItemDTO(pizzaRepository.findOne(1L).getOrdernumber(), 2)));
    }

    /*
    register a user
    */
    @Test
    public void testRegisterUser() throws Exception {
        
        MvcResult result = mockMvc.perform(post("/api/user")
                .content(user1)
                .contentType(contentType))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("mail", is("a@b.de")))
                .andReturn();
        
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);

        Optional<User> findOneByMail = userRepository.findOneByMail("a@b.de");
        assertTrue(findOneByMail.isPresent());
    }
    
    /*
    register a user with already registerd mail
    */
    @Test
    public void testSameMail() throws Exception {

        mockMvc.perform(post("/api/user")
                .content(user1)
                .contentType(contentType))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/user")
                .content(user1)
                .contentType(contentType))
                .andExpect(status().isConflict());
                // why is there no response but in curl?
                //.andExpect(jsonPath("$.message", is("already registered")));

    }
    
    @Test
    public void testOrderWithoutRegister() throws Exception {
        order.setCustomer(customer);
        
        mockMvc.perform(post("/api/order")
                .content(json(order))
                .contentType(contentType))
                .andExpect(status().isCreated());
    }
    
    /*
    user is registered, logged in and orders a pizza
    but there a no saved customer data
    */
    @Test
    @WithMockUser(username = "a@b.de", roles = "USER")
    public void testRegisterdOrderWithoutSavedCustomer() throws Exception {
        mockMvc.perform(post("/api/order")
                .content(json(order))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }
    
    /*
    user is registered, logged in and orders a pizza
    */
    @Test
    @WithMockUser(username = "a@b.de", roles = "USER")
    public void testRegisterdOrder() throws Exception {
        order.setCustomer(customer);
        mockMvc.perform(post("/api/user")
                .content(user1)
                .contentType(contentType))
                .andExpect(status().isCreated());
        
        mockMvc.perform(post("/api/customer")
                .content(json(customer))
                .contentType(contentType))
                .andExpect(status().isCreated());
        
        mockMvc.perform(post("/api/order")
                .content(json(order))
                .contentType(contentType))
                .andExpect(status().isCreated());
    }
    
    /*
    logged in user orders without given customer data
    */
    @Test
    @WithMockUser(username = "a@b.de", roles = "USER")
    public void testOrderWithoutCustomer() throws Exception {
        mockMvc.perform(post("/api/user")
                .content(user1)
                .contentType(contentType))
                .andExpect(status().isCreated());
        
        mockMvc.perform(post("/api/order")
                .content(json(order))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
