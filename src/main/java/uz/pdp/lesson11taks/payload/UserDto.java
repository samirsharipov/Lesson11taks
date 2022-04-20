package uz.pdp.lesson11taks.payload;

import lombok.Data;
import java.util.Set;

@Data
public class UserDto {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String password;
    private Set<Integer> warehouseIds;
}
