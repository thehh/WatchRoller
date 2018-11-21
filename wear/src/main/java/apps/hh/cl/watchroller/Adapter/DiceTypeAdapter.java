package apps.hh.cl.watchroller.Adapter;

import android.graphics.Color;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.support.wear.widget.WearableRecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

import apps.hh.cl.watchroller.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static android.content.Context.VIBRATOR_SERVICE;

public class DiceTypeAdapter extends WearableRecyclerView.Adapter<DiceTypeAdapter.DiceViewHolder> {
    private int[] diceTypes;
    private DiceViewHolder holder;


    public DiceTypeAdapter(int[] diceTypes){
        this.diceTypes = diceTypes;
    }

    @NonNull
    @Override
    public DiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        holder = new DiceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.die_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiceViewHolder holder, int position) {
        holder.diceType.setText(String.valueOf(diceTypes[position]));
        iconSelector(position, holder);
    }

    @Override
    public int getItemCount() {
        return diceTypes.length;
    }

    private void iconSelector(int item, DiceViewHolder holder){
        String[] diceColors = {
                "#6161A7", "#F46600", "#607d8b", "#FFAA05", "#43a047", "#B14D4D"
        };
        switch (item){
            case 0:
                holder.diceImage.setImageResource(R.drawable.d4);
                holder.diceType.setTextColor(Color.parseColor(diceColors[0]));
                break;
            case 1:
                holder.diceImage.setImageResource(R.drawable.d6);
                holder.diceType.setTextColor(Color.parseColor(diceColors[1]));
                break;
            case 2:
                holder.diceImage.setImageResource(R.drawable.d8);
                holder.diceType.setTextColor(Color.parseColor(diceColors[2]));
                break;
            case 3:
                holder.diceImage.setImageResource(R.drawable.d10);
                holder.diceType.setTextColor(Color.parseColor(diceColors[3]));
                break;
            case 4:
                holder.diceImage.setImageResource(R.drawable.d12);
                holder.diceType.setTextColor(Color.parseColor(diceColors[4]));
                break;
            case 5:
                holder.diceImage.setImageResource(R.drawable.d20);
                holder.diceType.setTextColor(Color.parseColor(diceColors[5]));
                break;
            default:
                holder.diceImage.setImageResource(R.drawable.d20);
                holder.diceType.setTextColor(Color.parseColor(diceColors[5]));
                break;

        }

    }

    public class DiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        @BindView(R.id.die_layout)
        public ConstraintLayout layout;
        @BindView(R.id.iv_dice_icon)
        public ImageView diceImage;
        @BindView(R.id.tv_dice_name)
        public TextView diceType;

        private DiceViewHolder(View view) {
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
            int diceRoll = randomDiceValue(diceTypes[position]);
            Log.d("Dice Roll: ", String.valueOf(diceRoll));

            YoYo.with(Techniques.RollIn)
                    .duration(1000)
                    .interpolate(new DecelerateInterpolator(3.0f))
                    .playOn(diceImage);
            YoYo.with(Techniques.RollIn)
                    .duration(1000)
                    .interpolate(new DecelerateInterpolator(3.0f))
                    .playOn(diceType);
            diceType.setText(String.valueOf(diceRoll));
            diceType.setTextColor(Color.parseColor("#03DAC6"));

        }

        @Override
        @OnLongClick
        public boolean onLongClick(View view) {
            int position = getLayoutPosition();
            YoYo.with(Techniques.Landing)
                    .duration(700)
                    .playOn(diceImage);
            YoYo.with(Techniques.Landing)
                    .duration(700)
                    .playOn(diceType);
            diceType.setText(String.valueOf(diceTypes[position]));
            diceType.setTextColor(Color.parseColor(resetIconColor(position)));

            vibrate(view);
            return true;
        }

        private String resetIconColor(int position){
             String[] diceColors = {
                    "#6161A7", "#F46600", "#607d8b", "#FFAA05", "#43a047", "#B14D4D"
                };

             return diceColors[position];

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

    }
}
