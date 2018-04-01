package pro.nanosystems.downloadingimagetolistview.model;

/**
 * Created by sayyid on 01/04/2018.
 */

public class ListData {
    String url;
    String headline;
    String reporterName;
    String date;

    public ListData() {
    }

    public ListData(String url, String headline, String reporterName, String date) {
        this.url = url;
        this.headline = headline;
        this.reporterName = reporterName;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
