package ru.lanit.test.controller.delete;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.test.utils.validation.DeleteValidation;

@RestController
public class DeleteAll {
    private final DeleteValidation deleteValidation;

    public DeleteAll(DeleteValidation deleteValidation) {
        this.deleteValidation = deleteValidation;
    }

    @GetMapping(value = "/clear")
    public ResponseEntity<String> deleteAll() {
        return deleteValidation.deleteValidation();
    }
}
