public class YearlyReportLine {
    int month;
    int amount;
    boolean isExpense;

    public YearlyReportLine(int month, Integer amount, Boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
