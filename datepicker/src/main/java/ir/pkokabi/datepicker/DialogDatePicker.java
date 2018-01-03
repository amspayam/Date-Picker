package ir.pkokabi.datepicker;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import ir.pkokabi.datepicker.databinding.DialogDateBinding;

public class DialogDatePicker extends AppCompatDialog implements DatePickerViewInterface, View.OnClickListener
        , DateRVAdapter.DateInterface, TimeRVAdapter.TimeInterface {

    private Context context;
    private DialogDateBinding binding;
    private DatePickerInterface datePickerInterface;
    private LinearLayoutManager layoutManagerDate, layoutManagerTime;
    private LinearSnapHelper snapHelperDate, snapHelperTime;
    private DateRVAdapter dateRVAdapter;
    private TimeRVAdapter timeRVAdapter;
    private int indexDateFromToday, startTime, endTime;

    public DialogDatePicker(Context context, int indexDateFromToday, int startTime, int endTime
            , DatePickerInterface datePickerInterface) {
        super(context);
        this.context = context;
        this.indexDateFromToday = indexDateFromToday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.datePickerInterface = datePickerInterface;
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_date, null, true);
        setContentView(binding.getRoot());

        setCancelable(false);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().getAttributes().windowAnimations = R.style.DialogAnimationDatePicker;
            getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER);
        }

        prepareDate();
        prepareTime();

        binding.confirmACBtn.setOnClickListener(this);
        binding.cancelACBtn.setOnClickListener(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.dateTimeTv.setText(getDateTime());
            }
        }, 1000);

    }

    @Override
    public void prepareDate() {
        /*Date*/
        snapHelperDate = new LinearSnapHelper();
        layoutManagerDate = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        binding.dateRv.setHasFixedSize(true);
        binding.dateRv.setLayoutManager(layoutManagerDate);
        snapHelperDate.attachToRecyclerView(binding.dateRv);
        dateRVAdapter = new DateRVAdapter(new DateTimeM().getDateList(indexDateFromToday), this);
        binding.dateRv.addItemDecoration(new ItemHorizontalDecoration(context));
        binding.dateRv.setAdapter(dateRVAdapter);
        binding.dateRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                synchronized (this) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        binding.dateTimeTv.setText(getDateTime());
                    }
                }
            }
        });
    }

    @Override
    public void prepareTime() {
        snapHelperTime = new LinearSnapHelper();
        layoutManagerTime = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.timeRv.setHasFixedSize(true);
        binding.timeRv.setLayoutManager(layoutManagerTime);
        snapHelperTime.attachToRecyclerView(binding.timeRv);
        timeRVAdapter = new TimeRVAdapter(new DateTimeM().getTimeList(startTime, endTime), this);
        binding.timeRv.addItemDecoration(new ItemVerticalDecoration(context));
        binding.timeRv.setAdapter(timeRVAdapter);
        binding.timeRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                synchronized (this) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        binding.dateTimeTv.setText(getDateTime());
                    }
                }
            }
        });
    }

    @Override
    public String getDate() {
        View view = snapHelperDate.findSnapView(layoutManagerDate);
        return dateRVAdapter.getDate(binding.dateRv.getChildAdapterPosition(view));
    }

    @Override
    public String getDateTime() {
        View view = snapHelperDate.findSnapView(layoutManagerDate);
        return dateRVAdapter.getDateString(binding.dateRv.getChildAdapterPosition(view))
                + " ساعت " + getTime();
    }

    @Override
    public String getTime() {
        View view = snapHelperTime.findSnapView(layoutManagerTime);
        return timeRVAdapter.getTime(binding.timeRv.getChildAdapterPosition(view));
    }

    @Override
    public int getHour() {
        View view = snapHelperTime.findSnapView(layoutManagerTime);
        return Integer.parseInt(timeRVAdapter.getTime(binding.timeRv.getChildAdapterPosition(view)).split(":")[0]);
    }

    @Override
    public int getMinute() {
        View view = snapHelperTime.findSnapView(layoutManagerTime);
        return Integer.parseInt(timeRVAdapter.getTime(binding.timeRv.getChildAdapterPosition(view)).split(":")[1]);
    }

    @Override
    public int dateDifference() {
        View view = snapHelperDate.findSnapView(layoutManagerDate);
        return binding.dateRv.getChildAdapterPosition(view);
    }

    @Override
    public void onDateItemClick(int position) {
    }

    @Override
    public void onTimeItemClick(int position) {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.confirmACBtn) {
            datePickerInterface.onDateSelect(getDate(), getDateTime(), getTime(), getHour(), getMinute(), dateDifference());
            dismiss();
        } else if (view.getId() == R.id.cancelACBtn)
            dismiss();
    }

    public interface DatePickerInterface {
        void onDateSelect(String date, String dateTime, String time, int hour, int minute, int dateDifference);
    }

}
