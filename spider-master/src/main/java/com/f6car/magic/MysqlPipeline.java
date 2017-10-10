package com.f6car.magic;

import com.jfinal.plugin.activerecord.Db;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * Created by qixiaobo on 2017/7/14.
 */
public class MysqlPipeline implements Pipeline {
    /*@Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, List<Oil>> oils = resultItems.get("oils");
        if (oils != null && !oils.isEmpty()) {
            for (Map.Entry<String, List<Oil>> entry : oils.entrySet()) {
                Db.batchSave(entry.getValue(),entry.getValue().size());
            }
        }
    }*/
    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, List<AccInfo>> accs = resultItems.get("accs");
        Map<String, List<CarModel>> models = resultItems.get("models");
        Map<String, List<EngineDetail>> engines = resultItems.get("engines");
        if (accs != null && !accs.isEmpty()) {
            for (Map.Entry<String, List<AccInfo>> entry : accs.entrySet()) {
                Db.batchSave(entry.getValue(),entry.getValue().size());
            }
        }
        if (engines != null && !engines.isEmpty()) {
            for (Map.Entry<String, List<EngineDetail>> entry : engines.entrySet()) {
                Db.batchSave(entry.getValue(),entry.getValue().size());
            }
        }
        if (models != null && !models.isEmpty()) {
            for (Map.Entry<String, List<CarModel>> entry : models.entrySet()) {
                Db.batchSave(entry.getValue(),entry.getValue().size());
            }
        }
    }
}



