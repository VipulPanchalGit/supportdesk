package com.vipul.supportdesk.faq.com.vipul.supportdesk.faq.util;

import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CommonUtils
{
    public String getNextId()
    {
        String id = Integer.toString(new Random().nextInt(1000));
        return id;
    }

    public List<String> getDefaultLabel(CategoryFAQ categoryFAQ)
    {
        List<String> tempLabels = new ArrayList<>();
        tempLabels.add(categoryFAQ.toString());
        return tempLabels;
    }
}
