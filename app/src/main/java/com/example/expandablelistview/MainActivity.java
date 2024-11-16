package com.example.expandablelistview;

import android.os.Bundle;
import android.widget.ExpandableListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> groupList;
    HashMap<String, List<String>> childMap;
    CustomExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.expandableListView);

        // Prepare data for ExpandableListView
        prepareData();

        // Set up adapter
        adapter = new CustomExpandableListAdapter(this, groupList, childMap);
        expandableListView.setAdapter(adapter);

        // Set listeners (optional)
        expandableListView.setOnGroupExpandListener(groupPosition ->
                System.out.println("Group expanded: " + groupList.get(groupPosition))
        );

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            System.out.println("Child clicked: " + childMap.get(groupList.get(groupPosition)).get(childPosition));
            return false;
        });
    }

    private void prepareData() {
        groupList = new ArrayList<>();
        childMap = new HashMap<>();

        // Add group titles
        groupList.add("Vegetables");
        groupList.add("Fruits");
        groupList.add("Dairy");

        // Add children for each group
        List<String> vegetables = new ArrayList<>();
        vegetables.add("Tomato");
        vegetables.add("Potato");
        vegetables.add("Cabbage");

        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");

        List<String> dairy = new ArrayList<>();
        dairy.add("Milk");
        dairy.add("Cheese");
        dairy.add("Yogurt");

        // Map groups to children
        childMap.put(groupList.get(0), vegetables);
        childMap.put(groupList.get(1), fruits);
        childMap.put(groupList.get(2), dairy);
    }
}
