package com.vipul.supportdesk.faq.persistence;

import com.vipul.supportdesk.faq.com.vipul.supportdesk.faq.util.CommonUtils;
import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import com.vipul.supportdesk.faq.domain.SupportArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupportArticleRepositorySetUp
{
    @Autowired
    private SupportArticleRepository supportArticleRepository;

    @Autowired
    private CommonUtils commonUtils;

    public void tearDownSupportArticles()
    {
        System.out.println();
        System.out.println("Deleting all support articles");
        supportArticleRepository.deleteAll();
    }

    public void setUpSupportArticles()
    {
        tearDownSupportArticles();

        System.out.println();
        System.out.println("Adding support articles");
        supportArticleRepository.save(getSupportArticle("Password reset steps"
                , "Follow these steps to reset password on www.atv.com/password-reset/ \n "
                        + "Step 1: Enter your account number \n"
                        + "Step 2: Choose verification code via SMS or EMAIL \n"
                        + "Step 3: Enter verification code \n"
                        + "Step 4: Provide new password \n"
                , CategoryFAQ.PASSWORD
                , null
                )
        );
        supportArticleRepository.save(getSupportArticle("How to subscribe newsfeed steps"
                , "Follow these steps to subscribe on www.atv.com/newsfeed/ \n "
                        + "Step 1: Enter your account number \n"
                        + "Step 2: Choose all desired newsfeed\n"
                        + "Step 3: Click on save subscriptions \n"
                , CategoryFAQ.SUBSCRIPTION
                , null
                )
        );
        supportArticleRepository.save(getSupportArticle("Shipment tracking steps"
                , "Follow these steps to track your pacakge on www.atv.com/shipment/ \n "
                        + "Step 1: Enter your account number \n"
                        + "Step 2: Enter shipment tracking number\n"
                        + "Step 3: Click on track-my-shipment\n"
                , CategoryFAQ.SHIPMENT
                , null
                )
        );
    }


    private SupportArticle getSupportArticle(String articleTitle, String articleText, CategoryFAQ categoryFAQ, List<String> labels)
    {
        SupportArticle entity = new SupportArticle(commonUtils.getNextId());
        entity.setCategory(categoryFAQ);
        if(labels != null && !labels.isEmpty())
        {
            entity.setLabels(labels);
        } else {
            entity.setLabels(commonUtils.getDefaultLabel(categoryFAQ));
        }
        if(articleText == null || articleText.isEmpty())
        {
            entity.setArticleText("NO ARTICLE TEXT PROVIDED !!!");
        } else {
            entity.setArticleText(articleText);
        }

        if(articleTitle == null || articleTitle.isEmpty())
        {
            entity.setArticleTitle("MISSING ARTICLE TITLE!!!");
        } else {
            entity.setArticleTitle(articleTitle);
        }
        return entity;
    }
}
