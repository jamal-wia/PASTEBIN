package com.example.jamal.pastebin.mvp.global;

public class MvpPresenter<T extends MvpView> {

    //ссылка на активити или фрагмент реализуюшие MainView или его наследника
    private T view;

    //прикрепляет ссылку на активити или фрагмент которые реализуют интерфейс MainView или его наследника
    public void attachView(T view) {
        this.view = view;
    }

    //открепляет ссылку на MainView или его наследника
    public void detachView() {
        view = null;
    }

    //возвращает ссылка на MainView
    protected T getView() {
        return view;
    }
}