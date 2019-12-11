package gabrielfernandeslemos.android.controledegastos.activity;

import androidx.appcompat.app.AppCompatActivity;
import gabrielfernandeslemos.android.controledegastos.R;
import gabrielfernandeslemos.android.controledegastos.helper.DateCustom;
import gabrielfernandeslemos.android.controledegastos.model.Movimentação;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ReceitasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    Double despesaGerada;
    Double despesaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        campoData = (TextInputEditText) findViewById(R.id.editData);
        campoCategoria = (TextInputEditText) findViewById(R.id.editCategoria);
        campoDescricao = (TextInputEditText) findViewById(R.id.editDescricao);
        campoValor = (EditText) findViewById(R.id.editValor);

        //Preenche o campo data com a data atual
        campoData.setText(DateCustom.dataAtual());
    }

    public void salvarDespesa(){

        if (validarCamposDespesa()){
            Movimentação movimentação = new Movimentação();

            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());
            movimentação.setValor(valorRecuperado);
            movimentação.setCategoria(campoCategoria.getText().toString());
            movimentação.setDescricao(campoDescricao.getText().toString());
            movimentação.setData(campoData.getText().toString());
            movimentação.setTipo("d");

            despesaGerada = valorRecuperado;
            despesaTotal = despesaTotal + despesaGerada;
        }

    }

    public Boolean validarCamposDespesa(){

        String valor = campoValor.getText().toString();
        String categoria = campoCategoria.getText().toString();
        String descricao = campoDescricao.getText().toString();
        String data = campoData.getText().toString();

        if ( !valor.isEmpty() ){
            if ( !categoria.isEmpty() ){
                if ( !descricao.isEmpty() ){
                    if ( !data.isEmpty() ){
                        return true;
                    }else{
                        Toast.makeText(ReceitasActivity.this, "Data não foi preenchido!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else{
                    Toast.makeText(ReceitasActivity.this, "Descrição não foi preenchido!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(ReceitasActivity.this, "Categoria não foi preenchido!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(ReceitasActivity.this, "Valor não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
