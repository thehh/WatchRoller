package apps.hh.cl.watchroller.adapter;

import android.support.annotation.NonNull;
import android.support.wear.widget.WearableRecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import apps.hh.cl.watchroller.R;
import apps.hh.cl.watchroller.data.DiceRow;
import apps.hh.cl.watchroller.viewholder.DiceViewHolder;

public class DiceTypeAdapter extends WearableRecyclerView.Adapter<DiceViewHolder> {
    private int[] diceTypes;
    private DiceViewHolder holder;
    private ViewGroup parent;

    private List<DiceRow> dices;

    public DiceTypeAdapter(int[] diceTypes){
        this.diceTypes = diceTypes;
        this.dices = new ArrayList<>();
        for(int dice: diceTypes){
            this.dices.add(new DiceRow(dice, 0));
        }
    }

    @NonNull
    @Override
    public DiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        holder = new DiceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.die_layout, parent, false));
        return holder;
    }

    @Override
    public void onViewAttachedToWindow(DiceViewHolder vh){
        super.onViewAttachedToWindow(vh);
        Log.d("Adapter", String.valueOf(this.dices.get(vh.getAdapterPosition()).getDiceType()));
        vh.firstDice.setText(String.valueOf(this.dices.get(vh.getAdapterPosition()).getDiceType()));
    }

    @Override
    public void onBindViewHolder(@NonNull DiceViewHolder holder, int position) {
        holder.setupDices(this.dices.get(holder.getAdapterPosition()).getDiceRolls());
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
                holder.firstDice.setTextColor(v.getContext().getResources().getColor(R.color.d4));
                break;
            case 1:
                holder.diceImage.setImageResource(R.drawable.d6);
                holder.firstDice.setTextColor(v.getContext().getResources().getColor(R.color.d6));
                break;
            case 2:
                holder.diceImage.setImageResource(R.drawable.d8);
                holder.firstDice.setTextColor(v.getContext().getResources().getColor(R.color.d8));
                break;
            case 3:
                holder.diceImage.setImageResource(R.drawable.d10);
                holder.firstDice.setTextColor(v.getContext().getResources().getColor(R.color.d10));
                break;
            case 4:
                holder.diceImage.setImageResource(R.drawable.d12);
                holder.firstDice.setTextColor(v.getContext().getResources().getColor(R.color.d12));
                break;
            case 5:
                holder.diceImage.setImageResource(R.drawable.d20);
                holder.firstDice.setTextColor(v.getContext().getResources().getColor(R.color.d20));
                break;
            default:
                holder.diceImage.setImageResource(R.drawable.d20);
                holder.firstDice.setTextColor(v.getContext().getResources().getColor(R.color.d20));
                break;

        }

    }

    public DiceViewHolder getHolder() {
        return holder;
    }

    public List<DiceRow> getDices(){
        return dices;
    }
}
