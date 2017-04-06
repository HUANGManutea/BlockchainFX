package Main.Model;

/**
 * Created by Manutea on 07/04/2017.
 */
public class Miner {
    private Blockchain _blockchain;
    private Block _workingBlock;

    public Miner(Blockchain blockchain){
        _blockchain = blockchain;
        _workingBlock = null;
    }

    public Blockchain get_blockchain() {
        return _blockchain;
    }

    public Block getWorkingBlock() {
        return _workingBlock;
    }

    public void setWorkingBlock(Block _workingBlock) {
        this._workingBlock = _workingBlock;
    }

    public void createNewBlock(){
        _workingBlock = new Block(_blockchain.getLastBlock().getCurrHash());
    }

    public void addBlock(){
        _blockchain.addBlock(_workingBlock);
    }

    public void mine(){
        Thread minerThread = new Thread(new MinerTask(this,_workingBlock));
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
