import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String pathY = "resources/y.2021.csv";
        ArrayList<String> filePath = new ArrayList<>();
        filePath.add("resources/m.202101.csv");
        filePath.add("resources/m.202102.csv");
        filePath.add("resources/m.202103.csv");
        HashMap<Integer, MonthlyReport> monthlyBase = new HashMap<>();
        YearlyReport yearlyReport = new YearlyReport();
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
                        System.out.println("Годовой или месячный отчет не загружены в базу. " +
                                "Попробуйте ещё раз");
                    } else {
                        Checker checker = new Checker();
                        checker.checkMonthYearReports(monthlyBase, yearlyReport);
                    }
                    break;
                case 4:
                    if (monthlyBase.isEmpty()) {
                        System.out.println("Месячный отчет не загружен в базу. " +
                                "Попробуйте ещё раз");
                    } else {
                        for (int i = 0; i < monthlyBase.size(); i++) {
                            System.out.println("Информация о месяце : " + MonthOfYear.monthNames[i]);
                            MonthlyReport m = monthlyBase.get(i);
                            m.printMaxExpenseAndProfit();
                        }
                    }
                    break;
                case 5:
                    if (yearlyReport.yearlyReport.isEmpty()) {
                        System.out.println("Годовой отчет не загружен в базу. " +
                                "Попробуйте ещё раз");
                    } else {
                        String[] yearPath = pathY.split("\\W+");
                        System.out.println("Годовой отчет за " + yearPath[2] + " год");
                        yearlyReport.printYearlyMonthProfit();
                        yearlyReport.printYearlyMidProfit(yearPath[2]);
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

    private static void addYearReportInBase(String pathY, YearlyReport yearlyReport,
                                            FileReader reader) {
        String reportY = reader.readFile(pathY);
        String[] lines = reportY.split(System.lineSeparator());
        // При использовании в коде разделителя "\n" на ОС Windows выпадает NumberFormatException
        // System.lineSeparator() возвращает разделитель в зависимости от файловой системы
        // (работает на всех ОС)
        // Использование данного метода согласовано с наставником Владимиром Ивановым
        // String[] lines = reportY.split("\n");
        for (int j = 1; j < lines.length; j++) {
            String[] values1 = lines[j].split(",");
            yearlyReport.addElements(Integer.valueOf(values1[0]),
                    Integer.valueOf(values1[1]),
                    Boolean.valueOf(values1[2]));
        }
        System.out.println("Годовой отчет успешно добавлен");
    }

    private static void addMonthReportInBase(ArrayList<String> filePath,
                                             HashMap<Integer, MonthlyReport> monthlyBase,
                                             FileReader reader) {
        for (int i = 0; i < filePath.size(); i++) {
            String report = reader.readFile(filePath.get(i));
            MonthlyReport month = new MonthlyReport();
            String[] lines = report.split(System.lineSeparator());

            for (int j = 1; j < lines.length; j++) {
                String[] values1 = lines[j].split(",");

                month.addElement(values1[0], Boolean.valueOf(values1[1]),
                        Integer.valueOf(values1[2]), Integer.valueOf(values1[3]));

            }
            monthlyBase.put(i, month);
            System.out.println("Отчет #" + (i + 1) + " успешно добавлен в базу");
        }
    }
}

