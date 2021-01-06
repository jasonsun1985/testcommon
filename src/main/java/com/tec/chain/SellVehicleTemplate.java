package com.tec.chain;

/**
 * @author sunlei
 * @title: SellVehicleTemplate
 * @projectName testcommon
 * @description: TODO
 * @date 2020/12/21 14:58
 */
public abstract class SellVehicleTemplate {
    public void sellVehicle() {
        getCustomerInfo();
        testDriveVehicle();
        negotiateSale();
        arrangeFinancing();
        closeSale();
    }

    public abstract void getCustomerInfo();

    public abstract void testDriveVehicle();

    public abstract void negotiateSale();

    public abstract void arrangeFinancing();

    public abstract void closeSale();
}
