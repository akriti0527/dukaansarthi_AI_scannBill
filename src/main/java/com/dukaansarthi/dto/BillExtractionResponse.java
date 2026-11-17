package com.dukaansarthi.dto;

import java.math.BigDecimal;
import java.util.List;

public class BillExtractionResponse {
    private SupplierDTO supplier;
    private InvoiceDTO invoice;
    private List<ExtractedItemDTO> items;
    private List<String> documentWarnings;

    public SupplierDTO getSupplier() { return supplier; }
    public void setSupplier(SupplierDTO supplier) { this.supplier = supplier; }

    public InvoiceDTO getInvoice() { return invoice; }
    public void setInvoice(InvoiceDTO invoice) { this.invoice = invoice; }

    public List<ExtractedItemDTO> getItems() { return items; }
    public void setItems(List<ExtractedItemDTO> items) { this.items = items; }

    public List<String> getDocumentWarnings() { return documentWarnings; }
    public void setDocumentWarnings(List<String> documentWarnings) { this.documentWarnings = documentWarnings; }

    public static class SupplierDTO {
        private String name;
        private String gstin;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getGstin() { return gstin; }
        public void setGstin(String gstin) { this.gstin = gstin; }
    }

    public static class InvoiceDTO {
        private String number;
        private String date;
        private String currency;
        private BigDecimal subtotal;
        private BigDecimal taxTotal;
        private BigDecimal grandTotal;

        public String getNumber() { return number; }
        public void setNumber(String number) { this.number = number; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }

        public BigDecimal getSubtotal() { return subtotal; }
        public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

        public BigDecimal getTaxTotal() { return taxTotal; }
        public void setTaxTotal(BigDecimal taxTotal) { this.taxTotal = taxTotal; }

        public BigDecimal getGrandTotal() { return grandTotal; }
        public void setGrandTotal(BigDecimal grandTotal) { this.grandTotal = grandTotal; }
    }

    public static class ExtractedItemDTO {
        private Integer lineNumber;
        private String productName;
        private String brand;
        private String barcode;
        private String hsnCode;
        private BigDecimal quantity;
        private String unit;
        private BigDecimal unitPrice;
        private BigDecimal discountAmount;
        private BigDecimal taxRate;
        private BigDecimal taxAmount;
        private BigDecimal lineTotal;
        private List<String> warnings;

        public Integer getLineNumber() { return lineNumber; }
        public void setLineNumber(Integer lineNumber) { this.lineNumber = lineNumber; }

        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }

        public String getBrand() { return brand; }
        public void setBrand(String brand) { this.brand = brand; }

        public String getBarcode() { return barcode; }
        public void setBarcode(String barcode) { this.barcode = barcode; }

        public String getHsnCode() { return hsnCode; }
        public void setHsnCode(String hsnCode) { this.hsnCode = hsnCode; }

        public BigDecimal getQuantity() { return quantity; }
        public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }

        public BigDecimal getUnitPrice() { return unitPrice; }
        public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

        public BigDecimal getDiscountAmount() { return discountAmount; }
        public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }

        public BigDecimal getTaxRate() { return taxRate; }
        public void setTaxRate(BigDecimal taxRate) { this.taxRate = taxRate; }

        public BigDecimal getTaxAmount() { return taxAmount; }
        public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }

        public BigDecimal getLineTotal() { return lineTotal; }
        public void setLineTotal(BigDecimal lineTotal) { this.lineTotal = lineTotal; }

        public List<String> getWarnings() { return warnings; }
        public void setWarnings(List<String> warnings) { this.warnings = warnings; }
    }
}
