package com.thebinaryheap.clientdebt.controller;

import com.thebinaryheap.clientdebt.data.repository.ClientRepository;
import com.thebinaryheap.clientdebt.data.repository.PaymentRepository;
import com.thebinaryheap.clientdebt.data.repository.TransactionRepository;
import com.thebinaryheap.clientdebt.data.resources.Client;
import com.thebinaryheap.clientdebt.data.resources.Payment;
import com.thebinaryheap.clientdebt.data.resources.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("transaction")
public class TransactionController {

  @Resource
  private PaymentRepository paymentRepository;

  @Resource
  private ClientRepository clientRepository;

  @Resource
  private TransactionRepository transactionRepository;

  @Transactional
  @RequestMapping(method = RequestMethod.POST, path = "pay")
  public void saveTransaction(HttpServletResponse response, @RequestBody Transaction transaction) {
    Client client = clientRepository.findById(transaction.getClientId()).get();
    BigDecimal currentBalance = client.getCurrentBalance();
    client.setCurrentBalance(currentBalance.subtract(transaction.getAmountPaid()));

    Date onDate = transaction.getOnDate();
    Payment payment = getPayment(transaction.getClientId(), transaction.getDueMonth(), transaction.getDueYear());
    transaction.setOnDate(onDate);
    transactionRepository.save(transaction);
    payment.setTotalAmountPaid(payment.getTotalAmountPaid().add(transaction.getAmountPaid()));
    paymentRepository.save(payment);
  }

  @Transactional
  @RequestMapping(method = RequestMethod.DELETE, path = "/{transactionId}")
  public void  deleteTransaction(@PathVariable Long transactionId) {
    Transaction transaction = transactionRepository.findById(transactionId).get();
    Client client = clientRepository.findById(transaction.getClientId()).get();
    BigDecimal currentBalance = client.getCurrentBalance();
    if (transaction.getAmountPaid() != null) {
      client.setCurrentBalance(currentBalance.add(transaction.getAmountPaid()));
    }
    transactionRepository.delete(transaction);

    Payment payment = getPayment(transaction.getClientId(), transaction.getDueMonth(), transaction.getDueYear());
    payment.setTotalAmountPaid(payment.getTotalAmountPaid().subtract(transaction.getAmountPaid()));
  }

  private Payment getPayment(Long clientId, String month, String year) {
    Payment criteria = new Payment();
    criteria.setClientId(clientId);
    criteria.setDueMonth(month);
    criteria.setDueYear(year);
    Example<Payment> transactionExample = Example.of(criteria);
    Optional<Payment> result = paymentRepository.findOne(transactionExample);
    return result.isPresent()? paymentRepository.findOne(transactionExample).get(): null;

  }
}