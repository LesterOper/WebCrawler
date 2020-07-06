package web;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WebCrawler {
    private static final int MAX_PAGE_NUMBER = 20;
    private Set<String> pageVisited = new HashSet<String>();
    private List<String> pageToVisit = new LinkedList<String>();

    private String nextUrl() {
        String url;
        do {
            url = pageToVisit.remove(0);
        } while (pageVisited.contains(url));
        pageVisited.add(url);
        return url;
    }

    public void search(String url, String[] words) {
        while(pageVisited.size() < MAX_PAGE_NUMBER) {
            String currentUrl;
            WebCrawlerSupport webCrawlerSupport = new WebCrawlerSupport();
            if(pageToVisit.isEmpty() && pageVisited.isEmpty()) {
                currentUrl = url;
                pageVisited.add(url);
            }
            else {
                currentUrl = nextUrl();
            }

            webCrawlerSupport.crawl(currentUrl);
            List<Integer> countWordsOnPage = webCrawlerSupport.searchWord(words);
            System.out.println("Found words on page " + currentUrl);
            System.out.println("Words:");
            for (String word: words) {
                System.out.print(word + "  ");
            }
            System.out.print("Common hits");
            System.out.println();
            System.out.println("Count: " + countWordsOnPage);
            saveToCSVFile(countWordsOnPage, currentUrl);
            pageToVisit.addAll(webCrawlerSupport.getLinks());
        }
        saveTop10Pages();
        System.out.println("\n Done! Visited " + pageVisited.size() + " web pages");
    }

    private void saveToCSVFile(List<Integer> countWordsOnPage, String url) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("data.csv", true));
            String[] strCountWords = new String[countWordsOnPage.size()+1];
            strCountWords[0] = url;
            int i=1;
            for (Integer integer : countWordsOnPage) {
                strCountWords[i] = integer.toString();
                i++;
            }
            writer.writeNext(strCountWords);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTop10Pages() {
        try {
            CsvToBean<ContainerForPages> csv = new CsvToBean<ContainerForPages>();
            String fileName = "data.csv";
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            csv.setCsvReader(csvReader);
            csv.setMappingStrategy(setColumnMapping());
            List<ContainerForPages> list = csv.parse();
            ContainerForPages[] container = new ContainerForPages[list.size()];
            int i=0;
            for (Object object: list) {
                container[i] = (ContainerForPages) object;
                i++;
            }
            sortPagesByTotal(container);
            saveToFile(container);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(ContainerForPages[] container) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("top10.csv", false));
            int i=0;
            System.out.println("Top 10 pages by total");
            for (ContainerForPages temp: container) {
                String[] str = temp.toString().split(" ");
                writer.writeNext(str);
                System.out.println(temp.toString());
                i++;
                if(i==10) break;
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ColumnPositionMappingStrategy<ContainerForPages> setColumnMapping() {
        ColumnPositionMappingStrategy<ContainerForPages> mapping = new ColumnPositionMappingStrategy<ContainerForPages>();
        mapping.setType(ContainerForPages.class);
        String[] columns = {
                "url", "detectedWordFirst", "detectedWordSecond",
                "detectedWordThird", "detectedWordFourth",
                "detectedWordFifth", "detectedWordsTotal"
        };
        mapping.setColumnMapping(columns);
        return mapping;
    }

    private void sortPagesByTotal(ContainerForPages[] container) {
        for(int i=0; i<container.length; i++) {
            ContainerForPages max = container[i];
            int maxId = i;
            for(int j=i+1; j<container.length; j++) {
                if(container[j].getDetectedWordsTotal() > max.getDetectedWordsTotal()) {
                    max = container[j];
                    maxId = j;
                }
            }
            ContainerForPages temp = container[i];
            container[i] = max;
            container[maxId] = temp;
        }
    }

    public Set<String> getPageVisited() {
        return pageVisited;
    }

    public List<String> getPageToVisit() {
        return pageToVisit;
    }
}
