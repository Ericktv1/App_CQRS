package co.vinni.cqrs.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Document(collection = "invoices_query")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice {
    @Id
    private String id;     // ObjectId en Mongo

    private Long orderId;  // esto sigue igual, es el id de la orden del dominio
    private String customer;
    private Kitchen kitchen;
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private boolean twoForOneApplied;
    private BigDecimal iva;
    private BigDecimal total;
}
