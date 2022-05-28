package ru.itmo.utils;

public class BinarySearch {
    public static int binarySearch(ValueProvider provider, int value, int left, int right) {
        if (right > left) {
            int mid = left + (right - left) / 2;

            int midValue = provider.get(mid);
            if (midValue == value)
                return mid;
            if (midValue > value)
                return binarySearch(provider, value, left, mid);

            return binarySearch(provider, value, mid + 1, right);
        }
        return -1;
    }

    public interface ValueProvider {
        int get(int value);
    }
}