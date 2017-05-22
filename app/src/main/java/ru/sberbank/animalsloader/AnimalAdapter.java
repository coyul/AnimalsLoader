package ru.sberbank.animalsloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends BaseAdapter {
    private List<Animal> mAnimals;

    public AnimalAdapter() {
        mAnimals = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mAnimals.size();
    }

    @Override
    public Object getItem(int position) {
        return mAnimals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if( view == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_list_item, parent, false);
            view.setTag(new ViewHolder(view));
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        Animal animal = mAnimals.get(position);
        Context context = view.getContext();
        holder.speciesTextView.setText(context.getString(R.string.species_format, animal.getSpecies()));
        holder.nameTextView.setText(context.getString(R.string.name_format, animal.getName()));
        holder.ageTextView.setText(context.getString(R.string.age_format, String.valueOf(animal.getAge())));
        holder.locationTextView.setText(context.getString(R.string.location_format, animal.getLocation()));
        return view;
    }

    public void setUpAdapter(List<Animal> list){
        mAnimals.clear();
        mAnimals.addAll(list);
        notifyDataSetChanged();
    }

    private static class ViewHolder {

        public final TextView speciesTextView;
        public final TextView ageTextView;
        public final TextView nameTextView;
        public final TextView locationTextView;

        public ViewHolder(View itemView) {
            speciesTextView = (TextView) itemView.findViewById(R.id.species);
            ageTextView = (TextView) itemView.findViewById(R.id.age);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            locationTextView = (TextView) itemView.findViewById(R.id.location);
        }
    }
}
