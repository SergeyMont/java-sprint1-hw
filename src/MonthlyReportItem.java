class MonthlyReportItem {
    String itemName;
    boolean isExpense;
    Integer quantity;
    Integer sumOfOne;

    public MonthlyReportItem(String itemName, boolean isExpense, Integer quantity,
                             Integer sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
