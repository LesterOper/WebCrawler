import web.WebCrawler;

public class Test {

    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        /*String[] wordsForSearch = {"Ракета", "Промышленность", "Космическая", "Отрасль", "Достижения"};
        crawler.search("https://vys-tech.ru/2019/02/02/raketno-kosmicheskaya-otrasl/", wordsForSearch);*/
        String[] wordsForSearch = {"Сортировка", "Java", "Сложность", "Алгоритм", "Реализация"};
        crawler.search("https://java-master.com/%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D1%8B-%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B8-%D0%BC%D0%B0%D1%81%D1%81%D0%B8%D0%B2%D0%B0/", wordsForSearch);
    }
}
