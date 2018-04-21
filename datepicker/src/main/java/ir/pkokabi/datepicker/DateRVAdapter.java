package ir.pkokabi.datepicker;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ir.pkokabi.datepicker.databinding.ItemDateBinding;


/**
 * Created by P.kokabi on 6/20/2016.
 */

class DateRVAdapter extends RecyclerView.Adapter<DateRVAdapter.ViewHolder> {

    private ArrayList<DateTimeM> dateList;
    private DateInterface dateInterface;

    DateRVAdapter(ArrayList<DateTimeM> dateList, DateInterface dateInterface) {
        this.dateList = dateList;
        this.dateInterface = dateInterface;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemDateBinding binding;

        ViewHolder(ItemDateBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

            binding.getRoot().setOnClickListener(this);
        }

        void bind(DateTimeM item) {
            binding.dateTv.setText(item.getDateTimeName());

            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            dateInterface.onDateItemClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDateBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_date, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dateList.get(position));
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    String getDate(int position) {
        return dateList.get(position).getId();
    }

    String getDateString(int position) {
        return dateList.get(position).getDateTimeName();
    }

    public interface DateInterface {

        void onDateItemClick(int position);

    }

}
