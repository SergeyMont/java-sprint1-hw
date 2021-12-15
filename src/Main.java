import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String path1 = "resources/m.202101.csv";
        String path2 = "resources/m.202102.csv";
        String path3 = "resources/m.202103.csv";
        String pathY = "resources/y.2021.csv";
        ArrayList<String> filePath = new ArrayList<>();
        filePath.add(path1);
        filePath.add(path2);
        filePath.add(path3);
        HashMap<Integer, MonthlyReport> monthlyBase = new HashMap<>();
        YearlyReport yearlyReport = new YearlyReport();
        // Поехали!
        Scanner scanner = new Scanner(System.in);
        FileReader reader = new FileReader();


        while (true) {
            Menu.printMenu();
            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    addMonthReportInBase(filePath, monthlyBase, reader);
                    break;
                case 2:
                    addYearReportInBase(pathY, yearlyReport, reader);
                    break;
                case 3:
                    if (monthlyBase.isEmpty() || yearlyReport.yearlyReport.isEmpty()) {
                        System.out.println("Годовой или месячный отчет не загружены в базу. Попробуйте ещё раз");
                    } else {
                        Checker checker=new Checker(yearlyReport);
                        checker.checkMonthYearReports(monthlyBase,yearlyReport);
                    }
                    break;
                case 4:
                    if (monthlyBase.isEmpty() || yearlyReport.yearlyReport.isEmpty()) {
                        System.out.println("Годовой или месячный отчет не загружены в базу. Попробуйте ещё раз");
                    } else {
                        for (int i = 0; i < monthlyBase.size(); i++) {
                            MonthOfYear name = new MonthOfYear();
                            System.out.println("Информация о месяце : " + name.monthNames[i]);
                            MonthlyReport m = monthlyBase.get(i);
                            m.printMaxExpenseAndProfit();
                        }
                    }
                    break;
                case 5:
                    if (monthlyBase.isEmpty() || yearlyReport.yearlyReport.isEmpty()) {
                        System.out.println("Годовой или месячный отчет не загружены в базу. Попробуйте ещё раз");
                    } else {
                        String[] yearPath = pathY.split("\\W+");
                        System.out.println("Годовой отчет за " + yearPath[2] + " год");
                        yearlyReport.printYearlyMonthProfit();
                        yearlyReport.printYearlyMidProfit();
                    }
                    break;
                case 0:
                    System.out.println("Выход");
                    return;
                default:
                    System.out.println("Извините, такой команды пока нет.Не пытайтесь сломать " +
                            "свой компьютер");
                    break;
            }
        }
     }

    private static void addYearReportInBase(String pathY, YearlyReport yearlyReport, FileReader reader) {
        String reportY = reader.readFile(pathY);
        String[] lines = reportY.split(System.lineSeparator());
        for (int j = 1; j < lines.length; j++) {
            String[] values1 = lines[j].split(",");
            yearlyReport.addElements(Integer.valueOf(values1[0]),
                    Integer.valueOf(values1[1]),
                    Boolean.valueOf(values1[2]));
        }
        System.out.println("Годовой отчет успешно добавлен");
    }

    private static void addMonthReportInBase(ArrayList<String> filePath,
                                  HashMap<Integer, MonthlyReport> monthlyBase, FileReader reader) {
        for (int i = 0; i < filePath.size(); i++) {
            int k = i + 1;
            String report = reader.readFile(filePath.get(i));
            MonthlyReport month = new MonthlyReport();
            String[] lines = report.split(System.lineSeparator());

            for (int j = 1; j < lines.length; j++) {
                String[] values1 = lines[j].split(",");

                month.addElement(values1[0], Boolean.valueOf(values1[1]),
                        Integer.valueOf(values1[2]), Integer.valueOf(values1[3]));

            }
            monthlyBase.put(i, month);
            System.out.println("Отчет #" + k + " успешно добавлен в базу");
        }
    }
}

