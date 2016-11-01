package helperClasses;

/**
 * Created by Ashutosh on 31-10-2016.
 */

public class ListItem {

    private String title;
    private String details;

    public ListItem(String title, String details){

        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
