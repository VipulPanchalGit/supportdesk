package com.vipul.supportdesk.faq;

import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import com.vipul.supportdesk.faq.domain.FaqData;
import com.vipul.supportdesk.faq.domain.SupportArticle;
import com.vipul.supportdesk.faq.persistence.FaqRepository;
import com.vipul.supportdesk.faq.persistence.FaqRepositorySetUp;
import com.vipul.supportdesk.faq.persistence.SupportArticleRepository;
import com.vipul.supportdesk.faq.persistence.SupportArticleRepositorySetUp;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaqApplicationTests {

	@Autowired
	private FaqRepository faqRepository;

	@Autowired
	private FaqRepositorySetUp faqRepositorySetUp;


	@Autowired
	private SupportArticleRepository supportArticleRepository;

	@Autowired
	private SupportArticleRepositorySetUp supportArticleRepositorySetUp;

	@Before
	public void setUp()
	{
		faqRepositorySetUp.setUpFAQ();
		supportArticleRepositorySetUp.setUpSupportArticles();
	}

	@Test
	public void testAllFAQ()
	{
		System.out.println("Retrieving all FAQ data");
		List<FaqData> allFaqs = faqRepository.findAll();
		if(allFaqs != null)
		{
			System.out.println("Found " + allFaqs.size() + " FAQ data");
			for (FaqData faq : allFaqs) {
				System.out.println(faq.toString());
			}
		} else {
			System.out.println("NO FAQ data !!!");
		}
	}

	@Test
	public void testAccountFAQ()
	{
		System.out.println("Retrieving all account related FAQ data");
		List<FaqData> allAcctFaqs = faqRepository.findFaqDataByCategoryOrderById(CategoryFAQ.ACCOUNT);
		if(allAcctFaqs != null)
		{
			System.out.println("Found " + allAcctFaqs.size() + " account related FAQ data");
			for (FaqData faq : allAcctFaqs) {
				System.out.println(faq.toString());
			}
		} else {
			System.out.println("NO FAQ data !!!");
		}
	}

	@Test
	public void testShipmentFAQ()
	{
		System.out.println("Retrieving all account related FAQ data");
		List<FaqData> allAcctFaqs = faqRepository.findFaqDataByCategoryOrderById(CategoryFAQ.SHIPMENT);
		if(allAcctFaqs != null)
		{
			System.out.println("Found " + allAcctFaqs.size() + " shipment related FAQ data");
			for (FaqData faq : allAcctFaqs) {
				System.out.println(faq.toString());
			}
		} else {
			System.out.println("NO FAQ data !!!");
		}
	}

	@Test
	public void testAllSupportArticles()
	{
		System.out.println("Retrieving all support articles");
		List<SupportArticle> allArticles = supportArticleRepository.findAll();
		if(allArticles != null)
		{
			System.out.println("Found " + allArticles.size() + " support articles");
			for (SupportArticle article : allArticles) {
				System.out.println(article.toString());
			}
		} else {
			System.out.println("NO support articles !!!");
		}
	}

	@Test
	public void testAccountSupportArticles()
	{
		System.out.println("Retrieving all account related support articles");
		List<SupportArticle> allAcctArticles = supportArticleRepository.findAllByCategoryOrderByArticleId(CategoryFAQ.ACCOUNT);
		if(allAcctArticles != null)
		{
			System.out.println("Found " + allAcctArticles.size() + " account related FAQ data");
			for (SupportArticle article : allAcctArticles) {
				System.out.println(article.toString());
			}
		} else {
			System.out.println("NO support articles !!!");
		}
	}

	@Test
	public void testPasswordSupportArticles()
	{
		System.out.println("Retrieving all account related support articles");
		List<SupportArticle> allAcctArticles = supportArticleRepository.findAllByCategoryOrderByArticleId(CategoryFAQ.PASSWORD);
		if(allAcctArticles != null)
		{
			System.out.println("Found " + allAcctArticles.size() + " password related FAQ data");
			for (SupportArticle article : allAcctArticles) {
				System.out.println(article.toString());
			}
		} else {
			System.out.println("NO support articles !!!");
		}
	}

	@After
	public void tearDown()
	{
		faqRepositorySetUp.tearDownFAQ();
		supportArticleRepositorySetUp.tearDownSupportArticles();
	}



}

