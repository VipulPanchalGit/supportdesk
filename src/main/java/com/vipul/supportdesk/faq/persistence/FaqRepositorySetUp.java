package com.vipul.supportdesk.faq.persistence;

import com.vipul.supportdesk.faq.com.vipul.supportdesk.faq.util.CommonUtils;
import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import com.vipul.supportdesk.faq.domain.FaqData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FaqRepositorySetUp
{
    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private CommonUtils commonUtils;

    public void tearDownFAQ()
    {
        System.out.println();
        System.out.println("Deleting all FAQ data");
        faqRepository.deleteAll();
    }

    public void setUpFAQ()
    {
        tearDownFAQ();

        System.out.println();
        System.out.println("Adding FAQ data");
        faqRepository.save(getFaqData("How to activate my account?"
                , "Call 1-800-ATV-ACCT to activate your account"
                , CategoryFAQ.ACCOUNT
                , null)
        );
        faqRepository.save(getFaqData("How to cancel my account?"
                , "Call 1-800-ATV-ACCT to cancel your account"
                , CategoryFAQ.ACCOUNT
                , null)
        );
        faqRepository.save(getFaqData("How to report fraud activity on my account?"
                , "Call 1-800-ATV-ACCT to report fraud activity on your account"
                , CategoryFAQ.FRAUD
                , null)
        );
        faqRepository.save(getFaqData("How to subscribe to newsfeed?"
                , "Visit www.atv.com/newsfeed/follow/ to subscribe to newsfeed"
                , CategoryFAQ.SUBSCRIPTION
                , null)
        );
        faqRepository.save(getFaqData("How to reset my account password?"
                , "Call 1-800-ATV-ACCT to reset your account password or visit www.atv.com/password-reset/ "
                , CategoryFAQ.PASSWORD
                , null)
        );
    }

    private FaqData getFaqData(String question, String answer, CategoryFAQ categoryFAQ, List<String> labels)
    {
        FaqData entity = new FaqData(commonUtils.getNextId());
        entity.setCategory(categoryFAQ);
        if(labels != null && !labels.isEmpty())
        {
            entity.setLabels(labels);
        } else {
            entity.setLabels(commonUtils.getDefaultLabel(categoryFAQ));
        }

        entity.setQuestion(question);
        entity.setAnswer(answer);
        entity.setLinkedArticle(null);

        return entity;
    }
}
