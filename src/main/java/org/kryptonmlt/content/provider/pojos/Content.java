package org.kryptonmlt.content.provider.pojos;

/**
 *
 * @author kurt
 */
public class Content implements Comparable<Content> {

    private String title;

    private long date;

    private String html;

    public Content() {
    }

    public Content(String title, long date, String html) {
        this.title = title;
        this.date = date;
        this.html = html;
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
        return "Content{" + "title=" + title + ", date=" + date + ", html=" + html + '}';
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
