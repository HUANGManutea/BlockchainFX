package Main.Model;

import javafx.concurrent.Task;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Manutea on 07/04/2017.
 */
public class MinerTask implements Runnable {
    private Block workingBlock;
    private Miner miner;

    public MinerTask(Miner myMiner, Block block){
        super();
        workingBlock = block;
        miner = myMiner;
    }

    @Override
    public void run() {
        try {
            workingBlock.calculateNonceAndHash();
            miner.notifyMe();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
