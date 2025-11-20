// co/vinni/cqrs/persistence/repository/PromotionViewRepository.java
package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.*;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PromotionViewRepository extends MongoRepository<PromotionView, String> {
    List<PromotionView> findByKitchen(Kitchen kitchen);
}
