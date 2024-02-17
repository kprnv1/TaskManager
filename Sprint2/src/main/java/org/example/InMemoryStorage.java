package org.example;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

public class InMemoryStorage {
    static ArrayList<Long> earningInYear = new ArrayList<>();
    static ArrayList<Long> earningInMonths = new ArrayList<>();
    static ArrayList<Long> netProfit = new ArrayList<>(); //чистая прибыль
    static ArrayList<Long> expenseInYear = new ArrayList<>();
    static ArrayList<Long> expenseInMonths = new ArrayList<>();


    LinkedHashMap<Integer, ArrayList<ItemMonth>> monthReports = new LinkedHashMap<>();

    public void saveMonthReport(int month, ArrayList<ItemMonth> itemMonth) {
        monthReports.put(month, itemMonth);
    }

    LinkedHashMap<Integer, ArrayList<ItemYear>> yearReports = new LinkedHashMap<>();

    public void saveYearReport(int year, ArrayList<ItemYear> itemYear) {
        yearReports.put(year, itemYear);
    }


    public Long getEarningYear(int year) {    // Средний доход за все месяцы в году
        Long count = Long.valueOf(0);
        Long total = Long.valueOf(0);
        ArrayList<ItemYear> items = yearReports.get(year);
        for (ItemYear item : items) {
            if (!item.expense) {
                continue;
            }
            count++;
            total = total + item.getAmountYear();
            earningInYear.add(item.getAmountYear());                    // добавляем в список !!!
        }
        return total / count;
    }

    public Long getExpenseYear(int year) {   // Средний расход за все месяцы в году
        Long total = Long.valueOf(0);
        Long count = Long.valueOf(0);
        ArrayList<ItemYear> items = yearReports.get(year);
        for (ItemYear item : items) {
            if (item.expense) {
                continue;
            }
            count++;
            total = total + item.getAmountYear();
            expenseInYear.add(item.getAmountYear());                    // добавляем в список !!!
        }
        return total / count;
    }

    public void getEarningMonthInYear(int year) {    // прибыль по каждому месяцу

        Long total1 = Long.valueOf(0);
        Long total2 = Long.valueOf(0);
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
                total1 = Long.valueOf(0);
                total2 = Long.valueOf(0);

            }
        }
    }

    public ItemMonth getMaxEarning(int month) {        // доход
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
//                earningInMonths.add(total);
            }
        }
        return max;
    }

    public ItemMonth getMaxExpense(int month) {
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

    public Long getEarning(int month) {
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
        System.out.println("Доход: " + count);
        earningInMonths.add(count);
        for (int i = 0; i < earningInMonths.size(); i++) {
            System.out.println(i + " - " + earningInMonths.get(i));
        }
        return count;
    }


    public Long getExpense(int month) {
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
        System.out.println("Расход: " + count);
        expenseInMonths.add(count);
        return count;
    }


    public void rev() {
        for (Long item : earningInYear) {
            if (!earningInMonths.contains(item)) {
                System.out.println("Элемент " + item + " не найден в отчете");
            } else System.out.println("Отчеты верны! Спасибо Вам за работу");
        }
    }
}
