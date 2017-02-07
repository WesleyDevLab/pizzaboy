package dev.ansuro.converter;

import dev.ansuro.domain.Customer;
import dev.ansuro.rest.dto.CustomerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andy
 */
@Component
public class CustomerConverter implements Converter<Customer, CustomerDTO> {

    @Override
    public CustomerDTO convert(Customer source) {
        return new CustomerDTO(
                source.getFirstname(),
                source.getLastname(),
                source.getStreet(),
                source.getHousenumber(),
                source.getZip(),
                source.getCity(),
                source.getPhone());
    }
    
}
