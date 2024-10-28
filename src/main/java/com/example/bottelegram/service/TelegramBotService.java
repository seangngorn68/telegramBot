package com.example.bottelegram.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TelegramBotService {
    private final String botToken = "7167977845:AAEPrDiCzxMyMjQiJbMtgpP4pUhEOeBQ8ss";
    private final String telegramUrlApi = "https://api.telegram.org/bot" + botToken + "/sendDocument";
    private final RestTemplate restTemplate = new RestTemplate();
    private static final long MAX_FILE_SIZE = 50 * 1024 * 1024;
    public String sendInvoiceFile(String chatId, MultipartFile file) throws Exception {
        if (file.getSize() > MAX_FILE_SIZE) {
            return "File size exceeds the 50 MB limit.";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("chat_id", chatId); // Ensure correct field name for Telegram API
        body.add("document", file.getResource()); // Correctly add the file as a Resource
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(telegramUrlApi, requestEntity, String.class);
    }
}
