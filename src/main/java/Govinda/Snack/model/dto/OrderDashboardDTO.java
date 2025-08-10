package Govinda.Snack.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDashboardDTO {
    private String id;
    private String customerName;
    private String email;
    private String contactNumber;
    private String address;
    private List<OrderItemDTO> items;
    private double totalAmount;
    private boolean delivered;
    private LocalDateTime createdAt;
}

