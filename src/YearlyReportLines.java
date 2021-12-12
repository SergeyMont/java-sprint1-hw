public class YearlyReportLines {
    Integer month;
    Integer amount;
    Boolean is_expense;

    public YearlyReportLines(Integer month, Integer amount, Boolean is_expense) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }
}
