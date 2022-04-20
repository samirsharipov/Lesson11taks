package uz.pdp.lesson11taks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lesson11taks.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String code;

    private String password;

    private boolean active = true;

    @ManyToMany
    private Set<Warehouse> warehouses;

}
