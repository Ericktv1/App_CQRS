// co/vinni/cqrs/persistence/repository/InvoiceRepository.java
package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Invoice;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {
    Optional<Invoice> findByOrderId(Long orderId);
}
