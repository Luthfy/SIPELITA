package id.digilabyte.sipelita.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.adapter.SlideBannerAdapter;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.model.Contact;
import id.digilabyte.sipelita.model.response.BannerResponse;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import id.digilabyte.sipelita.ui.BangunanActivity;
import id.digilabyte.sipelita.ui.HargaActivity;
import id.digilabyte.sipelita.ui.HistoryActivity;
import id.digilabyte.sipelita.ui.PelatihanActivity;
import id.digilabyte.sipelita.ui.PemesananActivity;
import id.digilabyte.sipelita.ui.TransaksiActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {

    private LinearLayout btnCallAction, btnPemesanaAction, btnTransaksiAction;
    private LinearLayout btnPelatihan, btnKamar, btnKelas, btnAula, btnHarga, btnHistory;
    private CardView cardViewBanner, cardViewBanner2;
    private ViewPager viewPager, viewPager2;
    String noTelpon;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        btnCallAction       = view.findViewById(R.id.btn_call_action);
        btnPemesanaAction   = view.findViewById(R.id.btn_pemesanan_action);
        btnTransaksiAction  = view.findViewById(R.id.btn_transaksi_action);


        btnPelatihan    = view.findViewById(R.id.btn_pelatihan);
        btnKamar        = view.findViewById(R.id.btn_kamar);
        btnKelas        = view.findViewById(R.id.btn_kelas);
        btnAula         = view.findViewById(R.id.btn_aula);
        btnHarga        = view.findViewById(R.id.btn_harga);
        btnHistory      = view.findViewById(R.id.btn_history);
        cardViewBanner  = view.findViewById(R.id.card_view_small_banner);
        cardViewBanner2 = view.findViewById(R.id.card_view_small_banner_2);
        viewPager       = view.findViewById(R.id.vp_small_banner);
        viewPager2      = view.findViewById(R.id.vp_small_banner_2);

        btnCallAction.setOnClickListener(this);
        btnPemesanaAction.setOnClickListener(this);
        btnTransaksiAction.setOnClickListener(this);

        btnPelatihan.setOnClickListener(this);
        btnKamar.setOnClickListener(this);
        btnKelas.setOnClickListener(this);
        btnAula.setOnClickListener(this);
        btnHarga.setOnClickListener(this);
        btnHistory.setOnClickListener(this);

        bannerRequest();
        banner2Request();
        contectRequest();

        return view;
    }

    private void banner2Request() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<BannerResponse> bannerDocumentationResponseCall = api.bannerResponseCall("documentation");

        bannerDocumentationResponseCall.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                ArrayList<String> list = new ArrayList<>();
                if (response.body() != null) {
                    if (response.body().getStatus().equals("success")) {
                        list = response.body().getData().getBanners();
                    }
                    setImageDocumentation(list);
                }
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {

            }
        });
    }

    private void setImageDocumentation(ArrayList<String> list) {
        Log.d("_banner_documentation", String.valueOf(list.size()));
        NUM_PAGES = list.size();
        if (NUM_PAGES > 0)  {
            cardViewBanner2.setVisibility(View.VISIBLE);
            SlideBannerAdapter adapter = new SlideBannerAdapter(Objects.requireNonNull(getContext()), list);
            viewPager2.setAdapter(adapter);

            final Handler handler = new Handler();
            final Runnable Update = () -> {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager2.setCurrentItem(currentPage++, true);
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 3000, 3000);
        }
    }

    private void bannerRequest() {

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<BannerResponse> bannerResponseCall = api.bannerResponseCall("small");

        bannerResponseCall.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(@NotNull Call<BannerResponse> call, @NotNull Response<BannerResponse> response) {
                ArrayList<String> list = new ArrayList<>();
                if (response.body() != null) {
                    if (response.body().getStatus().equals("success")) {
                        list = (response.body().getData().getBanners());
                    }
                    setImageSlider(list);
                }
            }

            @Override
            public void onFailure(@NotNull Call<BannerResponse> call, @NotNull Throwable t) {
                Log.e("_banner_error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    private void setImageSlider(ArrayList<String> list) {
        Log.d("_banner_slide", String.valueOf(list.size()));
        NUM_PAGES =list.size();
        if (NUM_PAGES > 0)  {
            cardViewBanner.setVisibility(View.VISIBLE);
            SlideBannerAdapter adapter = new SlideBannerAdapter(Objects.requireNonNull(getContext()), list);
            viewPager.setAdapter(adapter);

            final Handler handler = new Handler();
            final Runnable Update = () -> {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 3000, 3000);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_call_action:
                callAction();
                break;
            case R.id.btn_pemesanan_action:
                pemesananAction();
                break;
            case R.id.btn_transaksi_action:
                transaksiAction();
                break;
            case R.id.btn_pelatihan:
                pelatihanAction();
                break;
            case R.id.btn_kamar:
                kamarAction();
                break;
            case R.id.btn_kelas:
                kelasAction();
                break;
            case R.id.btn_aula:
                aulaAction();
                break;
            case R.id.btn_harga:
                hargaAction();
                break;
            case R.id.btn_history:
                historyAction();
                break;
        }
    }

    private void callAction() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", this.noTelpon, null));
        startActivity(intent);
    }

    private void pemesananAction() {
        Intent intent = new Intent(getActivity(), PemesananActivity.class);
        startActivity(intent);
    }

    private void transaksiAction() {
        Intent intent = new Intent(getActivity(), TransaksiActivity.class);
        startActivity(intent);
    }

    private void pelatihanAction() {
        Intent intent = new Intent(getActivity(), PelatihanActivity.class);
        startActivity(intent);
    }

    private void kamarAction() {
        Intent intent = new Intent(getActivity(), BangunanActivity.class);
        intent.putExtra("TYPE", "bedroom");
        startActivity(intent);
    }

    private void kelasAction() {
        Intent intent = new Intent(getActivity(), BangunanActivity.class);
        intent.putExtra("TYPE", "classroom");
        startActivity(intent);
    }

    private void aulaAction() {
        Intent intent = new Intent(getActivity(), BangunanActivity.class);
        intent.putExtra("TYPE", "auditorium");
        startActivity(intent);
    }

    private void hargaAction() {
        Intent intent = new Intent(getActivity(), HargaActivity.class);
        startActivity(intent);
    }

    private void historyAction() {
        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        startActivity(intent);
    }

    private void contectRequest() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<Contact> contactResponseCall = api.contactResponseCall();

        contactResponseCall.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(@NotNull Call<Contact> call, @NotNull Response<Contact> response) {
                assert response.body() != null;
                noTelpon = response.body().getTelephone();
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

            }
        });
    }

}
