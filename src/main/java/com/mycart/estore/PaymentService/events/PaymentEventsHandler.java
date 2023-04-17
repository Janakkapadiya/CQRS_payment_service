package com.mycart.estore.PaymentService.events;

import com.mycart.estore.PaymentService.data.PaymentEntity;
import com.mycart.estore.PaymentService.data.PaymentsRepository;
import com.mycart.estore.core.events.ProcessPaymentEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventsHandler {

   private final PaymentsRepository paymentsRepository;

   @EventHandler
   public void on(ProcessPaymentEvent processPaymentEvent){
       PaymentEntity paymentEntity = new PaymentEntity();
       BeanUtils.copyProperties(processPaymentEvent,paymentEntity);
       paymentsRepository.save(paymentEntity);
   }
}
