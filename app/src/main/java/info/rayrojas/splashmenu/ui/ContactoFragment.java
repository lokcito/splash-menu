package info.rayrojas.splashmenu.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.rayrojas.splashmenu.R;
import info.rayrojas.splashmenu.helpers.QueueUtils;
import info.rayrojas.splashmenu.models.Contacto;

public class ContactoFragment extends Fragment {

  private ContactoViewModel mViewModel;
  public Contacto contactoObj;
  ImageLoader imageLoader;
  public static ContactoFragment newInstance() {
    return new ContactoFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.contacto_fragment, container, false);
    NetworkImageView image = root.findViewById(R.id.imageX);
    imageLoader = QueueUtils.getInstance(this.getContext()).getImageLoader();

    image.setImageUrl(this.contactoObj.urlImage, imageLoader);

    TextView name = root.findViewById(R.id.name);
    name.setText(this.contactoObj.nickname);

    return root;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this).get(ContactoViewModel.class);
    // TODO: Use the ViewModel
  }

}
