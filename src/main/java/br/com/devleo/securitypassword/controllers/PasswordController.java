package br.com.devleo.securitypassword.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.devleo.securitypassword.domain.Password;
import br.com.devleo.securitypassword.services.PasswordService;

@RestController
@RequestMapping(path = "api/v1/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping
    public ResponseEntity<String> getNewPassword(@RequestParam("uppercase") boolean uppercase,
            @RequestParam("lowercase") boolean lowercase,
            @RequestParam("symbols") boolean symbols,
            @RequestParam("numbers") boolean numbers,
            @RequestParam("size") int size) {
        Password password = passwordService.newPassword(uppercase, lowercase, symbols, numbers, size);
        if (password == null) {
            return ResponseEntity.badRequest().body("Error to generate password, please try again.");
        }
        return ResponseEntity.ok().body(password.getPassword());
    }

}
