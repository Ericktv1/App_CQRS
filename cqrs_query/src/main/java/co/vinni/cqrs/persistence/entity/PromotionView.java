package co.vinni.cqrs.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "promotions_query")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PromotionView {
    @Id
    private String id; // ya no hay @GeneratedValue, Mongo genera el ObjectId

    private Kitchen kitchen;
    private PromotionType type;
    private boolean active;
    private String targetProductCode;
}
