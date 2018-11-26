package apps.hh.cl.watchroller.ViewHolder;

import android.graphics.Color;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

import apps.hh.cl.watchroller.Common.Utils;
import apps.hh.cl.watchroller.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static android.content.Context.VIBRATOR_SERVICE;

public class DiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    @BindView(R.id.die_layout)
    public ConstraintLayout layout;
    @BindView(R.id.iv_dice_icon)
    public ImageView diceImage;
    @BindView(R.id.tv_dice_name)
    public TextView firstDice;
    @BindView(R.id.tv_dice_name_2)
    public TextView secondDice;

    private int swipeCount = 0;

    public DiceViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
        ButterKnife.bind(this, view);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        if(onClickListener != null){
            layout.setOnClickListener(onClickListener);
        }
    }

    @Override
    @OnClick
    public void onClick(View view) {
        int position = getAdapterPosition();

        switch(rollThisManyDices()){
            case 1:
                rollADice(position);
                break;
            case 2:
                rollWithAdvantage(position);
                break;
            default:
                rollNDices(position);
                break;
        }

    }

    private void rollADice(int position){
        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(diceImage);
        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(firstDice);
        int firstDiceRoll = randomDiceValue(Utils.DICE_TYPES[position]);
        firstDice.setText(String.valueOf(firstDiceRoll));
        firstDice.setTextColor(Color.parseColor("#03DAC6"));
    }

    private void rollWithAdvantage(int position){
        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(diceImage);
        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(firstDice);
        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(secondDice);
        if(!firstDice.getText().toString().contains("d")){
            int firstDiceRoll = randomDiceValue(Utils.DICE_TYPES[position]);
            int secondDiceRoll = randomDiceValue(Utils.DICE_TYPES[position]);
            Log.d("DICES", "first: " + firstDiceRoll + "\n second: " + secondDiceRoll);
            firstDice.setText(String.valueOf(firstDiceRoll));
            secondDice.setText(String.valueOf(secondDiceRoll));
            firstDice.setTextColor(Color.parseColor("#03DAC6"));
            secondDice.setTextColor(Color.parseColor("#03DAC6"));
        }
    }

    private void rollNDices(int position){
        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(diceImage);
        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(firstDice);
        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(secondDice);
        secondDice.setText(String.valueOf(returnDiceSum(Utils.DICE_TYPES[position])));
        secondDice.setTextColor(Color.parseColor("#03DAC6"));
    }

    @Override
    @OnLongClick
    public boolean onLongClick(View view) {
        resetDices(view);
        vibrate(view);
        return true;
    }

    private void resetDices(View view){
        int position = getAdapterPosition();
        swipeCount = 0;
        secondDice.setVisibility(View.GONE);
        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(diceImage);
        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(firstDice);
        firstDice.setText(String.valueOf(Utils.DICE_TYPES[position]));
        firstDice.setTextColor(view.getContext().getResources().getColor(resetIconColor(position)));

    }

    private int resetIconColor(int position){
        int returnedColor = 0;
        switch (position){
            case 0:
                returnedColor = R.color.d4;
                break;
            case 1:
                returnedColor = R.color.d6;
                break;
            case 2:
                returnedColor = R.color.d8;
                break;
            case 3:
                returnedColor = R.color.d10;
                break;
            case 4:
                returnedColor = R.color.d12;
                break;
            case 5:
                returnedColor = R.color.d20;
                break;
            default:
                returnedColor =R.color.d20;
                break;

        }

        return returnedColor;
    }

    private void vibrate(View v){
        Vibrator vibrator = (Vibrator) v.getContext().getSystemService(VIBRATOR_SERVICE);
        long[] vibrationPattern = {0, 500, 50, 300};
        //-1 - don't repeat
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibrationPattern, indexInPatternToRepeat);
    }

    private int randomDiceValue(int diceType){
        Random rnd = new Random();
        int result = 0;
        while(result == 0){
            result = rnd.nextInt(diceType + 1);
        }
        return result;
    }

    public int getSwipeCount(){
        return this.swipeCount;
    }

    public void setSwipeCount(int swipes){
        this.swipeCount = swipes;
    }

    private String setManyDices(int howManyDices){
        String diceLabels = "";
        if(howManyDices > 2){
            diceLabels = setupLabel(getLayoutPosition());
        }
        String diceHint = howManyDices + diceLabels;
        return diceHint;
    }

    private String setupLabel(int position){
        String diceLabel = "";
        switch (position){
            case 0:
                diceLabel = "d4";
                break;
            case 1:
                diceLabel = "d6";
                break;
            case 2:
                diceLabel = "d8";
                break;
            case 3:
                diceLabel = "d10";
                break;
            case 4:
                diceLabel = "d12";
                break;
            case 5:
                diceLabel = "d20";
                break;
            default:
                diceLabel = "d20";
                break;

        }
        return diceLabel;
    }

    public void setupDices(int swipeCount){
        if(swipeCount == 0){ //1 dado
            secondDice.setVisibility(View.GONE);
        }
        else if(swipeCount == 1){ //2 dados
            secondDice.setVisibility(View.VISIBLE);
        }
        else{ //n dados
            String dicesText = setManyDices(swipeCount);
            firstDice.setText(dicesText);
            secondDice.setVisibility(View.VISIBLE);
        }
    }

    private int rollThisManyDices(){
        int rollthese = 0;
        if(firstDice.getVisibility() == View.VISIBLE && !firstDice.getText().toString().contains("d"))
            rollthese++;
        if(secondDice.getVisibility() == View.VISIBLE && !firstDice.getText().toString().contains("d"))
            rollthese++;
        if(firstDice.getVisibility() == View.VISIBLE && firstDice.getText().toString().contains("d")){
            String rolling = firstDice.getText().toString().split("d")[0];
            rollthese = Integer.parseInt(rolling);
        }
        return rollthese;
    }

    private int returnDiceSum(int diceType){
        int sum = 0;
        for(int i = 0; i< rollThisManyDices(); i++){
            sum += randomDiceValue(diceType);
        }
        return sum;
    }
}