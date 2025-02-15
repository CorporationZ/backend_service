package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.exceotion.EntityAlreadyExistsException;
import uz.salikhdev.backend_service.repository.CustomerRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            log.error("Customer with email {} already exists", customer.getEmail());
            throw new EntityAlreadyExistsException("Customer with email " + customer.getEmail() + " already exists");
        }
        customerRepository.save(customer);
    }


    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Long id, Customer customer) {
        Customer customerToUpdate = getCustomer(id);
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        customerRepository.save(customerToUpdate);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
