package br.com.devleo.securitypassword.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devleo.securitypassword.domain.Password;
import br.com.devleo.securitypassword.repositories.PasswordRepository;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public Password newPassword(boolean uppercase, boolean lowercase, boolean symbols, boolean numbers, int size) {
        if (!uppercase && !lowercase && !symbols && !numbers) {
            return null;
        }
        String listChars = "abcdefghijklmnopqrstuvwxyz";
        String listSymbols = "!@#$%&";
        String listNumbers = "0123456789";

        StringBuilder password = new StringBuilder(size);
        Random random = new Random();
        String chars = "";
        if (uppercase)
            chars = chars.concat(listChars.toUpperCase());
        if (lowercase)
            chars = chars.concat(listChars.toLowerCase());
        if (symbols)
            chars = chars.concat(listSymbols);
        if (numbers)
            chars = chars.concat(listNumbers);

        for (int i = 0; i < size; i++) {
            int r = random.nextInt(chars.length());
            password.append(chars.charAt(r));
        }

        if (!checkPassword(chars)) {
            Password newpassword = new Password();
            newpassword.setPassword(password.toString());
            newpassword.setTimestamp(LocalDateTime.now());
            passwordRepository.save(newpassword);
            return newpassword;
        }
        return null;
    }

    private boolean checkPassword(String password) {
        Optional<Password> findPassword = passwordRepository.findPasswordByPassword(password);
        return findPassword.isPresent();
    }
}