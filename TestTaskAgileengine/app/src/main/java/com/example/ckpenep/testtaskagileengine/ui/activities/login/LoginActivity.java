package com.example.ckpenep.testtaskagileengine.ui.activities.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ckpenep.testtaskagileengine.R;
import com.example.ckpenep.testtaskagileengine.ui.activities.splash.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @BindView(R.id.name)
    EditText mNameView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_button)
    Button mLoginButton;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;

    private AlertDialog mErrorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.logIn(mNameView.getText().toString(), mPasswordView.getText().toString());
            }
        });
    }

    @Override
    public void startLogIn() {
        toggleProgressVisibility(true);
    }

    @Override
    public void finishLogIn() {
        toggleProgressVisibility(false);
    }

    private void toggleProgressVisibility(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void hideFormError() {
        mNameView.setError(null);
        mPasswordView.setError(null);
    }

    @Override
    public void showFormError(Integer nameError, Integer passwordError) {
        mNameView.setError(nameError == null ? null : getString(nameError));
        mPasswordView.setError(passwordError == null ? null : getString(passwordError));
    }

    @Override
    public void successLogIn() {
        final Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}
