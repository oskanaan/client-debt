package com.thebinaryheap.clientdebt.controller;

import com.thebinaryheap.clientdebt.data.repository.ClientRepository;
import com.thebinaryheap.clientdebt.data.repository.PaymentRepository;
import com.thebinaryheap.clientdebt.data.repository.TransactionRepository;
import com.thebinaryheap.clientdebt.data.resources.Client;
import com.thebinaryheap.clientdebt.data.resources.Payment;
import com.thebinaryheap.clientdebt.data.resources.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("client")
public class ClientController {

  @Resource
  private ClientRepository clientRepository;

  @Resource
  private PaymentRepository paymentRepository;

  @Transactional
  @RequestMapping(method = RequestMethod.POST, path = "addNewMonthBalance")
  public void addNewMonthBalance(@RequestBody Map<String, String> params) {
    String month = params.get("month");
    String year = params.get("year");
    if (!StringUtils.isEmpty(month) && !StringUtils.isEmpty(year)) {
      clientRepository.findByActiveTrue().stream().forEach(client -> {
        if (client.isActive() && !isRecordAlreadyCreated(client.getId(), month, year)) {
          client.setCurrentBalance(client.getCurrentBalance().add(client.getMonthlySubscription()));
          client = clientRepository.save(client);
          Payment payment = new Payment(month, year, client.getMonthlySubscription(), BigDecimal.ZERO, client.getId());
          paymentRepository.save(payment);
        }
      });
    }
  }

  private boolean isRecordAlreadyCreated (Long clientId, String month, String year) {
    Payment criteria = new Payment();
    criteria.setClientId(clientId);
    criteria.setDueMonth(month);
    criteria.setDueYear(year);
    Example<Payment> paymentExample = Example.of(criteria);
    return paymentRepository.findOne(paymentExample).isPresent();
  }

}