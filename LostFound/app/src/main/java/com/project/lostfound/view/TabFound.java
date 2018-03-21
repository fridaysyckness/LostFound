package com.project.lostfound.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.project.lostfound.R;
import com.project.lostfound.adapter.ReportAdapter;
import com.project.lostfound.database.LostFoundDatabase;
import com.project.lostfound.model.Report;

import java.util.ArrayList;
import java.util.List;

public class TabFound extends Fragment implements SearchView.OnQueryTextListener {

    ReportAdapter reportAdapter;
    List<Report> reportList;
    RecyclerView rv;
    MenuItem item;
    LinearLayoutManager myLayoutManager;

    public TabFound() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        rv = rootView.findViewById(R.id.rv_recycler_view);
        myLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(myLayoutManager);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LostFoundDatabase databaseHandler = new LostFoundDatabase(getActivity());
        reportList = databaseHandler.getReportFound();

        reportAdapter = new ReportAdapter(getActivity(), reportList);
        rv.setAdapter(reportAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

        item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                reportAdapter.setFilter(reportList);
                return true; // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true; // Return true to expand action view
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Report> filterList = filter(reportList, newText);

        reportAdapter.setFilter(filterList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<Report> filter(List<Report> list, String query) {
        query = query.toLowerCase();final List<Report> filterList = new ArrayList<>();
        for (Report report : list) {
            final String text, details, location, brand, category, contactInformation, remarks;
            text = report.getItemname().toLowerCase();
            details = report.getDetails().toLowerCase();
            location = report.getLocation().toLowerCase();
            brand = report.getBrand().toLowerCase();
            category = report.getCategory().toLowerCase();
            contactInformation = report.getContactinformation().toLowerCase();
            remarks = report.getRemarks().toLowerCase();

            if (text.contains(query) || details.contains(query) || location.contains(query) || brand.contains(query) || category.contains(query) || contactInformation.contains(query) || remarks.contains(query)) {
                filterList.add(report);
            }
        }
        return filterList;
    }
}