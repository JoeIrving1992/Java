
/**
 * Write a description of class Model here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Comparator;
public class Model implements Comparator<Model>
{
    private int LibraryId;
    private String title;
    private String owner;
    private String type;
    private boolean flag;
    private String date;

    public Model(){
        this.LibraryId = 0;
    }
    public Model(int libraryId,String owner, String title, String type, boolean flag, String date) {
        LibraryId = libraryId;
        this.owner = owner;
        this.title = title;
        this.type = type;
        this.flag = flag;
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getLibraryId() {
        return LibraryId;
    }

    public void setLibraryId(int libraryId) {
        LibraryId = libraryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        if(isFlag()) {
            return "item-" + getLibraryId() + "," + getType() + ","+getOwner()+"," + getTitle() + "," +
                    "YES" + "," + getDate()+"\n";
        }
        else {
            return "item-" + getLibraryId() + "," + getType() + ","+getOwner()+"," + getTitle() + "," +
                    "NO"+"\n";
        }
    }

    @Override
    public int compare(Model o1, Model o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
