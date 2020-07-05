package info.rayrojas.splashmenu.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.rayrojas.splashmenu.helpers.QueueUtils;
import info.rayrojas.splashmenu.ui.gallery.GalleryFragment;

public class Contacto {
  public int id;
  public String phone;
  public String nickname;
  public  String urlImage;
  public Contacto(int _id, String _phone, String _nickname, String _urlImage) {
    this.id = _id;
    this.phone = _phone;
    this.nickname = _nickname;
    this.urlImage = _urlImage;
  }
  public static ArrayList getCollection() {
    ArrayList<Contacto> collection = new ArrayList<>();
    collection.add(new Contacto(0, "981999923", "Bichito", ""));
    collection.add(new Contacto(0, "9859913923", "Plaga", ""));
    collection.add(new Contacto(0, "981914213", "Libelula", ""));
    collection.add(new Contacto(0, "981914213", "Alcachofa", ""));
    collection.add(new Contacto(0, "981999923", "Bichito", ""));
    collection.add(new Contacto(0, "9859913923", "Plaga", ""));
    collection.add(new Contacto(0, "981914213", "Libelula", ""));
    collection.add(new Contacto(0, "981914213", "Alcachofa", ""));
    collection.add(new Contacto(0, "981999923", "Bichito", ""));
    collection.add(new Contacto(0, "9859913923", "Plaga", ""));
    collection.add(new Contacto(0, "981914213", "Libelula", ""));
    collection.add(new Contacto(0, "981914213", "Alcachofa", ""));
    return collection;
  }

  public static void postContactFromLocal(final QueueUtils.QueueObject o,
                                          final Contacto contacto,
                                          final GalleryFragment _interface) {

    String url = "http://192.168.56.101:8200/api/auth/disciplinas";
    url = "https://fipo.equisd.com/api/products/new.json";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            int x = 0;
            x++;
            //Aqui el codigo cuando llega la respuesta

//            try {
//
//            } catch (JSONException e) {
//              e.printStackTrace();
//            }
          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            // Aqui el codigo para el manejo de errores.

            try {
              String responseBody = new String(error.networkResponse.data, "utf-8");
              JSONObject data = new JSONObject(responseBody);
              JSONArray errors = data.getJSONArray("errors");
              JSONObject jsonMessage = errors.getJSONObject(0);
              String message = jsonMessage.getString("message");
              int a = 0;
              a++;
            } catch (JSONException e) {
              int a = 0;
              a++;
            } catch (UnsupportedEncodingException errorr) {
              int a = 0;
              a++;
            }
          }
        }){
      @Override
      public String getBodyContentType() {
        return "application/json";
      }

      @Override
      protected Map<String,String> getParams(){
        //Datos a enviar
        Map<String,String> params = new HashMap<String, String>();
        params.put("name", "Bichito Enciso");
        params.put("precio", "1");
        params.put("avatar", "----");

        return params;
      }
    };

    o.addToRequestQueue(stringRequest);

  }

  public static void injectContactFromCloud(final QueueUtils.QueueObject o,
                                            final Contacto contacto,
                                            final GalleryFragment _interface) {
    String url = "https://reqres.in/api/users/" + contacto.id;
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        (Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {

              @Override
              public void onResponse(JSONObject response) {
                if (response.has("data")) {

                  try {
                    JSONObject objecto = response.getJSONObject("data");
                    contacto.phone = objecto.getString("first_name");
                    contacto.nickname = objecto.getString("last_name");
                    contacto.urlImage = objecto.getString("avatar");
                  } catch (JSONException e) {
                    //llega aca cuando hay un error dentro Try
                    e.printStackTrace();
                  }
                  _interface.refresh(); // Esta función debemos implementarla
                  // en nuestro activity
                }
              }
            }, new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError error) {

          }
        });
    o.addToRequestQueue(jsonObjectRequest);
  }

  public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                             final ArrayList<Contacto> contactos,
                                             final GalleryFragment _interface) {
    String url = "http://fipo.equisd.com/api/users.json";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        (Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {

              @Override
              public void onResponse(JSONObject response) {
                if (response.has("objects")) {

                  try {
                    JSONArray list = response.getJSONArray("objects");
                    for (int i=0; i < list.length(); i++) {
                      JSONObject o = list.getJSONObject(i);
                      contactos.add(new Contacto(o.getInt("id"),
                          o.getString("first_name"),
                          o.getString("last_name"), o.getString("avatar")));
                    }

                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                  _interface.refreshList(); // Esta función debemos implementarla
                  // en nuestro activity
                }
              }
            }, new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError error) {

          }
        });
    o.addToRequestQueue(jsonObjectRequest);
  }


}