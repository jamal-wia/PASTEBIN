package wia.soft.official.pastebin.data.global;

import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import wia.soft.official.pastebin.data.local.Database;
import wia.soft.official.pastebin.data.local.PreferencesHelper;
import wia.soft.official.pastebin.data.models.PasteRoom;
import wia.soft.official.pastebin.data.network.PastebinServise;

import static wia.soft.official.pastebin.utils.Constants.DEV_KEY;
import static wia.soft.official.pastebin.utils.Constants.PASTE_PARAM_LIST;
import static wia.soft.official.pastebin.utils.Constants.PASTE_PARAM_SHOW_PASTE;
import static wia.soft.official.pastebin.utils.Constants.PASTE_PARAM_TRENDS;

public class DataManager {

    private PastebinServise pastebinServise;
    private PreferencesHelper preferencesHelper;
    private Database database;

    public DataManager(PastebinServise pastebinServise, PreferencesHelper preferencesHelper,
                       Database database) {
        this.pastebinServise = pastebinServise;
        this.preferencesHelper = preferencesHelper;
        this.database = database;
    }

    public Call<ResponseBody> login(String login, String password) {
        return pastebinServise.login(DEV_KEY, login, password);
    }

    public Call<ResponseBody> getListPasteByUser(String apiUserKey) {
        return pastebinServise.getListPasteByUser(DEV_KEY, apiUserKey, 100,
                PASTE_PARAM_LIST);
    }

    public Call<ResponseBody> getRawPaste(String apiPasteKey) {
        return pastebinServise.getRawPaste(DEV_KEY, preferencesHelper.getToken(), apiPasteKey,
                PASTE_PARAM_SHOW_PASTE);
    }

    public Call<ResponseBody> getRawTrendingPaste(String apiPasteKey) {
        return pastebinServise.getRawTrendingPaste(apiPasteKey);
    }

    public Call<ResponseBody> getListTrendingPaste() {
        return pastebinServise.getListTrendingPaste(DEV_KEY, PASTE_PARAM_TRENDS);
    }

    public Call<ResponseBody> createPaste(String apiPasteName, String apiPasteFormat,
                                          int apiPastePrivate, String apiPasteExpireDate,
                                          String apiPasteCode) {
        return pastebinServise.createPaste(preferencesHelper.getToken(), apiPasteName, apiPasteFormat,
                apiPastePrivate, apiPasteExpireDate, "paste", DEV_KEY, apiPasteCode);
    }

    public Call<ResponseBody> removePaste(String apiPasteKey){
        return pastebinServise.removePaste(DEV_KEY,preferencesHelper.getToken(),apiPasteKey,"delete");
    }

    public Call<ResponseBody> infoUser() {
        return pastebinServise.infoUser(DEV_KEY, preferencesHelper.getToken(), "userdetails");
    }

    public void setToken(String token) {
        preferencesHelper.setToken(token);
    }

    public String getToken() {
        return preferencesHelper.getToken();
    }

    public List<PasteRoom> getAllPaste() {
        try {
            return new GetAllPaste().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PasteRoom getPaste(int id) {
        try {
            return new GetPaste(id).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertPaste(PasteRoom paste) {
        new InsertPaste(paste).execute();
    }

    public void updatePaste(PasteRoom pasteRoom){
        new UpdatePaste(pasteRoom).execute();
    }

    public void deletePaste(PasteRoom paste) {
        new DeletePaste(paste).execute();
    }

    public void exit() {
        preferencesHelper.setToken(null);
    }

    private class GetAllPaste extends AsyncTask<Void, Void, List<PasteRoom>> {

        @Override
        protected List<PasteRoom> doInBackground(Void... voids) {
            return database.pasteDao().getAll();
        }
    }

    private class GetPaste extends AsyncTask<Void, Void, PasteRoom> {

        private int id;

        private GetPaste(int id) {
            this.id = id;
        }

        @Override
        protected PasteRoom doInBackground(Void... voids) {
            return database.pasteDao().getById(id);
        }
    }

    private class InsertPaste extends AsyncTask<Void, Void, Void> {

        private PasteRoom pasteRoom;

        private InsertPaste(PasteRoom pasteRoom) {
            this.pasteRoom = pasteRoom;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.pasteDao().insert(pasteRoom);
            return null;
        }
    }

    private class DeletePaste extends AsyncTask<Void, Void, Void> {

        private PasteRoom pasteRoom;

        private DeletePaste(PasteRoom pasteRoom) {
            this.pasteRoom = pasteRoom;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.pasteDao().delete(pasteRoom);
            return null;
        }
    }

    private class UpdatePaste extends AsyncTask <Void,Void,Void>{

        private PasteRoom pasteRoom;

        private UpdatePaste(PasteRoom pasteRoom) {
            this.pasteRoom = pasteRoom;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.pasteDao().update(pasteRoom);
            return null;
        }
    }
}