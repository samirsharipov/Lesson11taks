package uz.pdp.lesson11taks.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class OutputProductDto {
    private Integer productId;
    private double amount;
    private double price;
    private Integer outputId;
}
