package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InMemoryStorage {

    protected static final ArrayList<Long> earningInYear = new ArrayList<>();
    private static final ArrayList<Long> earningInMonths = new ArrayList<>();
    protected static final ArrayList<Long> netProfit = new ArrayList<>();
    protected static final ArrayList<Long> expenseInYear = new ArrayList<>();
    private static final ArrayList<Long> expenseInMonths = new ArrayList<>();

    protected final LinkedHashMap<Integer, ArrayList<ItemMonth>> monthReports = new LinkedHashMap<>();

    protected final void saveMonthReport(int month, ArrayList<ItemMonth> itemMonth) {
        monthReports.put(month, itemMonth);
    }

    protected final LinkedHashMap<Integer, ArrayList<ItemYear>> yearReports = new LinkedHashMap<>();

    protected final void saveYearReport(int year, ArrayList<ItemYear> itemYear) {
        yearReports.put(year, itemYear);
    }

    protected final Long getEarningYear(int year) {
        long count = 0L;
        long total = 0L;
        ArrayList<ItemYear> items = yearReports.get(year);
        for (ItemYear item : items) {
            if (!item.expense) {
                continue;
            }
            count++;
            total = total + item.getAmountYear();
            earningInYear.add(item.getAmountYear());
        }
        return total / count;
    }

    protected final Long getExpenseYear(int year) {
        long total = 0L;
        long count = 0L;
        ArrayList<ItemYear> items = yearReports.get(year);
        for (ItemYear item : items) {
            if (item.expense) {
                continue;
            }
            count++;
            total = total + item.getAmountYear();
            expenseInYear.add(item.getAmountYear());
        }
        return total / count;
    }

    protected final void getEarningMonthInYear(int year) {

        Long total1 = 0L;
        Long total2 = 0L;
        long totalEnd;
        ArrayList<ItemYear> items = yearReports.get(year);
        for (ItemYear item : items) {
            if (item.expense) {
                total1 = item.amount;
            }
            if (!item.expense) {
                total2 = item.amount;
            }
            if (total1 > 0 && total2 > 0) {
                totalEnd = total1 - total2;
                System.out.println(item.month + " месяц: " + totalEnd);
                netProfit.add(totalEnd);
                total1 = 0L;
                total2 = 0L;

            }
        }
    }

    protected final ItemMonth getMaxEarning(int month) {
        ArrayList<ItemMonth> items = monthReports.get(month);
        ItemMonth max = null;
        long total = 0;
        for (ItemMonth item : items) {
            if (!item.expense) {
                continue;
            }
            if (item.getTotal() > total) {
                total = item.getTotal();
                max = item;
            }
        }
        return max;
    }

    protected final ItemMonth getMaxExpense(int month) {
        ArrayList<ItemMonth> items = monthReports.get(month);
        ItemMonth max = null;
        long total = 0;
        for (ItemMonth item : items) {
            if (item.expense) {
                continue;
            }
            if (item.getTotal() > total) {
                total = item.getTotal();
                max = item;
            }
        }
        return max;
    }

    protected final void getEarning(int month) {
        ArrayList<ItemMonth> items = monthReports.get(month);
        long count = 0;
        for (ItemMonth item : items) {
            if (item.expense) {
                if (count == 0) {
                    count = item.getTotal();
                } else if (count > 0) {
                    count = count + item.getTotal();
                }
            }
        }
        earningInMonths.add(count);
        for (int i = 0; i < earningInMonths.size(); i++) {
            System.out.println(i + " - " + earningInMonths.get(i));
        }
    }

    protected final void getExpense(int month) {
        ArrayList<ItemMonth> items = monthReports.get(month);
        long count = 0;
        for (ItemMonth item : items) {
            if (!item.expense) {
                if (count == 0) {
                    count = item.getTotal();
                } else if (count > 0) {
                    count = count + item.getTotal();
                }
            }
        }
        expenseInMonths.add(count);
    }

    protected final void rev() {
        for (Long item : earningInYear) {
            if (!earningInMonths.contains(item)) {
                System.out.println("Элемент " + item + " не найден в отчете");
            } else System.out.println("Отчеты верны! Спасибо Вам за работу");
        }
    }
}
