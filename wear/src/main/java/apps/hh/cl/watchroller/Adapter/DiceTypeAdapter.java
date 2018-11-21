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
import apps.hh.cl.watchroller.ViewHolder.DiceViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static android.content.Context.VIBRATOR_SERVICE;

public class DiceTypeAdapter extends WearableRecyclerView.Adapter<DiceViewHolder> {
    private int[] diceTypes;
    private DiceViewHolder holder;
    private ViewGroup parent;


    public DiceTypeAdapter(int[] diceTypes){
        this.diceTypes = diceTypes;
    }

    @NonNull
    @Override
    public DiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        holder = new DiceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.die_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiceViewHolder holder, int position) {
        holder.diceType.setText(String.valueOf(diceTypes[position]));
        iconSelector(position, holder, parent);
    }

    @Override
    public int getItemCount() {
        return diceTypes.length;
    }

    private void iconSelector(int item, DiceViewHolder holder, View v){
        switch (item){
            case 0:
                holder.diceImage.setImageResource(R.drawable.d4);
                holder.diceType.setTextColor(v.getContext().getResources().getColor(R.color.d4));
                break;
            case 1:
                holder.diceImage.setImageResource(R.drawable.d6);
                holder.diceType.setTextColor(v.getContext().getResources().getColor(R.color.d6));
                break;
            case 2:
                holder.diceImage.setImageResource(R.drawable.d8);
                holder.diceType.setTextColor(v.getContext().getResources().getColor(R.color.d8));
                break;
            case 3:
                holder.diceImage.setImageResource(R.drawable.d10);
                holder.diceType.setTextColor(v.getContext().getResources().getColor(R.color.d10));
                break;
            case 4:
                holder.diceImage.setImageResource(R.drawable.d12);
                holder.diceType.setTextColor(v.getContext().getResources().getColor(R.color.d12));
                break;
            case 5:
                holder.diceImage.setImageResource(R.drawable.d20);
                holder.diceType.setTextColor(v.getContext().getResources().getColor(R.color.d20));
                break;
            default:
                holder.diceImage.setImageResource(R.drawable.d20);
                holder.diceType.setTextColor(v.getContext().getResources().getColor(R.color.d20));
                break;

        }

    }
}
