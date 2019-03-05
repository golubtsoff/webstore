package entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

/**
 * Created by Evgeniy Golubtsov on 09.02.2018.
 */

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Column(name = "DATETIME")
    private LocalDateTime dateTime;

    @Column(name = "COST")
    private BigDecimal cost;

    @Column(name = "AMOUNT")
    private int amount;

    public Purchase() {
    }

    public Purchase(Person person, Item item, LocalDateTime dateTime, int amount) {
        this.person = person;
        this.item = item;
        this.dateTime = dateTime;
        this.cost = item.getPrice().multiply(new BigDecimal(amount).setScale(0, RoundingMode.CEILING));
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public Item getItem() {
        return item;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @NotNull
    public BigDecimal getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", person=" + person +
                ", item=" + item +
                ", dateTime=" + dateTime +
                ", cost=" + cost +
                ", amount=" + amount +
                '}';
    }
}
