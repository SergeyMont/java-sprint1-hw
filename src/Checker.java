import java.util.HashMap;

public class Checker {
    boolean check[];
    MonthOfYear name;
    String nameLoose;

    public Checker(YearlyReport yearlyReport){
        check = new boolean[yearlyReport.yearlyReport.size()];
        name=new MonthOfYear();
        nameLoose = "";
    }
    void checkMonthYearReports(HashMap<Integer, MonthlyReport> monthlyBase,
                               YearlyReport yearlyReport) {
        for (YearlyReportLine y : yearlyReport.yearlyReport) {
            for (int i = 0; i < (yearlyReport.yearlyReport.size() / 2); i++) {
                if (y.month == (i + 1)) {
                    if (y.isExpense && y.amount.equals(monthlyBase.get(i).findMonthExpense())) {
                        check[i] = true;
                    } else if (!y.isExpense && y.amount.equals(monthlyBase.get(i).findMonthProfit())) {
                        check[i + 1] = true;
                    } else {
                        nameLoose = name.monthNames[i];
                    }
                }
            }
        }
        //Не нашел способ вытащить из массива все boolean и одновременно сравнить между собой. Оставил как есть сейчас
        //Цикл вытаскивает их по одной, а нужны все одновременно
        if (check[0]&&check[1]&&check[2]&&check[3]&&check[4]&&check[5]&&check[6]) {
            System.out.println("Сверка отчетов прошла успешно. Ошибок нет");
        } else {
            System.out.println("Обнаружена ошибка в месяце:" + nameLoose);
        }
    }
}
