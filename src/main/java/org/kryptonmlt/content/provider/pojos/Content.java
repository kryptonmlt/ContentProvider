package org.kryptonmlt.content.provider.pojos;

/**
 *
 * @author kurt
 */
public class Content implements Comparable<Content> {

    private int id;

    private String title;

    private long date;

    private String html;

    private String summary;

    public Content() {
    }

    public Content(int id, String title, long date, String html, String summary) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.html = html;
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Content{" + "id=" + id + ", title=" + title + ", date=" + date + ", html=" + html + ", summary=" + summary + '}';
    }

    @Override
    public int compareTo(Content o) {
        if (this.getDate() > o.getDate()) {
            return -1;
        } else if (this.getDate() > o.getDate()) {
            return 1;
        }
        return 0;
    }

}
