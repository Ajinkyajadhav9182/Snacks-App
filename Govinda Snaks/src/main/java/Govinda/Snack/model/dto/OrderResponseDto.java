package Govinda.Snack.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponseDto {
    private String message;
    private double totalPrice;
}
