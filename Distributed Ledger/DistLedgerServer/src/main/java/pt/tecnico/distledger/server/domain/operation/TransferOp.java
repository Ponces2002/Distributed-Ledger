package pt.tecnico.distledger.server.domain.operation;

import pt.ulisboa.tecnico.distledger.contract.DistLedgerCommonDefinitions;

import java.util.List;

public class TransferOp extends Operation {
    private String destAccount;
    private int amount;

    public TransferOp(String fromAccount, String destAccount, int amount) {
        super(fromAccount);
        this.destAccount = destAccount;
        this.amount = amount;
    }

    public String getDestAccount() {
        return destAccount;
    }

    public void setDestAccount(String destAccount) {
        this.destAccount = destAccount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public DistLedgerCommonDefinitions.Operation getOperationMessageFormat(List<Integer> prevTS, List<Integer> TS){
        return DistLedgerCommonDefinitions.Operation.newBuilder()
                .setType(DistLedgerCommonDefinitions.OperationType.OP_TRANSFER_TO)
                .setUserId(getAccount())
                .setAmount(amount)
                .setDestUserId(destAccount)
                .setPrevTS(DistLedgerCommonDefinitions.Timestamp.newBuilder().addAllTimestamp(prevTS).build())
                .setTS(DistLedgerCommonDefinitions.Timestamp.newBuilder().addAllTimestamp(TS).build())
                .build();
    }

    @Override
    public String toString() {
        return "  ledger {\n    type: OP_TRANSFER_TO\n    userId: \"" + getAccount() + "\"\n    destUserId: \"" + getDestAccount() + "\"\n    amount: " + getAmount() + "\n  }";      
    }

}
