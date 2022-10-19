package com.hfut.beike.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;


/**
 * @author Chenzh
 */
@Component
public class HouseComponent implements PageProcessor {

    private Site site = Site.me()
            .setCharset("utf8")
            .setTimeOut(10 * 1000)
            .setRetrySleepTime(3 * 1000)
            .setRetryTimes(3);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        String name = html.css("div.resblock-name a", "text").toString();
        String state = html.css("div.resblock-name span.resblock-type", "text").toString();
        String model = html.css("div.resblock-name span", "text").nodes().get(1).toString();
        String address = html.css("div.resblock-desc-wrapper a.resblock-location", "text").toString();
        String type = Jsoup.parse(html.css("a.resblock-room").regex(".*室").toString()).text();
        String area = html.css("a.resblock-room span.area", "text").toString();
        String price = html.css("div.main-price span.number", "text").toString();
        Document image = Jsoup.parse(html.css("a.resblock-img-wrapper img.lj-lazy").toString());
        String result = image.getElementsByAttribute("src").attr("src");
        page.putField("name", name);
        page.putField("state", state);
        page.putField("model", model);
        page.putField("address", address);
        page.putField("type", type);
        page.putField("area", area);
        page.putField("price", price);
        page.putField("image", image);
        page.putField("result", result);

    }

    @Override
    public Site getSite() {
        return site;
    }


}
