package uz.pdp.lesson11taks.payload;

import lombok.Data;

@Data
public class InputDto {
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
}
