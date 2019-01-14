package com.vipul.supportdesk.faq;

import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import com.vipul.supportdesk.faq.domain.FaqData;
import com.vipul.supportdesk.faq.domain.SupportArticle;
import com.vipul.supportdesk.faq.persistence.FaqRepository;
import com.vipul.supportdesk.faq.persistence.FaqRepositorySetUp;
import com.vipul.supportdesk.faq.persistence.SupportArticleRepository;
import com.vipul.supportdesk.faq.persistence.SupportArticleRepositorySetUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class FaqApplication implements CommandLineRunner {

	@Autowired
	private FaqRepository faqRepository;

	@Autowired
	private FaqRepositorySetUp faqRepositorySetUp;


	@Autowired
	private SupportArticleRepository supportArticleRepository;

	@Autowired
	private SupportArticleRepositorySetUp supportArticleRepositorySetUp;

	public static void main(String[] args) {
		SpringApplication.run(FaqApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		faqRepositorySetUp.setUpFAQ();
		supportArticleRepositorySetUp.setUpSupportArticles();
	}
}

