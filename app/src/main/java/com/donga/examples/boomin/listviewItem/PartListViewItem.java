package com.donga.examples.boomin.listviewItem;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class PartListViewItem {
    private int type;

    private String title_year;
    private String title_term;

    private String name_subject;
    private String name_grade;

    private String content_none;
    private String content_subject;
    private String content_grade;

    private String distin, grade_number;

    public String getContent_grade() {
        return content_grade;
    }

    public void setContent_grade(String content_grade) {
        this.content_grade = content_grade;
    }

    public String getContent_none() {
        return content_none;
    }

    public void setContent_none(String content_none) {
        this.content_none = content_none;
    }

    public String getContent_subject() {
        return content_subject;
    }

    public void setContent_subject(String content_subject) {
        this.content_subject = content_subject;
    }

    public String getName_grade() {
        return name_grade;
    }

    public void setName_grade(String name_grade) {
        this.name_grade = name_grade;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    public String getTitle_term() {
        return title_term;
    }

    public void setTitle_term(String title_term) {
        this.title_term = title_term;
    }

    public String getTitle_year() {
        return title_year;
    }

    public void setTitle_year(String title_year) {
        this.title_year = title_year;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDistin() {
        return distin;
    }

    public void setDistin(String distin) {
        this.distin = distin;
    }

    public String getGrade_number() {
        return grade_number;
    }

    public void setGrade_number(String grade_number) {
        this.grade_number = grade_number;
    }
}