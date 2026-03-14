package com.example.logistics_api.controller;

import com.example.logistics_api.dto.ShipmentReportDto;
import com.example.logistics_api.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/summary")
    public ShipmentReportDto getShipmentSummaryReport() {
        return reportService.getShipmentSummaryReport();
    }
}