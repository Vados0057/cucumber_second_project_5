package utils;

public class RandomNumberGenerator {
    public static int getARandomNumber(int start, int end) {
        return (int) (Math.random() * (Math.abs(start - end) + 1)) + Math.min(start, end);
    }
}
