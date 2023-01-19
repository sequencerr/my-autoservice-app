package task.autoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer productionYear;
    private String numberPlate;
    @ManyToOne(fetch = FetchType.LAZY)
    private CarOwner owner;

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", productionYear=" + productionYear
                + ", numberPlate='" + numberPlate + '\''
                + ", owner=" + owner
                + '}';
    }
}
