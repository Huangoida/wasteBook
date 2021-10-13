package com.example.finaltsest.viewModel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.finaltsest.MainActivity;
import com.example.finaltsest.R;
import com.example.finaltsest.activity.BookingActivity;
import com.example.finaltsest.bean.User;
import com.example.finaltsest.bean.WasteBook;
import com.example.finaltsest.utils.ActivitysBuilder;
import com.example.finaltsest.utils.CommonUtils;
import com.example.finaltsest.utils.DialogTuil;
import com.example.finaltsest.utils.ToastUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.itimetraveler.widget.pickerselector.TimeWheelPicker;

public class BookOutViewModel extends BaseViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private SimpleDateFormat mDateFormat;
    private DecimalFormat mAmountFormat = new DecimalFormat("0.00");
    private ObservableField<String> mDateText = new ObservableField<>();
    private ObservableField<String> mAmountText = new ObservableField<>();
    private ObservableField<String> mDesc = new ObservableField<>();
    private ObservableField<String> mType = new ObservableField<>();
    private int iconId = -1;
    private long mDate;
    private double mAmount;
    private Context context;
    private WasteBook wasteBookEdit;




    private BookingViewModel activityViewModel;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public BookOutViewModel(@NonNull Application application) {
        super(application);
        mDateFormat =new SimpleDateFormat(application.getResources().getString(R.string.date_format_y_m_d)+"HH:mm",Locale.getDefault());
        mDate = System.currentTimeMillis();
        mDateText.set(mDateFormat.format(new Date(mDate)));
    }


    /**
     * 键盘数字点击
     */
    public void onNumberClick(String number) {
        String amount = mAmountText.get();
        amount = TextUtils.isEmpty(amount) ? "" : amount;
        if ("0".equals(amount)) {
            amount = "";
        }
        amount += number;
        mAmountText.set(amount);
        mAmount = CommonUtils.string2float(amount, 0);
    }

    /**
     * 键盘删除点击
     */
    public void onDeleteClick() {
        String amount = mAmountText.get();
        amount = TextUtils.isEmpty(amount) ? "" : amount;
        if (!TextUtils.isEmpty(amount)) {
            amount = amount.substring(0, amount.length() - 1);
        }
        if (TextUtils.isEmpty(amount)) {
            amount = "0";
        }
        mAmountText.set(amount);
        mAmount = CommonUtils.string2float(amount, 0);
    }

    /**
     * 键盘清除点击
     */
    public void onClearClick() {
        mAmountText.set("0");
        mAmount=0;
    }
    /**
     * 键盘 . 点击
     */
    public void onDotClick() {
        String amount = mAmountText.get();
        amount = TextUtils.isEmpty(amount) ? "" : amount;
        if (!amount.contains(".")) {
            amount = amount + ".";
        }
        mAmountText.set(amount);
        mAmount = CommonUtils.string2float(amount, 0);
    }
    /**
     * 消费记录时间点击
     */
    public void onDateClick() {
        TimeWheelPicker picker = new TimeWheelPicker(this.context);
        picker.setOnTimeChangedListener(new TimeWheelPicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimeWheelPicker view, Calendar date) {
                mDateText.set(TimeWheelPicker.DEFAULT_DATE_FORMAT.format(date.getTime())+" " + TimeWheelPicker.DEFAULT_TIME_FORMAT.format(date.getTime()));
                setDate(date.getTime().getTime());
            }
        });
        DialogTuil.showDialog(this.context,"",picker);
    }


    /**
     * 确定点击
     */
    public void onEnterClick(Activity activity) {
        if (getAmountText().get().isEmpty() || iconId == -1) {
            ToastUtils.showToast("请输入完整信息");
        } else {
            Boolean wasteBookType = false;
            Log.e("getDesc", getDesc().get());
            User user = activityViewModel.getLoginUser().getValue();
            if (getType().get() == null){
                getType().set("");
            }
            if (wasteBookEdit != null) {
                wasteBookEdit.setAmount(mAmount);
                wasteBookEdit.setIcon(iconId);
                wasteBookEdit.setType(wasteBookType);
                wasteBookEdit.setNote(getType().get());
                wasteBookEdit.setTime(mDate);
                wasteBookEdit.save();
                // edit
            } else {
                WasteBook wasteBook = new WasteBook(user.getId(),wasteBookType,mAmount,iconId, getType().get(),mDate);
                wasteBook.save();
            }
            //activity.finish();
            ActivitysBuilder.build(context, MainActivity.class).startActivity();
        }
    }

    public MutableLiveData<Integer> getIndex() {
        return mIndex;
    }

    public void setIndex(MutableLiveData<Integer> mIndex) {
        this.mIndex = mIndex;
    }

    public SimpleDateFormat getDateFormat() {
        return mDateFormat;
    }

    public void setDateFormat(SimpleDateFormat mDateFormat) {
        this.mDateFormat = mDateFormat;
    }

    public DecimalFormat getAmountFormat() {
        return mAmountFormat;
    }

    public void setAmountFormat(DecimalFormat mAmountFormat) {
        this.mAmountFormat = mAmountFormat;
    }

    public ObservableField<String> getDateText() {
        return mDateText;
    }

    public void setDateText(ObservableField<String> mDateText) {
        this.mDateText = mDateText;
    }

    public void setmDateText(String mDateText){
        this.mDateText.set(mDateText);
    }

    public ObservableField<String> getAmountText() {
        return mAmountText;
    }

    public void setmAmountText(ObservableField<String> mAmountText) {
        this.mAmountText = mAmountText;
    }

    public void setAmountText(String mAmountText) {
        this.mAmountText.set(mAmountText);
    }


    public void setmAmountTextClear() {
        this.mAmountText.set("");

    }

    public ObservableField<String> getDesc() {
        if (mDesc.get() == null)
            mDesc.set("");
        return mDesc;
    }

    public void setDesc(ObservableField<String> mDesc) {
        this.mDesc = mDesc;
    }
    public void setmDest(String mDesc){
        this.mDesc.set(mDesc);
    }

    public ObservableField<String> getType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType.set(mType);
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long mDate) {
        this.mDate = mDate;
    }

    public double getAmount() {
        return this.mAmount;
    }

    public void setAmount(double mAmount) {
        this.mAmount= mAmount;
    }

    //pager data
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "index:" + input;
        }
    });

    public LiveData<String> getText() {
        return mText;
    }

    public BookingViewModel getActivityViewModel() {
        return activityViewModel;
    }

    public WasteBook getWasteBookEdit() {
        return wasteBookEdit;
    }

    public void setWasteBookEdit(WasteBook wasteBookEdit) {
        this.wasteBookEdit = wasteBookEdit;
    }

    public void setActivityViewModel(BookingViewModel activityViewModel) {
        this.activityViewModel = activityViewModel;
    }
}