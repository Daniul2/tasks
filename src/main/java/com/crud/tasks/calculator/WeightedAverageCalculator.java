

package com.crud.tasks.calculator;

import java.util.List;

public class WeightedAverageCalculator {

    public double calculate(List<Integer> grades, List<Integer> weights) {
        if (grades == null || weights == null || grades.isEmpty() || weights.isEmpty()) {
            throw new IllegalArgumentException("Lists cannot be empty.");
        }
        if (grades.size() != weights.size()) {
            throw new IllegalArgumentException("Lists must be of the same length.");
        }

        double sumOfProducts = 0;
        double sumOfWeights = 0;

        for (int i = 0; i < grades.size(); i++) {
            int grade = grades.get(i);
            int weight = weights.get(i);

            if (grade < 1 || grade > 6) throw new IllegalArgumentException("Grade outside the range 1-6");
            if (weight < 1 || weight > 10) throw new IllegalArgumentException("Weight outside the range 1-10");

            sumOfProducts += grade * weight;
            sumOfWeights += weight;
        }

        return sumOfProducts / sumOfWeights;
    }
}
