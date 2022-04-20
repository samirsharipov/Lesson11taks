package uz.pdp.lesson11taks.payload;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private Integer categoryId;
    private Integer attachmentId;
    private Integer measurementId;
}
