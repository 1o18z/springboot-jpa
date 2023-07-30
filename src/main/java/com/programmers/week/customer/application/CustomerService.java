package com.programmers.week.customer.application;

import com.programmers.week.Message;
import com.programmers.week.customer.ui.CustomerCreateRequest;
import com.programmers.week.customer.domain.Customer;
import com.programmers.week.customer.infra.CustomerRepository;
import com.programmers.week.customer.ui.CustomerResponse;
import com.programmers.week.customer.ui.CustomerUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  @Transactional
  public Long create(CustomerCreateRequest request) {
    Customer customer = new Customer(
            request.firstName(),
            request.lastName()
    );
    Customer saved = customerRepository.save(customer);
    return saved.getId();
  }

  public CustomerResponse findById(Long id) {
    return customerRepository.findById(id)
            .map(CustomerResponse::from)
            .orElseThrow(() -> new IllegalArgumentException(Message.CUSTOMER_IS_NO_EXIST));
  }

  public Page<CustomerResponse> findAll(PageRequest pageRequest) {
    Page<Customer> customerPage = customerRepository.findAll(pageRequest);
    return customerPage.map(CustomerResponse::from);
  }

  @Transactional
  public Long update(CustomerUpdateRequest request) {
    Customer customer = customerRepository.findById(request.id())
            .orElseThrow(() -> new IllegalArgumentException(Message.CUSTOMER_IS_NO_EXIST));
    customer.changeFirstName(request.firstName());
    customer.changeLastName(request.lastName());
    return customer.getId();
  }

  @Transactional
  public void deleteById(Long id) {
    customerRepository.deleteById(id);
  }

}
