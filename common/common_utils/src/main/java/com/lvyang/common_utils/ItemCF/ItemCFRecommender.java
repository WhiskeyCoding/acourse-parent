package com.lvyang.common_utils.ItemCF;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/4/19 16:10
 * @Version: 1.0
 */
public class ItemCFRecommender {
    final static int RECOMMENDER_NUM = 3;
    public static List<String> getRecommend(String memberId) throws IOException, TasteException {
        List<String> recommendCourseIdList = new ArrayList<>();
        String file = "D:\\AcoursePath\\CFTest\\statistics_course_rate.csv";
        DataModel model = new FileDataModel(new File(file));
        ItemSimilarity item = new EuclideanDistanceSimilarity(model);
        Recommender recommender = new GenericItemBasedRecommender(model, item);
        LongPrimitiveIterator iter = model.getUserIDs();
        long resultId = Long.parseLong(memberId);;
        List<RecommendedItem> list = recommender.recommend(resultId, RECOMMENDER_NUM);
        for (RecommendedItem ritem : list) {
            Long courseitemId = ritem.getItemID();
            String courseId = courseitemId.toString();
            recommendCourseIdList.add(courseId);
        }
        return recommendCourseIdList;
    }
}
