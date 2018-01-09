
package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 10.05.2017.
 */



public class MoikrugStrategy implements Strategy {
    //private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java&page=2";
    private static final String URL_FORMAT= "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    //private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data2.html";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> Vacancies = new ArrayList<>();
        int pageNum = 0;
        Document doc = null;
        while(true)
        {
            try {
                doc = getDocument(searchString, pageNum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements vacancies = doc.getElementsByClass("job");
            if (vacancies.size()==0) break;
            for (Element element: vacancies)
            {
                if (element != null)
                {
                    Vacancy vac = new Vacancy();
                    vac.setTitle(element.getElementsByAttributeValue("class", "title").text());
                    vac.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                    vac.setSiteName(URL_FORMAT);
                    vac.setUrl("https://moikrug.ru" + element.select("a[class=job_icon]").attr("href"));
                    String salary = element.getElementsByAttributeValue("class", "salary").text();
                    String city = element.getElementsByAttributeValue("class", "location").text();
                    vac.setSalary(salary.length()==0 ? "" : salary);
                    vac.setCity(city.length()==0 ? "" : city);
                    Vacancies.add(vac);
                }
            }
            pageNum++;
        }
        return Vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        //String url = String.format("%s?q==%s&page=%s",URL_FORMAT, searchString, page);
        String url = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                .timeout(5000)
                .referrer("http://google.ru")
                .get();
    }
}

/*
package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
   // private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
   private static final String URL_FORMAT= "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    private static String userAgent = "Chrome/62.0.3202.62";
    private static String referrer = "none";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancyList =  new ArrayList<>();

        String vacancyQuery = "[class='job  ']";
        String vacancyCity = "[class='location']";
        String vacancyCompanyName = "[class='company_name']";
        String vacancyTitle = "[class='title']";
        String vacancySalary = "[class='salary']";
        int i = 0;

        try {
            while(true) {
                Document doc = getDocument(searchString, i);
                Elements vacancyElements = doc.select( vacancyQuery );
                if(!vacancyElements.isEmpty()) {
                    for(Element x: vacancyElements) {
                        Vacancy foundVacancy = new Vacancy();

                        foundVacancy.setCity(x.select(vacancyCity).text());
                        foundVacancy.setCompanyName(x.select(vacancyCompanyName).text());
                        foundVacancy.setSiteName("https://moikrug.ru");
                        foundVacancy.setTitle(x.select(vacancyTitle).text());
                        foundVacancy.setUrl("https://moikrug.ru/vacancies/" + x.select(vacancyQuery).attr("href").substring(4));
                        foundVacancy.setSalary(x.select(vacancySalary).isEmpty()? "" : x.select(vacancySalary).text());

                        vacancyList.add(foundVacancy);
                    }
                }
                else{break;}
                i++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }

        return vacancyList;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                .timeout(5000)
                .referrer("http://google.ru")
                .get();
    }
}
*/
