package org.example;

public class Allocation {
    private String name;
    private double percentage;

    public Allocation(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f%%", name, percentage * 100);
    }
}
