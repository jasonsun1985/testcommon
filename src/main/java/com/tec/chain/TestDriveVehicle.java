package com.tec.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class TestDriveVehicle implements Command {
    @Override
    public boolean execute(Context ctx) throws Exception {
        System.out.println("Test drive the vehicle");
        return false;
    }

}
