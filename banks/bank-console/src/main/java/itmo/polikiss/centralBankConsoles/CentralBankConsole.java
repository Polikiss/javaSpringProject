package itmo.polikiss.centralBankConsoles;

import banks.CentralBank;
import picocli.CommandLine;

/**
 * console for central bank
 */
@CommandLine.Command(name = "CentralBank")
public class  CentralBankConsole {
    private final CentralBank centralBank;
    public CentralBankConsole(CentralBank centralBank){
        this.centralBank = centralBank;
    }
    @CommandLine.Command(name = "CreateBank", description = "Create new bank")
    public void CreateBank(@CommandLine.Parameters(index = "0", description = "Name of the bank") String name,
                           @CommandLine.Parameters(index = "1", defaultValue = "3.0", description = "Interest of the bank") double interest,
                           @CommandLine.Parameters(index = "2", defaultValue = "0", description = "Limit of the bank") double limit,
                           @CommandLine.Parameters(index = "3", defaultValue = "0", description = "Commission of the bank") double commission,
                           @CommandLine.Parameters(index = "4", defaultValue = "0", description = "Limit for doubtful accounts") double doubtfulAccountLimit,
                           @CommandLine.Parameters(index = "5", defaultValue = "3", description = "Amount of month in term for deposit account") long depositAccountMonth)
    {
        centralBank.createBank(name, interest, limit, commission, doubtfulAccountLimit, depositAccountMonth);
        System.out.printf("Bank %s created%n", name);
    }
}
