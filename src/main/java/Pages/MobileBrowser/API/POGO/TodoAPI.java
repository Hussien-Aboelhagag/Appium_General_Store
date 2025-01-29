package Pages.MobileBrowser.API.POGO;

public class TodoAPI {
    private boolean isCompleted;
    private String item;

    public TodoAPI(boolean isCompleted,String item){
        this.isCompleted=isCompleted;
        this.item=item;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

}
