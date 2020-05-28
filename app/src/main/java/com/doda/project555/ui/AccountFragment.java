package com.doda.project555.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.doda.project555.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import static com.doda.project555.MainActivity.APP_PREFERENCES;

public class AccountFragment extends Fragment {

    private static final int RC_SIGN_IN = 123;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);

        final SharedPreferences mySettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final TextView header = this.getActivity().findViewById(R.id.nav_user_name);
        final TextView header1 = this.getActivity().findViewById(R.id.nav_user_city);
        final TextView header2 = this.getActivity().findViewById(R.id.nav_user_mail);
        final EditText fio = root.findViewById(R.id.user_name);
        final EditText city = root.findViewById(R.id.user_city);
        final EditText mail = root.findViewById(R.id.user_mail);
        final FloatingActionButton button1 = root.findViewById(R.id.sign_out_button);
        final FloatingActionButton button2 = root.findViewById(R.id.delete_button);
        final FloatingActionButton button3 = root.findViewById(R.id.login_button);

        View.OnClickListener oclBtnOk1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        };
        View.OnClickListener oclBtnOk2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
                mySettings.edit().putString("FIO", null);
                header.setText("ФИО");
                fio.setText(null);
            }
        };

        View.OnClickListener oclBtnOk3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        };

        button1.setOnClickListener(oclBtnOk1);
        button2.setOnClickListener(oclBtnOk2);
        button3.setOnClickListener(oclBtnOk3);

        fio.setText(mySettings.getString("FIO", null));
        fio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                header.setText(fio.getText());
                SharedPreferences.Editor ed = mySettings.edit();
                ed.putString("FIO", String.valueOf(fio.getText()));
                ed.apply();
            }
        });

        city.setText(mySettings.getString("CITY", null));
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                header1.setText(city.getText());
                SharedPreferences.Editor ed = mySettings.edit();
                ed.putString("CITY", String.valueOf(city.getText()));
                ed.apply();
            }
        });

        mail.setText(mySettings.getString("MAIL", null));
        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                header2.setText(mail.getText());
                SharedPreferences.Editor ed = mySettings.edit();
                ed.putString("MAIL", String.valueOf(mail.getText()));
                ed.apply();
            }
        });
        return root;
    }

    private void signOut() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            AuthUI.getInstance()
                    .signOut(getContext())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getContext(), "НУ ПОКА",Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getContext(), "МОЖЕТ ЛУЧШЕ ЗАЙДЕШЬ ВНАЧАЛЕ?", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete() {
        AuthUI.getInstance()
                .delete(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "ТЫ ЕЩЕ И АКК УДАЛИЛ",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signIn() {
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.AnonymousBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        } else {
            Toast.makeText(getContext(), "СЛЫШЬ, МЫШЬ, ТЫ УЖЕ ЗАШЕЛ!", Toast.LENGTH_SHORT).show();
        }
    }

}
