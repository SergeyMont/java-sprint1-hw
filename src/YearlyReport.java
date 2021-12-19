import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YearlyReportLine> yearlyReport;

    public YearlyReport() {
        yearlyReport = new ArrayList<>();
    }

    public void addElements(Integer month, Integer amount, Boolean isExpense) {
        yearlyReport.add(new YearlyReportLine(month, amount, isExpense));
    }

    public void printYearlyMonthProfit() {
        int[] profit = new int[yearlyReport.size() / 2];
        for (YearlyReportLine y:yearlyReport) {
            if (y.isExpense) {
                profit[y.month-1] -= y.amount;
            } else {
                profit[y.month-1] += y.amount;
            }
        }
        for (int i = 0; i < profit.length; i++) {
            System.out.println("Прибыль за " + MonthOfYear.monthNames[i] + ":" + profit[i]);
        }
    }

    public void printYearlyMidProfit() {
        int yearProfit = 0;
        int yearExpense = 0;

        for (YearlyReportLine y : yearlyReport) {
            if (y.isExpense) {
                yearExpense += y.amount;
            } else {
                yearProfit += y.amount;
            }
        }
        System.out.println("Средний расход в этом году:" + yearExpense / (yearlyReport.size() / 2));
        System.out.println("Средний доход в этом году:" + yearProfit / (yearlyReport.size() / 2));

    }
}