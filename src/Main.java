import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Report report1 = new Report(1l, "no_punkz", 1, LocalDate.of(2021, 9, 25), "Из-за внешних факторов все никак не могу сделать " +
                "задачу" +
                " со стримами, не хватило времени");
        Report report2 = new Report(2l, "no_punkz", 5, LocalDate.of(2021, 9, 27), "Продолжаю мучать предпоследнюю задачу (теперь и " +
                "Толяна), собес на котором узнал много нового");
        Report report3 = new Report(3l, "no_punkz", 2, LocalDate.of(2021, 9, 26), "Бился над компаратором, пытался накостылить через " +
                "видимую внешнюю переменную, начал читать о решениях из чата");
        Report report4 = new Report(4l, "no_punkz", 6, LocalDate.of(2021, 9, 23), "тестовый");
        Report report5 = new Report(5l, "no_punkz", 3, LocalDate.of(2021, 9, 24), "тестовый2");
        Report report6 = new Report(6l, "no_punkz", 4, LocalDate.of(2021, 9, 19), "тестовый2");
        Report report7 = new Report(7l, "alekshas", 2, LocalDate.of(2021, 9, 25), "тестовый леха");
        Report report8 = new Report(8l, "alekshas", 1, LocalDate.of(2021, 9, 24), "тестовый лех2");
        List<Report> list = new ArrayList<>();
        list.add(report1);
        list.add(report2);
        list.add(report3);
        list.add(report4);
        list.add(report5);
        list.add(report6);
        list.add(report7);
        list.add(report8);

        System.out.println(Report.reportHistory(list, "no_punkz", 3));
    }
    public static class Report {
        Long id;
        String username;
        int hours;
        LocalDate date;
        String message;

        public Report(Long id, String username, int hours, LocalDate date, String message) {
            this.id = id;
            this.username = username;
            this.hours = hours;
            this.date = date;
            this.message = message;
        }

        public static String reportHistory(List<Report> reports, String username, int count) {
            StringBuilder sb = new StringBuilder();

            reports.stream()
                    .filter(report -> report.username.equals(username)) //фильтруем по имени
                    .sorted(Comparator.comparing(Report::getDate).reversed()) //сортируем по дате в обратном порядке
                    .limit(count) //ограничиваем количество результатов
                    .forEach(report -> {
                        sb.insert(0, "-----------------\n")
                                .insert(0, report.message + "\n")
                                .insert(0, report.hours + "\n")
                                .insert(0, report.getDate() + "\n")
                                .insert(0, report.username + "\n");
                    });

            return sb.toString();
        }

        public LocalDate getDate() {
            return date;
        }
    }
}