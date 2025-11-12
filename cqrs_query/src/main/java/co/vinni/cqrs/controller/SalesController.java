package co.vinni.cqrs.controller;

import co.vinni.model.Venta;
import co.vinni.repository.SalesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesRepository repo;

    public SalesController(SalesRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Venta> all() {
        return repo.all();
    }
}
