package com.vipul.supportdesk.faq.domain;

import org.springframework.data.annotation.Id;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class FaqData {
    @Id
    private String id;

    private CategoryFAQ category;
    private List<String> labels;
    private String question;
    private String answer;
    private URL linkedArticle;

    public FaqData(String id) {
        this.id = id;
    }

    public CategoryFAQ getCategory() {
        return category;
    }

    public void setCategory(CategoryFAQ category) {
        this.category = category;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public URL getLinkedArticle() {
        return linkedArticle;
    }

    public void setLinkedArticle(URL linkedArticle) {
        this.linkedArticle = linkedArticle;
    }

    @Override
    public String toString() {
        return "FaqData{" +
                "id='" + id + '\'' +
                ", category=" + category +
                ", labels=" + labels +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", linkedArticle=" + linkedArticle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FaqData)) return false;
        FaqData faqData = (FaqData) o;
        return id.equals(faqData.id) &&
                getCategory() == faqData.getCategory() &&
                getQuestion().equals(faqData.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getCategory(), getQuestion());
    }
}
