package com.vipul.supportdesk.faq.persistence;

import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import com.vipul.supportdesk.faq.domain.SupportArticle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SupportArticleRepository extends MongoRepository<SupportArticle, String> {
    public List<SupportArticle> findAllByLabelsContainingOrderByArticleId(String label);
    public SupportArticle findAllByCategoryAndArticleIdOrderByArticleId(CategoryFAQ cat, String articleId);
    public List<SupportArticle> findAllByCategoryOrderByArticleId(CategoryFAQ cat);
    public SupportArticle findSupportArticleByArticleId(String id);
}
