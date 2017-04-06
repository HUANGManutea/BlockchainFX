package Main.Model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manutea on 07/04/2017.
 */
public class Blockchain {
    List<Block> blocks;

    public Blockchain(){
        blocks = new ArrayList<>();
        try {
            addInitBlock();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void addInitBlock() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = "0";
        Block block = new Block(hash);
        block.calculateNonceAndHash();
        blocks.add(block);
    }

    public void addBlock(Block block){
        if (blocks.size() == 0) {
            System.out.println("add init block first");
        }
        else {
            if (block.isNonceCalculated())
            {
                blocks.add(block);
            }
            else
            {
                System.out.println("calculate the nonce first");
            }
        }
    }

    public Block removeLastBlock(){
        return blocks.remove(blocks.size()-1);
    }

    public Block getLastBlock(){
        return blocks.get(blocks.size()-1);
    }

    public String toString(){
        String res = "Blockchain:\n";
        for (int i = 0;i < blocks.size();i++) {
            res += "Block " + i + ": ";
            res += blocks.get(i).toString();
            res += "\n";
        }
        return res;
    }
}
