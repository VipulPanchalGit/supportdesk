package com.vipul.supportdesk.faq.controller;

import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import com.vipul.supportdesk.faq.domain.FaqData;
import com.vipul.supportdesk.faq.persistence.FaqRepository;
import com.vipul.supportdesk.faq.persistence.SupportArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/index")
    public String index(@RequestParam(value="category", defaultValue = "ALL") String category)
    {
        CategoryFAQ cat = CategoryFAQ.valueOf(category.toUpperCase());

        String response = new String("Looking for FAQs belonging to " + category + " category");

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
        StringBuffer responseFaqs = new StringBuffer();
        responseFaqs.append("<html><Head><Title>FAQ for ATV</Title></Head>");
        responseFaqs.append("<Body>");
        for (FaqData faq: faqs)
        {
            responseFaqs.append("<h1>");
            responseFaqs.append(faq.toString());
            responseFaqs.append("</br></h1>");
        }
        responseFaqs.append("</Body></html>");
        return responseFaqs.toString();
    }
}
