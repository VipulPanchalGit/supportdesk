package com.vipul.supportdesk.faq;

import com.vipul.supportdesk.faq.domain.CategoryFAQ;
import com.vipul.supportdesk.faq.domain.FaqData;
import com.vipul.supportdesk.faq.domain.SupportArticle;
import com.vipul.supportdesk.faq.persistence.FaqRepository;
import com.vipul.supportdesk.faq.persistence.SupportArticleRepository;
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
	private SupportArticleRepository supportArticleRepository;

	public static void main(String[] args) {
		SpringApplication.run(FaqApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		setUpFAQ();
		setUpSupportArticles();
	}

	private void setUpFAQ()
	{
		System.out.println();
		System.out.println("Deleting all FAQ data");
		faqRepository.deleteAll();

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

	private void setUpSupportArticles()
	{
		System.out.println();
		System.out.println("Deleting all support articles");
		supportArticleRepository.deleteAll();

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

	private String getNextId()
	{
		String id = Integer.toString(new Random().nextInt(1000));
		return id;
	}

	private List<String> getDefaultLabel(CategoryFAQ categoryFAQ)
	{
		List<String> tempLabels = new ArrayList<>();
		tempLabels.add(categoryFAQ.toString());
		return tempLabels;
	}

	private FaqData getFaqData(String question, String answer, CategoryFAQ categoryFAQ, List<String> labels)
	{
		FaqData entity = new FaqData(getNextId());
		entity.setCategory(categoryFAQ);
		if(labels != null && !labels.isEmpty())
		{
			entity.setLabels(labels);
		} else {
			entity.setLabels(getDefaultLabel(categoryFAQ));
		}

		entity.setQuestion(question);
		entity.setAnswer(answer);
		entity.setLinkedArticle(null);

		return entity;
	}

	private SupportArticle getSupportArticle(String articleTitle, String articleText, CategoryFAQ categoryFAQ, List<String> labels)
	{
		SupportArticle entity = new SupportArticle(getNextId());
		entity.setCategory(categoryFAQ);
		if(labels != null && !labels.isEmpty())
		{
			entity.setLabels(labels);
		} else {
			entity.setLabels(getDefaultLabel(categoryFAQ));
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

