package gabrielfernandeslemos.android.controledegastos.activity;

import androidx.appcompat.app.AppCompatActivity;
import gabrielfernandeslemos.android.controledegastos.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha, campoNome;
    private Button botaoCadastrar;

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

    }
}
