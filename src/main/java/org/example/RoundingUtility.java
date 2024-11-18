package org.example;

import java.util.List;

public class RoundingUtility {

    public static List<Allocation> processAllocations(List<Allocation> allocations) {
        // Sort allocations by descending order
        allocations.sort((a, b) -> Double.compare(b.getPercentage(), a.getPercentage()));

        // Round the percentages
        for (Allocation allocation : allocations) {
            allocation.setPercentage(Math.round(allocation.getPercentage() * 100.0) / 100.0);
        }

        // Normalize the percentages to ensure the total is 100%
        normalizePercentages(allocations);

        return allocations;
    }

    // Ensures that the sum of the percentages of all allocations is 1.0 (100%)
    private static void normalizePercentages(List<Allocation> allocations) {
        double total = allocations.stream().mapToDouble(Allocation::getPercentage).sum();

        // If total exceeds 100%, reduce the largest allocation
        if (total > 1.0) {
            double excess = total - 1.0;
            for (Allocation allocation : allocations) {
                // Reduce the largest allocations, but ensure no negative percentages
                double adjustedPercentage = allocation.getPercentage() - (allocation.getPercentage() / total) * excess;
                allocation.setPercentage(Math.max(0, adjustedPercentage));
            }
        } else if (total < 1.0) {
            // If total is less than 100%, increase the smallest allocation
            double difference = 1.0 - total;
            Allocation lastAllocation = allocations.get(allocations.size() - 1);
            lastAllocation.setPercentage(lastAllocation.getPercentage() + difference);
        }

        // Ensure no negative percentages and that the total is exactly 100%
        double adjustedTotal = allocations.stream().mapToDouble(Allocation::getPercentage).sum();
        if (Math.abs(adjustedTotal - 1.0) > 0.0001) {
            Allocation lastAllocation = allocations.get(allocations.size() - 1);
            lastAllocation.setPercentage(lastAllocation.getPercentage() + (1.0 - adjustedTotal));
        }
    }
}
