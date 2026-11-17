package com.dukaansarthi.controller;

import com.dukaansarthi.dto.BillExtractionResponse;
import com.dukaansarthi.service.BillExtractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ai-bill")
@CrossOrigin(origins = "*")
public class AIBillController {

    private final BillExtractionService billExtractionService;

    public AIBillController(BillExtractionService billExtractionService) {
        this.billExtractionService = billExtractionService;
    }

    @PostMapping("/scan")
    public ResponseEntity<BillExtractionResponse> scanBill(
            @RequestParam("file") MultipartFile file,
            @RequestParam("businessId") Long businessId) {
        try {
            BillExtractionResponse response = billExtractionService.processAndExtractBill(file, businessId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
