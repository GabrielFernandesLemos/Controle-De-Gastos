package gabrielfernandeslemos.android.controledegastos.activity;

import androidx.appcompat.app.AppCompatActivity;
import gabrielfernandeslemos.android.controledegastos.R;
import gabrielfernandeslemos.android.controledegastos.helper.DateCustom;
import gabrielfernandeslemos.android.controledegastos.model.Movimentação;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class ReceitasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;

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
        Movimentação movimentação = new Movimentação();
        movimentação.setValor(Double.parseDouble(campoValor.getText().toString()));
        movimentação.setCategoria(campoCategoria.getText().toString());
        movimentação.setData(campoData.getText().toString());
        movimentação.setTipo("d");
    }
}
