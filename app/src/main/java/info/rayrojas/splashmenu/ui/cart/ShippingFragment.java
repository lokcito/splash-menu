package info.rayrojas.splashmenu.ui.cart;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.rayrojas.splashmenu.R;

public class ShippingFragment extends Fragment {

  private ShippingViewModel mViewModel;

  public static ShippingFragment newInstance() {
    return new ShippingFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.shipping_fragment, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this).get(ShippingViewModel.class);
    // TODO: Use the ViewModel
  }

}
