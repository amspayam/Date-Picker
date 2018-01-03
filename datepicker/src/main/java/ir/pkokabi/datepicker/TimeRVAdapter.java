package ir.pkokabi.datepicker;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ir.pkokabi.datepicker.databinding.ItemTimeBinding;

/**
 * Created by P.kokabi on 6/20/2016.
 */

class TimeRVAdapter extends RecyclerView.Adapter<TimeRVAdapter.ViewHolder> {

    private ArrayList<DateTimeM> timeList = new ArrayList<>();
    private TimeInterface timeInterface;

    TimeRVAdapter(ArrayList<DateTimeM> dateList, TimeInterface timeInterface) {
        this.timeList = dateList;
        this.timeInterface = timeInterface;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemTimeBinding binding;

        ViewHolder(ItemTimeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

            binding.getRoot().setOnClickListener(this);
        }

        public void bind(DateTimeM item) {
            binding.timeTv.setText(item.getDateTimeName());

            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            timeInterface.onTimeItemClick(getAdapterPosition());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTimeBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_time, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(timeList.get(position));
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    String getTime(int position) {
        return timeList.get(position).getDateTimeName();
    }

    public interface TimeInterface {

        void onTimeItemClick(int position);

    }

}
