package jrl.jroldan.dynamicfragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Created by usuario on 16/11/17.
 */

public class FragmentA extends Fragment {

    public static final String TAG = "frmA";
    private FragmentAListener mCallBack;
    private EditText edtMessage;
    private Button btnSize;
    private SeekBar skbSize;

    /** Se define la interfaz que servirá de contrato entre el Fragment y la Activity **/

    public interface FragmentAListener {
        void onFragmentAEvent(String message, int size);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("FrmAOnAttach", "Ejecutando onAttach() del FragmentA");
        try {
            mCallBack=(FragmentAListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FragmentAListener");
        }
    }

    /**
     * Este método sólo funciona desde la API 23 en adelante. Si se ejecuta en una API inferior NO DA ERROR; PERO NO FUNCIONA LA COMUNICACIÓN Activity-Fragment
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("FrmAOnAttach", "Ejecutando onAttach() del FragmentA");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("FrmAOnCreateView", "Ejecutando onCreateView() del FragmentA");
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        edtMessage = view.findViewById(R.id.edtMessage);
        btnSize = view.findViewById(R.id.btnSize);
        skbSize = view.findViewById(R.id.skbSize);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("FrmAOnViewCreated", "Ejecutando onViewCreate() del FragmentA");
        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onFragmentAEvent(edtMessage.getText().toString(),skbSize.getProgress());
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FrmAOnCreate", "Ejecutando onCreate() del FragmentA");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("FrmAOnDetach", "Ejecutando onDetach() del FragmentA");
        mCallBack = null;
    }
}
