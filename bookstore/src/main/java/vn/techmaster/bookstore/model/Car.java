package vn.techmaster.bookstore.model;

import java.text.DecimalFormat;


public class Car {
    private int id = 0;
    private String name;
    private String manufacturner;
    private int price;

    public Car( int id, String name, String manufacturner, int price) {
        this.id = id;
        this.name = name;
        this.manufacturner = manufacturner;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturner() {
        return manufacturner;
    }

    public void setManufacturner(String manufacturner) {
        this.manufacturner = manufacturner;
    }

    public int getPrice() {
        return price;

    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((manufacturner == null) ? 0 : manufacturner.hashCode());
        result = prime * result + id;
        result = prime * result + ((manufacturner == null) ? 0 : manufacturner.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturner='" + manufacturner + '\'' +
                ", price=" + price +
                '}';
    }
}
