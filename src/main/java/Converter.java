public class Converter {
    static final double ONE_STEP_AT_KM = 0.00075;
    static final double CALORIES_TO_STEP = 0.05;

    static double convertToKm(int steps) {
        return (ONE_STEP_AT_KM * steps);
    }

    static double convertStepsToKilocalories(int steps) {
        return (steps / CALORIES_TO_STEP);
    }
}

