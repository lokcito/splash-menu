package info.rayrojas.splashmenu.ui.nosotros;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import info.rayrojas.splashmenu.R;

public class NosotrosFragment extends Fragment {

  private NosotrosViewModel mViewModel;

  public static NosotrosFragment newInstance() {
    return new NosotrosFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {

    View root = inflater.inflate(R.layout.nosotros_fragment, container, false);

    return root;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this).get(NosotrosViewModel.class);
    // TODO: Use the ViewModel
  }

}
