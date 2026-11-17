package com.dukaansarthi.service;

import com.dukaansarthi.dto.BillExtractionResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class BillExtractionService {

    @Value("${gemini.api-key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String modelId;

    @Value("${gemini.url}")
    private String apiUrl;

    private final ObjectMapper objectMapper;

    public BillExtractionService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public BillExtractionResponse processAndExtractBill(MultipartFile file, Long businessId) throws Exception {
        byte[] fileBytes = file.getBytes();
        String base64Data = Base64.getEncoder().encodeToString(fileBytes);
        String mimeType = file.getContentType();

        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        String prompt = "You are an invoice data extraction assistant for a retail ERP system. Extract supplier details, invoice details, and every product line from the provided invoice. " +
                "Return ONLY valid JSON matching this schema: " +
                "{\"supplier\": {\"name\": \"string\", \"gstin\": \"string\"}, " +
                "\"invoice\": {\"number\": \"string\", \"date\": \"YYYY-MM-DD\", \"currency\": \"INR\", \"subtotal\": 0.0, \"taxTotal\": 0.0, \"grandTotal\": 0.0}, " +
                "\"items\": [{\"lineNumber\": 1, \"productName\": \"string\", \"brand\": \"string\", \"barcode\": \"string\", \"hsnCode\": \"string\", \"quantity\": 0, \"unit\": \"string\", \"unitPrice\": 0.0, \"discountAmount\": 0.0, \"taxRate\": 0.0, \"taxAmount\": 0.0, \"lineTotal\": 0.0, \"warnings\": []}], " +
                "\"documentWarnings\": []}. Do not guess values; use null if missing.";

        Map<String, Object> requestBody = Map.of(
            "contents", List.of(
                Map.of(
                    "parts", List.of(
                        Map.of(
                            "inline_data", Map.of(
                                "mime_type", mimeType,
                                "data", base64Data
                            )
                        ),
                        Map.of(
                            "text", prompt
                        )
                    )
                )
            )
        );

        String endpointUrl = apiUrl + "/" + modelId + ":generateContent?key=" + apiKey;

        WebClient client = WebClient.builder()
                .defaultHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        String responseJson = client.post()
                .uri(endpointUrl)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonNode rootNode = objectMapper.readTree(responseJson);
        String textContent = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
        
        if (textContent.startsWith("```json")) {
            textContent = textContent.substring(7);
        } else if (textContent.startsWith("```")) {
            textContent = textContent.substring(3);
        }
        if (textContent.endsWith("```")) {
            textContent = textContent.substring(0, textContent.length() - 3);
        }
        textContent = textContent.trim();

        return objectMapper.readValue(textContent, BillExtractionResponse.class);
    }
}
