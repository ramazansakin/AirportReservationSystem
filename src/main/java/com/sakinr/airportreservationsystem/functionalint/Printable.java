package com.sakinr.airportreservationsystem.functionalint;

@FunctionalInterface
public interface Printable {
    void print(Object obj);   // single  abstract method called SAM

    // The interface can contain any number of Object class methods
    int hashCode();

    String toString();

    boolean equals(Object obj);
}
