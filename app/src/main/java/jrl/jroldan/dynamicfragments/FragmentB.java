package jrl.jroldan.dynamicfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by usuario on 16/11/17.
 */

public class FragmentB extends Fragment {

    public static final String TAG = "frmB";
    private TextView txvMessage;
    private String message;
    private int size;

    /**
     * PATRÓN FACTORY, que es una simplificación del patrón BUILDER (patrón CREATOR)
     * @param bundle
     * @return
     */
    public static Fragment newInstance(Bundle bundle) {
        FragmentB fragmentB = new FragmentB();
        if(bundle != null) {
            fragmentB.setArguments(bundle);
        }
        return fragmentB;
    }

    /**
     * ATENCIÓN: Para que el estado dinámico de un fragment sea permanente ante un cambio de
     * configuración usar setRetainInstance();
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // USAR CON PRUDENCIA: Guarda todos los datos SIEMPRE y evita
        // savedInstanceState y onViewStateRestored
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("FrmBOnCreateView", "Ejecutando onCreateView() del FragmentB");
        View view = inflater.inflate(R.layout.fragment_b,container,false);
        txvMessage = view.findViewById(R.id.txvMessage);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("FrmBOnViewCreated", "Ejecutando onViewCreated() del FragmentB");
        Bundle bundle = getArguments();
        if(savedInstanceState==null) // No hay cambio de configuración. Se ejecuta por primera vez
        {
            if (bundle != null) {   // Si hay parámetros se asignan
                message = bundle.getString("message");
                size = bundle.getInt("size");
                //changeTextAndSize(bundle.getString("message"), bundle.getInt("size"));
            }
        }
        changeTextAndSize(message, size);
    }

    public void changeTextAndSize(String message, int size) {
        txvMessage.setText(message);
        txvMessage.setTextSize(size);
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("FrmBOnSaveInstanceState", "Ejecutando onSaveInstanceState() del FragmentB");
        outState.putString("message", txvMessage.getText().toString());
        outState.putInt("size", txvMessage.getTextSize());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("FrmBOnViewStateRestored", "Ejecutando onViewStateRestored() del FragmentB");
        if(savedInstanceState != null) {
            txvMessage.setText(savedInstanceState.getString("message"));
            txvMessage.setTextSize(savedInstanceState.getInt("size"));
        }
    }
    */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("FrmBOnActivityCreated", "Ejecutando onActivityCreated() del FragmentB");
        if(savedInstanceState!=null) {
            txvMessage.setTextSize(savedInstanceState.getFloat("size"));
            txvMessage.setText(savedInstanceState.getString("message"));
        }
    }
}
