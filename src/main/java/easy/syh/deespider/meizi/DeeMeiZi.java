package easy.syh.deespider.meizi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeeMeiZi {

  private ChromeDriver webDriver;
  private File dir;
  private String fileName;
  private BufferedWriter bw;

  public void xxImg() throws Exception {
    dir = new File("F:/Dee_Riven");
    if (!dir.exists()) {
      dir.mkdirs();
    }
    fileName = "/riven_img.txt";
    //http://jandan.net/ooxx
    System.getProperties().setProperty("webdriver.chrome.driver", "D:/_2018/_git/DeeRivenFiora/src/main/res/chromedriver.exe");
    webDriver = new ChromeDriver();
    bw = new BufferedWriter(new FileWriter(dir+fileName));
    LinkFromFile linkFromFile = new LinkFromFile();
    List<String> list = linkFromFile.operateF();
    for (int i = 0; i < list.size(); i++) {
      catSpider(list.get(i));
    }
    bw.close();
    webDriver.close();
  }

  private void catSpider(String url) throws IOException {
    Document doc = getDocument(url);
    Elements cpPages = doc.getElementsByClass("row");
    for (Element cpPage : cpPages) {
      Elements aPages = cpPage.getElementsByTag("a");
      for (Element aPage : aPages) {
        Attributes attributes = aPage.attributes();
        String href = attributes.get("href");
        if (href.contains("large")){
          System.err.println(href);
          bw.write("http:"+href);
          bw.newLine();
        }
      }
    }

  }

  private Document getDocument(String url) {
    System.out.println("url: "+url);
    webDriver.get(url);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {

      e.printStackTrace();
    }
    WebElement webElement = webDriver.findElement(By.xpath("/html"));

    String attribute = webElement.getAttribute("outerHTML");

    return Jsoup.parse(attribute);
  }
}
