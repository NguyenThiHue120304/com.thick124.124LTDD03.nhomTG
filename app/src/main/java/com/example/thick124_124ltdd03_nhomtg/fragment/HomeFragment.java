package com.example.appdulich.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appdulich.Activity.EntertainmentActivity;
import com.example.appdulich.Activity.HotelActivity;
import com.example.appdulich.Activity.MoveActivity;
import com.example.appdulich.Activity.SearchActivity;
import com.example.appdulich.Adapter.CityAdapter;
import com.example.appdulich.Adapter.TicketAdapter;
import com.example.appdulich.Api.ApiClient;
import com.example.appdulich.Api.ApiService;
import com.example.appdulich.CartActivity;
import com.example.appdulich.Model.ApplicationResponse;
import com.example.appdulich.Model.City;
import com.example.appdulich.Model.Tickets;
import com.example.appdulich.NotificationActivity;
import com.example.appdulich.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ArrayList<City> list;
    RecyclerView rvTicket;
    RecyclerView rvCity;
    LinearLayout khuvc, ksan, tk, dichuyen;
    TextView Test ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rvCity = v.findViewById(R.id.rcv_city);
        rvTicket = v.findViewById(R.id.rcv_list_ticket);
        initDataRvCity();
        //initDataRvTicket();
        CityAdapter cityAdapter = new CityAdapter(list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridManager = new GridLayoutManager(getActivity(), 2);
        rvTicket.setLayoutManager(gridManager);

        rvCity.setLayoutManager(linearLayoutManager);
        rvCity.setAdapter(cityAdapter);

        khuvc = v.findViewById(R.id.linearLayout2);

        khuvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EntertainmentActivity.class);
                startActivity(intent);
            }
        });

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<ApplicationResponse<List<Tickets>>> call = apiService.GetTickets();
        call.enqueue(new Callback<ApplicationResponse<List<Tickets>>>() {
            @Override
            public void onResponse(Call<ApplicationResponse<List<Tickets>>> call, Response<ApplicationResponse<List<Tickets>>> response) {
                if (response.isSuccessful()) {
                    ApplicationResponse<List<Tickets>>data = response.body();
                    TicketAdapter ticketAdapter = new TicketAdapter(data.data, getActivity());
                    rvTicket.setAdapter(ticketAdapter);

                } else {
                    Test.setText("Error into call api get data");
                    System.out.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ApplicationResponse<List<Tickets>>> call, Throwable t) {
                Test.setText("Error into call api get data" + t.getMessage());
                t.printStackTrace();
            }
        });

        ksan = v.findViewById(R.id.linearLayout3);
        ksan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotelActivity.class);
                startActivity(intent);
            }
        });

        tk = v.findViewById(R.id.linearLayout);
        tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        dichuyen = v.findViewById(R.id.linearLayout4);
        dichuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MoveActivity.class);
                startActivity(intent);
            }
        });

        ImageButton iconGioHang = v.findViewById(R.id.icon_giohang);
        ImageButton iconThongBao = v.findViewById(R.id.imageButton);

        iconGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });

        iconThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public void initDataRvCity(){
        list = new ArrayList<>();
        City city1 = new City(R.drawable.notifications, "Hà Nội"); list.add(city1);
        City city2 = new City(R.drawable.notifications, "Hồ Chí Minh"); list.add(city2);
        City city3 = new City(R.drawable.notifications, "Đà Nẵng"); list.add(city3);
        City city4 = new City(R.drawable.notifications, "Đà Lạt"); list.add(city4);
    }

//    public void initDataRvTicket(){
//        ticketArrayList = new ArrayList<>();
//        Ticket ticket1 = new Ticket(R.drawable.img_new4, "Vé cáp treo Bà Nà\n Hill_ Đà ...",4.7, 10000, 800);ticketArrayList.add(ticket1);
//        Ticket ticket2 = new Ticket(R.drawable.img_new6, "Vé Dà Nẵng\nDowntown.....",4.7, 870, 564);ticketArrayList.add(ticket2);
//        Ticket ticket3 = new Ticket(R.drawable.img_new3, "Vé xe lửa Hà\nNội - Lào Cai",4.7, 126, 309);ticketArrayList.add(ticket3);
//        Ticket ticket4 = new Ticket(R.drawable.img_new5, "Vé À Ố Show Ở\nNhà Hát Thàn...",4.7, 368, 599);ticketArrayList.add(ticket4);
//        Ticket ticket5 = new Ticket(R.drawable.img_new2, "Vé cáp treo Bà Nà\n Hill_ Đà ...",4.7, 1287, 760);ticketArrayList.add(ticket5);
//        Ticket ticket6 = new Ticket(R.drawable.img_new1, "Vé cáp treo Bà Nà\n Hill_ Đà ...",4.7, 1923, 900);ticketArrayList.add(ticket6);
//    }
}