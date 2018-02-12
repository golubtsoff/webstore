package entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

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

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "AMOUNT")
    private int amount;

    public Item(){}

    public Item(String title, String description, double price, int amount) {
        this(null, title, description, price, amount);
    }

    public Item(Long id, @NotNull String title, String description, double price, int amount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
