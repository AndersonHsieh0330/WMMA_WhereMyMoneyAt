package com.AndersonHsieh.WMMA.Model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public Transaction(String name, Double amount, LocalDateTime time) {
        this.name = name;
        this. amount = amount;
        this.time = time;
    }

    public Transaction(Long id, String name, Double amount, LocalDateTime time) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.time = time;
    }

    public Transaction() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Amount=" + amount +
                ", time=" + time +
                '}';
    }
}
