package com.doda.project555.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.doda.project555.MainActivity;
import com.doda.project555.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;


public class AccountFragment extends Fragment {

    private static final int RC_SIGN_IN = 123;
    public static final String APP_PREFERENCES = "mySettings";

    public String user_name;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        View root1 = inflater.inflate(R.layout.nav_header_main, container, false);
        final SharedPreferences mySettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

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

        final NavigationView navigationView = root1.findViewById(R.id.nav_view);

        final EditText editText = root.findViewById(R.id.user_name);
        editText.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    // сохраняем текст, введенный до нажатия Enter в переменную
                    user_name = editText.getText().toString();
                    SharedPreferences.Editor ed = mySettings.edit();
                    ed.putString("NAME", user_name);
                    ed.apply();
                    MainActivity.setUserName(mySettings, navigationView);
                    return true;
                }
                return false;
            }
        });

        /*editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/
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
            Toast.makeText(getContext(), "СЛЫШЬ, МЫШЬ. ТЫ УЖЕ ЗАШЕЛ!", Toast.LENGTH_SHORT).show();
        }
    }

    public void set_user_name() {

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Pause", "onPause");
    }
}
