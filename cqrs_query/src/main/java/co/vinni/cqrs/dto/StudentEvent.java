package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEvent {
    private String eventType;
    private Student student;

    public String getEventType() {
        return eventType;
    }

    public Student getStudent() {
        return student;
    }
}
