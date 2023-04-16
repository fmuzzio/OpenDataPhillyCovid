package edu.upenn.cit5940.processor;

import edu.upenn.cit5940.util.Property;
import java.util.List;

interface AverageStrategy {
    double calculateAverage(List<Property> properties);
}
