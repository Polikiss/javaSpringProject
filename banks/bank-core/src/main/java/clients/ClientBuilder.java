package clients;

import banks.Bank;

/**
 * Class to build client by steps
 */

public class ClientBuilder {
    private final Bank bank;
    private String name;
    private String surname;
    private String address;
    private String passport;
    public ClientBuilder(Bank bank){
        this.bank = bank;
    }

    /**
     * add name to client
     * @param name
     * @return builder
     */
    public ClientBuilder withName(String name){
        this.name = name;
        return this;
    }

    /**
     * add surname to client
     * @param surname
     * @return builder
     */
    public ClientBuilder withSurname(String surname){
        this.surname = surname;
        return this;
    }

    /**
     * add address to client
     * @param address
     * @return builder
     */
    public ClientBuilder withAddress(String address){
        this.address = address;
        return this;
    }

    /**
     * add passportNumber to client
     * @param passportNumber
     * @return builder
     */
    public ClientBuilder withPassport(String passportNumber){
        this.passport = passportNumber;
        return this;
    }

    /**
     * create new client
     * @return new client
     */

    public Client сreateClient(){
        Client client = new Client(bank, name, surname, address,passport);
        client.changeClientState();
        return client;
    }
}
