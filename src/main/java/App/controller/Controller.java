package App.controller;

import App.services.IbanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/iban/{iban}")
    public String hello(@PathVariable final String iban) {
        IbanService ibanService = new IbanService();
        return ibanService.ibanService(iban);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}