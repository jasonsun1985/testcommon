package com.tec.pattern.strategy;


public class Context {
    GoForWork goForWork;

    public Context setContext(GoForWork goForWork) {
        this.goForWork = goForWork;
        return this;
    }

    public void go(){
        goForWork.gotoWork();
    }

}
