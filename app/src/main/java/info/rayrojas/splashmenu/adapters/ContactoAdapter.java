package info.rayrojas.splashmenu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import info.rayrojas.splashmenu.R;
import info.rayrojas.splashmenu.models.Contacto;

public class ContactoAdapter extends ArrayAdapter<Contacto> {
  Context context;
  ImageLoader queue; //<-------- Añadir esta variable

  private class ViewHolder {
    TextView phone;
    TextView nickname;
    NetworkImageView image; //<-------- Añadir esta variable


    private ViewHolder() {
    }
  }
  public ContactoAdapter(Context context, List<Contacto> items, ImageLoader _queue) {
    super(context, 0, items);
    this.context = context;
    this.queue = _queue; //<---- Notar esa linea
  }
  public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    final Contacto rowItem = (Contacto) getItem(position);
    LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    if (convertView == null) {
      convertView = mInflater.inflate(R.layout.item_contacto, null);
      holder = new ViewHolder();
      holder.phone = (TextView) convertView.findViewById(R.id.phone);
      holder.nickname = (TextView) convertView.findViewById(R.id.nickname);
      holder.image = (NetworkImageView) convertView.findViewById(R.id.image);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    holder.phone.setText(rowItem.phone);
    holder.nickname.setText(rowItem.nickname);
    holder.image.setImageUrl(rowItem.urlImage, this.queue);
    return convertView;
  }
}