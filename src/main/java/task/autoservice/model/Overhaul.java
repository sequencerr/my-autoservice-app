package task.autoservice.model;

import jakarta.persistence.Column;
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

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "overhauls")
public class Overhaul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    @Column(name = "is_paid")
    private Boolean isPaid;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    private Repairer repairer;

    @Override
    public String toString() {
        return "Service{"
                + "id=" + id
                + ", price=" + price
                + ", isPaid=" + isPaid
                + ", orderId=" + (order == null ? null : order.getId())
                + ", repairerId=" + (repairer == null ? null : repairer.getId())
                + '}';
    }
}
