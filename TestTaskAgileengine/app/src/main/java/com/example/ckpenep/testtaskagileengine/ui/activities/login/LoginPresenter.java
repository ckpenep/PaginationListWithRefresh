package com.example.ckpenep.testtaskagileengine.ui.activities.login;

import android.os.Handler;
import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.testtaskagileengine.R;
import com.example.ckpenep.testtaskagileengine.constants.Constants;
import com.example.ckpenep.testtaskagileengine.utils.PrefUtils;

/**
 * Created by ckpenep on 23.03.2018.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    public LoginPresenter() {}

    public void logIn(final String name, String password) {

        Integer nameError = null;
        Integer passwordError = null;

        getViewState().hideFormError();

        if (TextUtils.isEmpty(name)) {
            nameError = R.string.error_field_required;
        }

        if (TextUtils.isEmpty(password)) {
            passwordError = R.string.error_invalid_password;
        }

        if (nameError != null || passwordError != null) {
            getViewState().showFormError(nameError, passwordError);
            return;
        }

        getViewState().startLogIn();

        try {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    PrefUtils.setPreference(Constants.TOKEN, "token");
                    PrefUtils.setPreference(Constants.USER_NAME, name);

                    getViewState().successLogIn();
                }
            }, 3000);
        } catch (Exception e) {
        }
    }

}
