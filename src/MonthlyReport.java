import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<MonthlyReportItem> monthlyReport;

    public MonthlyReport() {
        monthlyReport = new ArrayList<>();
    }
    public void addElements(String itemName, boolean isExpense, int quantity, int sumOfOne){
        monthlyReport.add(new MonthlyReportItem(itemName,isExpense, quantity,sumOfOne));
    }

    public ArrayList<MonthlyReportItem> getMonthlyReport() {
        return monthlyReport;
    }

    public void findMaxExpenseAndProfit(){
        Integer maxProfit=0;
        String itemProfit="";
        Integer maxExpense=0;
        String itemExpense="";
        for (MonthlyReportItem v:
                monthlyReport) {
            if (!v.is_expense){
                if(v.quantity*v.sum_of_one>maxProfit){
                    maxProfit=v.quantity*v.sum_of_one;
                    itemProfit=v.item_name;
                }
            }else {
                if (v.quantity*v.sum_of_one>maxExpense){
                    maxExpense=v.quantity*v.sum_of_one;
                    itemExpense= v.item_name;
                }
            }

        }
        System.out.println("Максимальный доход в этом месяце: "+maxProfit);
        System.out.println("Категория максимальной прибыли: "+itemProfit);
        System.out.println("Самая большая трата в этом месяце: "+maxExpense);
        System.out.println("Категория максимальной траты: "+itemExpense);
    }

    public Integer monthProfit (){
        Integer totalProfit=0;

        for (MonthlyReportItem v:
                monthlyReport) {
            if (!v.is_expense){
                totalProfit+=v.quantity* v.sum_of_one;
            }
        }
        return totalProfit;
    }
    public Integer monthExpense(){
        Integer totalExpense=0;
        for (MonthlyReportItem v:
                monthlyReport) {
            if (v.is_expense){
                totalExpense+=v.quantity*v.sum_of_one;
            }
        }
        return totalExpense;
    }
}
