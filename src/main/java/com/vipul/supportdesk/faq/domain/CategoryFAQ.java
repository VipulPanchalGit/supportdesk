package com.vipul.supportdesk.faq.domain;

public enum CategoryFAQ {

    ALL("ALL")
    ,ACCOUNT("ACCT")
    ,PASSWORD("PSWD")
    ,FRAUD("FRAUD")
    ,SUBSCRIPTION("DESC")
    ,SHIPMENT("SHIPMNT")
    ,RETURNS("RETRNS");

    private final String value;

    CategoryFAQ(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CategoryFAQ{" +
                "value='" + value + '\'' +
                '}';
    }
}
