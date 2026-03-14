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
    private final StatusService statusService;

    public ReportService(ShipmentRepository shipmentRepository, StatusService statusService) {
        this.shipmentRepository = shipmentRepository;
        this.statusService = statusService;
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

            if (shipment.getStatusId() != null) {
                Status status = statusService.getStatusById(shipment.getStatusId());
                String statusName = status.getName();

                if (statusName != null) {
                    String normalized = statusName.trim().toLowerCase();

                    if (normalized.equals("delivered")) {
                        deliveredCount++;
                    } else if (normalized.equals("in transit")) {
                        inTransitCount++;
                    } else if (normalized.equals("stored")) {
                        storedCount++;
                    }
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