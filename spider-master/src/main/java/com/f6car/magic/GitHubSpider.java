package com.f6car.magic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import org.apache.http.HttpHost;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectable;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qixiaobo on 2017/7/13.
 */
public class GitHubSpider implements PageProcessor {
    private Site site = Site.me().setTimeOut(30000).setRetryTimes(3).setSleepTime(1000).setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1" );
        /*  .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Connection", "keep-alive")*/
    private static final String brandUrl = "http://technology.vin114.net/ngk/level/NGK/getAllBrands";
    private static final String helpUrl = "http://technology.vin114.net/ngk/level/NGK/";
    private static final String manuUrl = "http://technology.vin114.net/ngk/level/NGK/getModels";
    private static final String modelUrl = "http://technology.vin114.net/ngk/level/NGK/getDisplacements";
    private static final String saleUrl = "http://technology.vin114.net/ngk/level/NGK/getProducedYears";
    private static final String yearUrl = "http://technology.vin114.net/ngk/level/NGK/getProductByVehiclePara";
    private static final String dataUrl = "http://oilchooser.fuchs.com.cn:90/fuchs/level/fuchs/getData?compressId=#COMPRESSID";
    private static final String dataDetailAddressUrl = "http://oilchooser.fuchs.com.cn:90/fuchs/level/fuchs/gotoDetailPage?productName=#PRODUECTNAME";
    private static final String dataDetailUrl = "http://oilchooser.fuchs.com.cn:90/fuchs/#PRODUCTADDRESS";
    private static final JsonPathSelector supplierUrlSelector = new JsonPathSelector("$.values");
    private static final JsonPathSelector subNoUrlSelector = new JsonPathSelector("$.models[*].value");
    private static final JsonPathSelector carUrlSelector = new JsonPathSelector("$.vehicles[*].value");
    private static final JsonPathSelector driveFormUrlSelector = new JsonPathSelector("$.variants[*].value");
    private static final JsonPathSelector productUrlSelector = new JsonPathSelector("$.productGroups[*].value");
    private static final JsonPathSelector productResultSelector = new JsonPathSelector("$.productResults[*].productCode");
    private static final JsonPathSelector manufacturers = new JsonPathSelector("$.manufacturers[*].text");
    private static final JsonPathSelector models = new JsonPathSelector("$.models[*].text");
    private static final JsonPathSelector carSelector = new JsonPathSelector("$.vehicles[*].text");
    private static final JsonPathSelector vehicles = new JsonPathSelector("$.variants[*].text");
    private static final JsonPathSelector productGroups = new JsonPathSelector("$.productGroups[*].text");


    /*private static final JsonPathSelector brandIdSelector = new JsonPathSelector("$.value[*].brandId");
    private static final JsonPathSelector manuIdSelector = new JsonPathSelector("$.value[*].manufacturersId");

    private static final JsonPathSelector modelSelector = new JsonPathSelector("$.value[*].modelsId");

    private static final JsonPathSelector saleIdSelector = new JsonPathSelector("$.value[*].salesName");
    private static final JsonPathSelector yearIdSelector = new JsonPathSelector("$.value[*].compressId");*/

