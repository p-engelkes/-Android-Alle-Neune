package patrickengelkes.com.alleneune.entities.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by patrickengelkes on 13/02/15.
 */
public class Drink implements Parcelable {
    public static String ROOT = "drink";
    //atributes
    private int id;
    private int clubID;
    private String name;
    private double price;

    //attribute strings for api calls
    public static String ID = "id";
    public static String CLUB_ID = "club_id";
    public static String NAME = "name";
    public static String PRICE = "price";

    //urls
    public static String GENERIC_URL = "/drinks";
    public static String GET_BY_CLUB = GENERIC_URL + "/get_by_club";


    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Drink(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Drink(String name, double price, int clubID) {
        this.name = name;
        this.price = price;
        this.clubID = clubID;
    }

    //<editor-fold desc="Getter & Setter">
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getClubID() {
        return this.clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }
    //</editor-fold>

    //<editor-fold desc="Parcelable">
    protected Drink(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.price = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeDouble(this.price);
    }

    public static final Creator<Drink> CREATOR = new Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel parcel) {
            return new Drink(parcel);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };
    //</editor-fold>
}