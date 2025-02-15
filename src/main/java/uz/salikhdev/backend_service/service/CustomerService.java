package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.request.CreateCustomerDto;
import uz.salikhdev.backend_service.dto.response.CustomerDto;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.exception.EntityAlreadyExistsException;
import uz.salikhdev.backend_service.exception.EntityNotFound;
import uz.salikhdev.backend_service.repositroy.CustomerRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void createCustomer(CreateCustomerDto dto){
        if (customerRepository.existsByEmail(dto.email())){
            log.error("Customer email with:" + dto.email() + "is already exists");
            throw new EntityAlreadyExistsException("Customer email with:" + dto.email() + "is already exists");
        }

        Customer customer = Customer.builder()
                .name(dto.name())
                .email(dto.email())
        .build();
        customerRepository.save(customer);
    }

    public Customer getCustomer(Long id){
        return customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("Customer not found id : %s".formatted(id))
        );
    }


}
