public class MonthData {
    public static int[] days;

    public MonthData() {
        days = new int[30];
    }

    public static int sumStepsFromMonth() {
        int sumSteps = 0;
        for (int i = 0; i < days.length; i++) {
            sumSteps += days[i];
        }
        return sumSteps;
    }

    public static int maxSteps(int month) {
        int maxSteps = 0;
        for (int i = 0; i < StepTracker.monthToData[month - 1].days.length; i++) {
            if (maxSteps < StepTracker.monthToData[month - 1].days[i]) {
                maxSteps = StepTracker.monthToData[month - 1].days[i];
            }
        }
        return maxSteps;
    }

    public static int bestSeries(int goalByStepsPerDay) {
        int currentSeries = 0;
        int finalSeries = 0;
        int indexStartBest = 0;
        int indexStartCurrent = 0;
        for (int i = 1; i < days.length; i++) {
            if (days[i] >= goalByStepsPerDay) {
                if (currentSeries == 0)
                    indexStartCurrent = i;
                currentSeries++;
            }
            if (days[i] < goalByStepsPerDay || i == days.length - 1) {
                if (currentSeries > finalSeries) {
                    indexStartBest = indexStartCurrent;
                    finalSeries = currentSeries;
                }
                currentSeries = 0;
            }
        }
        System.out.println(finalSeries);
        return finalSeries;
    }

    public static double getAverageSteps(int month) {
        int stepsCount = 0;
        for (int i = 0; i < StepTracker.monthToData[month - 1].days.length; i++) {
            stepsCount = stepsCount + StepTracker.monthToData[month - 1].days[i];
        }
        return stepsCount / StepTracker.monthToData[month - 1].days.length;
    }
}
