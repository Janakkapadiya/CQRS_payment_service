package com.mycart.estore.PaymentService.command;

import com.mycart.estore.core.commands.ProcessPaymentCommand;
import com.mycart.estore.core.events.ProcessPaymentEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@RequiredArgsConstructor
@Aggregate
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private String orderId;

    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand){
        if(processPaymentCommand.getPaymentId() == null){
           throw new IllegalArgumentException("missing payment id");
        }
        if(processPaymentCommand.getOrderId() == null){
           throw new IllegalArgumentException("missing order id");
        }
        if(processPaymentCommand.getPaymentDetails() == null){
           throw new IllegalArgumentException("missing payment details");
        }

        AggregateLifecycle.apply(new ProcessPaymentEvent(processPaymentCommand.getOrderId(),
                processPaymentCommand.getPaymentId()));
    }

    @EventSourcingHandler
    public void on(ProcessPaymentEvent processPaymentEvent){
        this.orderId = processPaymentEvent.getOrderId();
        this.paymentId = processPaymentEvent.getPaymentId();
    }
}
