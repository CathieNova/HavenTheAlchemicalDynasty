package net.cathienova.havenalchemy.capabilities;

public interface IEmcHandler {

    void storeEMC(long emc);
    void takeEMC(long emc);

    long getEMC();
}
