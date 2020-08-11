package com.tec.pattern.principle.dip;

public class WrongMother {
    public void read(WrongBook book) {
        System.out.println("妈妈开始讲故事");
        System.out.println(book.getContent());
    }

    public static void main(String[] args) {
        WrongMother wm = new WrongMother();
        wm.read(new WrongBook());
        // TODO 如果有一天书上故事读完了，孩子让妈妈读报纸，该怎么办……
    }
}
