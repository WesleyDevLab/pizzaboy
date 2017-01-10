package dev.ansuro.repository;

import dev.ansuro.domain.Customer;
import dev.ansuro.domain.User;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Andy
 */
@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindOneByEmail() {
        System.out.println("findOneByEmail");
        
        String email = "test@test.com";
        
        Customer customer = new Customer();
        customer.setFirstname("firstname");
        customer.setLastname("lastname");
        customer.setStreet("first street");
        customer.setHousenumber("1");
        customer.setCity("city");
        customer.setZip(555);
        customer.setPhone("555 / 12345");
        customer.setMail(email);
        customerRepository.saveAndFlush(customer);
        
        User user = new User();
        user.setPassword("1234");
        user.setCustomer(customer);
        userRepository.saveAndFlush(user);
        
        Optional<User> result = userRepository.findOneByEmail(email);
        assertTrue(result.isPresent());
        assertEquals(result.get().getCustomer().getMail(), email);
    }

    
}
