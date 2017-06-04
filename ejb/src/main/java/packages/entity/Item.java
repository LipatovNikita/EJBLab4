package packages.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "warehouse_address", nullable = false)
    private String warehouse_address;

    @Column(name = "is_closed")
    @Type(type = "boolean")
    private boolean isClosed;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;
}
