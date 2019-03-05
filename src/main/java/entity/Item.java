package entity;

import org.hibernate.annotations.Type;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Evgeniy Golubtsov on 09.02.2018.
 */

@Entity
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", unique = true)
    @Type(type = "text")
    private String title;

    @Column(name = "DESCRIPTION")
    @Type(type = "text")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "AMOUNT")
    private int amount;

    public Item() {
    }

    public Item(String title, String description, BigDecimal price, int amount) {
        this(null, title, description, price, amount);
    }

    public Item(Long id, @NotNull String title, String description, BigDecimal price, int amount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price.setScale(2, RoundingMode.CEILING);
        this.amount = amount;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        return getTitle().equals(item.getTitle())
                && (getDescription() != null ? getDescription().equals(item.getDescription()) : item.getDescription() == null)
                && getPrice().equals(item.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
