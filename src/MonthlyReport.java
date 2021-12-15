import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<MonthlyReportItem> monthlyReport;

    public MonthlyReport() {
        monthlyReport = new ArrayList<>();
    }
    public void addElement(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        monthlyReport.add(new MonthlyReportItem(itemName, isExpense, quantity, sumOfOne)); }

    public void printMaxExpenseAndProfit() {
        int maxProfit = 0;
        String itemProfit = "";
        int maxExpense = 0;
        String itemExpense = "";
        for (MonthlyReportItem v: monthlyReport) {
            if (!v.isExpense) {
                if(v.quantity*v.sumOfOne >maxProfit) {
                    maxProfit = v.quantity*v.sumOfOne;
                    itemProfit = v.itemName;
                }
            } else {
                if (v.quantity*v.sumOfOne >maxExpense) {
                    maxExpense = v.quantity*v.sumOfOne;
                    itemExpense = v.itemName;
                }
            }

        }
        System.out.println("Максимальный доход в этом месяце: "+ maxProfit);
        System.out.println("Категория максимальной прибыли: "+ itemProfit);
        System.out.println("Самая большая трата в этом месяце: "+ maxExpense);
        System.out.println("Категория максимальной траты: "+ itemExpense);
    }

    public int findMonthProfit() {
        int totalProfit = 0;

        for (MonthlyReportItem v:
                monthlyReport) {
            if (!v.isExpense) {
                totalProfit += v.quantity* v.sumOfOne;
            }
        }
        return totalProfit;
    }

    public int findMonthExpense() {
        int totalExpense = 0;
        for (MonthlyReportItem v: monthlyReport) {
            if (v.isExpense) {
                totalExpense += v.quantity*v.sumOfOne;
            }
        }
        return totalExpense;
    }
}
