package info.rayrojas.splashmenu.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import info.rayrojas.splashmenu.R;

public class HomeFragment extends Fragment {

  private HomeViewModel homeViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    homeViewModel =
        ViewModelProviders.of(this).get(HomeViewModel.class);
    View root = inflater.inflate(R.layout.fragment_home, container, false);
    final TextView textView = root.findViewById(R.id.text_home);
    homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });

    PusherOptions options = new PusherOptions();
    options.setCluster("us2");

    Pusher pusher = new Pusher("146d0e3321b4ed15fdcb", options);

    pusher.connect(new ConnectionEventListener() {
      @Override
      public void onConnectionStateChange(ConnectionStateChange change) {
        HomeFragment.this.getActivity().runOnUiThread(new Runnable() {
          public void run() {
        Toast.makeText(HomeFragment.this.getContext(),
            "Connexion", Toast.LENGTH_LONG).show();
          }
        });
        Log.i("Pusher", "State changed from " + change.getPreviousState() +
            " to " + change.getCurrentState());
      }

      @Override
      public void onError(String message, String code, Exception e) {
        HomeFragment.this.getActivity().runOnUiThread(new Runnable() {
          public void run() {
        Toast.makeText(HomeFragment.this.getContext(),
            "Error", Toast.LENGTH_LONG).show();
          }
        });
        Log.i("Pusher", "There was a problem connecting! " +
            "\ncode: " + code +
            "\nmessage: " + message +
            "\nException: " + e
        );
      }
    }, ConnectionState.ALL);

    Channel channel = pusher.subscribe("my-channel");

    channel.bind("my-event", new SubscriptionEventListener() {
      @Override
      public void onEvent(final PusherEvent event) {
        HomeFragment.this.getActivity().runOnUiThread(new Runnable() {
          public void run() {
            Toast.makeText(HomeFragment.this.getContext(),
            "Received: " + event.toString(),
            Toast.LENGTH_LONG).show();
          }
        });
        Log.i("Pusher", "Received event with data: " + event.toString());
      }
    });

    return root;
  }
}
