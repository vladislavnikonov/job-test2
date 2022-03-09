package ru.inovus.jobtest2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inovus.jobtest2.service.NumberService;

@RestController
@RequestMapping(path = "/number", produces = "text/plain;charset=UTF-8")
public class NumberController {
    public NumberService numberService;

    @Autowired
    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/random")
    public ResponseEntity<String> random() {
        return ResponseEntity.ok().body(numberService.random());
    }

    @GetMapping("/next")
    public ResponseEntity<String> next() {
        return ResponseEntity.ok().body(numberService.next());
    }
}
