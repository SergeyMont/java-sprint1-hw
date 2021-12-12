import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String path1="resources/m.202101.csv";
        String path2="resources/m.202102.csv";
        String path3="resources/m.202103.csv";
        String pathY="resources/y.2021.csv";
        ArrayList<String>filePath=new ArrayList<>();
        filePath.add(path1);
        filePath.add(path2);
        filePath.add(path3);
        HashMap<Integer,MonthlyReport>monthlyBase=new HashMap<>();
        YearlyReport yearlyReport=new YearlyReport();
        // Поехали!
        Scanner scanner = new Scanner(System.in);
        FileReader a=new FileReader();
        Menu menu=new Menu();

        while (true) {
            menu.printMenu();
            int command = scanner.nextInt();
            switch (command){
                case 1:
                    for (int i = 0; i < filePath.size(); i++) {
                        int k=i+1;
                        String report=a.readFile(filePath.get(i));
                        MonthlyReport month=new MonthlyReport();
                        String [] lines=report.split(System.lineSeparator());


                        for (int j=1;j< lines.length;j++){
                            String [] values1=lines[j].split(",");

                            month.addElements(values1[0],Boolean.valueOf(values1[1]), Integer.valueOf(values1[2]), Integer.valueOf(values1[3]));

                        }
                        monthlyBase.put(i,month);
                        System.out.println("Отчет #"+k+" успешно добавлен в базу");
                    }
                    break;
                case 2:
                    String reportY=a.readFile(pathY);

                    String [] lines=reportY.split(System.lineSeparator());
                    for (int j=1;j< lines.length;j++){
                        String [] values1=lines[j].split(",");
                        yearlyReport.addElements(Integer.valueOf(values1[0]),Integer.valueOf(values1[1]),Boolean.valueOf(values1[2]));
                    }
                    System.out.println("Годовой отчет успешно добавлен");
                    break;
                case 3:
                    boolean janE=false;
                    boolean janP=false;
                    boolean febE=false;
                    boolean febP=false;
                    boolean marE=false;
                    boolean marP=false;

                    String nameLoose="";
                    for (YearlyReportLines y:
                         yearlyReport.yearlyReport) {

                        switch (y.month){
                            case 1:
                                if(y.is_expense&&y.amount.equals(monthlyBase.get(0).monthExpense())){
                                    janE=true;
                                }else if (!y.is_expense&&y.amount.equals(monthlyBase.get(0).monthProfit())){
                                    janP=true;
                                }else{
                                    nameLoose="Январь";
                                }
                            break;
                            case 2:
                                if(y.is_expense&&y.amount.equals(monthlyBase.get(1).monthExpense())){
                                    febE=true;
                                }else if (!y.is_expense&&y.amount.equals(monthlyBase.get(1).monthProfit())){
                                    febP=true;
                                }else{
                                    nameLoose="Февраль";
                                }

                                break;
                            case 3:
                                if(y.is_expense&&y.amount.equals(monthlyBase.get(2).monthExpense())){
                                    marE=true;
                                }else if (!y.is_expense&&y.amount.equals(monthlyBase.get(2).monthProfit())){
                                    marP=true;
                                }else{
                                    nameLoose="Март";
                                }
                                break;
                        }
                    }
                    if(janE&&janP&&febE&&febP&&marE&&marP){
                        System.out.println("Сверка отчетов прошла успешно. Ошибок нет");
                    }else {
                        System.out.println("Обнаружена ошибка в месяце:"+nameLoose);
                    }
                    break;
                case 4:
                for (int i=0;i< monthlyBase.size();i++){
                    MonthNames name=new MonthNames();
                    System.out.println("Информация о месяце : "+name.monthNames.get(i));
                    MonthlyReport m= monthlyBase.get(i);
                    m.findMaxExpenseAndProfit();
                }
                    break;
                case 5:
                    String []yearPath=pathY.split("\\W+");
                    System.out.println("Годовой отчет за "+yearPath[2]+" год");
                    yearlyReport.yearlyMonthProfit();
                    yearlyReport.yearlyMidProfit();

                    break;
                case 0:
                    System.out.println("Выход");
                    return;
                default:
                    System.out.println("Извините, такой команды пока нет.Не пытайтесь сломать свой компьютер");
                    break;
            }


        }

    }

}

