package co.vinni.cqrs.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data  // Esta anotación genera getters, setters, toString, equals y hashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "students_query")
public class Student {

    @Id
    private String code;
    private String firstName;
    private String lastName;
    private String email;
}