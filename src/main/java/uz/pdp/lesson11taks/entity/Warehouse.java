package uz.pdp.lesson11taks.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.lesson11taks.entity.template.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Warehouse extends AbsEntity {
}
