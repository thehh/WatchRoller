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

import apps.hh.cl.watchroller.Common.Commons;
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
    @BindView(R.id.tv_dice_name_3)
    public TextView thirdDice;

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
        int position = getLayoutPosition();
        int diceRoll = randomDiceValue(Commons.DICE_TYPES[position]);
        Log.d("Dice Roll: ", String.valueOf(diceRoll));

        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(diceImage);
        YoYo.with(Techniques.RollIn)
                .duration(1000)
                .interpolate(new DecelerateInterpolator(3.0f))
                .playOn(firstDice);
        firstDice.setText(String.valueOf(diceRoll));
        firstDice.setTextColor(Color.parseColor("#03DAC6"));
        secondDice.setText(String.valueOf(diceRoll));
        secondDice.setTextColor(Color.parseColor("#03DAC6"));
        thirdDice.setText(String.valueOf(diceRoll));
        thirdDice.setTextColor(Color.parseColor("#03DAC6"));

    }

    @Override
    @OnLongClick
    public boolean onLongClick(View view) {
        int position = getLayoutPosition();
        secondDice.setVisibility(View.GONE);
        thirdDice.setVisibility(View.GONE);
        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(diceImage);
        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(firstDice);
        firstDice.setText(String.valueOf(Commons.DICE_TYPES[position]));
        secondDice.setText(String.valueOf(Commons.DICE_TYPES[position]));
        thirdDice.setText(String.valueOf(Commons.DICE_TYPES[position]));
        firstDice.setTextColor(view.getContext().getResources().getColor(resetIconColor(position)));
        secondDice.setTextColor(view.getContext().getResources().getColor(resetIconColor(position)));
        thirdDice.setTextColor(view.getContext().getResources().getColor(resetIconColor(position)));

        vibrate(view);
        return true;
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

    public void vibrate(View v){
        Vibrator vibrator = (Vibrator) v.getContext().getSystemService(VIBRATOR_SERVICE);
        long[] vibrationPattern = {0, 500, 50, 300};
        //-1 - don't repeat
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibrationPattern, indexInPatternToRepeat);
    }

    private int randomDiceValue(int diceType){
        Random rnd = new Random(System.currentTimeMillis());
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

    public String setManyDices(int howManyDices){
        String diceLabels = "";
        if(howManyDices != 2){
            diceLabels = setupLabel(getLayoutPosition());
        }

        return howManyDices+diceLabels;
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
        if(swipeCount == 0){
            secondDice.setVisibility(View.GONE);
            thirdDice.setVisibility(View.GONE);
        }
        else if(swipeCount == 1){
            secondDice.setVisibility(View.VISIBLE);
        }
        else if(swipeCount == 3){
            secondDice.setVisibility(View.VISIBLE);
            thirdDice.setVisibility(View.VISIBLE);
        }
        else{
            secondDice.setVisibility(View.VISIBLE);
            thirdDice.setVisibility(View.GONE);
            firstDice.setText(setManyDices(swipeCount));
        }
    }
}