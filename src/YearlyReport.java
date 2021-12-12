import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YearlyReportLines> yearlyReport;

    public YearlyReport() {
        yearlyReport = new ArrayList<>();
    }
    public void addElements(Integer month, Integer amount, Boolean is_expense){
        yearlyReport.add(new YearlyReportLines(month,amount,is_expense));
    }

    public void yearlyMonthProfit(){
        Integer jan=0;
        Integer feb=0;
        Integer mar=0;
        for (YearlyReportLines y: yearlyReport
        ) {
            switch (y.month){
                case 1:
                    if(y.is_expense){
                        jan-=y.amount;
                    }else{
                        jan+=y.amount;
                    }
                    break;
                case 2:
                    if(y.is_expense){
                        feb-=y.amount;
                    }else{
                        feb+=y.amount;
                    }
                    break;
                case 3:
                    if(y.is_expense){
                        mar-=y.amount;
                    }else{
                        mar+=y.amount;
                    }
                    break;

            }
        }
        System.out.println("Прибыль за январь:"+jan);
        System.out.println("Прибыль за февраль:"+feb);
        System.out.println("Прибыль за март:"+mar);

    }

    public void yearlyMidProfit(){
       Integer yearProfit=0;
       Integer yearExpense=0;
       Integer counter=0;
       for (YearlyReportLines y:
                         yearlyReport) {
       counter =y.month;
       if(y.is_expense){
       yearExpense+=y.amount;
       }else{
       yearProfit+=y.amount;
       }
       }
       System.out.println("Средний расход в этом году:"+yearExpense/counter);
       System.out.println("Средний доход в этом году:"+yearProfit/counter);

    }
}
