package task.autoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car_owners")
public class CarOwner extends IdentifiableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Car> cars;
    @OneToMany
    private List<Order> orders;

    @Override
    public String toString() {
        return "CarOwner{"
                + "id=" + id
                + ", cars=" + cars
                + ", orders=" + orders
                + '}';
    }
}
