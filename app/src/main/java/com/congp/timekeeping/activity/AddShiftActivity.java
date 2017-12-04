package com.congp.timekeeping.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.congp.timekeeping.DatabaseHandler;
import com.congp.timekeeping.R;
import com.congp.timekeeping.common.Contans;
import com.congp.timekeeping.data.Shift;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import picker.ugurtekbas.com.Picker.Picker;
import picker.ugurtekbas.com.Picker.TimeChangedListener;

public class AddShiftActivity extends AppCompatActivity {

    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.edt_note_shift)
    EditText edtNoteShift;
    @BindView(R.id.tv_total_shift)
    TextView tvTotalShift;
    @BindView(R.id.amPicker1)
    Picker amPicker1;
    @BindView(R.id.amPicker2)
    Picker amPicker2;
    @BindView(R.id.btn_save_Shift)
    Button btnSaveShift;
    @BindView(R.id.tv_intime_add_shift)
    TextView tvIntimeAddShift;
    @BindView(R.id.tv_outtime_add_shift)
    TextView tvOuttimeAddShift;
    private String sDate;
    private double totalT;
    private int inH;
    private int inM;
    private int outH;
    private int outM;
    private DatabaseHandler databaseHandler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,NoteActivity.class);
        i.putExtra("sDate",sDate);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);
        ButterKnife.bind(this);
        getSDate();
        databaseHandler = new DatabaseHandler(this);
        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        amPicker1.setTime(Calendar.getInstance());
        if (currentHour >= 12) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            amPicker2.setTime(c);
            rb2.setChecked(true);
        } else {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 12);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            rb1.setChecked(true);
            amPicker2.setTime(c);
        }
        setTime();
        amPicker1.setTimeChangedListener(new TimeChangedListener() {
            @Override
            public void timeChanged(Date date) {
                 inH = date.getHours();
                 inM = date.getMinutes();
                double inTime = inH + (inM / 60f);
                outH = amPicker2.getCurrentHour();
                if (outH == 0) {
                    outH = 24;
                }
                 outM = amPicker2.getCurrentMin();
                double outTime = outH + (outM / 60f);
                double khoang;
                if (inTime <= outTime) {
                    khoang = (outTime - inTime) / (0.5);
                } else {
                    khoang = ((24 - inTime) + (outTime)) / (0.5);
                }
                int totalTimeInt = (int) (khoang / 2);
                int totalTimeF = (int) khoang % 2;
                if (totalTimeF == 1) {
                    totalT = totalTimeInt + 0.5;
                } else totalT = ((double) totalTimeInt);
                tvTotalShift.setText(String.valueOf(totalT));
                tvIntimeAddShift.setText(String.valueOf(inH+":"+inM));


            }
        });
        amPicker2.setTimeChangedListener(new TimeChangedListener() {
            @Override
            public void timeChanged(Date date) {
                int outH = date.getHours();
                int outM = date.getMinutes();
                double outTime = outH + (outM / 60f);
                int inH = amPicker1.getCurrentHour();
                if (inH == 0) {
                    inH = 24;
                }
                int inM = amPicker1.getCurrentMin();
                double inTime = inH + (inM / 60f);
                double khoang;
                if (inTime <= outTime) {
                    khoang = (outTime - inTime) / (0.5);
                } else {
                    khoang = ((24 - inTime) + (outTime)) / (0.5);
                }
                int totalTimeInt = (int) (khoang / 2);
                int totalTimeF = (int) khoang % 2;
                if (totalTimeF == 1) {
                    totalT = totalTimeInt + 0.5;
                } else totalT = ((double) totalTimeInt);
                tvOuttimeAddShift.setText(String.valueOf(outH+":"+outM));
                tvTotalShift.setText(String.valueOf(totalT));
            }
        });
    }

    private void setTime() {
         inH = amPicker1.getCurrentHour();
        if (inH == 0) {
            inH = 24;
        }
         inM = amPicker1.getCurrentMin();
        double inTime = inH + (inM / 60f);
       outH = amPicker2.getCurrentHour();
        if (outH == 0) {
            outH = 24;
        }
         outM = amPicker2.getCurrentMin();
        double outTime = outH + (outM / 60f);
        double khoang;
        if (inTime <= outTime) {
            khoang = (outTime - inTime) / (0.5);
        } else {
            khoang = ((24 - inTime) + (outTime)) / (0.5);
        }
        int totalTimeInt = (int) (khoang / 2);
        int totalTimeF = (int) khoang % 2;
        if (totalTimeF == 1) {
            totalT = totalTimeInt + 0.5;
        } else totalT = ((double) totalTimeInt);
        tvOuttimeAddShift.setText(String.valueOf(outH+":"+outM));
        tvIntimeAddShift.setText(String.valueOf(inH+":"+inM));
        tvTotalShift.setText(String.valueOf(totalT));
    }

    private void getSDate() {
        Intent intent = getIntent();
        sDate = intent.getStringExtra("sDate");
    }

    @OnClick(R.id.btn_save_Shift)
    public void onViewClicked() {
        String sName="Ca 1";
        if(rb2.isChecked()){
            sName="Ca 2";
        }
        Shift shift=new Shift(sDate,sName,String.valueOf(inH+":"+inM),
                String.valueOf(outH+":"+outM),totalT,edtNoteShift.getText().toString(),sDate.split("tháng ")[1]);
        int e=databaseHandler.existShift(shift);
        if(e!=(-1)){
            showD(e,shift);

        }else {
            databaseHandler.addShift(shift);
            onBackPressed();
        }


    }

    private void showD(int e, Shift shift) {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog);
            TextView tvDialogMsg = (TextView) dialog.findViewById(R.id.tv_dialog_msg);
            Button btnCancel = (Button) dialog.findViewById(R.id.btn_update_cancel);
            Button btnContinute = (Button) dialog.findViewById(R.id.btn_update_continute);
            tvDialogMsg.setText(Contans.CONFRIM_UPDATE_SHIFT);
            btnCancel.setText(Contans.CANCEL);
            btnContinute.setText(Contans.UPDATE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setCancelable(false);
            btnContinute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseHandler.delete(e);
                    databaseHandler.addShift(shift);
                    dialog.dismiss();
                    onBackPressed();
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }


}
