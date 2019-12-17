package gabrielfernandeslemos.android.controledegastos.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import gabrielfernandeslemos.android.controledegastos.config.ConfiguracaoFirebase;
import gabrielfernandeslemos.android.controledegastos.R;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha, campoNome;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoEmail = (EditText) findViewById(R.id.emailCadastro);
        campoNome = (EditText) findViewById(R.id.nomeCadastro);
        campoSenha = (EditText) findViewById(R.id.senhaCadastro);
        botaoCadastrar = (Button) findViewById(R.id.botaoCadastro);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarCamposCadastro();
                usuario = new Usuario();
                usuario.setNome(campoNome.getText().toString());
                usuario.setEmail(campoEmail.getText().toString());
                usuario.setSenha(campoSenha.getText().toString());
                cadastrarUsuario();
            }
        });
    }

    public Boolean verificarCamposCadastro(){
        if ( !campoNome.getText().toString().isEmpty()){
            if ( !campoEmail.getText().toString().isEmpty()){
                if ( !campoSenha.getText().toString().isEmpty()){
                    return true;
                }else{
                    Toast.makeText(this, "Preencha um nome", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(this, "Preencha um nome", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(this, "Preencha um nome", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
//                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(CadastroActivity.this, PrincipalActivity.class));
                    finish();
                }else{

                    String excecao;
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Digite um email válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Essa conta já foi cadastrada";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuário " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
