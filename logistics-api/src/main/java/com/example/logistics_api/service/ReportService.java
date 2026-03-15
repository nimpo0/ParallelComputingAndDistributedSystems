package com.example.logistics_api.service;

import com.example.logistics_api.dto.ShipmentReportDto;
import com.example.logistics_api.model.Shipment;
import com.example.logistics_api.model.Status;
import com.example.logistics_api.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ShipmentRepository shipmentRepository;

    public ReportService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public ShipmentReportDto getShipmentSummaryReport() {
        List<Shipment> shipments = shipmentRepository.findAll();

        int totalShipments = shipments.size();
        double totalWeight = 0.0;
        int deliveredCount = 0;
        int inTransitCount = 0;
        int storedCount = 0;

        for (Shipment shipment : shipments) {
            if (shipment.getWeight() != null) {
                totalWeight += shipment.getWeight();
            }

            Status status = shipment.getStatus();
            if (status != null && status.getName() != null) {
                String normalized = status.getName().trim().toLowerCase();

                if (normalized.equals("delivered")) {
                    deliveredCount++;
                } else if (normalized.equals("in transit")) {
                    inTransitCount++;
                } else if (normalized.equals("at warehouse") || normalized.equals("stored")) {
                    storedCount++;
                }
            }
        }

        double averageWeight = totalShipments > 0 ? totalWeight / totalShipments : 0.0;

        return new ShipmentReportDto(
                totalShipments,
                totalWeight,
                averageWeight,
                deliveredCount,
                inTransitCount,
                storedCount
        );
    }
}