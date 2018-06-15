package model;

import java.util.Date;

public class Block {
    private int index;
    private long timestamp;
    public String hash;
    public String previous_hash;
    private String data;
    private int nonce;

    public Block(String data, String previous_hash) {
        this.timestamp = new Date().getTime();
        this.previous_hash = previous_hash;
        this.data = data;
        this.hash = calculateHash();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getDate() {
        return timestamp;
    }

    public void setDate(long date) {
        this.timestamp = date;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrevious_hash() {
        return previous_hash;
    }

    public void setPrevious_hash(String previous_hash) {
        this.previous_hash = previous_hash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String calculateHash(){
        String hash = new Sha256().sha256(this.previous_hash +
                Long.toString(timestamp) +
                Integer.toString(nonce) +
                data);
        return hash;
    }

    public void mine(int difficulty){
        String target = new String(new char[difficulty]).replace("\0", "0");
        while(!hash.substring(0, difficulty).equals(target)){
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("block mined: " + hash);
    }
}
