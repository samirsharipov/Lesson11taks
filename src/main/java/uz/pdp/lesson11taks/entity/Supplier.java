package uz.pdp.lesson11taks.entity;

import lombok.*;
import org.hibernate.Hibernate;
import uz.pdp.lesson11taks.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Supplier extends AbsEntity {
    @Column(nullable = false,unique = true)
    private String phoneNumber;

    public Supplier() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Supplier supplier = (Supplier) o;
        return getId() != null && Objects.equals(getId(), supplier.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
