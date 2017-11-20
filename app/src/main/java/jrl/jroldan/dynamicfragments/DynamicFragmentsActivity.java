package jrl.jroldan.dynamicfragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class DynamicFragmentsActivity extends Activity implements FragmentA.FragmentAListener {

    // Se utiliza la libería app antigua

    private Fragment frmA;
    private Fragment frmB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragments);
        FragmentManager fragmentManager = getFragmentManager();

        frmA = fragmentManager.findFragmentByTag(FragmentA.TAG);
        if(frmA == null) {
            frmA = new FragmentA();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(android.R.id.content, frmA);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onFragmentAEvent(String message, int size) {
        frmB = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putInt("size", size);
        // Con el método setArguments se pasan la información que necesita el argumentos
        frmB.setArguments(bundle);
        // A continuación, se empieza la transacción de frmA a frmB
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, frmB);
        fragmentTransaction.commit();
    }
}