    @Override
    public void process(Page page) {
       final String url = page.getUrl().get();
        System.err.println(url);
        List<String> urls = new ArrayList<>();




       if (url.contains("getAllBrands")) {
           JSONObject jsStr = JSONObject.parseObject(page.getRawText());
           JSONArray dataArr = new  JSONArray(jsStr.getJSONArray("value"));
           /*for (int i =0;i<dataArr.size();i++){*/
           for (int i =0;i<1;i++){
               JSONObject brandObj = (JSONObject)dataArr.get(i);
               urls.add(url.replace("getAllBrands","getModels")+"?&brandName="+brandObj.get("brand").toString()+"&manufacturersName="+brandObj.get("manufacturers").toString());
           }
           page.addTargetRequests(urls);
           page.setSkip(true);
       }
        else if (url.contains("getModels")) {
           JSONObject jsStr = JSONObject.parseObject(page.getRawText());
           JSONArray dataArr = new  JSONArray(jsStr.getJSONArray("value"));
           /*for (int i =0;i<dataArr.size();i++){*/
               for (int i =0;i<1;i++){
               JSONObject modelObj = (JSONObject)dataArr.get(i);
               urls.add(url.replace("getModels","getDisplacements")+"&modelName="+modelObj.get("modelName").toString());
           }
           page.addTargetRequests(urls);
           page.setSkip(true);
        }
        else if (url.contains("getDisplacements")) {
           JSONObject jsStr = JSONObject.parseObject(page.getRawText());
           JSONArray dataArr = new  JSONArray(jsStr.getJSONArray("value"));
           /*for (int i =0;i<dataArr.size();i++){*/
           for (int i =0;i<1;i++){
               JSONObject modelObj = (JSONObject)dataArr.get(i);
               urls.add(url.replace("getDisplacements","getProducedYears")+
                       "&displacement="+modelObj.get("displacement").toString()+
                       "&inductionCode="+modelObj.get("inductionCode").toString()+
                       "&inductionValue="+modelObj.get("inductionValue").toString());
           }
           page.addTargetRequests(urls);
           page.setSkip(true);
        }
        else if (url.contains("getProducedYears")) {
           JSONObject jsStr = JSONObject.parseObject(page.getRawText());
           JSONArray dataArr = new  JSONArray(jsStr.getJSONArray("value"));
           /*for (int i =0;i<dataArr.size();i++){*/
           for (int i =0;i<1;i++){
               JSONObject modelObj = (JSONObject)dataArr.get(i);
               urls.add(url.replace("getProducedYears","getProductByVehiclePara")+
                       "&enginemodel="+modelObj.get("enginemodel").toString()+
                       "&producedYear="+modelObj.get("producedYear").toString()+
                       "&idlingYear="+modelObj.get("idlingYear").toString()+
                       "&engineYear="+modelObj.get("combineName").toString()
               );
           }
           page.addTargetRequests(urls);
           page.setSkip(true);
       }
       else if (url.contains("getProductByVehiclePara")){
           Map<String, List<EngineDetail>> map = Maps.newHashMapWithExpectedSize(100);
           StringBuilder stringBuilder = null;
           EngineDetail detail = new EngineDetail();
           List<EngineDetail> engineList = Lists.newArrayListWithExpectedSize(1);
           detail.setBrandName(page.getHtml().xpath("//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[1]/td[2]/text()").toString());
           detail.setCompany(page.getHtml().xpath("//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[2]/td[2]/text()").toString());
           detail.setTypeName(page.getHtml().xpath("//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[3]/td[2]/text()").toString());
           detail.setExhaustVolume(page.getHtml().xpath("//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[4]/td[2]/text()").toString());
           detail.setEngineType(page.getHtml().xpath("//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[5]/td[2]/text()").toString());
           detail.setEngineYear( url.split("engineYear=")[1].toString());
           detail.setProductsType(page.getHtml().xpath("//*[@id=\"NGKProducts_List\"]/div/div/div[1]/span/text()").toString());
           detail.setProductsNo(page.getHtml().xpath("//*[@id=\"NGKProducts_List\"]/div/div/div[2]/span/text()").toString());
           detail.setProductsYear(page.getHtml().xpath("//*[@id=\"NGKProducts_List\"]/div/div/div[3]/span/text()").toString());
           detail.setRemark(page.getHtml().xpath("//*[@id=\"NGKProducts_List\"]/div/div/div[4]/span/text()").toString());
            detail.set("brandName", detail.getBrandName()).set("company", detail.getCompany()).set("typeName", detail.getTypeName()).set("exhaustVolume", detail.getExhaustVolume())
                   .set("engineType", detail.getEngineType()).set("engineYear", detail.getEngineYear()).set("productsType", detail.getProductsType())
                   .set("productsNo", detail.getProductsNo()).set("productsYear",detail.getProductsYear()).set("remark", detail.getRemark());
           engineList.add(detail);
           map.put(stringBuilder.toString(), engineList);
           page.putField("engines", map);
           /*String engineYear = url.split("engineYear=")[1].toString();
         *//*   String carbrand = nodes.get(0).$("td").toString()*//*
           String brandName = page.getHtml().xpath("/*//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[1]/td[2]/text()").toString();
           String company = page.getHtml().xpath("/*//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[2]/td[2]/text()").toString();
           String typeName = page.getHtml().xpath("/*//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[3]/td[2]/text()").toString();
           String exhaustVolume = page.getHtml().xpath("/*//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[4]/td[2]/text()").toString();
           String engineType = page.getHtml().xpath("/*//*[@id=\"NGKVehicleInfoArea\"]/div/table/tbody/tr[5]/td[2]/text()").toString();
           String  productsType = page.getHtml().xpath("/*//*[@id=\"NGKProducts_List\"]/div/div/div[1]/span").toString();
           String  productsNo = page.getHtml().xpath("/*//*[@id=\"NGKProducts_List\"]/div/div/div[2]/span").toString();
           String  productsYear = page.getHtml().xpath("/*//*[@id=\"NGKProducts_List\"]/div/div/div[3]/span").toString();
           String  remark = page.getHtml().xpath("/*//*[@id=\"NGKProducts_List\"]/div/div/div[4]/span").toString();*/
        }
        else if (url.contains("vehicleType")) {
            page.addTargetRequests(Lists.transform(supplierUrlSelector.selectList(page.getRawText()), new Function<String, String>() {
                @Override
                public String apply(String value) {
                    return url+"&manufacturerId="+encodeParam(value);
                }
            }));
            page.setSkip(true);
        } /*else if (url.contains("getManufacturers")) {
            final String brandId = page.getUrl().regex(".*brandId=(.*)", 1).get();
            page.addTargetRequests(Lists.transform(manuIdSelector.selectList(page.getRawText()), new Function<String, String>() {
                @Override
                public String apply(String manuId) {
                    return modelUrl.replace("#MANUID", encodeParam(manuId)).replace("#BRANDID", brandId);
                }
            }));
            page.setSkip(true);

        } else if (url.contains("getModels")) {
            final String brandId = page.getUrl().regex(".*brandId=(.*)&manufacturersId.*", 1).get();
            final String manuId = page.getUrl().regex(".*manufacturersId=(.*)", 1).get();
            page.addTargetRequests(Lists.transform(modelSelector.selectList(page.getRawText()), new Function<String, String>() {
                @Override
                public String apply(String modelId) {
                    return saleUrl.replace("#MODELID", encodeParam(modelId)).replace("#MANUID", manuId).replace("#BRANDID", brandId);
                }
            }));
            page.setSkip(true);

        } else if (url.contains("getSalesNames")) {
            final String brandId = page.getUrl().regex(".*brandId=(.*)&manufacturersId.*", 1).get();
            final String manuId = page.getUrl().regex(".*manufacturersId=(.*)&modelId.*", 1).get();
            final String modelId = page.getUrl().regex(".*modelId=(.*)", 1).get();
            page.addTargetRequests(Lists.transform(saleIdSelector.selectList(page.getRawText()), new Function<String, String>() {
                @Override
                public String apply(String saleName) {
                    return yearUrl.replace("#SALEID", encodeParam(saleName)).replace("#MODELID", modelId).replace("#MANUID", manuId).replace("#BRANDID", brandId);
                }
            }));
            page.setSkip(true);



        } else if (url.contains("getYears")) {

            page.addTargetRequests(Lists.transform(yearIdSelector.selectList(page.getRawText()), new Function<String, String>() {
                @Override
                public String apply(String compressId) {
                    return dataUrl.replace("#COMPRESSID", encodeParam(compressId));
                }
            }));
            page.setSkip(true);

        } else if (url.contains("getData")) {
            List<Selectable> nodes = page.getHtml().$("#VehicleInfoModel_modelShowArea table").nodes();
            Map<String, List<Oil>> map = Maps.newHashMapWithExpectedSize(nodes.size() / 2);
            StringBuilder stringBuilder = null;
            for (int i = 0; i < nodes.size(); i++) {
                if (i % 2 == 0) {
                    stringBuilder = new StringBuilder();
                    Joiner.on(";").appendTo(stringBuilder, nodes.get(i).$("td", "text").all());
                } else {
                    Selectable node = nodes.get(i);
                    Selectable titleNode = node.$("td[rowSpan]");
                    Selectable oilsNode = node.$("td[lang]");
                    List<String> rowSpanList = titleNode.$("td", "rowSpan").all();
                    List<String> titleNameList = titleNode.$("td", "text").all();
                    List<String> oilNameList = oilsNode.$("span", "text").all();
                    List<String> oilCapList = oilsNode.$("td", "text").all();
                    List<Oil> oilList = Lists.newArrayListWithExpectedSize(oilNameList.size());
                    int current = 0;
                    for (int j = 0; j < titleNameList.size(); j++) {
                        String title = titleNameList.get(j);
                        int row = Integer.parseInt(rowSpanList.get(j));
                        for (int k = 0; k < row; k++) {
                            Oil oil = new Oil();
                            oil.setCat(title);
                            oil.setCap(oilCapList.get(current));
                            oil.setName(oilNameList.get(current));
                            oil.set("cap", oil.getCap()).set("car", stringBuilder.toString()).set("cat", oil.getCat()).set("name", oil.getName());
                            oilList.add(oil);
                            current++;
                        }

                    }
                    map.put(stringBuilder.toString(), oilList);
                   *//* List<Selectable> productNames = node.$("span", "text").nodes();

                    page.addTargetRequests(Lists.transform(productNames, new Function<Selectable, String>() {
                        @Override
                        public String apply(Selectable selectable) {
                            return dataDetailAddressUrl.replace("#PRODUECTNAME", encodeParam(selectable.get()));
                        }
                    }));*//*
                }
            }
            page.putField("oils", map);
        } else if (url.contains("gotoDetailPage")) {
            page.putField("value", dataDetailUrl.replace("#PRODUCTADDRESS", encodeParam(page.getRawText())));
        }*/
    }

    private String encodeParam(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
    // httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1", 6379)));
         DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://localhost:3306/test", "root", "");
       ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
       activeRecordPlugin.addMapping("engine_detail", EngineDetail.class);
       activeRecordPlugin.setDialect(new MysqlDialect());
       activeRecordPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
        druidPlugin.start();
       activeRecordPlugin.start();
         Spider.create(new GitHubSpider())
                .addUrl(brandUrl)
                .setDownloader(httpClientDownloader)
//                .addPipeline(new JsonFilePipeline("/Users/qixiaobo/Downloads/spider"))
               .addPipeline(new MysqlPipeline())
   //            .setScheduler(new RedisScheduler("127.0.0.1"))
                //开启5个线程抓取
                .thread(10)
                //启动爬虫
                .run();
    }
}
