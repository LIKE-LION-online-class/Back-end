package likelion.happyrestaurant.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    LocalDate createDate;
    LocalDate updateDate;
    LocalDate deleteDate;
}
