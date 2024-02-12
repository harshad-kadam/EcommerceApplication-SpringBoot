package com.eci.gae.ecommerce.util;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ApiFeatures {
    private Query query;

    public ApiFeatures(Query query) {
        this.query = query;
    }

    public ApiFeatures search(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            Criteria criteria = new Criteria().orOperator(
                    Criteria.where("name").regex(keyword, "i")
                    // Add more fields for search if needed
            );
            query.addCriteria(criteria);
        }
        return this;
    }

    public ApiFeatures filter(Double minPrice, Double maxPrice) {
        if (minPrice != null || maxPrice != null) {
            Criteria priceCriteria = Criteria.where("price");
            if (minPrice != null) {
                priceCriteria = priceCriteria.gte(minPrice);
            }
            if (maxPrice != null) {
                priceCriteria = priceCriteria.lte(maxPrice);
            }
            query.addCriteria(priceCriteria);
        }
        return this;
    }

    public Query getQuery() {
        return query;
    }
}


//import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import java.util.Map;
//import java.util.regex.Pattern;
//
//public class ApiFeatures {
//    private final Query query;
//    private final Map<String, String> queryStr;
//
//    public ApiFeatures(Query query, Map<String, String> queryStr) {
//        this.query = query;
//        this.queryStr = queryStr;
//    }
//
//    public ApiFeatures search() {
//        Criteria keywordCriteria = queryStr.containsKey("keyword")
//                ? Criteria.where("name").regex(Pattern.compile(queryStr.get("keyword"), Pattern.CASE_INSENSITIVE))
//                : new Criteria();
//
//        query.addCriteria(keywordCriteria);
//        return this;
//    }
//
//    public ApiFeatures filter() {
//        Map<String, String> queryCopy = new HashMap<>(queryStr);
//        // Removing some fields for category
//        String[] removeFields = {"keyword", "page", "limit"};
//
//        for (String key : removeFields) {
//            queryCopy.remove(key);
//        }
//
//        // Filter for Price and Rating
//        String queryStr = queryCopy.toString();
//        queryStr = queryStr.replaceAll("\\b(gt|gte|lt|lte)\\b", (key) -> "$" + key);
//
//        // Parse the queryStr into a Criteria object and add it to the main query
//        Criteria criteria = Criteria.where("").andOperator(Criteria.where("").orOperator(Criteria.where("").orOperator(Criteria.where("").is(queryStr))));
//        query.addCriteria(criteria);
//
//        return this;
//    }
//
//    public ApiFeatures pagination(int resultPerPage) {
//        int currentPage = queryStr.containsKey("page") ? Integer.parseInt(queryStr.get("page")) : 1;
//
//        int skip = resultPerPage * (currentPage - 1);
//
//        query.limit(resultPerPage).skip(skip);
//
//        return this;
//    }
//
//    public AbstractInsnNode getQuery() {
//    }
//}
