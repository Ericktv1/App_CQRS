package co.vinni.cqrs.service.messaging;

import co.vinni.model.Notificacion;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificacionConsumerService {

    private final Map<String, Notificacion> notificaciones = new ConcurrentHashMap<>();

    @RabbitListener(queues = "notificacion-queue")
    public void recibirNotificacion(Notificacion notificacion) {
        System.out.println("📥 Notificación recibida: " + notificacion);
        notificaciones.put(notificacion.getOrderId(), notificacion);
    }

    public List<Notificacion> getAllNotificaciones() {
        return new ArrayList<>(notificaciones.values());
    }

    public Notificacion getNotificacionByOrderId(String orderId) {
        return notificaciones.get(orderId);
    }
}