package model;

import java.util.Date;

/**
 *
 * @author kienct
 */
public class Exam {

    private int id;
    private boolean isFree;
    private Date updatedDate;
    private String title;
    private String thumbnail;
    private String category;
    private String className;
    private String briefInfo;
    private String author;
    private String content;

    public Exam() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        if (this.isFree) {
            this.className = "Free";
        } else {
            this.className = className;
        }
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Exam{" + "id=" + id + ", isFree=" + isFree + ", updatedDate=" + updatedDate + ", title=" + title + ", thumbnail=" + thumbnail + ", category=" + category + ", className=" + className + ", briefInfo=" + briefInfo + ", author=" + author + ", content=" + content + '}';
    }

}
