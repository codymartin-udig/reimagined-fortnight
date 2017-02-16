package com.udig.whiteboard.models;

import org.springframework.data.annotation.Id;

public class Whiteboard {

    @Id
    public String id;

    public String manufacturer;
    public String size;
    public int quantity;
    public int price;
    public int sold;

    public Whiteboard () {
        this ("", "", 0, 0);
    }

    public Whiteboard (final String manufacturer, final String size, final int quantity, final int price) {
        this.manufacturer = manufacturer;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.sold = 0;
    }

    public void setSold(int sold) {
        this.sold += sold;
        this.quantity -= sold;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getId () {
        return this.id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString () {
        return String.format("Whiteboard[id=%s, manufacturer=%s, size=%s, quantity=%s, price=%s, sold=%s]",
                id, manufacturer, size,
                quantity, price, sold);
    }

}
