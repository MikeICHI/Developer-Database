package org.example.company.employer;

public interface Worker {
    default void work() {
        throw new RuntimeException("Not implemented");
    }
}
