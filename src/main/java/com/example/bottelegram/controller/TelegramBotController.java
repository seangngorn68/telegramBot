package com.example.bottelegram.controller;


import com.example.bottelegram.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/telegram")
public class TelegramBotController {

    @Autowired
    private TelegramBotService telegramBotService;

    @PostMapping("/sendInvoice")
    public ResponseEntity<String> sendInvoice(@RequestParam("chatId") String chatId,
                                              @RequestParam("file") MultipartFile file) {
        try {
            String response = telegramBotService.sendInvoiceFile(chatId, file);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
