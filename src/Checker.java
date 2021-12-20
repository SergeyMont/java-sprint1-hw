import java.util.HashMap;

public class Checker {
    boolean checkFall;
    String nameLoose;

    public Checker() {
        checkFall = false;
        nameLoose = "";
    }

    void checkMonthYearReports(HashMap<Integer, MonthlyReport> monthlyBase,
                               YearlyReport yearlyReport) {
        for (YearlyReportLine y : yearlyReport.yearlyReport) {
            for (int i = 0; i < (yearlyReport.yearlyReport.size() / 2); i++) {
                if (y.month == (i + 1)) {
                    if (y.isExpense && y.amount == monthlyBase.get(i).findMonthExpense()) {

                    } else if (!y.isExpense && y.amount == monthlyBase.get(i).findMonthProfit()) {

                    } else {
                        nameLoose = MonthOfYear.monthNames[i];
                        checkFall = true;
                    }
                }
            }
        }
        if (checkFall) {
            System.out.println("Обнаружена ошибка в месяце:" + nameLoose);
        } else {
            System.out.println("Сверка отчетов прошла успешно. Ошибок нет");
        }
    }
}
