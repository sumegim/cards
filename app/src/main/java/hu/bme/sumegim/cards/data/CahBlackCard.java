package hu.bme.sumegim.cards.data;

/**
 * Created by mars on 2017.11.10..
 */

public class CahBlackCard {
    private int id;
    private String text;
    private int pick;
    private String ownerUid;

    public CahBlackCard(){
        id = -1;
        pick = 1;
        text = "";
        ownerUid = "";
    }

    public CahBlackCard(int pick, String text){
        id = -1;
        this.pick = pick;
        this.text = text;
        ownerUid = "";
    }

    public CahBlackCard(int id, int pick, String text){
        this.id = id;
        this.pick = pick;
        this.text = text;
        ownerUid = "";
    }



    public CahBlackCard(int id, int pick, String text, String uid){
        this.id = id;
        this.pick = pick;
        this.text = text;
        this.ownerUid = uid;
    }

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
}
