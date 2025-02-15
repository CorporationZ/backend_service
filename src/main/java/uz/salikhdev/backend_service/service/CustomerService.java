package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.repository.CustomerRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);

    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomer(String id , Customer customer) {
        Customer cutomerToUpdate = getCustomer(id);
        cutomerToUpdate.setName(customer.getName());
        cutomerToUpdate.setEmail(customer.getEmail());
        customerRepository.save(cutomerToUpdate);
    }







}
