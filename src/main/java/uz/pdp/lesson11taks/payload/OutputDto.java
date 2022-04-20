package uz.pdp.lesson11taks.payload;

import lombok.Data;

@Data
public class OutputDto {
    private String factureNumber;
    private Integer warehouseId;
    private Integer currencyId;
    private Integer clientId;
}
