package br.com.devleo.securitypassword.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.devleo.securitypassword.domain.Password;
import br.com.devleo.securitypassword.services.PasswordService;

@RestController
@RequestMapping(path = "api/v1/password")
@CrossOrigin(origins = "*")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping
    public ResponseEntity<Password> getNewPassword(@RequestParam("uppercase") boolean uppercase,
            @RequestParam("lowercase") boolean lowercase,
            @RequestParam("symbols") boolean symbols,
            @RequestParam("numbers") boolean numbers,
            @RequestParam("size") int size) {
        Password password = passwordService.newPassword(uppercase, lowercase, symbols, numbers, size);
        if (password == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(password, HttpStatus.OK);
    }

}
