package Main.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by Manutea on 07/04/2017.
 */
public class Miner {
    private ObjectProperty<Blockchain> _blockchain;
    private ObjectProperty<Block> _workingBlock;

    public Miner(Blockchain blockchain){
        _blockchain = new SimpleObjectProperty<>(blockchain);
        _workingBlock = new SimpleObjectProperty<>();
    }

    public Blockchain get_blockchain() {
        return _blockchain.get();
    }

    public ObjectProperty<Blockchain> blockchainProperty() {
        return _blockchain;
    }

    public void setBlockchain(Blockchain _blockchain) {
        this._blockchain.set(_blockchain);
    }

    public Block getWorkingBlock() {
        return _workingBlock.get();
    }

    public ObjectProperty<Block> workingBlockProperty() {
        return _workingBlock;
    }

    public void setWorkingBlock(Block _workingBlock) {
        this._workingBlock.set(_workingBlock);
    }

    public void createNewBlock(){
        _workingBlock = new SimpleObjectProperty<>(new Block(_blockchain.getValue().getLastBlock().getCurrHash()));
    }

    public void addBlock(){
        _blockchain.getValue().addBlock(_workingBlock.getValue());
    }

    public void addData(String data){
        _workingBlock.getValue().addData(data);
    }

    public void mine(){
        Thread minerThread = new Thread(new MinerTask(this,_workingBlock.getValue()));
        minerThread.start();
    }

    public void notifyMe(){
        addBlock();
        createNewBlock();
        System.out.println(toString());
    }

    public String toString(){
        String res = "Miner:\n";
        res += "current Block:\n";
        res += _workingBlock.toString()+"\n";
        res += _blockchain.toString();
        return res;
    }
}
