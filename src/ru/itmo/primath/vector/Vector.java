package ru.itmo.primath.vector;

import java.util.Arrays;

public class Vector {
    private final double[] data;

    public Vector(int length) {
        data = new double[length];
    }

    public Vector(double[] data) {
        this.data = data;
    }

    public void set(int i, double value) {
        data[i] = value;
    }

    public double get(int i) {
        return data[i];
    }

    public int size() {
        return data.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
