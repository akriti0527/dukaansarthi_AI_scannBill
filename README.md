# Dukaansarthi AI Purchase Invoice Scanner (Complete Package)

This project implements the **Dukaansarthi AI Supplier Bill Scanning & Automatic Product Entry** feature using **Google Gemini API**, Spring Boot (No Lombok), PostgreSQL, and a standalone HTML/CSS/JS frontend.

## Architecture & Stack
- **Frontend**: Standalone HTML/CSS/JS (`frontend/index.html`) with file upload, preview, extraction results table, and Add New Product modal.
- **Backend**: Java Spring Boot REST API (Plain Java DTOs, no Lombok annotation processing issues) for file validation, Gemini API integration (`gemini-2.5-flash`), and PostgreSQL database persistence.
- **AI Service**: Google Gemini API for document/image understanding and structured invoice extraction.
- **Database**: PostgreSQL (`suppliers`, `products`, `purchases`, `purchase_items`, `ai_bill_scans`).

## Quick Setup Guide
1. **Database Setup**:
   ```sql
   CREATE DATABASE dukaansarthi;
   ```
   Run the DDL script from `src/main/resources/schema.sql`.

2. **Configure API Key**:
   Set your Gemini API key in `src/main/resources/application.yml` or as an environment variable:
   ```bash
   export GEMINI_API_KEY="your_actual_gemini_api_key"
   ```

3. **Run Backend**:
   Open the project in IntelliJ IDEA, let Maven sync dependencies, and run `DukaansarthiApplication.java`.

4. **Run Frontend**:
   Open `frontend/index.html` directly in your web browser.
