public class YearlyReportLine {
    int month;
    //тут оставил обертку, потомучто в Main методе идет сравнение значений месячного и годового
    // отчетов через метод .equals()
    Integer amount;
    Boolean isExpense;

    public YearlyReportLine(int month, Integer amount, Boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
