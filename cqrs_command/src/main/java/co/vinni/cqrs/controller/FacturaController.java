package co.vinni.cqrs.controller;

import co.vinni.messaging.FacturaConsumerService;
import co.vinni.model.Factura;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Profile("productor")
@RestController
@RequestMapping("/invoices")
public class FacturaController {

    private final FacturaConsumerService facturaConsumerService;

    public FacturaController(FacturaConsumerService facturaConsumerService) {
        this.facturaConsumerService = facturaConsumerService;
    }

    @GetMapping
    public List<Factura> getAllFacturas() {
        return facturaConsumerService.getAllFacturas();
    }

    @GetMapping("/order/{orderId}")
    public Factura getFacturaByOrderId(@PathVariable("orderId") String orderId) {
        return facturaConsumerService.getFacturaByOrderId(orderId);
    }

}