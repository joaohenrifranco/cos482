package com.cos482.ingressa.delegates;

import com.cos482.ingressa.domain.MovieTicket;
import com.cos482.ingressa.service.dto.MovieTicketProcessDTO;
import java.math.BigDecimal;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmPaymentDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        MovieTicketProcessDTO pi = (MovieTicketProcessDTO) delegateExecution.getVariable("pi");
        String name = pi.getMovieTicket().getCreditCardNumber();
        System.out.println("=================================================");
        System.out.println("=============== CARD VALIDATED ======================");
        System.out.println("=============== " + name + "======================");
        System.out.println("=================================================");
    }
}
