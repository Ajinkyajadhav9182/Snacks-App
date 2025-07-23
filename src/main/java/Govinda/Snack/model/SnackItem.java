package Govinda.Snack.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SnackItem {

    @NotBlank(message = "Snack name is required")
    @Column(name = "snack_name", nullable = false)
    private String snackName;

    @Positive(message = "Quantity must be greater than zero")
    @Column(nullable = false)
    private int quantity;
}