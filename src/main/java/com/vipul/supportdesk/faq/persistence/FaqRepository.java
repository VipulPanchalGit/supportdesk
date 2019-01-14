package com.vipul.supportdesk.faq.persistence;

import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import com.vipul.supportdesk.faq.domain.FaqData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FaqRepository extends MongoRepository<FaqData, String> {
    public List<FaqData> findFaqDataByLabelsContainingOrderById(String label);
    public List<FaqData> findFaqDataByCategoryOrderById(CategoryFAQ cat);
    public FaqData findFaqDataById(String id);
}
