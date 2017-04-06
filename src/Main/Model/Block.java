package Main.Model;

import sun.plugin2.message.Message;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by Manutea on 06/04/2017.
 */
public class Block {
    private String prevHash;
    private String currHash;
    private int nonce;
    private List<String> datas;
    private boolean nonceCalculated;

    public Block(String previousHash) {
        prevHash = previousHash;
        nonceCalculated = false;
        datas = new ArrayList<>();
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getCurrHash() {
        return currHash;
    }

    public int getNonce() {
        return nonce;
    }

    public List<String> getDatas() {
        return datas;
    }

    public boolean isNonceCalculated() {
        return nonceCalculated;
    }

    public void addData(String data){
        datas.add(data);
        nonceCalculated = false;
    }

    public String removeLastData(){
        return datas.remove(datas.size());
    }

    public void calculateNonceAndHash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (nonceCalculated)
        {
            System.out.println("nonce already calculated");
        }
        else
        {
            System.out.println("calculating nonce, may take some time");
            int int_limit = Integer.MAX_VALUE;

            String mergedata = "";
            for (String data : datas) {
                mergedata+=data;
            }

            String hashes = prevHash;
            hashes += mergedata;

            boolean nonceFound = false;
            int _nonce = 0;
            String _newHash = "";
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            for (int i = 0;!nonceFound && i < int_limit;i++) {
                String hashes_and_i = hashes;
                hashes_and_i += i;
                byte[] digested= md.digest(hashes_and_i.getBytes("UTF-8"));
                String newHash = String.format("%064x", new java.math.BigInteger(1, digested));
                if (newHash.startsWith("000")) {
                    nonceFound = true;
                    _nonce = i;
                    _newHash = newHash;
                }
            }

            if (nonceFound)
            {
                nonceCalculated = true;
                nonce = _nonce;
                currHash = _newHash;
                System.out.println("nonce found");
            }
            else
            {
                System.out.println("nonce not found");
            }
        }
    }

    public String toString(){
        String res = "nonce: ";
        res += nonce;
        res += " prevHash: ";
        res += prevHash;
        res += " currHash: ";
        res += currHash+"\n";
        res += "datas : \n";
        for (int i = 0;i < datas.size();i++) {
            res += "data" + i +" : ";
            res += datas.get(i);
            res += "\n";
        }
        return res;
    }
}
