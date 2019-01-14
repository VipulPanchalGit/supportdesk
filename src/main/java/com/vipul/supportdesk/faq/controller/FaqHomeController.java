package com.vipul.supportdesk.faq.controller;

import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import com.vipul.supportdesk.faq.domain.FaqData;
import com.vipul.supportdesk.faq.domain.SupportArticle;
import com.vipul.supportdesk.faq.persistence.FaqRepository;
import com.vipul.supportdesk.faq.persistence.SupportArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FaqHomeController {

    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private SupportArticleRepository supportArticleRepository;

    @RequestMapping("/home")
    public String home(@RequestParam(value="name", defaultValue = "World") String name)
    {
        final String response;
        response = new String("Hello " + name + " !!!");
        return response;
    }

    @RequestMapping("/articles")
    public String articles(@RequestParam(value="category", defaultValue = "ALL") String category
            ,@RequestParam(value = "articleId", required = false) String articleId)
    {
        CategoryFAQ cat = CategoryFAQ.valueOf(category.toUpperCase());

        List<SupportArticle> articles = new ArrayList<SupportArticle>();
        if(cat.equals(CategoryFAQ.ALL))
        {
            articles = supportArticleRepository.findAll();
        } else {
            if(articleId == null || !validArticleId(articleId))
            {
                articles = supportArticleRepository.findAllByCategoryOrderByArticleId(cat);
            } else {
                SupportArticle article = supportArticleRepository.findAllByCategoryAndArticleIdOrderByArticleId(cat, articleId);
                if(article != null)
                {
                    articles.add(article);
                }
            }

        }

        String responseArticle = buildResponseArticle(articles);
        return responseArticle;
    }

    private boolean validArticleId(String articleId)
    {
        try {
            Integer i = Integer.valueOf(articleId);
            if(i != null && i > 0 && i < 1000)
            { // TODO: Remove hard-coding of upper bound 1000
                return true;
            }
        } catch (NumberFormatException e)
        {
            return false;
        }
        return false;
    }

    private String buildResponseArticle(List<SupportArticle> articles)
    {
        StringBuffer response = new StringBuffer();
        response.append("<html><Head><Title>FAQ for ATV</Title></Head>");
        response.append("<Body>");
        if(articles == null || articles.isEmpty())
        {
            response.append("<section>");
            response.append("Support article not found !!! Please try different category and/or article Id.");
            response.append("</br>");
            response.append("</section>");
        } else {
            for (SupportArticle article: articles)
            {
                response.append("<section>");
                response.append("<h1>");
                response.append(article.getArticleTitle());
                response.append("</br></h1>");
                response.append(article.getArticleText());
                response.append("</br>");
                response.append("</section>");
            }
        }

        response.append("</Body></html>");
        return response.toString();
    }

    @RequestMapping("/index")
    public String index(@RequestParam(value="category", defaultValue = "ALL") String category)
    {
        CategoryFAQ cat = CategoryFAQ.valueOf(category.toUpperCase());

        List<FaqData> faqs = new ArrayList<FaqData>();
        if(cat.equals(CategoryFAQ.ALL))
        {
            faqs= faqRepository.findAll();

        } else {
            faqs = faqRepository.findFaqDataByCategoryOrderById(cat);
        }

        String responseFaqs = buildResponseFaq(faqs);
        return responseFaqs.toString();
    }

    private String buildResponseFaq(List<FaqData> faqs)
    {
        StringBuffer response = new StringBuffer();
        response.append("<html><Head><Title>FAQ for ATV</Title></Head>");
        response.append("<Body>");
        for (FaqData faq: faqs)
        {
            response.append("<h1>");
            response.append(faq.getQuestion());
            response.append("</br></h1>");
            response.append(faq.getAnswer());
            response.append("</br>");
        }
        response.append("</Body></html>");
        return response.toString();
    }
}
