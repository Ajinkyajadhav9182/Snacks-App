package Govinda.Snack.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "snack_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnackProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotBlank(message = "Snack name is required")
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    private double price;
}

