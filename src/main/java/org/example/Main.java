package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Allocation> allocations = new ArrayList<>();
        allocations.add(new Allocation("Bonds", 0.439865));
        allocations.add(new Allocation("Stocks", 0.234798));
        allocations.add(new Allocation("Crypto", 0.181233));
        allocations.add(new Allocation("Cash", 0.095678));
        allocations.add(new Allocation("Gold", 0.040978));
        allocations.add(new Allocation("Others", 0.007448));
        allocations.add(new Allocation("Real Estate", 0.062000)); // Extra allocation more than 6
        allocations.add(new Allocation("Commodities", 0.075500)); // Extra allocation more than 6

        // Original Allocations
        System.out.println("Original Allocations:");
        allocations.forEach(System.out::println);

        // Sort allocations from highest to lowest percentage using Stream
        List<Allocation> sortedAllocations = allocations.stream()
                .sorted((a1, a2) -> Double.compare(a2.getPercentage(), a1.getPercentage()))
                .collect(Collectors.toList());

        System.out.println("\nSorted Allocations (Highest to Lowest):");
        sortedAllocations.forEach(System.out::println);

        // Process the allocations (assumes processAllocations method exists)
        List<Allocation> processedAllocations = RoundingUtility.processAllocations(sortedAllocations);

        // Sort the processed allocations
        List<Allocation> sortedProcessedAllocations = processedAllocations.stream()
                .sorted((a1, a2) -> Double.compare(a2.getPercentage(), a1.getPercentage()))
                .collect(Collectors.toList());

        System.out.println("\nProcessed and Sorted Allocations:");
        sortedProcessedAllocations.forEach(System.out::println);

        // Calculate the total percentage of all allocations
        double totalPercentage = sortedProcessedAllocations.stream()
                .mapToDouble(Allocation::getPercentage)
                .sum();

        System.out.println("\nTotal Percentage: " + totalPercentage * 100 + "%");
    }
}
