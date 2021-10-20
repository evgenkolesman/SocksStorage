package ru.koleson.socksstorage.model;

import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "socks")
public class SocksModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name = "cottonpart")
    @Max(value = 100)
    @Min(value = 0)
    private int cottonPart;

    @Column(name = "quantity")
    @Min(value = 1)
    private int quantity;

    public static SocksModel of(String color, int cottonPart, int quantity) {
        SocksModel socksModel = new SocksModel();
        socksModel.color = color;
        socksModel.cottonPart = cottonPart;
        socksModel.quantity = quantity;
        return socksModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocksModel that = (SocksModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SocksModel{");
        sb.append("id=").append(id);
        sb.append(", color='").append(color).append('\'');
        sb.append(", cottonPart=").append(cottonPart);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
