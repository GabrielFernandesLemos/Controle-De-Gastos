package gabrielfernandeslemos.android.controledegastos.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import gabrielfernandeslemos.android.controledegastos.R;
import gabrielfernandeslemos.android.controledegastos.config.ConfiguracaoFirebase;
import gabrielfernandeslemos.android.controledegastos.model.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private FirebaseAuth autenticacao;
    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.emailLogin);
        campoSenha = findViewById(R.id.senhaLogin);
        botaoEntrar = findViewById(R.id.botaoLogin);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarCampos();
                usuario.setEmail(campoEmail.getText().toString());
                usuario.setSenha(campoSenha.getText().toString());
                validarLogin();
                abrirTelaPrincipal();
            }
        });

    }

    public Boolean verificarCampos(){
        if ( !campoEmail.getText().toString().isEmpty()){
            if ( !campoSenha.getText().toString().isEmpty()){
                return true;
            }else{
                Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(this, "Preencha um nome", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful() ){
                    Toast.makeText(LoginActivity.this, "Sucesso ao fazer login", Toast.LENGTH_SHORT).show();

                }else{
                    String excecao;
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não está cadastrado";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Email e senha não correspondem a um usuário cadastrado";
                    } catch (Exception e) {
                        excecao = "Erro ao fazer login" + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
    }
}
