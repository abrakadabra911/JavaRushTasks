package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static String userAgent = "Chrome/62.0.3202.62 Safari/537.36";
    private static String referrer = "none";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancyList =  new ArrayList<>();

        String vacancyQuery = "[data-qa='vacancy-serp__vacancy']";
        String vacancyCity = "[data-qa='vacancy-serp__vacancy-address']";
        String vacancyCompanyName = "[data-qa='vacancy-serp__vacancy-employer']";
        String vacancyTitle = "[data-qa='vacancy-serp__vacancy-title']";
        String vacancySalary = "[data-qa='vacancy-serp__vacancy-compensation']";
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
                        foundVacancy.setSiteName("https://hh.ru");
                        foundVacancy.setTitle(x.select(vacancyTitle).text());
                        foundVacancy.setUrl(x.select(vacancyTitle).attr("href"));
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
        return Jsoup.connect(url).userAgent(userAgent).referrer(referrer).get();
    }
}
