package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
     private final String filePath = "E:\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task28\\task2810\\view\\vacancies.html";
   // private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancyList) {
        Document doc = null;
        try {
            doc = getDocument();

            Element template = doc.getElementsByClass("template").first();
            Element cloneTemplate = template.clone();
            cloneTemplate.removeAttr("style");
            cloneTemplate.removeClass("template");

            doc.select("tr[class=vacancy]").remove();

            for (Vacancy vacancy : vacancyList) {
                Element templateElement = cloneTemplate.clone();
                templateElement.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
                templateElement.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
                templateElement.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
                templateElement.getElementsByAttribute("href").get(0).attr("href", vacancy.getUrl()).text(vacancy.getTitle());
                    /*    <td class="title"><a href="url"></a></td>
                    <td class="city"></td>
                    <td class="companyName"></td>
                    <td class="salary"></td>*/
                template.before(templateElement.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
           return ("Some exception occurred");
        }
        return doc.outerHtml();
    }

    private void updateFile(String string) {

        try (BufferedWriter fWriter = new BufferedWriter(new FileWriter(filePath))) {
            fWriter.write(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
