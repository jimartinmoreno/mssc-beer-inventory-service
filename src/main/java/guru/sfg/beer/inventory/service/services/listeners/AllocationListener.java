package guru.sfg.beer.inventory.service.services.listeners;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.services.AllocationService;
import guru.sfg.brewery.model.events.AllocateOrderRequest;
import guru.sfg.brewery.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Listener JMS de la cola JmsConfig.ALLOCATE_ORDER_QUEUE  que llama al servicio para crear una nueva order
 * y envía un mensaje de respuesta con el resultado a la cola JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest request) {

        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();
        builder.beerOrderDto(request.getBeerOrderDto());

        try {
            builder.allocationError(false);
            builder.pendingInventory(allocationService.allocateOrder(request.getBeerOrderDto()).booleanValue());

            //            Boolean allocationResult = allocationService.allocateOrder(request.getBeerOrderDto());
            //            if (allocationResult){
            //                builder.pendingInventory(false);
            //            } else {
            //                builder.pendingInventory(true);
            //            }
        } catch (Exception e) {
            log.error("Allocation failed for Order Id:" + request.getBeerOrderDto().getId());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE,
                builder.build());

    }
}
