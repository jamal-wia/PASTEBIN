package wia.soft.official.pastebin.mvp.auth.login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wia.soft.official.pastebin.data.global.DataManager;
import wia.soft.official.pastebin.mvp.global.MvpPresenter;
import wia.soft.official.pastebin.utils.AuthUtils;

public class LoginPresenter extends MvpPresenter<LoginView> {
    private DataManager dataManager;

    private Call<ResponseBody> loginCall;

    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void login(String login, String password) {
        if (getView() != null && isValidFields(login, password)) {
            getView().showProgress(true);
            // Выполняет авторизацию
            loginCall = dataManager.login(login, password);
            loginCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (getView() != null) getView().showProgress(false);
                    if (response.isSuccessful() && getView() != null) {
                        try {
                            String token = response.body().string();
                            switch (token) {
                                case "Bad API request, use POST request, not GET":
                                    getView().showMessage("Bad API request, use POST request, not GET");
                                    break;
                                case "Bad API request, invalid api_dev_key":
                                    getView().showMessage("Bad API request, invalid api_dev_key");
                                    break;
                                case "Bad API request, invalid login":
                                    getView().showMessage("Bad API request, invalid login");
                                    break;
                                case "Bad API request, account not active":
                                    getView().showMessage("Bad API request, account not active");
                                    break;
                                case "Bad API request, invalid POST parameters":
                                    getView().showMessage("Bad API request, invalid POST parameters");
                                    break;
                                default:
                                    dataManager.setToken(token);
                                    getView().navigateToMain();
                                    break;
                            }
                        } catch (Exception e) {

                        }
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (getView() != null) {
                        getView().showProgress(false);
                        getView().showMessage("No connection");
                    }
                }
            });
        }
    }

    private Boolean isValidFields(String login, String password) {
        boolean isValid = true;

        if (AuthUtils.isEmptyFields(login)) {
            isValid = false;
            getView().showEmptyLoginError();
        }
        if (AuthUtils.isEmptyFields(password)) {
            isValid = false;
            getView().showEmptyPasswordError();
        }
        return isValid;
    }

    public void cancelRequest() {
        if (loginCall != null) loginCall.cancel();
    }
}