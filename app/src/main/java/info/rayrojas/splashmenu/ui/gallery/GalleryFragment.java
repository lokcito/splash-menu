package info.rayrojas.splashmenu.ui.gallery;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import info.rayrojas.splashmenu.R;
import info.rayrojas.splashmenu.adapters.ContactoAdapter;
import info.rayrojas.splashmenu.helpers.QueueUtils;
import info.rayrojas.splashmenu.models.Contacto;
import info.rayrojas.splashmenu.ui.ContactoFragment;
import info.rayrojas.splashmenu.ui.nosotros.NosotrosFragment;

public class GalleryFragment extends Fragment {

  private GalleryViewModel galleryViewModel;
  Button btnGallery;
  ListView lista;
  ContactoAdapter contactoAdapter;
  ArrayList<Contacto> datos;
  QueueUtils.QueueObject encolador;
  ImageLoader encoladorImagenes;
  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    galleryViewModel =
        ViewModelProviders.of(this).get(GalleryViewModel.class);

    View root = inflater.inflate(R.layout.fragment_gallery, container, false);

//    final TextView textView = root.findViewById(R.id.text_gallery);
    
//    btnGallery = root.findViewById(R.id.btnGallery);

//    btnGallery.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        Toast.makeText(GalleryFragment.this.getContext(),
//            ":D-----", Toast.LENGTH_LONG).show();
//      }
//    });

    /////////////////////////////////////
    encolador = QueueUtils.getInstance(this.getContext());
    encoladorImagenes = encolador.getImageLoader();
    datos = new ArrayList<>();

    Contacto.injectContactsFromCloud(encolador, datos, this);

    contactoAdapter = new ContactoAdapter(this.getContext(), datos, encoladorImagenes);
    lista = root.findViewById(R.id.lista);
    lista.setAdapter(contactoAdapter);
    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContactoFragment o = new ContactoFragment();
        o.contactoObj = datos.get(position);
        getFragmentManager().beginTransaction()
            .replace(R.id.nav_host_fragment,
                o)
            .addToBackStack(null).commit();
      }
    });

    /////////////////////////////////////
//    galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//      @Override
//      public void onChanged(@Nullable String s) {
//        textView.setText(s);
//      }
//    });
    return root;
  }

  public void refreshList() {
    if ( this.contactoAdapter != null ) {
      this.contactoAdapter.notifyDataSetChanged();
    }
  }
  public void refresh() {

  }
}
