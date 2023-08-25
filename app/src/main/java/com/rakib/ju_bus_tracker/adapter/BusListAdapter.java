package com.rakib.ju_bus_tracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rakib.ju_bus_tracker.R;
import com.rakib.ju_bus_tracker.model.Bus;

import java.util.List;

public class BusListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<Object> listRecyclerItem;

    public BusListAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.bus_list_item, parent, false);

        return new ItemViewHolder((layoutView));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Bus bus = (Bus) listRecyclerItem.get(position);

        itemViewHolder.busNo.setText(bus.getBusNo());
        itemViewHolder.regNo.setText(bus.getRegNo());
        itemViewHolder.route.setText(bus.getRoute());

        itemViewHolder.busListItem.setOnClickListener(view1 -> Toast.makeText(context, String.format("Current location of %s is %s.", bus.getBusNo(), bus.getRoute()), Toast.LENGTH_LONG).show());
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView busNo;
        private final TextView regNo;
        private final TextView route;
        private final CardView busListItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            busNo = itemView.findViewById(R.id.busNoTextView);
            regNo = itemView.findViewById(R.id.regNoTextView);
            route = itemView.findViewById(R.id.routeTextView);
            busListItem = itemView.findViewById(R.id.busListItemCardView);
        }
    }
}
