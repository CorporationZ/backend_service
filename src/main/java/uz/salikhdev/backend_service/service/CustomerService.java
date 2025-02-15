package uz.salikhdev.backend_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.request.CreateCustomerDto;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = Customer.builder()
                .name(createCustomerDto.name())
                .email(createCustomerDto.email())
                .build();

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public Customer updateCustomer(Long id, CreateCustomerDto createCustomerDto) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setName(createCustomerDto.name());
        customer.setEmail(createCustomerDto.email());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
