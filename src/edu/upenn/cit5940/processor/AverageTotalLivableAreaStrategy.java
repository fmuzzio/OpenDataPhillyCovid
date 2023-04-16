package edu.upenn.cit5940.processor;
import edu.upenn.cit5940.util.Property;
import java.util.List;

class AverageTotalLivableAreaStrategy implements AverageStrategy {
    public double calculateAverage(List<Property> properties) {
        return properties.stream()
                .mapToDouble(Property::getTotalLivableArea)
                .average()
                .orElse(0);
    }
}