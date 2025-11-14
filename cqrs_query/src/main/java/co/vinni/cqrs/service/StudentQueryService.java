package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.StudentEvent;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.persistence.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentQueryService {

    private final StudentRepository studentRepository;

    @KafkaListener(topics = "student-event-topic", groupId = "student-event-group")
    public void processProductEvents(StudentEvent studentEvent) {

        try {
            Student student = studentEvent.getStudent();
            String event = studentEvent.getEventType();

            log.info("Processing event: {} for student code: {}", event, student.getCode());

            switch (event) {
                case "CreateStudent":
                    handleCreateStudent(student);
                    break;

                case "UpdateStudent":
                    handleUpdateStudent(student);
                    break;

                case "DeleteStudent":
                    handleDeleteStudent(student);
                    break;

                default:
                    log.warn("Unknown event type: {}", event);
            }
        } catch (Exception e) {
            log.error("Error processing student event: {}", studentEvent, e);
            // Aquí podrías implementar lógica de retry o dead letter queue
        }
    }

    private void handleCreateStudent(Student student) {
        // Verificar si ya existe para evitar duplicados
        String code = student.getCode();
        if (studentRepository.existsById(code)) {
            log.warn("Student with code {} already exists. Updating instead.", code);
            handleUpdateStudent(student);
        } else {
            studentRepository.save(student);
            log.info("Student created successfully: {}", code);
        }
    }

    private void handleUpdateStudent(Student student) {
        String code = student.getCode();
        studentRepository.findById(code).ifPresentOrElse(
                existing -> {
                    existing.setFirstName(student.getFirstName());
                    existing.setLastName(student.getLastName());
                    existing.setEmail(student.getEmail());
                    studentRepository.save(existing);
                    log.info("Student updated successfully: {}", code);
                },
                () -> {
                    log.warn("Student not found for update: {}. Creating new student.", code);
                    studentRepository.save(student);
                }
        );
    }

    private void handleDeleteStudent(Student student) {
        String code = student.getCode();
        studentRepository.deleteById(code);
        log.info("Student deleted successfully: {}", code);
    }

    public List<Student> getAll() {
        log.debug("Fetching all students");
        return studentRepository.findAll();
    }

    public Student getByCode(String code) {
        log.debug("Fetching student by code: {}", code);
        return studentRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Student not found with code: " + code));
    }
}