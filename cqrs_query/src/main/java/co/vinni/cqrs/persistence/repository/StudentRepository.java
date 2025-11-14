package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    // Query methods derivados del nombre (Spring Data los implementa automáticamente)
    Optional<Student> findByEmail(String email);

    List<Student> findByFirstNameContainingIgnoreCase(String firstName);

    List<Student> findByLastNameContainingIgnoreCase(String lastName);

    // Query personalizada con @Query para búsquedas más complejas
    @Query("{ '$or': [ " +
            "{ 'firstName': { $regex: ?0, $options: 'i' } }, " +
            "{ 'lastName': { $regex: ?0, $options: 'i' } }, " +
            "{ 'email': { $regex: ?0, $options: 'i' } } " +
            "] }")
    List<Student> searchByKeyword(String keyword);

    // Verificar existencia por email
    boolean existsByEmail(String email);
}