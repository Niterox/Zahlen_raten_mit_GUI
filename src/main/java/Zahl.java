import java.util.Random;

public class Zahl {

    private static int min = 0;
    private static int max = 1000;
    protected static int randomNumber = getRandomNumberInRange(min, max);
    protected static int usrNumber = 0;

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    protected static void compareNumber(int usrNumber) {

        if (usrNumber < randomNumber) {
            JavaFx.root.setRight(JavaFx.correctGreyView);
            JavaFx.root.setLeft(JavaFx.downGreyView);
            JavaFx.root.setCenter(JavaFx.upView);
        } else if (usrNumber > randomNumber) {
            JavaFx.root.setRight(JavaFx.correctGreyView);
            JavaFx.root.setCenter(JavaFx.upGreyView);
            JavaFx.root.setLeft(JavaFx.downView);
        } else {
            JavaFx.root.setLeft(JavaFx.downGreyView);
            JavaFx.root.setCenter(JavaFx.upGreyView);
            JavaFx.root.setRight(JavaFx.correctView);
        }
    }
    public static int getMin() {
        return min;
    }

    public static int getMax() {
        return max;
    }

}