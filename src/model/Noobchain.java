package model;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class Noobchain {

    public ArrayList<Block> blockchain = new ArrayList<Block>();
    public int difficulty;
    Scanner scanner = new Scanner(System.in);

    public boolean isChainValid(){
        Block current, previous;
        for(int i = 1; i < blockchain.size(); i++){
            current = blockchain.get(i);
            previous = blockchain.get(i-1);
            String hashTarget = new String(new char[difficulty]).replace("\0", "0");
            //check curren block's hash
            if(!current.hash.equals(current.calculateHash())){
                System.out.println("curren hash not equal");
                return false;
            }
            //check previous block's hash
            if(!previous.hash.equals(previous.calculateHash())){
                System.out.println("previous hash not equal");
                return false;
            }
            //check if hash is solved
            if(!current.hash.substring(0, difficulty).equals(hashTarget)){
                System.out.println("this block hasn't been mined");
                return false;
            }
        }
        return true;
    }

    public int getDifficulty(){
        //enter difficulty, it should be 1 to 3, so long if greater than 3
        System.out.print("enter difficulty: ");
        difficulty = scanner.nextInt();
        return difficulty;
    }

    public void addBlock(String data){
        if (blockchain.size() == 0){
            //create genesis block
            blockchain.add(new Block(data, "0"));
            //set difficulty
            blockchain.get(blockchain.size()-1).mine(difficulty);
        }
        else{
            //create new block
            blockchain.add(new Block(data,blockchain.get(blockchain.size()-1).hash));
            //set difficulty
            blockchain.get(blockchain.size()-1).mine(difficulty);
            //set index for block
            blockchain.get(blockchain.size()-1).setIndex(blockchain.size()-1);
        }
    }

    public void printBlockChain(){
        String blockchain_json = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("the blockchain: ");
        System.out.println(blockchain_json);
    }

    public static void main(String[] args){
        Noobchain nc = new Noobchain();

        nc.getDifficulty();

        nc.addBlock("first block");
        nc.addBlock("second block");

        System.out.println("is valid chain: " + nc.isChainValid());
        nc.printBlockChain();
    }
}
