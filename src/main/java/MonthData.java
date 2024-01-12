public class MonthData {
    static int[] days;

    MonthData() {
        days = new int[30];
    }


//    void printDaysAndStepsFromMonth() {
//        for (int i = 0; i < days.length; i++) {
//            System.out.println(i + " день: " + days[i] + " шагов");
//        }
//    }


    static int sumStepsFromMonth() {
        int sumSteps = 0;
        for (int i = 0; i < days.length; i++) {
            sumSteps += days[i];
// подсчёт суммы элементов массива days[]
        }
        return sumSteps;
    }


    static int maxSteps(int month) {
        int maxSteps = 0;
        for (int i = 0; i < StepTracker.monthToData[month - 1].days.length; i++) {
            if (maxSteps < StepTracker.monthToData[month - 1].days[i]) {
                maxSteps = StepTracker.monthToData[month - 1].days[i];
            }
        }
// поиск максимального элемента

        return maxSteps;
    }


    static int bestSeries(int goalByStepsPerDay) {
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
// поиск максимальной серии
        return finalSeries;
    }
    static double getAverageSteps(int month) {
        int stepsCount = 0;
        for (int i = 0; i < StepTracker.monthToData[month - 1].days.length; i++) {
            stepsCount = stepsCount + StepTracker.monthToData[month - 1].days[i];
        }
        return stepsCount / StepTracker.monthToData[month - 1].days.length;
    }
}
