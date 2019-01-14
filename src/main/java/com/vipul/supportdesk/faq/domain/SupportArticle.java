package com.vipul.supportdesk.faq.domain;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;

public class SupportArticle {
    @Id
    private String articleId;

    private CategoryFAQ category;
    private List<String> labels;
    private String articleTitle;
    private String articleText;

    public SupportArticle(String articleId) {
        this.articleId = articleId;
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

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupportArticle)) return false;
        SupportArticle that = (SupportArticle) o;
        return articleId.equals(that.articleId) &&
                getCategory() == that.getCategory() &&
                getArticleTitle().equals(that.getArticleTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, getCategory(), getArticleTitle());
    }

    @Override
    public String toString() {
        return "SupportArticle{" +
                "articleId='" + articleId + '\'' +
                ", category=" + category +
                ", labels=" + labels +
                ", articleText='" + articleText + '\'' +
                '}';
    }
}
