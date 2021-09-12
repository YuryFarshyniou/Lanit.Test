package ru.lanit.test.controller.delete;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;

@Controller
public class DeleteAll {
    private final ICarService carService;
    private final IPersonService personService;

    public DeleteAll(ICarService carService, IPersonService personService) {
        this.carService = carService;
        this.personService = personService;
    }

    @GetMapping(value = "/clear")
    public ResponseEntity<String> deleteAll() {
        carService.deleteAll();
        personService.deleteAll();
        return new ResponseEntity<>("Entities successfully deleted", HttpStatus.OK);
    }
}
