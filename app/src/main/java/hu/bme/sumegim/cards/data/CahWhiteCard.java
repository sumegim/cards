package hu.bme.sumegim.cards.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mars on 2017.11.09..
 */

public class CahWhiteCard implements Parcelable{
    private int id;
    private String text;
    private String ownerUid;
    private boolean revealed;

    public CahWhiteCard(){
        id = -1;
        text = "";
        ownerUid = "";
        revealed = false;
    }

    public CahWhiteCard(int id, String text){
        this.id = id;
        this.text = text;
        ownerUid = "";
        revealed = false;
    }

    public CahWhiteCard(int id, String text, String uid){
        this.id = id;
        this.text = text;
        this.ownerUid = uid;
        revealed = false;
    }

    protected CahWhiteCard(Parcel in) {
        id = in.readInt();
        text = in.readString();
        ownerUid = in.readString();
        revealed = in.readByte() != 0;
    }

    public static final Creator<CahWhiteCard> CREATOR = new Creator<CahWhiteCard>() {
        @Override
        public CahWhiteCard createFromParcel(Parcel in) {
            return new CahWhiteCard(in);
        }

        @Override
        public CahWhiteCard[] newArray(int size) {
            return new CahWhiteCard[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }

    public boolean isRevealed(){
        return revealed;
    }

    public void setRevealed(boolean r){
        revealed = r;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(text);
        dest.writeString(ownerUid);
    }
}
